package bbc.forge.music.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "url_keys")
public class UrlKeys  implements Serializable {

	@Id
	@Column(name="id") 
	private long id;

	public Long sequence;

	public Long getSequence() {
		return sequence;
	}

	public void setSequence(Long sequence) {
		this.sequence = sequence;
	}

}
