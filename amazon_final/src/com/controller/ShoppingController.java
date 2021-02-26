package com.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.model.Item;
import com.model.Shop;
import com.model.User;
import com.service.PaymentServiceInterface;
import com.service.ShoppingServiceInterface;
import com.service.UserLoginServiceInterface;

@Controller
public class ShoppingController {
	
	@Autowired
	private ShoppingServiceInterface shoppingService;
	
	@Autowired
	private UserLoginServiceInterface userLoginService;
	
	@Autowired
	private PaymentServiceInterface paymentService;

	public ShoppingServiceInterface getShoppingService() {
		return shoppingService;
	}

	public void setShoppingService(ShoppingServiceInterface shoppingService) {
		this.shoppingService = shoppingService;
	}
	
	public UserLoginServiceInterface getUserLoginService() {
		return userLoginService;
	}

	public void setUserLoginService(UserLoginServiceInterface userLoginService) {
		this.userLoginService = userLoginService;
	}
	
	public PaymentServiceInterface getPaymentService() {
		return paymentService;
	}

	public void setPaymentService(PaymentServiceInterface paymentService) {
		this.paymentService = paymentService;
	}
	
//	@ModelAttribute("itemList")
//	public List<Item> getItems(String shopId){
//		List<Item> itemList = this.shoppingService.getAllItemOfShop(shopId);
//		return itemList;
//	}

	@RequestMapping(value="/showshop", method=RequestMethod.POST)
	public ModelAndView showshop(ModelAndView mandv, HttpServletRequest request, HttpSession session) {
		
		Enumeration<String> en = request.getParameterNames();
		while(en.hasMoreElements()) {
			String name = en.nextElement();
			String value = request.getParameter(name);
			session.setAttribute(name, value);
		}
		
		String shopId = request.getParameter("shopid");
		
		if(!shopId.equals("invoice")) {
			Shop shop = this.shoppingService.getShopByShopId(shopId);
			List<Item> itemList = this.shoppingService.getAllItemOfShop(shop.getShopID());
			
			mandv.addObject("shop", shop);
			mandv.addObject("items", itemList);
		}
		
		mandv.setViewName(shopId);
		
		return mandv;
	}
	
	@RequestMapping(value="/payment", method=RequestMethod.POST)
	public ModelAndView makePayment(ModelAndView mandv, HttpSession session) {
		
		String username = (String) session.getAttribute("uname");
		User user = this.userLoginService.getUserByUname(username);
		List<Item> items = new ArrayList<>();
		
		Enumeration<String> en = session.getAttributeNames();
		while(en.hasMoreElements()){
			String name = en.nextElement();
			if(!name.equals("formid") && !name.equals("shopid") && !name.equals("uname") && !name.equals("billno")){
				Item item = this.shoppingService.getItemByItemId(name);
				items.add(item);
			}
		}
		
		Integer billNo = this.paymentService.insertBillAndGetBillNumber(user.getUid(), new java.sql.Date(Calendar.getInstance().getTime().getTime()));
		
		session.setAttribute("billno", billNo);
		
		for(Item item : items) {
			this.paymentService.insertItemInInvoiceTransaction(billNo, item.getItemID());
		}
		
		mandv.setViewName("download");
		
		return mandv;
	}
}
