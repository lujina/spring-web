package com.demo.spring_web.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.demo.spring_web.dao.IPersonDao;
import com.demo.spring_web.model.PersonEntity;

public class PersonDao implements IPersonDao {
	private SessionFactory sessionFactory;
	public void setSessionFactory(SessionFactory sessionFactory){
		this.sessionFactory = sessionFactory;
	}

	public void save(PersonEntity p) {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		session.persist(p);
		tx.commit();
		session.close();
		System.out.println("------------from PersonDao.save()"); 
	}

	public List<PersonEntity> list(){
		Session session = this.sessionFactory.openSession();
		List<PersonEntity> personList = session.createQuery("from Person").list();
		session.close();
		return personList;
	}

}
