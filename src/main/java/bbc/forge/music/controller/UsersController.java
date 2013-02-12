package bbc.forge.music.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import bbc.forge.music.model.Guides;
import bbc.forge.music.model.Users;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

@Controller
public class UsersController extends BaseController{
	

	//Create User
	@RequestMapping(value={"/users", "/users/"}, method=RequestMethod.POST)
	public void createUser(@RequestBody String body,HttpServletResponse response) throws Exception, JSONException {
	
		JSONObject json;
		Users user=new Users();
		try{
			json=JSONObject.fromObject(body);
			user.setPropertiesFromJSON(json);		
			usersDao.insert(user);
		} catch(JSONException je){
			response.sendError(HttpServletResponse.SC_NOT_ACCEPTABLE);
		}
		response.setStatus(HttpServletResponse.SC_OK);

	}
	
	//Update User
	@RequestMapping(value={"/users/{username}"}, method=RequestMethod.PUT)
	public void updateUser(@RequestBody String body,@PathVariable String username,HttpServletResponse response) throws Exception, JSONException {
		JSONObject json;
		Users user=usersDao.fetch(username);
		try{
			json=JSONObject.fromObject(body);
			user.setPropertiesFromJSON(json);
			usersDao.insert(user);
		} catch(JSONException je){
			response.sendError(HttpServletResponse.SC_NOT_ACCEPTABLE);
		}
		response.setStatus(HttpServletResponse.SC_OK);
	}

	/*
	//Update User
	@RequestMapping(value={"/users/{username}"}, method=RequestMethod.PUT)
	public RedirectView updateUser(@RequestBody String body,@PathVariable String username,HttpServletResponse response) throws Exception, JSONException {
		JSONObject json;
		User user=userMatDao.fetch(username);
		try{
			json=JSONObject.fromObject(body);
			user.setPropertiesFromJSON(json);
			userMatDao.insert(user);
		} catch(JSONException je){
			response.sendError(HttpServletResponse.SC_NOT_ACCEPTABLE);
		}
		return new RedirectView("/users/" + user.getUsername() , true);
	}
	 */
	
	//Delete User
	@RequestMapping(value={"/users/{username}"}, method=RequestMethod.DELETE)
	public void deleteUser(@PathVariable String username,HttpServletResponse response) throws Exception, JSONException {
			
		List<Guides> guides = usersDao.fetchGuide(username);
		for (Guides guide : guides) {
			usersDao.delete(guide);
		}		
		response.setStatus(HttpServletResponse.SC_OK);
	}


	@RequestMapping(value={"/"}, method=RequestMethod.GET)
	public ModelAndView index(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException, IOException {

		Map<String, Object> model = new HashMap<String, Object>();

		model.put("status", 200);
		model.put("msg", "OK");
		return new ModelAndView("jsonView",model);
	}



	@RequestMapping(value = "/users/{username}", method = RequestMethod.GET)
	public String getUser(@PathVariable String username, ModelMap modelMap,HttpServletRequest request,HttpServletResponse response) throws Exception {

		setJSONPreProcessors(request);

		Users user = usersDao.fetch(username);	      
		if (user == null) 
			response.sendError(HttpServletResponse.SC_NOT_FOUND);
		else 
			modelMap.put("user", user);

		return "jsonView";

	}


	@RequestMapping(value ={"/users", "/users/"}, method = RequestMethod.GET)
	public String getUsers(ModelMap modelMap,@RequestParam(required = false) String order,HttpServletRequest request,HttpServletResponse response) throws Exception {

		setJSONPreProcessors(request);
		
		List<Users> users=new ArrayList<Users>();
		
		if (order!=null ){
			if("featured_position".equals(order)){
				users= usersDao.fetchByOrder(order);
			}else
				response.sendError(HttpServletResponse.SC_NOT_FOUND);
		}else
			users= usersDao.fetchAll();

		modelMap.put("users", users);

		return "jsonView";
	}

}
