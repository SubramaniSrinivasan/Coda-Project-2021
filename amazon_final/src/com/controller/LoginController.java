package com.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.service.UserLoginServiceInterface;

@Controller
public class LoginController {

	@Autowired
	UserLoginServiceInterface userLoginService;
	
	public UserLoginServiceInterface getUserLoginService() {
		return userLoginService;
	}

	public void setUserLoginService(UserLoginServiceInterface userLoginService) {
		this.userLoginService = userLoginService;
	}

	@RequestMapping(value="/loadlogin", method=RequestMethod.GET)
	public ModelAndView loadLoginForm(ModelAndView mandv) {
		LoginFormBean lfb = new LoginFormBean();
		mandv.addObject("formBeanObject", lfb);
		mandv.setViewName("login");
		return mandv;
	}
	
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public ModelAndView signIn(ModelAndView mandv, LoginFormBean lfb, HttpServletRequest request, HttpSession session) {
		
		boolean isMember = this.userLoginService.checkUserExists(lfb.getUname(), lfb.getUpass());
		
		if(isMember) {
			session.setAttribute("uname", request.getParameter("uname"));
			if(this.userLoginService.checkIfLoggedIn(lfb.getUname())) {
				mandv.setViewName("already_logged_in");
			}
			else {
				this.userLoginService.loginUser(lfb.getUname());
				mandv.setViewName("welcome");
			}
		}
		else {
			mandv.setViewName("register");
		}
		
		return mandv;
	}
	
	@RequestMapping(value="/goToHomePage", method=RequestMethod.POST)
	public ModelAndView goToHomePage(ModelAndView mandv) {
		
		mandv.setViewName("welcome");
		
		return mandv;
	}
	
	@RequestMapping(value="/logoutUser", method=RequestMethod.POST)
	public ModelAndView LogoutUser(ModelAndView mandv, HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		String uname = (String)session.getAttribute("uname");
		
		this.userLoginService.logoutUser(uname);
		
		mandv.setViewName("logged_out");
		
		return mandv;
		
	}
	
}
