package bbc.forge.music.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "brands_users")
public class BrandsUsers implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Column(name="id") 
	private long id; 
	
	private long user_id;
	private long brand_id;
	
	private Date updated_at=new Date();
	private Date created_at;
	
	private int display_order;
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "brand_id", insertable = false, updatable = false)
	private UserBrands brand;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public long getUserId() {
		return user_id;
	}
	public void setUserId(long userId) {
		this.user_id = userId;
	}
	public long getBrandId() {
		return brand_id;
	}
	public void setBrandId(long brandId) {
		this.brand_id = brandId;
	}
	public Date getUpdated() {
		return updated_at;
	}
	public void setUpdated(Date updated) {
		this.updated_at = updated;
	}
	public Date getCreated() {
		return created_at;
	}
	public void setCreated(Date created) {
		this.created_at = created;
	}
	public int getDisplay_order() {
		return display_order;
	}
	public void setDisplay_order(int display_order) {
		this.display_order = display_order;
	}
	public UserBrands getBrand() {
		return brand;
	}
	public void setBrand(UserBrands brand) {
		this.brand = brand;
	}

}
