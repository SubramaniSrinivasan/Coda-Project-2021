package com.service;

import java.sql.Date;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.AbstractBillDAO;
import com.dao.AbstractInvoiceTransactionDAO;

@Service("paymentService")
@Transactional
public class PaymentService implements PaymentServiceInterface {

	@Autowired
	private AbstractInvoiceTransactionDAO invoicetransactionDAO;
	
	@Autowired
	private AbstractBillDAO billDAO;

	public AbstractInvoiceTransactionDAO getInvoicetransactionDAO() {
		return invoicetransactionDAO;
	}

	public void setInvoicetransactionDAO(AbstractInvoiceTransactionDAO invoicetransactionDAO) {
		this.invoicetransactionDAO = invoicetransactionDAO;
	}
	
	public AbstractBillDAO getBillDAO() {
		return billDAO;
	}

	public void setBillDAO(AbstractBillDAO billDAO) {
		this.billDAO = billDAO;
	}

	@Override
	public void insertItemInInvoiceTransaction(Integer billNo, String ItemId) {
		
		this.invoicetransactionDAO.insertItem(billNo, ItemId);
		
	}
	
	@Override
	public Integer insertBillAndGetBillNumber(int UserId, Date date) {
		
		return this.billDAO.createBillAndGetBillNumber(UserId, date);
		
	}
	
}
