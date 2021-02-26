package com.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.model.Bill;
import com.model.Item;
import com.model.User;
import com.service.DownloadServiceInterface;
import com.service.UserLoginServiceInterface;

import com.utility.ExcelGenerator;
import com.utility.PDFGenerator;

@Controller
public class DownloadController {

	@Autowired
	private UserLoginServiceInterface userLoginService;
	
	@Autowired
	private DownloadServiceInterface downloadService;

	public UserLoginServiceInterface getUserLoginService() {
		return userLoginService;
	}

	public void setUserLoginService(UserLoginServiceInterface userLoginService) {
		this.userLoginService = userLoginService;
	}
	
	public DownloadServiceInterface getDownloadService() {
		return downloadService;
	}

	public void setDownloadService(DownloadServiceInterface downloadService) {
		this.downloadService = downloadService;
	}

	@RequestMapping(value="/download", method=RequestMethod.GET)
	public ModelAndView shodownloadwshop(ModelAndView mandv, HttpServletRequest request, HttpServletResponse response,
																				HttpSession session) {
		
		String choise = request.getParameter("choise");
		String username = (String)session.getAttribute("uname");
		Integer billNo = (Integer)session.getAttribute("billno");
		
		User user = this.userLoginService.getUserByUname(username);
		OutputStream out = null;
		
		try {
			out = response.getOutputStream();
		} catch (IOException e1) {
			e1.printStackTrace();
			mandv.setViewName("download_failure");
		}
		
		Bill bill = this.downloadService.getBillByBillId(billNo);
		List<String> itemNames = this.downloadService.getAllItemsFromInvoiceTransaction(billNo);
		
		List<Item> itemList = new ArrayList<>();
		
		for(String itemName : itemNames) {
			itemList.add(this.downloadService.getItemByItemId(itemName));
		}
		
		if(choise.endsWith("pdf")) {
			response.setContentType("application/pdf");
			response.setHeader("Content-disposition", "attachment; filename=invoice.pdf");
			PDFGenerator.execute(user, bill, itemList, out);
		}
		else if(choise.endsWith("xls")) {
			response.setContentType("application/vnd.ms-excel");
			response.setHeader("Content-disposition", "attachment; filename=invoice.xls");
			ExcelGenerator.execute(user, bill, itemList, out);
		}
			
		mandv.setViewName("download");
		
		return mandv;
		
	}
	
}
