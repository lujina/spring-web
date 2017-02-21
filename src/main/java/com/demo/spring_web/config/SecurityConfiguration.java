package com.demo.spring_web.config;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.vote.AffirmativeBased;
import org.springframework.security.access.vote.AuthenticatedVoter;
import org.springframework.security.access.vote.RoleVoter;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

/**
 * spring安全配置，使用java而不是xml进行配置。
 * 完成用户登陆控制、remember-me，以及权限控制功能。
 * 
 * @author Administrator
 *
 */
@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	@Autowired
	@Qualifier("CustomUserDetailsService")
	UserDetailsService userDetailsService;

	@Autowired
	PersistentTokenRepository tokenRepository;

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService);
		// auth.authenticationProvider(authenticationProvider());
	}
	
	@Override  
    public void configure(WebSecurity web) throws Exception {  
        // 设置不拦截规则  
        web.ignoring().antMatchers("/res/**","/register.html","/back/registe","/login.html"
        		,"/login/error","/login/success","/403.html");  
  
    }  

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		//添加自定义url过滤器
		http.addFilterBefore(urlFilterSecurityInterceptor(),FilterSecurityInterceptor.class);
		//使用自定义voter
		http.authorizeRequests()
				.anyRequest().authenticated().and().exceptionHandling()
				.accessDeniedPage("/Access_Denied");
//		http.authorizeRequests().antMatchers("/res/**", "/back/**", "/register.html").permitAll()
//				.antMatchers("/mailbox.html").access("hasRole('ADMIN')")
//				.anyRequest().hasRole("USER").anyRequest().authenticated().and().exceptionHandling()
//				.accessDeniedPage("/Access_Denied");
		http.formLogin().loginPage("/login.html").loginProcessingUrl("/login").defaultSuccessUrl("/login/success", true)
				.failureUrl("/login/error").and().rememberMe().rememberMeParameter("remember_me")
				.tokenRepository(tokenRepository).tokenValiditySeconds(86400).and().logout()
				.logoutRequestMatcher(new AntPathRequestMatcher("/logout", "GET")).logoutSuccessUrl("/login.html")
				.permitAll();
		http.csrf().ignoringAntMatchers("/login");
		

	}
	/*
	 * 可以使用BCrypt对密码进行加密，但前提需要你保存在数据库的密码是经过BCrypt加密的
	 */
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
		authenticationProvider.setUserDetailsService(userDetailsService);
		authenticationProvider.setPasswordEncoder(passwordEncoder());
		return authenticationProvider;
	}

	// @Bean
	// public PersistentTokenBasedRememberMeServices
	// getPersistentTokenBasedRememberMeServices() {
	// PersistentTokenBasedRememberMeServices tokenBasedservice = new
	// PersistentTokenBasedRememberMeServices(
	// "remember-me", userDetailsService, tokenRepository);
	// return tokenBasedservice;
	// }

	// @Bean
	// public AuthenticationTrustResolver getAuthenticationTrustResolver() {
	// return new AuthenticationTrustResolverImpl();
	// }
	
	
	/*
	 * 增加自定义UrlMatchVoter投票器
	 */
	@Bean(name = "accessDecisionManager")
	public AffirmativeBased accessDecisionManager() {
		List<AccessDecisionVoter<? extends Object>> decisionVoters = new ArrayList<>();
	        
	    decisionVoters.add(new AuthenticatedVoter());
	    decisionVoters.add(new RoleVoter());
	    decisionVoters.add(new UrlMatchVoter());

	    AffirmativeBased affirmativeBased = new AffirmativeBased(decisionVoters);
	    return affirmativeBased;
	}
	
	@Bean
	public FilterSecurityInterceptor urlFilterSecurityInterceptor(){
		FilterSecurityInterceptor urlFilter = new FilterSecurityInterceptor();
		urlFilter.setSecurityMetadataSource(new UrlFilterInvocationSecurityMetadataSource());
		urlFilter.setAccessDecisionManager(accessDecisionManager());
		return urlFilter;
	}

}