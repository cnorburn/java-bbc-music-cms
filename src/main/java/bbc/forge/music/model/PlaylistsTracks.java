package bbc.forge.music.model;

import java.io.Serializable;
import java.util.Date;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.JoinColumn;

@Entity
@Table(name = "playlists_tracks")
public class PlaylistsTracks implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	@Column(name="id") 
	private long id;

	private long playlist_id;

	private String medium_synopsis;
	private long track_position;
	private long track_id;
	private Date updated_at=new Date();
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "track_id", insertable = false, updatable = false)
	private Tracks track;
			
	public String getMedium_synopsis() {
		return medium_synopsis;
	}	
	public void setMedium_synopsis(String mediumSynopsis) {
		this.medium_synopsis = mediumSynopsis;
	}
	
	public Tracks getTrack() {
		return track;
	}
	public void setTrack(Tracks track) {
		this.track = track;
	}
	public long getTrack_position() {
		return track_position;
	}
	public void setTrack_position(long track_position) {
		this.track_position = track_position;
	}
	

}
