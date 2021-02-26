package com.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "invoicetransaction")
public class InvoiceTransaction implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id()
	private Integer billno;
	@Id()
	private String ItemID;
	
	public InvoiceTransaction() {
		// TODO Auto-generated constructor stub
	}
	
	public InvoiceTransaction(Integer billno, String ItemID) {
		this.billno = billno;
		this.ItemID = ItemID;
	}
	
	public Integer getBillno() {
		return billno;
	}
	
	public void setBillno(Integer billno) {
		this.billno = billno;
	}
	
	public String getItemID() {
		return ItemID;
	}
	
	public void setItemID(String itemID) {
		ItemID = itemID;
	}

	@Override
	public String toString() {
		return "InvoiceTxnDTO [billno=" + billno + ", ItemID=" + ItemID + "]";
	}
	
}
