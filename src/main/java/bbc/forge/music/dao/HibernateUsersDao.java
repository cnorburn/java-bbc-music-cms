package bbc.forge.music.dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.transaction.annotation.Transactional;

import bbc.forge.music.model.Guides;
import bbc.forge.music.model.Users;

@Transactional
public class HibernateUsersDao extends HibernateDaoSupport implements UsersDao {

	public List<Users> fetchAll(){
		return  this.getHibernateTemplate().find("from Users user order by featured_position desc");
	}

	public Users fetch(String username){	
		List<Users> _users=this.getHibernateTemplate().find("from Users user where username='" + username + "'");
		return  (( _users.size()==0) ? null :  _users.get(0));
	}
	
	public Users insert(Users user) {
		this.getHibernateTemplate().saveOrUpdate(user);
		return user;
	}

	public void delete(Users user) {
		this.getHibernateTemplate().delete(user);		
	}

	public void delete(Guides guide) {
		this.getHibernateTemplate().delete(guide);		
	}

	public List<Guides> fetchGuide(String username) {
		return this.getHibernateTemplate().find("from Guides guides where username='" + username + "'");
	}

	public List<Users> fetchByOrder(String order) {
		return this.getHibernateTemplate().find("from Users user order by " + order);
	}
	
	
}
