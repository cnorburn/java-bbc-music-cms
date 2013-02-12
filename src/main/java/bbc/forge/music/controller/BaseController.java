package bbc.forge.music.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;
import net.sf.json.util.CycleDetectionStrategy;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.servlet.view.json.JsonWriterConfiguratorTemplateRegistry;

import bbc.forge.music.dao.ClipsDao;
import bbc.forge.music.dao.CollectionsDao;
import bbc.forge.music.dao.PlaylistsDao;
import bbc.forge.music.dao.TracksDao;
import bbc.forge.music.dao.UrlKeysDao;
import bbc.forge.music.dao.UsersDao;
import bbc.forge.music.utils.DateJsonValueProcessor;
import bbc.forge.music.utils.HexUtils;
import bbc.forge.music.utils.KeyGenerator;
import bbc.forge.music.utils.PropertyExclusionSojoJsonWriterConfiguratorTemplate;
import bbc.forge.music.utils.StringNullJsonValueProcessor;
import bbc.forge.music.validation.Validate;

@Controller
public abstract class BaseController {

	@Autowired UsersDao usersDao;
	@Autowired PlaylistsDao playlistsDao;
	@Autowired TracksDao tracksDao;
	@Autowired CollectionsDao collectionsDao;
	@Autowired ClipsDao clipsDao;
	@Autowired UrlKeysDao urlKeysDao;

	protected static HexUtils hex=new HexUtils();
	protected static KeyGenerator keyGen=new KeyGenerator();

	protected final Log logger = LogFactory.getLog(getClass());
	protected static Validate validate=new Validate();
	
	static KeyGenerator keygen = new KeyGenerator();

	protected void setJSONPreProcessors(HttpServletRequest request){
		JsonWriterConfiguratorTemplateRegistry registry = JsonWriterConfiguratorTemplateRegistry.load(request);             
		PropertyExclusionSojoJsonWriterConfiguratorTemplate template = new PropertyExclusionSojoJsonWriterConfiguratorTemplate();

		//TODO Get the processor to map all Longs and Dates to the relevant config		
		
		template.getJsonConfig().registerJsonValueProcessor("artist_gid", new StringNullJsonValueProcessor());
		template.getJsonConfig().registerJsonValueProcessor("created_at", new DateJsonValueProcessor());
		template.getJsonConfig().registerJsonValueProcessor("updated_at", new DateJsonValueProcessor());
		template.getJsonConfig().registerJsonValueProcessor("promoted_at", new DateJsonValueProcessor());

		template.getJsonConfig().setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
			
		registry.registerConfiguratorTemplate(template);

	}


	@InitBinder
	public void initBinder(HttpServletRequest request, WebDataBinder binder) throws Exception {	
		setJSONPreProcessors(request);		
	}

	public void validationErrors(HttpServletResponse res, JSONObject errs) throws IOException {

		PrintWriter out;
		out = new PrintWriter (res.getOutputStream());

		out.println(errs.toString());

		res.setStatus(res.SC_BAD_REQUEST);
		out.close();


	}
}
