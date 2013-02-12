package bbc.forge.music.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "externals_playlists")
public class ExternalsPlaylists implements Serializable {

	@Id
	@GeneratedValue
	@Column(name="id") 
	private long id;

	private String url;
	private int tracks;
	private int playlist_id;
	private int external_id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "playlist_id", referencedColumnName = "id", insertable = false, updatable = false)
	private Playlists playlists;

	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "external_id", insertable = false, updatable = false)
	private Externals external;
	
	public int getTracks() {
		return tracks;
	}
	@Column(name = "tracks")
	public void setTracks(int tracks) {
		this.tracks = tracks;
	}
	@Column(name = "url")
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Externals getExternal() {
		return external;
	}
	public void setExternal(Externals external) {
		this.external = external;
	}
	public Playlists getPlaylists() {
		return playlists;
	}
	public void setPlaylists(Playlists playlists) {
		this.playlists = playlists;
	}
	
}
