package com.gl.weave;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.*;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;

import com.gl.weave.dao.SysUserMapper;
import com.gl.weave.model.SysUser;

@Configuration
@EnableWebSecurity

public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		.authorizeRequests()
		.antMatchers("/templates").permitAll()
		.anyRequest().authenticated()
		.and()
		.formLogin()
		//.loginPage("/login").permitAll()
		.and()
		.logout().permitAll()
		.and()
        .csrf().disable();
	}
	
	/*@Bean
    public UserDetailsService userDetailsService() {
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        manager.createUser(User.withDefaultPasswordEncoder().username("admin").password("123").roles("USER").build());
        return manager;
    }*/

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication().passwordEncoder(new BCryptPasswordEncoder()).withUser("admin").password(new BCryptPasswordEncoder().encode("123")).roles("USER");
	}
	
	/*@Autowired
	private SysUserMapper sysUserMapper;
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		List<SysUser> list=sysUserMapper.selectAll();
    	for (SysUser sysUser : list) {
		auth.inMemoryAuthentication().passwordEncoder(new BCryptPasswordEncoder()).withUser(sysUser.getLoginName()).password(new BCryptPasswordEncoder().encode(sysUser.getPassword())).roles(sysUser.getUserName());
    		}
    }*/
	
}