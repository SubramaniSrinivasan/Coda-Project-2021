package com.service;

import java.sql.Date;

public interface PaymentServiceInterface {

	public void insertItemInInvoiceTransaction(Integer billNo, String ItemId);
	public Integer insertBillAndGetBillNumber(int UserId, Date date);
	
}
