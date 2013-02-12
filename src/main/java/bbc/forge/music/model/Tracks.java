package bbc.forge.music.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Entity
@Table(name = "tracks")
public class Tracks implements Serializable {

	@Id
	@GeneratedValue
	@Column(name="id") 
	private long id;
	private String url_key;
	private String title;
	private String artist_name;
	private String artist_gid;
	private String track_gid;
	private Date created_at;
	private Date updated_at=new Date();
	
	public String getArtist_name() {
		return artist_name;
	}
	public void setArtist_name(String artistName) {
		this.artist_name = artistName;
	}
	public String getArtist_gid() {
		return artist_gid;
	}
	public void setArtist_gid(String artistGid) {
		this.artist_gid = artistGid;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getTrack_gid() {
		return track_gid;
	}
	public void setTrack_gid(String trackGid) {
		this.track_gid = trackGid;
	}
	public String getUrl_key() {
		return url_key;
	}
	public void setUrl_key(String url_key) {
		this.url_key = url_key;
	}
	public Date getCreated_at() {
		return created_at;
	}
	public void setCreated_at(Date created_at) {
		this.created_at = created_at;
	}
	public Date getUpdated_at() {
		return updated_at;
	}
	public void setUpdated_at(Date updated_at) {
		this.updated_at = updated_at;
	}
	
	public void setPropertiesFromJSON(JSONObject json) throws Exception{

		JSONObject playlists_track=json.getJSONObject("playlists_track");

		JSONObject track=playlists_track.getJSONObject("track");

		if (track.containsKey("title"))
			this.setTitle((String)track.get("title"));
		if (track.containsKey("artist_name"))
			this.setArtist_name((String)track.get("artist_name"));
		if (track.containsKey("artist_gid"))
			this.setArtist_gid ((String)track.get("artist_gid"));
		if (track.containsKey("track_gid"))
			this.setTrack_gid ((String)track.get("track_gid"));
		if (track.containsKey("updated_at"))
			this.setUpdated_at(bbc.forge.music.utils.DateUtils.timestamp( track.get("updated_at").toString()));
		
	}
	
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}


}
