package com.gl.weave.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.gl.weave.dao.SysUserMapper;
import com.gl.weave.exception.MyUsernameNotFoundException;
import com.gl.weave.model.SysUser;
@Service("UserService")
public class UserService implements UserDetailsService {

    
    @Autowired
	private SysUserMapper sysUserMapper;
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException  {
        //根据用户名从数据库查询对应记录
    	SysUser sysUser = sysUserMapper.queryByUserName(s);
    	String password=null;
        if (sysUser == null) {
        	throw new MyUsernameNotFoundException("用户名不存在");
        }
        password=sysUser.getPassword();
        List<GrantedAuthority> AUTHORITIES = new ArrayList<GrantedAuthority>();
        AUTHORITIES.add(new SimpleGrantedAuthority("ROLE_USER"));
        User user = new User(sysUser.getLoginName(),password, true, true, true, true, AUTHORITIES);
        //User user = new User(sysUser.getLoginName(),password, (Collection<? extends GrantedAuthority>) au);
        //System.out.println("username is : " + sysUser.getLoginName() + ", password is :" + sysUser.getPassword());
        return user;
    }
    

    
}
