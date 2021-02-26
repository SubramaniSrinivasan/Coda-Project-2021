package com.dao;

import java.sql.Date;

import com.model.Bill;

public abstract class AbstractBillDAO {

	public abstract Integer createBillAndGetBillNumber(int UserId, Date date);
	public abstract Bill getBillByBillId(Integer billNo);
	
}
