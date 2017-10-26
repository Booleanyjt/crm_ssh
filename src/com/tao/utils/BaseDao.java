package com.tao.utils;

import java.util.List;

public interface BaseDao<T> {
	
	void add(T t);
	void delete(T t);
	void update(T t);
	T get(int id);
	List<T> findAll();
}
