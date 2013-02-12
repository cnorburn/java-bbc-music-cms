package bbc.forge.music.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OrderBy;
import javax.persistence.Table;

@Entity
@Table(name = "brands")
public class UserBrands implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Column(name="id") 
	private long id; 
	private String title;
	private String brand;
	private String broadcast_slot;
	private String pid;
	private Date updated_at=new Date();
	private Date created_at;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@OrderBy("display_order asc")
	@JoinColumn(name = "id", referencedColumnName = "brand_id", insertable = false, updatable = false)
	private BrandsDisplay display;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getBroadcast_slot() {
		return broadcast_slot;
	}

	public void setBroadcast_slot(String broadcast_slot) {
		this.broadcast_slot = broadcast_slot;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public Date getUpdated_at() {
		return updated_at;
	}

	public void setUpdated_at(Date updated_at) {
		this.updated_at = updated_at;
	}

	public Date getCreated_at() {
		return created_at;
	}

	public void setCreated_at(Date created_at) {
		this.created_at = created_at;
	}

	public BrandsDisplay getDisplay() {
		return display;
	}

	public void setDisplay(BrandsDisplay display) {
		this.display = display;
	}


}
