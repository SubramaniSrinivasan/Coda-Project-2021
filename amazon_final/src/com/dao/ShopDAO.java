package com.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Property;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.model.Item;
import com.model.Shop;

@Repository
public class ShopDAO extends AbstractShopDAO {

	@Autowired
	SessionFactory sessionFactory;
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@Override
	public Shop getShopByShopId(String shopId) {
		
		Session session = sessionFactory.getCurrentSession();
		
		Criteria criteria = session.createCriteria(Shop.class);
		criteria.add(Property.forName("shopID").eq(shopId));
		Shop shop = (Shop)criteria.uniqueResult();
		
		return shop;
		
	}
	
	@Override
	public List<Item> getAllItemOfShop(String shopId) {
		
		Session session = sessionFactory.getCurrentSession();
		
		Criteria criteria=session.createCriteria(Item.class);
		criteria.add(Property.forName("shopID").eq(shopId));
		List<Item> items = criteria.list();
		
		return items;
		
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
