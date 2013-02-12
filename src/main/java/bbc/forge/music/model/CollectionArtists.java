package bbc.forge.music.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "artists_collection")
public class CollectionArtists implements  Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Column(name="id") 
	private long id;
	private long collection_id;
	
	private String artist_name;
	private String mbid;
	
	public String getArtist() {
		return artist_name;
	}
	public void setArtist(String artist_name) {
		this.artist_name = artist_name;
	}
	public String getMbid() {
		return mbid;
	}
	public void setMbid(String mbid) {
		this.mbid = mbid;
	}
	
	
	

}
