package com.demo.spring_web.service.impl;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.demo.spring_web.config.MyUser;
import com.demo.spring_web.config.UrlGrantedAuthority;
import com.demo.spring_web.model.Privilege;
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
//使用spring security自定义的UserDetails
//		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
//				true, true, true, true, getGrantedAuthorities(user));
		return new MyUser(user, getGrantedAuthorities(user));
	}
//使用spring security默认的authority	
//	private List<GrantedAuthority> getGrantedAuthorities(User user){
//        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
////        authorities.add(new SimpleGrantedAuthority("ROLE_"+"USER"));
//        for(Role role : user.getRoles()){
//            System.out.println("Role : " + role);
//            authorities.add(new SimpleGrantedAuthority("ROLE_"+role.getRole_name()));
//        }
//        System.out.print("authorities :"+authorities);
//        return authorities;
//    }
	//使用自定义authorities
	private Set<GrantedAuthority> getGrantedAuthorities(User user){
        Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
//        authorities.add(new SimpleGrantedAuthority("ROLE_"+"USER"));
        for(Role role : user.getRoles()){
            System.out.println("Role : " + role);
            for(Privilege privilege : role.getPrivileges()){
            	authorities.add(new UrlGrantedAuthority(privilege.getMethod(),privilege.getUrl()));
            }
            
        }
        System.out.println("authorities :"+authorities);
        return authorities;
    }

}
