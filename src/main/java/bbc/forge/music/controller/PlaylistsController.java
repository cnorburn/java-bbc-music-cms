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

import bbc.forge.music.model.Playlists;
import bbc.forge.music.model.Tracks;
import bbc.forge.music.model.TracksPlaylist;
import bbc.forge.music.model.Users;


@Controller
public class PlaylistsController extends BaseController{

	@RequestMapping(value ={"/users/{username}/playlists", "/users/{username}/playlists/"}, method = RequestMethod.GET)
	public String getUsersPlaylists(@PathVariable String username,@RequestParam(required = false) Integer status,ModelMap model,HttpServletResponse response,HttpServletRequest request) throws Exception{
		setJSONPreProcessors(request);
		
		Users user = usersDao.fetch(username); 
		
		if (user == null) 
			response.sendError(HttpServletResponse.SC_NOT_FOUND);
		
		if (status==null)
			model.put("playlists", playlistsDao.fetchAllByUserName(username));
		else
			model.put("playlists", playlistsDao.fetchAll(status));
				
		return "jsonView";
	}

	@RequestMapping(value ={"/playlists/{urlKey}"}, method = RequestMethod.GET)
	public String getPlaylistByUrlKey(@PathVariable String urlKey,ModelMap model,HttpServletResponse response) throws Exception{

		model.put("playlist", playlistsDao.fetch(urlKey));
		return "jsonView";
	}

	@RequestMapping(value ={"/playlists"}, method = RequestMethod.GET)
	public String getAllPlaylists(ModelMap model,@RequestParam(required = false) String order,HttpServletResponse response,HttpServletRequest request) throws Exception{
		
		setJSONPreProcessors(request);
		
		List<Playlists> playlists=new ArrayList<Playlists>();
		
		if (order!=null ){
			if("updated_at".equals(order) || "promoted_at".equals(order) || "created_at".equals(order)){
				 playlists= playlistsDao.fetchByOrder(order);
			}else
				response.sendError(HttpServletResponse.SC_NOT_FOUND);
		}else
			 playlists= playlistsDao.fetchAll();
	
		model.put("playlists",playlists);
		model.put("count", playlists.size());
		return "jsonView";
		
	}
	/*
	@RequestMapping(value ={"/playlists/"}, method = RequestMethod.GET)
	public String getAllPlaylistsByStatus(@RequestParam int status ,ModelMap model,HttpServletResponse response,HttpServletRequest request) throws Exception{
		
		//setJSONPreProcessors(request);
		
		System.out.println("############ status + " + status);
		
		List<PlaylistsAll> playlists= playlistMatDao.fetchAll(status);
		model.put("playlists",playlists);
		model.put("count", playlists.size());
		return "jsonView";
		
	}

	*/

	//Create Playlist
	@RequestMapping(value={"/users/{username}/playlists", "/users/{username}/playlists/"}, method=RequestMethod.POST)
	public void createPlaylist(@RequestBody String body,@PathVariable String username, HttpServletResponse response) throws Exception, JSONException {
		JSONObject json;
		Playlists playlist = new Playlists();
		Users user = usersDao.fetch(username);		
		try{
			json=JSONObject.fromObject(body);
			playlist.setPropertiesFromJSON(json);		

			playlist.setUrl_key(keygen.getUrlKey(urlKeysDao.generate()));
			playlist.setUser_id(user.getId());
			playlistsDao.insert(playlist);


		} catch(JSONException je){
			response.sendError(HttpServletResponse.SC_NOT_ACCEPTABLE);
		}
		
		response.setStatus(HttpServletResponse.SC_OK);
	}

	//Update Playlist
	@RequestMapping(value={"/playlists/{urlKey}", "/playlists/{urlKey}/"}, method=RequestMethod.PUT)
	public void updatePlaylist(@RequestBody String body,@PathVariable String urlKey, HttpServletResponse response) throws Exception, JSONException {
		JSONObject json;
		Playlists playlist=playlistsDao.fetch(urlKey);
		try{
			json=JSONObject.fromObject(body);
			playlist.setPropertiesFromJSON(json);		
			playlistsDao.insert(playlist);

		} catch(JSONException je){
			response.sendError(HttpServletResponse.SC_NOT_ACCEPTABLE);
		}
		response.setStatus(HttpServletResponse.SC_OK);
	}


	//Delete Playlist
	@RequestMapping(value={"/playlists/{urlKey}"}, method=RequestMethod.DELETE)
	public void deletePlaylist(@PathVariable String urlKey,HttpServletResponse response) throws Exception, JSONException {

		Playlists playlist=playlistsDao.fetch(urlKey);
		playlistsDao.delete(playlist);

		response.setStatus(HttpServletResponse.SC_OK);
	}

	//Create Tracks
	@RequestMapping(value={"/playlists/{urlKey}/tracks/", "/playlists/{urlKey}/tracks"}, method=RequestMethod.POST)
	public void createTrack(@RequestBody String body,@PathVariable String urlKey,HttpServletResponse response) throws Exception, JSONException {
		JSONObject json;
		Tracks track =new Tracks();

		Playlists playlist=playlistsDao.fetch(urlKey);

		try{
			json=JSONObject.fromObject(body);

			track.setPropertiesFromJSON(json);
			track.setUrl_key(keygen.getUrlKey(urlKeysDao.generate()));
			tracksDao.insert(track);

			TracksPlaylist trackPlaylist=new TracksPlaylist(playlist,track);
			trackPlaylist.setPropertiesFromJSON(json);
			tracksDao.insert(trackPlaylist);

		} catch(JSONException je){
			response.sendError(HttpServletResponse.SC_NOT_ACCEPTABLE);
		}

		response.setStatus(HttpServletResponse.SC_OK);

	}

	//Update Tracks
	@RequestMapping(value={"/playlists/{playlistKey}/tracks/{trackKey}/", "/playlists/{playlistKey}/tracks/{trackKey}"}, method=RequestMethod.PUT)
	public void updateTrack(@RequestBody String body,@PathVariable String playlistKey,@PathVariable String trackKey,HttpServletResponse response) throws Exception, JSONException {
		JSONObject json;

		Playlists playlist=playlistsDao.fetch(playlistKey);
		List<TracksPlaylist> tracksPlaylist=playlistsDao.fetch(playlist.getId());
		Tracks track=tracksDao.fetch(trackKey);

		//TODO A more elegant way to select the tracks parent playists_tracks
		for (TracksPlaylist playlistTrack : tracksPlaylist) {
			Tracks t=playlistTrack.getTrack();
			if(t.getId()==track.getId()){
				try{
					json=JSONObject.fromObject(body);

					track.setPropertiesFromJSON(json);
					tracksDao.insert(track);

					playlistTrack.setPropertiesFromJSON(json);
					tracksDao.insert(playlistTrack);

				} catch(JSONException je){
					response.sendError(HttpServletResponse.SC_NOT_ACCEPTABLE);
				}

			}
		}

		response.setStatus(HttpServletResponse.SC_OK);

	}

	//Delete tracks
	@RequestMapping(value={"/playlists/{playlistKey}/tracks/{trackKey}/", "/playlists/{playlistKey}/tracks/{trackKey}"}, method=RequestMethod.DELETE)
	public void deleteTrack(@PathVariable String playlistKey,@PathVariable String trackKey,HttpServletResponse response) throws Exception, JSONException {

		Playlists playlist=playlistsDao.fetch(playlistKey);
		List<TracksPlaylist> tracksPlaylists=playlistsDao.fetch(playlist.getId());
		Tracks track=tracksDao.fetch(trackKey);
	
		//TODO A more elegant way to select the tracks parent playists_tracks 
		for (TracksPlaylist tracksPlaylist : tracksPlaylists) {
			Tracks t=tracksPlaylist.getTrack();
			if(t.getId()==track.getId()){
				playlistsDao.delete(tracksPlaylist);
				tracksDao.delete(t);
			}
		}
		
		response.setStatus(HttpServletResponse.SC_OK);

	}

}
