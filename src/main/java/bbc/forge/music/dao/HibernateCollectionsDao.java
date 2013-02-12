package bbc.forge.music.dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import bbc.forge.music.model.Collections;
import bbc.forge.music.model.UserCollections;

public class HibernateCollectionsDao extends HibernateDaoSupport implements CollectionsDao {

	public Collections insert(Collections collections) {
		this.getHibernateTemplate().saveOrUpdate(collections);
		return collections;
	}

	public List<Collections> fetchAll() {
		return this.getHibernateTemplate().find("from Collections order by updated_at desc");
	}

	public List<UserCollections> fetchAllByUserName(String username) {
		return this.getHibernateTemplate().find("from UserCollections userCollections where username='" + username + "'");
	}

	public Collections fetch(String urlKey) {	
		List<Collections>  _collections= this.getHibernateTemplate().find("from Collections collections where url_key='" + urlKey + "'") ;
		return  (( _collections.size()==0) ? null :  _collections.get(0));
	}

	public void delete(Collections collections) {
		this.getHibernateTemplate().delete(collections);		
	}

	public List<Collections> fetchByStatus(int status) {
		return this.getHibernateTemplate().find("from Collections collections where status=" + status) ;
	}

	public List<Collections> fetchByOrder(String order) {
		return this.getHibernateTemplate().find("from Collections collections order by " + order) ;
	}
	
}
