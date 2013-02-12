package bbc.forge.music.dao;

import java.util.List;

import bbc.forge.music.model.Tracks;
import bbc.forge.music.model.TracksPlaylist;

public interface TracksDao {

	public List<Tracks> fetchAll();
	public List<Tracks> fetchAllByPlaylistId(Long id);
	
	public Tracks fetch(String urlKey);
	public Tracks insert(Tracks track);
	public TracksPlaylist insert(TracksPlaylist playlistTrack);
	public void delete(Tracks track);

		
}
