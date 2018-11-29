package com.gl.weave.controller;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.gl.weave.dao.SysUserMapper;
import com.gl.weave.model.SysUser;


@Controller
public class UserController {
	@Autowired
	private SysUserMapper sysUserMapper;
    @RequestMapping("/")
    public String index() {
        return "index";
    }

    @RequestMapping("/hello")
    public String hello() {
        return "hello";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {
    	List<SysUser> list=sysUserMapper.selectAll();
    	System.out.println(list.toString());
        return "login";
    }
}
