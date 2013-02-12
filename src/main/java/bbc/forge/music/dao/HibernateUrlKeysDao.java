package bbc.forge.music.dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import bbc.forge.music.model.UrlKeys;

public class HibernateUrlKeysDao extends HibernateDaoSupport implements UrlKeysDao {

	public Long generate() throws Exception {
		
		List<UrlKeys> keys=this.getHibernateTemplate().find("from UrlKeys urlKeys");
		
		if (keys.size()==0)
			throw new Exception("Cannot get a new sequence number from table [url_key] to generate a new url_key");
		
		UrlKeys key=keys.get(0);
		Long _key=key.getSequence();
		
		key.setSequence(key.getSequence() + 1);
		this.getHibernateTemplate().update(key);
		
		return _key;
		
	}
	

}
