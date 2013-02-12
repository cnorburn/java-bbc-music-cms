package bbc.forge.music.validation;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import net.sf.json.JSONObject;

import org.hibernate.validator.ClassValidator;
import org.hibernate.validator.InvalidValue;

import bbc.forge.music.model.Clips;
import bbc.forge.music.model.Collections;
import bbc.forge.music.model.Playlists;
import bbc.forge.music.model.Tracks;


public class Validate {

	private static ClassValidator<Collections>   collectionsValidator = new ClassValidator<Collections>(Collections.class);  
	private static ClassValidator<Clips>   clipsValidator = new ClassValidator<Clips>(Clips.class);  
	private static ClassValidator<Playlists>   playlistsValidator = new ClassValidator<Playlists>(Playlists.class);  
	private static ClassValidator<Tracks>   tracksValidator = new ClassValidator<Tracks>(Tracks.class);  
	//private static ClassValidator<Users>   usersValidator = new ClassValidator<Users>(Users.class);  

	public JSONObject validateCollections(Collections collection){

		InvalidValue[] invalidValues = collectionsValidator.getInvalidValues(collection);
		HashMap<String, String> errs=new HashMap<String, String>();

		for (InvalidValue value : invalidValues) {  
			errs.put("Collection " + value.getPropertyName(), value.getMessage());			 
		}
		return createJson(errs);

	}
	
	public JSONObject validateClips(Clips clips){

		InvalidValue[] invalidValues = clipsValidator.getInvalidValues(clips);
		HashMap<String, String> errs=new HashMap<String, String>();

		for (InvalidValue value : invalidValues) {  
			errs.put("Clips " + value.getPropertyName(), value.getMessage());			 
		}
		return createJson(errs);

	}
/*
	public JSONObject validateUsers(Users users){

		InvalidValue[] invalidValues = usersValidator.getInvalidValues(users);
		HashMap<String, String> errs=new HashMap<String, String>();

		for (InvalidValue value : invalidValues) {  
			errs.put("Users " + value.getPropertyName(), value.getMessage());			 
		}
		return createJson(errs);

	}
*/
	public JSONObject validateTracks(Tracks tracks){

		InvalidValue[] invalidValues = tracksValidator.getInvalidValues(tracks);
		HashMap<String, String> errs=new HashMap<String, String>();

		for (InvalidValue value : invalidValues) {  
			errs.put("Tracks " + value.getPropertyName(), value.getMessage());			 
		}
		return createJson(errs);

	}

	public JSONObject validatePlaylists(Playlists playlists){

		InvalidValue[] invalidValues = playlistsValidator.getInvalidValues(playlists);
		HashMap<String, String> errs=new HashMap<String, String>();

		for (InvalidValue value : invalidValues) {  
			errs.put("Clips " + value.getPropertyName(), value.getMessage());			 
		}
		return createJson(errs);

	}

	@SuppressWarnings("rawtypes")
	private JSONObject createJson(HashMap<String, String> msg){

		if (msg.isEmpty()) return null;

		JSONObject json = new JSONObject();
		Set set = msg.entrySet();
		Iterator i = set.iterator();		 

		while(i.hasNext()){
			Map.Entry me = (Map.Entry)i.next();
			json.put(me.getKey(),me.getValue());
		}

		return json;

	}

}
