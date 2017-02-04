package com.demo.spring_web.model;

import java.util.EnumSet;
import java.util.List;

import org.hibernate.Session;  
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.hibernate.tool.schema.TargetType;
import org.junit.Assert;
import org.junit.Test;

import com.demo.spring_web.model.PersonEntity;
import com.demo.spring_web.util.HibernateUtil;

public class ModelTest {
	@Test  
    public void testGetSession() {  
        Session session = HibernateUtil.getSession();  
          
        Assert.assertNotNull(session);  
          
        HibernateUtil.closeSession();  
    }  
      
    @Test  
    public void testExport() {  
    	  ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().configure().build();
    	  //创建Metadata对象
    	  Metadata metadata =new MetadataSources(serviceRegistry).buildMetadata();
    	  
    	  //创建SchemaExport对象
    	  SchemaExport export = new SchemaExport();  
    	  export.create(EnumSet.of(TargetType.DATABASE),metadata);   
   
    }  
      
    @Test  
    public void testSave() {  
        PersonEntity person = new PersonEntity();  
        person.setId(100);  
        person.setName("路飞");  
          
        Session session = HibernateUtil.getSession();  
        Transaction tx = session.beginTransaction();  
          
        session.save(person);  
          
        tx.commit();  
        HibernateUtil.closeSession();  
    }  
      
    @Test  
    public void testQuery() {  
        Session session = HibernateUtil.getSession();  
        session.beginTransaction();  
          
        @SuppressWarnings("unchecked")  
        List<PersonEntity> personList = session.createQuery("select p from PersonEntity p").list();  
          
        for(PersonEntity eachPerson : personList) {  
            System.out.println(eachPerson);  
        }  
          
        session.getTransaction().commit();  
        HibernateUtil.closeSession();  
    }  


}
