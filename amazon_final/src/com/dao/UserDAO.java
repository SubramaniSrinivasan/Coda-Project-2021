package com.dao;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Property;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.model.User;

@Repository
public class UserDAO extends AbstractUserDAO {
	
	@Autowired
	SessionFactory sessionFactory;
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@Override
	public void createUser(User user) {
		
		Session session = sessionFactory.getCurrentSession();
		
		session.persist(user);	
		
	}
	
	@Override
	public void updateUser(User user) {
		
		Session session = sessionFactory.getCurrentSession();
		
		User oldUser = findUserByUid(user.getUid());
		
		oldUser.setUname(user.getUname());
		oldUser.setUpass(user.getUpass());
		oldUser.setFlag(user.getFlag());
		
		session.persist(user);
		
	}
	
	@Override
	public User findUserByUid(Integer uid) {
		
		Session session = sessionFactory.getCurrentSession();
		
		Criteria criteria = session.createCriteria(User.class);
		criteria.add(Property.forName("uid").eq(uid));
		User user = (User)criteria.uniqueResult();
		
		return user;
		
	}
	
	@Override
	public User findUserByUname(String uname) {
		
		Session session = sessionFactory.getCurrentSession();
		
		Criteria criteria = session.createCriteria(User.class);
		criteria.add(Property.forName("uname").eq(uname));
		User user = (User)criteria.uniqueResult();
		
		return user;
		
	}
	
}
