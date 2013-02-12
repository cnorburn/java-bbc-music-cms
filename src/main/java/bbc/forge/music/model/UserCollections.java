package bbc.forge.music.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class UserCollections implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	@Column(name="id") 
	private long id;
	
	private String username;

	@OneToMany(mappedBy = "user_id")
	@OrderBy("promoted_at desc")
	private List<Collections> collections;

	public List<Collections> getCollections() {
		return collections;
	}

	public void setCollections(List<Collections> collections) {
		this.collections = collections;
	}

}
