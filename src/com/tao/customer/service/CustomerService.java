package com.tao.customer.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.tao.customer.dao.CustomerDao;
import com.tao.customer.domain.Customer;
import com.tao.page.PageBean;
import com.tao.page.PageConstants;
import com.tao.utils.Dict;


@Transactional
public class CustomerService {
	CustomerDao customerDao;
	

	public void setCustomerDao(CustomerDao customerDao) {
		this.customerDao = customerDao;
	}


	public List<Customer> list() {
		// TODO Auto-generated method stub
		List<Customer> list=customerDao.list();
		return list;
	}


	public void add(Customer customer) {
		// TODO Auto-generated method stub
		customerDao.add(customer);
	}


	public Customer findOne(int cid) {
		// TODO Auto-generated method stub
		Customer customer=customerDao.findOne(cid);
		return customer;
	}


	public void del(Customer c) {
		// TODO Auto-generated method stub
		customerDao.del(c);
	}


	public void edit(Customer customer) {
		// TODO Auto-generated method stub
		customerDao.edit(customer);
	}


	public PageBean<Customer> findCustomerPage(int pc) {
		// TODO Auto-generated method stub
		PageBean<Customer> pb=new PageBean<Customer>();
		int tr=customerDao.findCountCustomer();
		int ps=PageConstants.CUSTOMER_PAGE_SIZE;
		List<Customer> customers=customerDao.findCustomerList(ps,pc);
		pb.setPc(pc);
		pb.setPs(ps);
		pb.setTr(tr);
		pb.setBeanList(customers);
		return pb;
	}


	public List<Customer> findCustomerByName(String name) {
		// TODO Auto-generated method stub
		
		return customerDao.findCustomerByName(name);
	}


	public List<Customer> findMoreCondition(Customer customer) {
		// TODO Auto-generated method stub
		return customerDao.findMoreCondition(customer);
	}


	public List<Dict> findAllDict() {
		// TODO Auto-generated method stub
		return customerDao.finAllDict();
	}
	
	public List countByLevel(){
		return customerDao.countByLevel();
	}
	
	public List countBySource(){
		return customerDao.countBySource();
	}
	
	
}
