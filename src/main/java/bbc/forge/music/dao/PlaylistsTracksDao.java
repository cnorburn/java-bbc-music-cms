package bbc.forge.music.dao;

import java.util.List;

import bbc.forge.music.model.PlaylistsTracks;
import bbc.forge.music.model.Tracks;

public interface PlaylistsTracksDao {

	public PlaylistsTracks insert(PlaylistsTracks playlistsTracks);
	public PlaylistsTracks update(PlaylistsTracks playlistsTracks);
	public void delete(Long id);
	public List<PlaylistsTracks> fetchByPlayListId(String url_key);
	public List<Tracks> fetchTracksByPlaylistId(Long playlist_id);
	public PlaylistsTracks fetch(Long id);
	public List<PlaylistsTracks> fetchAll();
	public PlaylistsTracks insertTracks(Tracks track);
	
}
