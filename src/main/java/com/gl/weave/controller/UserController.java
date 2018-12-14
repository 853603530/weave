package com.gl.weave.controller;



import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;



import com.gl.weave.dao.SysRoleMapper;
import com.gl.weave.dao.SysUserMapper;
import com.gl.weave.model.SysUser;


@Controller
public class UserController {
	private static final Logger log = LoggerFactory.getLogger(UserController.class);
	@Autowired
	private SysUserMapper sysUserMapper;
	@Autowired
	private SysRoleMapper sysRoleMapper;
    @RequestMapping("/")
    public String index() {
        return "/page/index";
    }

    @RequestMapping("/hello")
    public String hello() {

    	List<SysUser> list=sysUserMapper.selectAll();
    	sysRoleMapper.selectByPrimaryKey((long)1);
    	System.out.println(list.toString());
        return "/page/hello";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {
        return "/page/login";
    }
}
