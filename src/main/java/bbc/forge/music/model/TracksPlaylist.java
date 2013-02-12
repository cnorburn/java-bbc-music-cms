package bbc.forge.music.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import bbc.forge.music.utils.DateUtils;

@Entity
@Table(name = "playlists_tracks")
public class TracksPlaylist implements Serializable{

	@Id
	@GeneratedValue
	@Column(name="id") 
	private long id; 

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "playlist_id")
	private Playlists playlists;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "track_id",referencedColumnName = "id")
	private Tracks track;
	
	public TracksPlaylist(){}
	
	public TracksPlaylist(Playlists playlists,Tracks track){
		this.playlists=playlists;
		this.track=track;
	}
	
	private String medium_synopsis;
	private int track_position;
	private Date created_at;
	private Date updated_at=new Date();
	

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Playlists getPlaylists() {
		return playlists;
	}

	public void setPlaylists(Playlists playlists) {
		this.playlists = playlists;
	}

	public Tracks getTrack() {
		return track;
	}

	public void setTrack(Tracks track) {
		this.track = track;
	}

	public String getMedium_synopsis() {
		return medium_synopsis;
	}

	public void setMedium_synopsis(String medium_synopsis) {
		this.medium_synopsis = medium_synopsis;
	}

	public int getTrack_position() {
		return track_position;
	}

	public void setTrack_position(int track_position) {
		this.track_position = track_position;
	}

	public Date getCreated_at() {
		return created_at;
	}

	public void setCreated_at(Date created_at) {
		this.created_at = created_at;
	}

	public Date getUpdated_at() {
		return DateUtils.timestamp();
	}

	public void setUpdated_at(Date updated_at) {
		this.updated_at = updated_at;
	}
	
	public void setPropertiesFromJSON(JSONObject json) throws Exception{
	
		JSONObject playlistTrack=json.getJSONObject("playlists_track");
		
		if (playlistTrack.containsKey("medium_synopsis"))
			this.setMedium_synopsis((String)playlistTrack.get("medium_synopsis"));
		if (playlistTrack.containsKey("track_position"))
			this.setTrack_position(Integer.parseInt(playlistTrack.get("track_position").toString()));

	}

	
}
