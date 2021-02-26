package com.service;

import java.util.List;

import com.model.Bill;
import com.model.Item;

public interface DownloadServiceInterface {

	public List<String> getAllItemsFromInvoiceTransaction(Integer billNo);
	public Bill getBillByBillId(Integer billId);
	public Item getItemByItemId(String itemId);
	
}
