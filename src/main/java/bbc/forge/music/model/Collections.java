package bbc.forge.music.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

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
@Table(name = "collections")
public class Collections implements Serializable {
	
	@Id
	@GeneratedValue
	@Column(name="id") 
	private long id;
	private long user_id;
	private String url_key;
	private String title;
	private String pid;
	private String short_synopsis;	
	private String medium_synopsis;	
	private Integer status;
	private Integer featured_position;
	private Date updated_at=new Date();
	private Date created_at;
	private Date promoted_at;
	
	@ManyToOne
	@JoinColumn(name = "user_id", referencedColumnName = "id", insertable = false, updatable = false)
	private Username user;

		
	@OneToMany(mappedBy = "collection_id")
	@OrderBy("display_order asc")
	private List<Clips> clips;

	@OneToMany(mappedBy = "collection_id")
	@OrderBy("id")
	private List<CollectionArtists> artists;
	
	@Transient
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "user_id")
	private Users users;
	
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
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	public List<Clips> getClips() {
		return clips;
	}
	public void setClips(List<Clips> clips) {
		this.clips = clips;
	}
	public List<CollectionArtists> getArtists() {
		return artists;
	}
	public void setArtists(List<CollectionArtists> artists) {
		this.artists = artists;
	}
	public Date getPromoted_at() {
		return promoted_at;
	}
	public void setPromoted_at(Date promoted_at) {
		this.promoted_at = promoted_at;
	}
	
	public void setPropertiesFromJSON(JSONObject json) throws Exception{
		
		JSONObject collection=json.getJSONObject("collection");
		
		if (collection.containsKey("title"))
			this.setTitle((String)collection.get("title"));
		if (collection.containsKey("short_synopsis"))
			this.setShort_synopsis ((String)collection.get("short_synopsis"));
		if (collection.containsKey("medium_synopsis"))
			this.setMedium_synopsis ((String)collection.get("medium_synopsis"));
		if (collection.containsKey("featured_position"))
			this.setFeatured_position((Integer.parseInt(collection.get("featured_position").toString())));
		if (collection.containsKey("pid"))
			this.setPid ((String)collection.get("pid"));
		if (collection.containsKey("promoted_at"))
			this.setPromoted_at(bbc.forge.music.utils.DateUtils.timestamp( collection.get("promoted_at").toString()));
		if (collection.containsKey("updated_at"))
			this.setUpdated_at(bbc.forge.music.utils.DateUtils.timestamp( collection.get("updated_at").toString()));
	
	}
	public long getUser_id() {
		return user_id;
	}
	public void setUser_id(long user_id) {
		this.user_id = user_id;
	}
	public Username getUser() {
		return user;
	}
	public void setUser(Username user) {
		this.user = user;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Integer getFeatured_position() {
		return featured_position;
	}
	public void setFeatured_position(Integer featured_position) {
		this.featured_position = featured_position;
	}
	
	

}
