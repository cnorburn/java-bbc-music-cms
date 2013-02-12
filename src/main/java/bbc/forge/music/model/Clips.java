package bbc.forge.music.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import net.sf.json.JSONObject;

@Entity 
@Table(name = "clips")
public class Clips implements Serializable {
	
	@Id
	@Column(name="pid") 
	private String pid;

	private Integer display_order;
	private Double duration;
	private Long collection_id;
	private String title;
	private Date updated_at=new Date();
	private Date created_at;

	@Transient
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "collection_id", referencedColumnName = "id", insertable = false, updatable = false)
	private Collections collection;

	public Long getCollection_id() {
		return collection_id;
	}
	public void setCollection_id(Long collectionId) {
		this.collection_id = collectionId;
	}
	
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	
	public Integer getDisplay_order() {
		return display_order;
	}
	public void setDisplay_order(Integer display_order) {
		this.display_order = display_order;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
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
	
	public Collections getCollection() {
		return collection;
	}

	public void setCollection(Collections collection) {
		this.collection = collection;
	}

	public Double getDuration() {
		return duration;
	}
	public void setDuration(Double duration) {
		this.duration = duration;
	}
	
	public void setPropertiesFromJSON(JSONObject json) throws Exception{
	
		JSONObject clip=json.getJSONObject("clip");

		if (clip.containsKey("title"))
			this.setTitle((String)clip.get("title"));
		if (clip.containsKey("pid"))
			this.setPid ((String)clip.get("pid"));
		if (clip.containsKey("duration"))
			this.setDuration ((Double.parseDouble(clip.get("duration").toString())));
		if (clip.containsKey("display_order"))
			this.setDisplay_order ((Integer.parseInt(clip.get("display_order").toString())));
		if (clip.containsKey("updated_at"))
			this.setUpdated_at(bbc.forge.music.utils.DateUtils.timestamp( clip.get("updated_at").toString()));
	
	}



}
