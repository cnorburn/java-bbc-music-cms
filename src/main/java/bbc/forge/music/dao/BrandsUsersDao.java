package bbc.forge.music.dao;

import java.util.List;

import bbc.forge.music.model.BrandsUsers;

public interface BrandsUsersDao {

	public BrandsUsers insert(BrandsUsers brandsUsers);
	public BrandsUsers update(BrandsUsers brandsUsers);
	public void delete(Long id);
	public List<BrandsUsers> fetchAll();
	public BrandsUsers fetch(Long id);
	
}
