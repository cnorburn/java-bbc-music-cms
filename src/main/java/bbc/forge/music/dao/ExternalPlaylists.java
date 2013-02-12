package bbc.forge.music.dao;

import java.util.List;

import bbc.forge.music.model.ExternalsPlaylists;

public interface ExternalPlaylists {

	public ExternalsPlaylists insert(ExternalsPlaylists externalsPlaylists);
	public ExternalsPlaylists update(ExternalsPlaylists externalsPlaylists);
	public void delete(Long id);
	public List<ExternalsPlaylists> fetchAll();
	public ExternalsPlaylists fetch(Long id);

}
