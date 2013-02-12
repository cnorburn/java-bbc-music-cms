package bbc.forge.music.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.transaction.annotation.Transactional;

import bbc.forge.music.model.PlaylistsTracks;
import bbc.forge.music.model.Tracks;

public class HibernatePlaylistsTracksDao extends HibernateDaoSupport implements PlaylistsTracksDao{

	@Transactional(readOnly = false)
	public PlaylistsTracks insert(PlaylistsTracks playlistsTracks) throws DataAccessException{
		
		try{
			getHibernateTemplate().save(playlistsTracks);
		}
		catch(DataAccessException dx){
			dx.printStackTrace(System.out);
			return null;
		}
		
		return playlistsTracks;
		
	}

	@Transactional(readOnly = false)
	public PlaylistsTracks update(PlaylistsTracks playlistsTracks) {
		getHibernateTemplate().update(playlistsTracks);
		return playlistsTracks;
	}

	public void delete(Long id) {
		PlaylistsTracks playlistsTracks=(PlaylistsTracks) this.getHibernateTemplate().get(PlaylistsTracks.class,id);
		this.getHibernateTemplate().delete(playlistsTracks);		
	}


	public PlaylistsTracks fetch(Long id) {
		return (PlaylistsTracks) getSession().get(PlaylistsTracks.class, id);	
	}

	public  List<PlaylistsTracks> fetchAll() {
		return this.getHibernateTemplate().find("from PlaylistsTracks order by updated_at desc");
	}

	public List<Tracks> fetchTracksByPlaylistId(Long playlist_id) throws DataAccessException{
		return this.getHibernateTemplate().findByNamedParam("select tracks from PlaylistsTracks where playlist_id=:playlist_id" , "playlist_id", playlist_id);
	}
	
	public List<PlaylistsTracks> fetchByPlayListId(String url_key) {
		return this.getHibernateTemplate().findByNamedParam("select playlistsTracks from Playlists p join p.playlistsTracks playlistsTracks where p.url_key=:urlKey", "urlKey", url_key);
	}

	public PlaylistsTracks insertTracks(Tracks track){
		
		return null;
	}
	
	
	
}
