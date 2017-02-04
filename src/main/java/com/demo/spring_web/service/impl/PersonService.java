package com.demo.spring_web.service.impl;

import com.demo.spring_web.dao.IPersonDao;
import com.demo.spring_web.service.IPersonService;

public class PersonService implements IPersonService {
	private IPersonDao personDao;

	public void setPersonDao(IPersonDao personDao) {
		this.personDao = personDao;
	}

	@Override
	public void processSave() {
		// TODO Auto-generated method stub
		System.out.println("-------------from PersonService.processSave()");        
	}

}
