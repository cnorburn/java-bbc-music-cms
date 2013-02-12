package bbc.forge.music.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "playlists")
public class PlaylistUpdated implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Column(name="id") 
	private long id; 
	private long user_id; 
	private String title;

	private Date updated_at=new Date();
	private Date created_at;
	private Date promoted_at;
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Date getPromoted_at() {
		return promoted_at;
	}
	public void setPromoted_at(Date promoted_at) {
		this.promoted_at = promoted_at;
	}
	

}
