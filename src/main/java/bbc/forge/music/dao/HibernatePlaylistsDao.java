package bbc.forge.music.dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import bbc.forge.music.model.Playlists;
import bbc.forge.music.model.TracksPlaylist;


public class HibernatePlaylistsDao extends HibernateDaoSupport implements PlaylistsDao{

	public Playlists insert(Playlists playlist) {
		this.getHibernateTemplate().saveOrUpdate(playlist);
		return playlist;
	}

	public List<Playlists> fetchAll() {
		return this.getHibernateTemplate().find("from Playlists order by updated_at desc");
	}

	public List<Playlists> fetchAllByUserName(String username) {
		return this.getHibernateTemplate().find("from UserPlaylists where username='" + username + "'");
	}

	public Playlists fetch(String urlKey) {	
		List<Playlists> _playlists=this.getHibernateTemplate().findByNamedParam("from Playlists where url_key=:urlKey", "urlKey", urlKey);
		return  (( _playlists.size()==0) ? null :  _playlists.get(0));
	}

	public void delete(Playlists playlist) {
		this.getHibernateTemplate().delete(playlist);
	}

	public List<Playlists> fetchAll(int status) {
		return this.getHibernateTemplate().find("from Playlists playlists where status=" + status);
	}
	
	public List<TracksPlaylist> fetch(Long playlist_id) {
		return this.getHibernateTemplate().find("from TracksPlaylist tracksPlaylist where playlist_id=" + playlist_id) ;
	}

	public void delete(TracksPlaylist tracksPlaylist) {
		this.getHibernateTemplate().delete(tracksPlaylist);
	}

	public List<Playlists> fetchByOrder(String order) {
		return this.getHibernateTemplate().find("from Playlists playlists order by " + order);
	}

}
