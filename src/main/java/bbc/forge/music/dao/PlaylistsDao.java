package bbc.forge.music.dao;

import java.util.List;

import bbc.forge.music.model.Playlists;
import bbc.forge.music.model.TracksPlaylist;

public interface PlaylistsDao {

	public Playlists insert(Playlists playlist);
	public void delete(Playlists playlist);
	public List<Playlists> fetchAll();
	public List<Playlists> fetchByOrder(String order);
	public Playlists fetch(String urlKey);
	public List<Playlists> fetchAllByUserName(String username);
	public List <Playlists> fetchAll(int status);
	public List<TracksPlaylist> fetch(Long playlist_id);
	public void delete(TracksPlaylist trackPlaylist);

}
