package com.dao;

import java.util.List;

import com.model.Item;

public abstract class AbstractInvoiceTransactionDAO {

	public abstract void insertItem(Integer billNo, String ItemId);
	public abstract List<String> getAllItems(Integer BillNo);
	public abstract Item getItemByItemId(String itemId);
	
}
