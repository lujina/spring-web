package com.demo.spring_web.service.impl;

import com.demo.spring_web.dao.IGeneralDao;
import com.demo.spring_web.model.User;
import com.demo.spring_web.service.IUserService;

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

}
