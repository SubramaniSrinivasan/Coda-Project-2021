package com.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.AbstractBillDAO;
import com.dao.AbstractInvoiceTransactionDAO;
import com.model.Bill;
import com.model.Item;

@Service("downloadService")
@Transactional
public class DownloadService implements DownloadServiceInterface {

	@Autowired
	private AbstractInvoiceTransactionDAO invoiceTransactionDAO;
	
	@Autowired
	private AbstractBillDAO billDAO;
	
	public AbstractInvoiceTransactionDAO getInvoiceTransactionDAO() {
		return invoiceTransactionDAO;
	}

	public void setInvoiceTransactionDAO(AbstractInvoiceTransactionDAO invoiceTransactionDAO) {
		this.invoiceTransactionDAO = invoiceTransactionDAO;
	}
	
	public AbstractBillDAO getBillDAO() {
		return billDAO;
	}

	public void setBillDAO(AbstractBillDAO billDAO) {
		this.billDAO = billDAO;
	}

	@Override
	public List<String> getAllItemsFromInvoiceTransaction(Integer billNo) {
		
		return this.invoiceTransactionDAO.getAllItems(billNo);
		
	}
	
	@Override
	public Bill getBillByBillId(Integer billId) {
		
		return this.billDAO.getBillByBillId(billId);
		
	}
	
	@Override
	public Item getItemByItemId(String itemId) {
		
		return this.invoiceTransactionDAO.getItemByItemId(itemId);
		
	}
	
}
