package com.model;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name = "invoice")
@Table(name = "invoice")
public class Bill {

	@Id()
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer billNo;
	private Integer uid;
	private Date billdate;
	
	public Bill() {
		// TODO Auto-generated constructor stub
	}
	
	public Bill(Integer billNo, Integer uid, Date date) {
		this.billNo = billNo;
		this.billdate = date;
		this.uid = uid;
	}
	
	public Integer getBillNo() {
		return billNo;
	}
	
	public void setBillNo(Integer billNo) {
		this.billNo = billNo;
	}
	
	public Integer getUid() {
		return uid;
	}
	
	public void setUid(Integer uid) {
		this.uid = uid;
	}
	
	public Date getDate() {
		return billdate;
	}
	
	public void setDate(Date date) {
		this.billdate = date;
	}

	@Override
	public String toString() {
		return "InvoiceDTO [billNo=" + billNo + ", uid=" + uid + ", date=" + billdate + "]";
	}
	
}
