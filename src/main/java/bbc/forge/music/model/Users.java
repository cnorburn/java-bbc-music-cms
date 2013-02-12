package bbc.forge.music.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.OneToMany;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Entity
@Table(name = "users")
public class Users implements Serializable {

	@Id
	@GeneratedValue
	@Column(name="id") 
	private long id;
	private String name;
	private Long bbcid=null;
	private String username;
	private String artist_gid;
	private String short_synopsis;
	private String medium_synopsis;
	private int is_guide;
	private int is_superuser;
	private Integer featured_position=null;
	private int status;
	private Date updated_at=new Date();
	
	@OneToMany(mappedBy = "user_id")
	@OrderBy("promoted_at desc")
	private List<PlaylistUpdated> playlist_last_updated;

	@OneToMany(mappedBy = "user_id")
	@OrderBy("promoted_at desc")
	private List<CollectionUpdated> collection_last_updated;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "brands_users", joinColumns = @JoinColumn(name = "user_id", updatable = false), inverseJoinColumns = @JoinColumn(name = "brand_id"))
	private List<UserBrands> brands=new ArrayList<UserBrands>();
	
		
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}

	public List<PlaylistUpdated> getPlaylist_last_updated() {
		return playlist_last_updated;
	}

	public void setPlaylist_last_updated(List<PlaylistUpdated> playlistLastUpdated) {
		this.playlist_last_updated = playlistLastUpdated;
	}

	public List<CollectionUpdated> getCollection_last_updated() {
		return collection_last_updated;
	}
	public void setCollection_last_updated(
			List<CollectionUpdated> collection_last_updated) {
		this.collection_last_updated = collection_last_updated;
	}

	public List<UserBrands> getBrands() {
		return brands;
	}
	public void setBrands(List<UserBrands> brands) {
		this.brands = brands;
	}

	public String getShort_synopsis() {
		return short_synopsis;
	}
	public void setShort_synopsis(String short_synopsis) {
		this.short_synopsis = short_synopsis;
	}
	public Long getBbcid() {
		return bbcid;
	}
	public void setBbcid(Long bbcid) {
		this.bbcid = bbcid;
	}
	public int getIs_guide() {
		return is_guide;
	}
	public void setIs_guide(int is_guide) {
		this.is_guide = is_guide;
	}
	public int getIs_superuser() {
		return is_superuser;
	}
	public void setIs_superuser(int is_superuser) {
		this.is_superuser = is_superuser;
	}
	public Date getUpdated_at() {
		return updated_at;
	}
	public void setUpdated_at(Date updated_at) {
		this.updated_at = updated_at;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getArtist_gid() {
		return artist_gid;
	}
	public void setArtist_gid(String artist_gid) {
		this.artist_gid = artist_gid;
	}
	public String getMedium_synopsis() {
		return medium_synopsis;
	}
	public void setMedium_synopsis(String medium_synopsis) {
		this.medium_synopsis = medium_synopsis;
	}
	
	public void setPropertiesFromJSON(JSONObject json) throws Exception{
		
		JSONObject user=json.getJSONObject("user");
	
		if (user.containsKey("name"))
			this.setName((String)user.get("name"));
		
		if (user.containsKey("bbcid"))
			this.setBbcid(Long.parseLong(user.get("bbcid").toString()));
		
		if (user.containsKey("username"))
			this.setUsername((String)user.get("username"));
				
		if (user.containsKey("artist_gid") && user.get("artist_gid")!=null)
			this.setArtist_gid (user.get("artist_gid").toString());
		
		if (user.containsKey("short_synopsis"))
			this.setShort_synopsis ((String)user.get("short_synopsis"));
		if (user.containsKey("medium_synopsis"))
			this.setMedium_synopsis ((String)user.get("medium_synopsis"));
		if (user.containsKey("is_guide"))
			this.setIs_guide(Integer.parseInt(user.get("is_guide").toString()));
		if (user.containsKey("is_superuser"))
			this.setIs_superuser(Integer.parseInt(user.get("is_superuser").toString()));
		if (user.containsKey("featured_position"))
			this.setFeatured_position (Integer.parseInt(user.get("featured_position").toString()));
		if (user.containsKey("status"))
			this.setStatus(Integer.parseInt(user.get("status").toString()));

	}
	public Integer getFeatured_position() {
		return featured_position;
	}
	public void setFeatured_position(Integer featured_position) {
		this.featured_position = featured_position;
	}
	
	
}
