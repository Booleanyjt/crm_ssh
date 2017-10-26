package com.tao.user.action;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.mchange.v2.codegen.bean.BeangenUtils;
import com.opensymphony.xwork2.ActionSupport;
import com.tao.user.domain.User;
import com.tao.user.service.UserService;

public class UserAction extends ActionSupport {
	
	private UserService userService;

	public void setUserService(UserService userService) {
		this.userService = userService;
	}


	public String login() throws Exception {
		
		HttpServletRequest request=ServletActionContext.getRequest();
		String username=(String) request.getParameter("username");
		String password=(String) request.getParameter("password");
		User formuser =new User();
		formuser.setUsername(username);
		formuser.setPassword(password);
		
		User user=userService.login(formuser);
		if(user!=null){
			request.getSession().setAttribute("user", user);
			return "loginsuccess";
		}else{
			return "loginfail";
		}
		
	}
}
