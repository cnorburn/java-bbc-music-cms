package bbc.forge.music.dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import bbc.forge.music.model.Clips;
import bbc.forge.music.model.Collections;

public class HibernateClipsDao extends HibernateDaoSupport implements ClipsDao  {

	public Clips insert(Clips clip) {
		this.getHibernateTemplate().saveOrUpdate(clip);
		return clip;
	}

	public List<Clips> fetchAll(Collections collection) {
		return  this.getHibernateTemplate().find("from Clips clips  where collection_id=" + collection.getId()) ;
	}

	public Clips fetch(String pid) {
		List<Clips>  _clips= this.getHibernateTemplate().find("from Clips clips where pid='" + pid + "'") ;
		return  (( _clips.size()==0) ? null :  _clips.get(0));
	}

	public void delete(Clips clip) {
		this.getHibernateTemplate().delete(clip);
	}

	public List<Clips> fetchAllByCollectionId(Long id) {
		return this.getHibernateTemplate().find("from Clips where collection_id=" + id);
	}

}
