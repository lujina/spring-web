package com.demo.spring_web.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.demo.spring_web.model.Role;
import com.demo.spring_web.model.User;
import com.demo.spring_web.service.IUserService;
/**
 * 实现spring security中的UserDetailsService。用于spring安全配置，例如登陆拦截和权限管理
 * @author Administrator
 *
 */
@Service("CustomUserDetailsService")
public class CustomUserDetailsService implements UserDetailsService {
	@Autowired
	private IUserService userService;
	
	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		User user = userService.findByName(username);
		System.out.println("User : " + user);
		if (user == null) {
			System.out.println("User not found");
			throw new UsernameNotFoundException("Username not found");
		}
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
				true, true, true, true, getGrantedAuthorities(user));
	}
	
	private List<GrantedAuthority> getGrantedAuthorities(User user){
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
//        authorities.add(new SimpleGrantedAuthority("ROLE_"+"USER"));
        for(Role role : user.getRoles()){
            System.out.println("Role : " + role);
            authorities.add(new SimpleGrantedAuthority("ROLE_"+role.getRole_name()));
        }
        System.out.print("authorities :"+authorities);
        return authorities;
    }

}
