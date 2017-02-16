package com.demo.spring_web.service.impl;

import com.demo.spring_web.dao.IGeneralDao;
import com.demo.spring_web.model.User;
import com.demo.spring_web.service.IUserService;


import java.util.ArrayList;

import org.hibernate.Criteria;
import org.springframework.beans.factory.annotation.Autowired;  
import org.springframework.stereotype.Service;  
import org.springframework.transaction.annotation.Transactional;  

@Service
public class UserService implements IUserService {
	@Autowired
	public IGeneralDao generalDao;
	@Override
	/*  
     * 这里要有事务注解，默认readOnly=true,不设置的话会报错。 
     * insert和update操作都要。 
     */  
    @Transactional(readOnly=false)  
	public boolean registe(User user) {
		// TODO Auto-generated method stub
		generalDao.save(user);
		return false;
	}
	
	@Transactional
	public boolean login(String username, String password){
		//count(1)和count(*)在执行上有什么区别，哪个效率会更高
		ArrayList<Object> list = (ArrayList<Object>) generalDao.find("from User u where u.username=? "
				+ "and u.password=?", new String[]{username,password});
		return list.size() > 0;
	}
	
	@Transactional
	public User findByName(String username){
		ArrayList<Object> list = (ArrayList<Object>) generalDao.find("from User u where u.username=? "
				, new String[]{username});
		return (User) list.get(0);
	}
}
