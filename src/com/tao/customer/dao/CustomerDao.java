package com.tao.customer.dao;

import java.util.List;

import com.tao.customer.domain.Customer;
import com.tao.utils.Dict;

public interface CustomerDao {

	List<Customer> list();

	void add(Customer customer);

	Customer findOne(int cid);

	void del(Customer c);

	void edit(Customer customer);

	int findCountCustomer();

	List<Customer> findCustomerList(int ps, int pc);

	List<Customer> findCustomerByName(String name);

	List<Customer> findMoreCondition(Customer customer);

	List<Dict> finAllDict();
	List countByLevel();
	List countBySource();



}
