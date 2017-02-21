package com.demo.spring_web.config;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.demo.spring_web.model.User;

/**
 * 自定义认证用户实例
 * 
 * @author Administrator
 *
 */
public class MyUser implements UserDetails {
	private final User user;
	private final List<GrantedAuthority> authorities;

	public MyUser(User user, Set<GrantedAuthority> grantedAuthorities) {
		this.user = user;
//		this.authorities = grantedPrivileges.stream().map(it -> {
//			return new UrlGrantedAuthority(it.getMethod(), it.getUrl());
//		}).collect(Collectors.toList());
		this.authorities = grantedAuthorities.stream().collect(Collectors.toList());
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return this.authorities;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return user.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

}
