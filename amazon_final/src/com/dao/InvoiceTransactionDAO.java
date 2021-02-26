package com.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Property;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.model.InvoiceTransaction;
import com.model.Item;
import com.model.User;

@Repository
public class InvoiceTransactionDAO extends AbstractInvoiceTransactionDAO {
	
	@Autowired
	SessionFactory sessionFactory;
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@Override
	public void insertItem(Integer billNo, String ItemId) {
		
		InvoiceTransaction invoiceTransaction = new InvoiceTransaction(billNo, ItemId);
		
		Session session = sessionFactory.getCurrentSession();
		
		session.persist(invoiceTransaction);
		
	}
	
	@Override
	public List<String> getAllItems(Integer billNo) {
		
		Session session = sessionFactory.getCurrentSession();
		
		String hql = "SELECT i.ItemID FROM InvoiceTransaction i where billno= :billNo";
		Query query = session.createQuery(hql);
		query.setParameter("billNo", billNo);
		List<String> itemList = query.list();
		
		return itemList;
		
	}
	
	@Override
	public Item getItemByItemId(String itemId) {
		
		Session session = sessionFactory.getCurrentSession();
		
		Criteria criteria = session.createCriteria(Item.class);
		criteria.add(Property.forName("itemID").eq(itemId));
		Item item = (Item)criteria.uniqueResult();
		
		return item;
		
	}
	
}
