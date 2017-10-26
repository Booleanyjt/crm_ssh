package com.tao.user.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate5.HibernateTemplate;

import com.tao.user.domain.User;

@SuppressWarnings("all")
public class UserDaoImpl implements UserDao {
	
	private HibernateTemplate hibernateTemplate;

	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}

	@Override
	public User login(User user) {
		// TODO Auto-generated method stub
		String hql="from User where username=? and password=?";
		HibernateTemplate h=hibernateTemplate;
		
		List<User> list=(List<User>) hibernateTemplate.find(hql, user.getUsername(),user.getPassword());
		
		if(list!=null && list.size()>0){
			User u=list.get(0);
			return u;
		}
		return null;
	}

	@Override
	public List<User> finAll() {
		// TODO Auto-generated method stub
		List<User> users=(List<User>) hibernateTemplate.find("from User");
		return users;
	}
	
	
}
