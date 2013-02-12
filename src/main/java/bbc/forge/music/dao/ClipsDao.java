package bbc.forge.music.dao;

import java.util.List;

import bbc.forge.music.model.Clips;
import bbc.forge.music.model.Collections;


public interface ClipsDao {

	public Clips insert(Clips clip);
	public List<Clips> fetchAll(Collections collection);
	public Clips fetch(String pid);
	public void delete(Clips clip);
	public List<Clips> fetchAllByCollectionId(Long id);

}
