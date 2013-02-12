package bbc.forge.music.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


/* Guides - Used for delete users
 * This takes any uneccessary logic out of Model Users
 */


@Entity
@Table(name = "users")
public class Guides implements Serializable{

	@Id
	@GeneratedValue
	@Column(name="id") 
	private long id; 
	
	private String username;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user",cascade=CascadeType.REMOVE)
	private List<BrandUser> userBrands=new ArrayList<BrandUser>();
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user_id",cascade=CascadeType.REMOVE)
	private List<Playlists> playlist=new ArrayList<Playlists>();

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user_id",cascade=CascadeType.REMOVE)
	private List<Collections> collections=new ArrayList<Collections>();

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public List<BrandUser> getUserBrands() {
		return userBrands;
	}

	public void setUserBrands(List<BrandUser> userBrands) {
		this.userBrands = userBrands;
	}

	public List<Collections> getCollections() {
		return collections;
	}

	public void setCollections(List<Collections> collections) {
		this.collections = collections;
	}

	public List<Playlists> getPlaylist() {
		return playlist;
	}

	public void setPlaylist(List<Playlists> playlist) {
		this.playlist = playlist;
	}

}
