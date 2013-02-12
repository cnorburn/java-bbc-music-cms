package bbc.forge.music.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "brands_users")
public class BrandsDisplay implements Serializable {
	
	@Id
	@GeneratedValue
	@Column(name="id") 
	private long id; 

	private int display_order;
	private long brand_id;
	private long user_id;

	public int getDisplay_order() {
		return display_order;
	}

	public void setDisplay_order(int display_order) {
		this.display_order = display_order;
	}

	
}
