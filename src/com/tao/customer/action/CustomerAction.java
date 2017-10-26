package com.tao.customer.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.sun.javafx.collections.MappingChange.Map;
import com.tao.customer.domain.Customer;
import com.tao.customer.service.CustomerService;
import com.tao.page.PageBean;
import com.tao.utils.Dict;

/**
 * @author tao
 *
 */
public class CustomerAction extends ActionSupport implements ModelDriven<Customer>{
	
	CustomerService customerService;
	
	public void setCustomerService(CustomerService customerService) {
		this.customerService = customerService;
	}

	Customer customer=new Customer();
	@Override
	public Customer getModel() {
		// TODO Auto-generated method stub
		return customer;
	}
	
	/**
	 * 得到要查询的页码
	 * @return
	 */
	private int getPc(){
		String pageCode=ServletActionContext.getRequest().getParameter("pc");
		int pc=Integer.parseInt(pageCode);
		return pc;
	}
	
	/**
	 * 查询要查询的url
	 * @return
	 */
	private String getUrl(){
		
		HttpServletRequest request=ServletActionContext.getRequest();
		String url=request.getRequestURL()+"?"+request.getQueryString();
		int index=url.lastIndexOf("pc=");
		url=url.substring(0, index-1);
		return url;
	}
	
	/**
	 * 添加客户
	 * @return
	 */
	public String add(){
		customerService.add(customer);
		return "add";
	}
	
	/**
	 * 查询所有的客户列表
	 * @return
	 */
	public String list(){
		
		List<Customer> list=customerService.list();
		HttpServletRequest request=ServletActionContext.getRequest();
		request.setAttribute("list", list);
		if(list!=null && !list.isEmpty()){
			return "list";
		}else {
			return null;
		}
		
	}
	
	/**
	 * 删除客户信息
	 * @return
	 */
	public String del(){
		//int cid=customer.getCid();
		String c=ServletActionContext.getRequest().getParameter("cid");
		int cid=Integer.parseInt(c);
		Customer customer=customerService.findOne(cid);
		if(customer!=null){
			customerService.del(customer);
			return "delete";
		}else{
			return "list";
		}
		
	}
	
	/**
	 * 修改客户信息之前先查询客户信息，进行回选
	 * @return
	 */
	public String editPre(){
		
		Customer c=customerService.findOne(customer.getCid());
		if(c!=null){
			HttpServletRequest request=ServletActionContext.getRequest();
			request.setAttribute("customer", c);
			return "editPre";
		}
		return "list";
	}
	
	/**
	 * 修改客户信息
	 * @return
	 */
	public String edit(){
		if(this.customer!=null){
			customerService.edit(this.customer);
			return "edit";
		}
		return "list";
	}
	
	/**
	 * 通过分页查找customer
	 * @return
	 */
	public String findCustomerPage(){
		PageBean<Customer> pb=new PageBean<Customer>();
		int pc=getPc();
		pb=customerService.findCustomerPage(pc);
		pb.setPc(pc);
		pb.setUrl(getUrl());
		ServletActionContext.getRequest().setAttribute("pb", pb);
		return "listpage";
	}
	
	
	/**
	 * 通过客户名查询客户
	 * @return
	 */
	public String findCustomerByName(){
		
		
		String name=this.customer.getCustName();
		List<Customer> customers=customerService.findCustomerByName(name);
		ServletActionContext.getRequest().setAttribute("list", customers);
		return "list";
	}
	
	/**
	 * 客户综合查询
	 * @return
	 */
	public String findMoreCondition(){
		List<Customer> customers=customerService.findMoreCondition(this.customer);
		ServletActionContext.getRequest().setAttribute("list", customers);
		return "query";
	}
	
	public String findAllDict(){
		List<Dict> dicts=customerService.findAllDict();
		ServletActionContext.getRequest().setAttribute("dicts", dicts);
		return "queryPre";
	}
	
	public String countByLevel(){
		List lists=customerService.countByLevel();
		ServletActionContext.getRequest().setAttribute("list", lists);
		return "countByLevel";
		
	}
	
	public String countBySource(){
		List lists=customerService.countBySource();
		ServletActionContext.getRequest().setAttribute("list", lists);
		return "countBySource";
		
	}
/*	
	public String edit(){
		
		Customer customer=customerService.findOne(cid);
		if(customer!=null){
			HttpServletRequest request=ServletActionContext.getRequest();
			request.setAttribute("customer", customer);
			return "editPre";
		}
		return null;
	}
	
	*/
	
}
