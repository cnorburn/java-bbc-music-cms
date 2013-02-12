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
import javax.persistence.Table;

import bbc.forge.music.utils.DateUtils;

@Entity
@Table(name = "brands_users")
public class BrandUser implements Serializable{

	@Id
	@GeneratedValue
	@Column(name="id") 
	private long id; 
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "user_id")
	private Users user;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "brand_id")
	private Brands brand;
	
	
	public BrandUser() {
	}
	
	public BrandUser(Users user, Brands brand) {
		this.user = user;
		this.brand = brand;
	}
	
	private int display_order;
	private Date created_at;
	private Date updated_at=new Date();

	public int getDisplay_order() {
		return display_order;
	}
	public void setDisplay_order(int display_order) {
		this.display_order = display_order;
	}
	public Users getUser() {
		return user;
	}
	public void setUser(Users user) {
		this.user = user;
	}
	public Brands getBrand() {
		return brand;
	}
	public void setBrand(Brands brand) {
		this.brand = brand;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Date getCreated_at() {
		return created_at;
	}
	public void setCreated_at(Date created_at) {
		this.created_at = created_at;
	}
	public Date getUpdated_at() {
		return DateUtils.timestamp();
	}
	public void setUpdated_at(Date updated_at) {
		this.updated_at = updated_at;
	}

	
}
