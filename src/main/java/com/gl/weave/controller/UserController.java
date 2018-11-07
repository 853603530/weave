package com.gl.weave.controller;

import java.util.HashMap;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserController {
	
	@RequestMapping(method=RequestMethod.GET,path="/login11")
	@ResponseBody
	public Object login(String username,String password) {
		HashMap<Object, Object> map=new HashMap<>();
		map.put("success", username+"_"+password);
		return map;
	}
	
	
	@RequestMapping("/word")
	public String index(Model model){
	    return "home";
	}

	@RequestMapping(value="/login",method = RequestMethod.GET)
	public String login(){
	    return "login";
	}
}
