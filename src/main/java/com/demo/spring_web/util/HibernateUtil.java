package com.demo.spring_web.util;

import org.apache.log4j.Logger;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
	//查查ThreadLocal
	public static final ThreadLocal<Session> SESSIONMAP = new ThreadLocal<Session>();
	private static final SessionFactory sessionFactory;
	private static final Logger LOGGER = Logger.getLogger(Hibernate.class);
	static{
		try{
			LOGGER.debug("HibernateUtil.static - loading config");
			sessionFactory = new Configuration().configure("hibernate.cfg.xml")
					.buildSessionFactory();
			LOGGER.debug("HibernateUtil.static - end");
		}catch(Throwable ex){
			ex.printStackTrace();
			LOGGER.error("HibernateUtil err : ExceptionInInitializerError");
			throw new ExceptionInInitializerError(ex);
		}
	}
	//单例模式
	private HibernateUtil(){
		
	}
	public static Session getSession() throws HibernateException{
		Session session = SESSIONMAP.get();
		if(session == null){
			session = sessionFactory.openSession();
			SESSIONMAP.set(session);
		}
		return session;
	}
	public static void closeSession() throws HibernateException{
		Session session = SESSIONMAP.get();
		SESSIONMAP.set(null);
		if(session != null){
			session.close();
		}
		
	}

}
