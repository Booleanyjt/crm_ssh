package com.tao.user.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.tao.user.dao.UserDao;
import com.tao.user.domain.User;

@Transactional
public class UserService {
	
	private UserDao userDao;

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	public User login(User user) {
		// TODO Auto-generated method stub
		return userDao.login(user);
	}
	
	public List<User> findAll(){
		return userDao.finAll();
	}
	
}
