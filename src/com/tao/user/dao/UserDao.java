package com.tao.user.dao;

import java.util.List;

import com.tao.user.domain.User;

public interface UserDao {

	User login(User user);

	List<User> finAll();

}
