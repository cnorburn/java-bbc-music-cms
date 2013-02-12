package bbc.forge.music.dao;

import java.util.List;

import bbc.forge.music.model.Guides;
import bbc.forge.music.model.Users;

public interface UsersDao {

	public Users insert(Users user);
	public List<Users> fetchAll();
	public List<Users> fetchByOrder(String order);
	public Users fetch(String username);
	public void delete(Users user);
	public void delete(Guides guide);
	
	public List<Guides> fetchGuide(String username);


}
