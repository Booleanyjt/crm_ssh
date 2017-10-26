package com.tao.utils;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;



@SuppressWarnings("all")
public class BaseDaoImpl<T> extends HibernateDaoSupport implements BaseDao<T> {
	private Class clazzType;
	
	public BaseDaoImpl() {
		// TODO Auto-generated constructor stub
		//1 获取当前运行对象的class
		//比如运行customerDao实现类，得到customerDao实现类class
		Class clazz=this.getClass();
		
		//2 获取运行类的父类的参数化类型
		Type type=clazz.getGenericSuperclass();
		
		//3 转换成子接口ParameterizedType
		ParameterizedType ptype=(ParameterizedType) type;
		
		//4 获取实际类型参数
		//比如 Map<key,value>

		Type[] types=ptype.getActualTypeArguments();
		
		//5 把Type变成class
		Class clazzParamenter=(Class) types[0];
		this.clazzType=clazzParamenter;
	}
	
	@Override
	public void add(T t) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().save(t);
	}

	@Override
	public void delete(T t) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().delete(t);
	}

	@Override
	public void update(T t) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().update(t);
	}

	
	@Override
	public T get(int id) {
		// TODO Auto-generated method stub
		
		return (T) this.getHibernateTemplate().get(clazzType, id);
	}

	@Override
	public List<T> findAll() {
		// TODO Auto-generated method stub
		
		return (List<T>) this.getHibernateTemplate().find("from "+clazzType);
	}


}
