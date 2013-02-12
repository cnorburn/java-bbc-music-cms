package bbc.forge.music.dao;

import java.util.List;

import bbc.forge.music.model.Externals;

public interface ExternalsDao {

	public Externals insert(Externals externals);
	public Externals update(Externals externals);
	public void delete(Long id);
	public List<Externals> fetchAll();
	public Externals fetch(Long id);

}
