package com.tao.customer.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.springframework.orm.hibernate5.HibernateTemplate;

import com.sun.org.apache.xml.internal.security.transforms.Transforms;
import com.tao.customer.domain.Customer;
import com.tao.utils.Dict;


@SuppressWarnings("all")
public class CustomerDaoImpl implements CustomerDao {
	
	HibernateTemplate hibernateTemplate;
	

	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}

	/**
	 * 查找所有客户
	 */
	@Override
	public List<Customer> list() {
		// TODO Auto-generated method stub
		
		List<Customer> list=(List<Customer>) hibernateTemplate.find("from Customer");
		return list;
	}

	/**
	 * 添加客户信息
	 */
	@Override
	public void add(Customer customer) {
		// TODO Auto-generated method stub
		hibernateTemplate.save(customer);
	}

	/**
	 * 查找指定的客户，用户删除
	 */
	@Override
	public Customer findOne(int cid) {
		// TODO Auto-generated method stub
		List<Customer> customers=(List<Customer>) hibernateTemplate.find("from Customer where cid=?", cid);
		if(customers!=null && !customers.isEmpty()){
			Customer customer=customers.get(0);
			return customer;
		}
		return null;
	}

	/**
	 * 删除客户
	 */
	@Override
	public void del(Customer c) {
		// TODO Auto-generated method stub
		hibernateTemplate.delete(c);
	}

	/**
	 * 修改客户信息
	 */
	@Override
	public void edit(Customer customer) {
		// TODO Auto-generated method stub
		hibernateTemplate.update(customer);
	}

	/**
	 * 分页查找之前查找客户的数量，便于分页
	 */
	@Override
	public int findCountCustomer() {
		// TODO Auto-generated method stub
		List<Object> count=(List<Object>) hibernateTemplate.find("select count(*) from Customer");
		if(count!=null && !count.isEmpty()){
			Long c=(long) count.get(0);
			int tr=c.intValue();
			return tr;
		}
		return 0;
		
	}
	
	
	/**
	 * 查询所有顾客的分页信息
	 */
	public List<Customer> findCustomerList(int ps, int pc) {
		// TODO Auto-generated method stub
		
		/*
		 * 自己写的方法
		 */
		/*
		Session session=hibernateTemplate.getSessionFactory().getCurrentSession();
		Criteria criteria=session.createCriteria(Customer.class);
		criteria.setFirstResult((pc-1)*ps);
		criteria.setMaxResults(ps);
		List<Customer> customers=criteria.list();
		return customers;
		*/
		
		
		/*
		 * 老师写的第一种分页方法
		 */
		
		/*
		SessionFactory sessionFactory=hibernateTemplate.getSessionFactory();
		Session session=sessionFactory.getCurrentSession();
		Query query=session.createQuery("from Customer");
		query.setFirstResult((pc-1)*ps);
		query.setMaxResults(ps);
		List<Customer> customers=query.list();
		return customers;
		*/
		
		/*
		 * 老师写的第二种分页方法
		 */
		
		DetachedCriteria detachedCriteria=DetachedCriteria.forClass(Customer.class);
		List<Customer> customers=(List<Customer>) hibernateTemplate
				.findByCriteria(detachedCriteria, (pc-1)*ps, ps);
		return customers;
		
		
	}

	@Override
	public List<Customer> findCustomerByName(String name) {
		
		/*
		 * 第一种：通过hql语句查询
		 */
		/*
		Session session=hibernateTemplate.getSessionFactory().getCurrentSession();
		Query query=session.createQuery("from Customer where custName like ?");
		query.setParameter(0, "%"+name+"%");
		List<Customer> customers=query.list();
		return customers;
		*/
		
		
		/*
		 * 第二种：通过Ctiteria查询
		 */
		
		/*
		Session session=hibernateTemplate.getSessionFactory().getCurrentSession();
		Criteria criteria=session.createCriteria(Customer.class);
		criteria.add(Restrictions.like("custName", "%"+name+"%"));
		List<Customer> customers=criteria.list();
		return customers;
		*/
		
		/*
		 * 第三种：通过hibernateTemplate来查询客户
		 */
		
		/*
		List<Customer> customers =(List<Customer>) hibernateTemplate
				.find("from Customer where custName like ?", "%"+name+"%");
		return customers;
		*/
		
		DetachedCriteria detachedCriteria=DetachedCriteria.forClass(Customer.class);
		detachedCriteria.add(Restrictions.like("custName", "%"+name+"%"));
		List<Customer> customers=(List<Customer>) hibernateTemplate.findByCriteria(detachedCriteria);
		return customers;
		
	}

	@Override
	public List<Customer> findMoreCondition(Customer customer) {
		// TODO Auto-generated method stub
		/**
		 * 方法一,hql语句
		 */
		
		/*
		String queryString="from Customer where 1=1 ";
		List<Object> values=new ArrayList<Object>();
		if(customer.getCustName()!=null || "".equals(customer.getCustName())){
			queryString+="and custName like ? ";
			values.add("%"+customer.getCustName()+"%");
		}
		
		if(customer.getCustLevel()!=null || "".equals(customer.getCustLevel())){
			queryString+="and custLevel like ? ";
			values.add("%"+customer.getCustLevel()+"%");
		}
		
		if(customer.getCustSource()!=null || "".equals(customer.getCustSource())){
			queryString+="and custSource like ? ";
			values.add("%"+customer.getCustSource()+"%");
		}
		List<Customer> customers=(List<Customer>) hibernateTemplate.find(queryString, values.toArray());
		return customers;
		*/
		
		
		/*
		 * criteria查询
		 */
		
		/*
		Session session =hibernateTemplate.getSessionFactory().getCurrentSession();
		Criteria criteria=session.createCriteria(Customer.class);
		
		criteria.add(Restrictions.like("custName", "%"+customer.getCustName()+"%"));
		criteria.add(Restrictions.like("custLevel","%"+ customer.getCustLevel()+"%"));
		criteria.add(Restrictions.like("custSource", "%"+customer.getCustSource()+"%"));
		List<Customer> customers=criteria.list();
//		错误的写法
//		List<Customer> customers=hibernateTemplate.findByCriteria(criteria);
		return customers;
		*/
		
		
		/*
		 * 离线查询
		 */
		
		
		DetachedCriteria criteria=DetachedCriteria.forClass(Customer.class);
		criteria.add(Restrictions.like("custName", "%"+customer.getCustName()+"%"));
		criteria.add(Restrictions.eq("dict.dictid", customer.getDict().getDictid()));
		criteria.add(Restrictions.like("custSource", "%"+customer.getCustSource()+"%"));
		List<Customer> customers=(List<Customer>) hibernateTemplate.findByCriteria(criteria);
		return customers;
		
	}

	@Override
	public List<Dict> finAllDict() {
		// TODO Auto-generated method stub
		
		
		return (List<Dict>)hibernateTemplate.find("from Dict");
	}
	
	public List countByLevel(){
		
		Session session=hibernateTemplate.getSessionFactory().getCurrentSession();
		SQLQuery sqlQuery=session.createSQLQuery("SELECT custName ,COUNT(*) AS 'count' FROM t_customer GROUP BY custSource");
		sqlQuery.setResultTransformer(Transformers.aliasToBean(HashMap.class));
		List list=sqlQuery.list();
		return list;
		
	}
	
	public List countBySource(){
		String sql="SELECT c.custName,c.dname ,COUNT(*) AS 'count'  "
				+ "FROM "
				+ "(SELECT u.custName,d.dname "
				+ "FROM t_customer u,t_dict d "
				+ "WHERE u.dictid = d.dictid) c "
				+ "GROUP BY c.dname";
		Session session=hibernateTemplate.getSessionFactory().getCurrentSession();
		SQLQuery sqlQuery=session.createSQLQuery(sql);
		sqlQuery.setResultTransformer(Transformers.aliasToBean(HashMap.class));
		List lists=sqlQuery.list();
		return lists;
	}
	
	
}
