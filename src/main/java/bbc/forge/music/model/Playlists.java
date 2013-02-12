package bbc.forge.music.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Transient;


import net.sf.json.JSONObject;

@Entity
@Table(name = "playlists")
public class Playlists implements Serializable{

	@Id
	@GeneratedValue
	@Column(name="id") 
	private long id;

	private long user_id;
		
	private String title;
	
	private String short_synopsis;
	
	private String medium_synopsis;	
	private int status;

	private Date updated_at=new Date();
	private Date created_at;
	private Date promoted_at;
	private String url_key;

	@OneToMany(mappedBy = "playlist_id")
	@OrderBy("track_position asc")
	private List<PlaylistsTracks> playlists_tracks;

	@ManyToOne
	@JoinColumn(name = "user_id", referencedColumnName = "id", insertable = false, updatable = false)
	private Username user;

	@Transient
	@ManyToOne(fetch = FetchType.LAZY,optional=true)
	@JoinColumn(name = "user_id")
	private Users users;
	
	
	@Transient
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "playlists",cascade=CascadeType.REMOVE)
	private List<TracksPlaylist> playlistTrack=new ArrayList<TracksPlaylist>();

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "playlists",cascade=CascadeType.REMOVE)
	private List<ExternalsPlaylists> externals=new ArrayList<ExternalsPlaylists>();
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getShort_synopsis() {
		return short_synopsis;
	}
	public void setShort_synopsis(String shortSynopsis) {
		this.short_synopsis = shortSynopsis;
	}
	public String getMedium_synopsis() {
		return medium_synopsis;
	}	
	public void setMedium_synopsis(String mediumSynopsis) {
		this.medium_synopsis = mediumSynopsis;
	}

	public Date getUpdated_at() {
		return updated_at;
	}
	public void setUpdated_at(Date updated) {
		this.updated_at = updated;
	}
	
	public Date getCreated_at() {
		return created_at;
	}
	public void setCreated_at(Date created) {
		this.created_at = created;
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getUrl_key() {
		return url_key;
	}
	public void setUrl_key(String url_key) {
		this.url_key = url_key;
	}

	public List<PlaylistsTracks> getPlaylists_tracks() {
		return playlists_tracks;
	}
	public void setPlaylists_tracks(List<PlaylistsTracks> playlist_tracks) {
		this.playlists_tracks = playlist_tracks;
	}
	public Username getUser() {
		return user;
	}
	public void setUser(Username user) {
		this.user = user;
	}
	public long getUser_id() {
		return user_id;
	}
	public void setUser_id(long user_id) {
		this.user_id = user_id;
	}
	public List<ExternalsPlaylists> getExternals() {
		return externals;
	}
	public void setExternals(List<ExternalsPlaylists> externals) {
		this.externals = externals;
	}
	public Date getPromoted_at() {
		return promoted_at;
	}
	public void setPromoted_at(Date promoted_at) {
		this.promoted_at = promoted_at;
	}
	public void setId(long id) {
		this.id = id;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}

	public void setPropertiesFromJSON(JSONObject json) throws Exception{
		
		JSONObject playlist=json.getJSONObject("playlist");
		
		if (playlist.containsKey("title"))
			this.setTitle((String)playlist.get("title"));
		if (playlist.containsKey("short_synopsis"))
			this.setShort_synopsis ((String)playlist.get("short_synopsis"));
		if (playlist.containsKey("medium_synopsis"))
			this.setMedium_synopsis ((String)playlist.get("medium_synopsis"));
		if (playlist.containsKey("status"))
			this.setStatus(Integer.parseInt(playlist.get("status").toString()));
		if (playlist.containsKey("promoted_at"))
			this.setPromoted_at(bbc.forge.music.utils.DateUtils.timestamp(playlist.get("promoted_at").toString()));
		
	}
	public Users getUsers() {
		return users;
	}
	public void setUsers(Users users) {
		this.users = users;
	}

	
}
