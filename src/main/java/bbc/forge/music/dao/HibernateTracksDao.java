package bbc.forge.music.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import bbc.forge.music.model.Tracks;
import bbc.forge.music.model.TracksPlaylist;

public class HibernateTracksDao extends HibernateDaoSupport implements TracksDao{

	public Tracks insert(Tracks tracks) throws HibernateException{
		this.getHibernateTemplate().saveOrUpdate(tracks);
		return tracks;
	}

	public List<Tracks> fetchAll() {
		return this.getHibernateTemplate().find("from Tracks order by updated_at desc");
	}

	public Tracks fetch(String url_key) {	
		List<Tracks>  _tracks= this.getHibernateTemplate().find("from Tracks tracks where url_key='" + url_key + "'") ;
		return  (( _tracks.size()==0) ? null :  _tracks.get(0));
	}

	public void delete(Tracks track) {
		this.getHibernateTemplate().delete(track);		
	}

	public List<Tracks> fetchAllByPlaylistId(Long id) {
		return this.getHibernateTemplate().find("from Tracks where id=" + id + " order by updated_at desc");
	}

	public TracksPlaylist insert(TracksPlaylist tracksPlaylist) {
		this.getHibernateTemplate().saveOrUpdate(tracksPlaylist);
		return tracksPlaylist;
	}

}
