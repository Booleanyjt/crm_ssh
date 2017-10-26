package com.tao.visit.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.springframework.http.HttpRequest;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.tao.customer.domain.Customer;
import com.tao.customer.service.CustomerService;
import com.tao.linkman.domain.LinkMan;
import com.tao.user.dao.UserDao;
import com.tao.user.domain.User;
import com.tao.user.service.UserService;
import com.tao.visit.domain.Visit;
import com.tao.visit.service.VisitService;

public class VisitAction extends ActionSupport implements ModelDriven<Visit>{

	Visit visit=new Visit();
	@Override
	public Visit getModel() {
		// TODO Auto-generated method stub
		return visit;
	}
	
	VisitService visitService;
	UserService userService;
	CustomerService customerService;
	public void setVisitService(VisitService visitService) {
		this.visitService = visitService;
	}
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	public void setCustomerService(CustomerService customerService) {
		this.customerService = customerService;
	}
	
	public String addPre(){
		List<User> users=userService.findAll();
		List<Customer> customers=customerService.list();
		HttpServletRequest request= ServletActionContext.getRequest();
		request.setAttribute("users", users);
		request.setAttribute("customers", customers);
		return "addPre";
	}
	
	public String add(){
		visitService.add(this.visit);
		return "add";
	}
	
	public String list(){
		List<Visit> visits=visitService.findAll();
		ServletActionContext.getRequest().setAttribute("list", visits);
		return "list";
	}
	
	public String queryPre(){
		List<User> users=userService.findAll();
		List<Customer> customers=customerService.list();
		HttpServletRequest request= ServletActionContext.getRequest();
		request.setAttribute("users", users);
		request.setAttribute("customers", customers);
		return "queryPre";
	}
	
	public String findMoreCondition(){
		List<Visit> visits=visitService.findMoreCondition(this.visit);
		ServletActionContext.getRequest().setAttribute("list", visits);
		return "list";
	}
	

}
