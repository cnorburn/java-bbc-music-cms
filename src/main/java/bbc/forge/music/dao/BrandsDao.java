package bbc.forge.music.dao;

import java.util.List;

import bbc.forge.music.model.BrandUser;
import bbc.forge.music.model.Brands;

public interface BrandsDao {

	public Brands fetch(Long id);
	public Brands insert(Brands brand);
	public BrandUser insert(BrandUser userBrand);
	public List<BrandUser> fetchUserBrand(Long userId);
	public void delete(Brands brand);
	public void delete(BrandUser userBrand);

	
}
