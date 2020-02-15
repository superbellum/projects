package com.emeldi.demo.dao;

import com.emeldi.demo.entity.Role;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class RoleDaoImpl implements RoleDao
{
	private SessionFactory sessionFactory;

	@Autowired
	public RoleDaoImpl(SessionFactory sessionFactory)
	{
		this.sessionFactory = sessionFactory;
	}

	@Override
	public Role findRoleByName(String theRoleName)
	{
		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();

		// now retrieve/read from database using name
		Query<Role> theQuery = currentSession.createQuery("from Role where name=:roleName", Role.class);
		theQuery.setParameter("roleName", theRoleName);
		
		Role theRole;
		
		try
		{
			theRole = theQuery.getSingleResult();
		}
		catch (Exception e)
		{
			theRole = null;
		}
		
		return theRole;
	}
}
