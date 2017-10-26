package com.tao.linkman.action;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.w3c.dom.stylesheets.LinkStyle;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.tao.customer.domain.Customer;
import com.tao.customer.service.CustomerService;
import com.tao.linkman.domain.LinkMan;
import com.tao.linkman.service.LinkManService;

import sun.print.resources.serviceui;

public class LinkManAction extends ActionSupport implements ModelDriven<LinkMan>{
	
	LinkManService linkManService;
	CustomerService customerService;

	public void setLinkManService(LinkManService linkManService) {
		this.linkManService = linkManService;
	}
	
	public void setCustomerService(CustomerService customerService) {
		this.customerService = customerService;
	}
	
	
	LinkMan linkMan=new LinkMan();
	@Override
	public LinkMan getModel() {
		// TODO Auto-generated method stub
		return linkMan;
	}
	
	/**
	 * 添加之前，将customer对象得到，以便联系人显示客户列表
	 * @return
	 */
	public String addPre(){
		List<Customer> customers=customerService.list();
		if(customers!=null && !customers.isEmpty()){
			ServletActionContext.getRequest().setAttribute("customers", customers);
		}
		
		return "addpre";
	}
	
	
	/*
	 * 文件上传的get和set方法
	 */
	File upload;
	String uploadFileName;
	
	public File getUpload() {
		return upload;
	}

	public void setUpload(File upload) {
		this.upload = upload;
	}

	public String getUploadFileName() {
		return uploadFileName;
	}

	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}
	
	
	/**
	 * 添加联系人
	 * @return
	 */
	public String add(){
		
		String realpath=ServletActionContext.getServletContext().getRealPath("/upload");
		if(upload!=null){
			File savefile=new File(new File(realpath),uploadFileName);
			System.out.println(savefile);
			try {
				FileUtils.copyFile(upload, savefile);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		linkManService.add(linkMan);
		return "add";
	}
	
	/**
	 * 显示所有联系人
	 * @return
	 */
	public String list(){
		List<LinkMan> linkMans=linkManService.list();
		ServletActionContext.getRequest().setAttribute("list", linkMans);
		return "list";
	}
	
	public String editPre(){
		int lid=this.linkMan.getLid();
		LinkMan linkman=linkManService.finOne(lid);
		ServletActionContext.getRequest().setAttribute("linkman", linkman);
		List<Customer> customers=customerService.list();
		if(customers!=null && !customers.isEmpty()){
			ServletActionContext.getRequest().setAttribute("customers", customers);
		}
		return "editPre";
	}
	
	public String edit(){
		linkManService.edit(this.linkMan);
		return "edit";
	}
	
	public String delete(){
		linkManService.delete(linkMan);
		return "delete";
	}
	
	public String findByName(){
		String name=linkMan.getLkmName();
		List<LinkMan> linkMans=linkManService.findByName(name);
		ServletActionContext.getRequest().setAttribute("list", linkMans);
		return "list";
	}
	
	public String queryPre(){
		List<Customer> customers=customerService.list();
		ServletActionContext.getRequest().setAttribute("customers", customers);
		return "queryPre";
	}
	
	public String findMoreCondition(){
		List<LinkMan> linkMans=linkManService.findMoreCondition(this.linkMan);
		ServletActionContext.getRequest().setAttribute("list", linkMans);
		return "list";
	}
	
	
}
