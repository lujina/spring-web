package com.demo.spring_web.service;
import com.demo.spring_web.model.User;

public interface IUserService {
	public boolean registe(User user);
	public boolean login(String username, String password);
	public User findByName(String username);
	public boolean update(User user);
}
