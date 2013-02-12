package bbc.forge.music.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "brands")
public class Brands implements Serializable {

	@Id
	@GeneratedValue
	@Column(name="id") 
	private long id; 
	private String title;
	private String brand;
	private String broadcast_slot;
	private String pid;
	private String master_brand;
	private String brand_url;
	private Date created_at;
	private Date updated_at=new Date();
	
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

	public String getMaster_brand() {
		return master_brand;
	}

	public void setMaster_brand(String master_brand) {
		this.master_brand = master_brand;
	}

	public String getBrand_url() {
		return brand_url;
	}

	public void setBrand_url(String brand_url) {
		this.brand_url = brand_url;
	}


}
