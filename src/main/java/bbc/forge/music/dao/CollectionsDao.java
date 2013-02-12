package bbc.forge.music.dao;

import java.util.List;

import bbc.forge.music.model.Collections;
import bbc.forge.music.model.UserCollections;

public interface CollectionsDao {

	public Collections insert(Collections collections);
	public void delete(Collections collections);
	public List<Collections> fetchAll();
	public List<Collections> fetchByOrder(String order);
	public Collections fetch(String urlKey);
	public List<UserCollections> fetchAllByUserName(String username);
	public List<Collections> fetchByStatus(int status);

}
