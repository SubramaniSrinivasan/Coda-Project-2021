package com.dao;

import java.sql.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Property;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.model.Bill;

@Repository
public class BillDAO extends AbstractBillDAO {

	@Autowired
	SessionFactory sessionFactory;
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@Override
	public Integer createBillAndGetBillNumber(int UserId, Date date) {
		
		Session session = sessionFactory.getCurrentSession();
		
		Bill bill = new Bill(null, UserId, date);
		session.persist(bill);
		
		return bill.getBillNo();
		
	}
	
	@Override
	public Bill getBillByBillId(Integer billNo) {
		
		Session session = sessionFactory.getCurrentSession();
		
		Criteria criteria = session.createCriteria(Bill.class);
		criteria.add(Property.forName("billNo").eq(billNo));
		Bill bill = (Bill)criteria.uniqueResult();

		return bill;
		
	}
	
}
