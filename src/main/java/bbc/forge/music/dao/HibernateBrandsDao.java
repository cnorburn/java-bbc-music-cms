package bbc.forge.music.dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.transaction.annotation.Transactional;

import bbc.forge.music.model.BrandUser;
import bbc.forge.music.model.Brands;

@Transactional
public class HibernateBrandsDao  extends HibernateDaoSupport implements BrandsDao{

	public Brands fetch(Long id) {
		List<Brands>  _brands= this.getHibernateTemplate().find("from Brands brands where id=" + id) ;
		return  (( _brands.size()==0) ? null :  _brands.get(0));
	}
	
	public List<BrandUser> fetchUserBrand(Long userId) {
		return  this.getHibernateTemplate().find("from BrandUser brandUser where user_id=" + userId) ;
	}

	public BrandUser insert(BrandUser userBrand) {
		this.getHibernateTemplate().saveOrUpdate(userBrand);
		return userBrand;
	}

	public Brands insert(Brands brand) {
		this.getHibernateTemplate().saveOrUpdate(brand);
		return brand;
	}

	public void delete(Brands brand) {
		this.getHibernateTemplate().delete(brand);		
	}

	public void delete(BrandUser userBrand) {
		this.getHibernateTemplate().delete(userBrand);		
	}


	
}
