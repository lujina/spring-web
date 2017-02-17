package com.demo.spring_web.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.demo.spring_web.dao.IGeneralDao;
import com.demo.spring_web.model.Role;
import com.demo.spring_web.service.IRoleService;

public class RoleService implements IRoleService {
	@Autowired
	public IGeneralDao generalDao;
	@Override
	@Transactional
	public List<Role> findAll() {
		// TODO Auto-generated method stub
		return (List<Role>) generalDao.findAll(Role.class);
	}
}
