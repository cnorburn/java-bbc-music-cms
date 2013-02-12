package bbc.forge.music.controller;


import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONException;
import net.sf.json.JSONObject;


import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import bbc.forge.music.model.Clips;
import bbc.forge.music.model.Collections;
import bbc.forge.music.model.UserCollections;
import bbc.forge.music.model.Users;

@Controller
public class CollectionsController extends BaseController {

	@RequestMapping(value ={"/users/{username}/collections", "/users/{username}/collections/"}, method = RequestMethod.GET)
	public String getUsersCollections(@PathVariable String username,@RequestParam(required = false) Integer status, ModelMap model,HttpServletResponse response) throws Exception{

		Users user = usersDao.fetch(username); 

		if (user == null) 
			response.sendError(HttpServletResponse.SC_NOT_FOUND);

		if (status!=null){
			List<Collections> collections=collectionsDao.fetchByStatus(status);
			model.put("collections", collections);
		}
		else{
			List<UserCollections> userCollections=collectionsDao.fetchAllByUserName(username);
			model.put("collections", userCollections);
		}
	
		return "jsonView";
	}
	
	
	@RequestMapping(value ={"/collections/{urlKey}"}, method = RequestMethod.GET)
	public String getCollection(@PathVariable String urlKey,ModelMap model,HttpServletResponse response,HttpServletRequest request) throws Exception{
		setJSONPreProcessors(request);
		
		model.put("collection", collectionsDao.fetch(urlKey));
		
		return "jsonView";
	}

	@RequestMapping(value ={"/collections"}, method = RequestMethod.GET)
	public String getAllCollections(ModelMap model,@RequestParam(required = false) String order,HttpServletResponse response,HttpServletRequest request) throws Exception{
		setJSONPreProcessors(request);
		
		List<Collections> collections=new ArrayList<Collections>();
		if (order!=null ){
			if("updated_at".equals(order) || "promoted_at".equals(order) || "created_at".equals(order) || "featured_position".equals(order)){
				collections= collectionsDao.fetchByOrder (order);
			}else
				response.sendError(HttpServletResponse.SC_NOT_FOUND);
		}else
			collections= collectionsDao.fetchAll();
		
		model.put("collections", collections);
		model.put("count", collections.size());
		return "jsonView";
	}

	//Create Collection
	@RequestMapping(value={"/users/{username}/collections"}, method=RequestMethod.POST)
	public void createCollection(@RequestBody String body,@PathVariable String username,HttpServletResponse response,HttpServletRequest request) throws Exception, JSONException {
		Users user = usersDao.fetch(username);        
		if (user == null) {
			response.sendError(HttpServletResponse.SC_NOT_FOUND);
		}

		JSONObject json;
		Collections collection=new Collections();
		try {
			json = JSONObject.fromObject(body);
			collection.setPropertiesFromJSON(json); 

			/*
			JSONObject errs=validate.validateCollections(collection);     
			if (errs!=null){
				validationErrors(response, errs);
				return null;        
			}
			 */
			
			collection.setUrl_key(keygen.getUrlKey(urlKeysDao.generate()));
			collection.setUser_id(user.getId());
			
			collectionsDao.insert(collection);
			
		} catch(JSONException je){
			response.sendError(HttpServletResponse.SC_BAD_REQUEST);
		}
		
		response.setStatus(HttpServletResponse.SC_OK);
		
		response.sendRedirect("/music/collections/" + collection.getUrl_key());
		
	}

	//Update Collection
	@RequestMapping(value={"/collections/{urlKey}"}, method=RequestMethod.PUT) //PETE: changed from PUT to POST
	public void updateCollection(@RequestBody String body,@PathVariable String urlKey,HttpServletResponse response) throws Exception, JSONException {
		
		Collections collection=collectionsDao.fetch(urlKey);
		
		if (collection == null) {
			response.sendError(HttpServletResponse.SC_NOT_FOUND);
		}

		JSONObject json;
		try {
			json = JSONObject.fromObject(body);
			collection.setPropertiesFromJSON(json); 

			/*TODO
			 * Validation 
			JSONObject errs=validate.validateCollections(collection);     
			if (errs!=null){
				validationErrors(response, errs);
				return null;        
			}
			 */
			
			collectionsDao.insert(collection);
			
		} catch(JSONException je){
			response.sendError(HttpServletResponse.SC_BAD_REQUEST);
		}
		response.setStatus(HttpServletResponse.SC_OK);
	}
	
	@RequestMapping(value={"/collections/{urlKey}"}, method=RequestMethod.DELETE)
	public void deleteCollection(@PathVariable String urlKey,HttpServletResponse response) throws Exception, JSONException {

		Collections collection=collectionsDao.fetch(urlKey);
		collectionsDao.delete(collection);

		response.setStatus(HttpServletResponse.SC_OK);
	}

	//Create Clips
	@RequestMapping(value={"/collections/{urlKey}/clips"}, method=RequestMethod.POST)
	public void createClip(@RequestBody String body,@PathVariable String urlKey,HttpServletResponse response) throws Exception, JSONException {
		JSONObject json;
		
		Clips clip=new Clips();
		
		Collections collection=collectionsDao.fetch(urlKey);

		try{
			json=JSONObject.fromObject(body);

			clip.setPropertiesFromJSON(json);
			clip.setCollection_id(collection.getId());
			clipsDao.insert(clip);
		

		} catch(JSONException je){
			response.sendError(HttpServletResponse.SC_NOT_ACCEPTABLE);
		}

		response.setStatus(HttpServletResponse.SC_OK);

	}

	//Update Clips
	@RequestMapping(value={"/clips/{pid}"}, method=RequestMethod.PUT)
	public void updateClip(@RequestBody String body,@PathVariable String pid,HttpServletResponse response) throws Exception, JSONException {
		JSONObject json;
		
		Clips clip=clipsDao.fetch(pid);

		try{
			json=JSONObject.fromObject(body);
			clip.setPropertiesFromJSON(json);
			clipsDao.insert(clip);

		} catch(JSONException je){
			response.sendError(HttpServletResponse.SC_NOT_ACCEPTABLE);
		}

		response.setStatus(HttpServletResponse.SC_OK);

	}

	//Delete Clip
	@RequestMapping(value={"/clips/{pid}"}, method=RequestMethod.DELETE)
	public void deleteClip(@PathVariable String pid,HttpServletResponse response) throws Exception, JSONException {

		Clips clip=clipsDao.fetch(pid);
		clipsDao.delete(clip);

		response.setStatus(HttpServletResponse.SC_OK);
	}


}
