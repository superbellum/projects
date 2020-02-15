package com.luv2code.springsecurity.demo.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.luv2code.springsecurity.demo.entity.User;

@Repository
public class UserDaoImpl implements UserDao
{
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public User findByUserName(String theUserName)
	{
		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();

		// now retrieve/read from database using username
		Query<User> theQuery = currentSession.createQuery("from User where userName=:uName", User.class);
		theQuery.setParameter("uName", theUserName);

		User theUser;

		try
		{
			theUser = theQuery.getSingleResult();
		}
		catch (Exception e)
		{
			theUser = null;
		}

		return theUser;
	}

	@Override
	public void save(User user)
	{
		// get current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();

		currentSession.saveOrUpdate(user);
	}

}
