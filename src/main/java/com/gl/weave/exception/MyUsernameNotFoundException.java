package com.gl.weave.exception;

import org.springframework.security.core.AuthenticationException;


public class MyUsernameNotFoundException extends AuthenticationException  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MyUsernameNotFoundException(String msg) {
		super(msg);
		// TODO Auto-generated constructor stub
	}
	public MyUsernameNotFoundException(String msg, Throwable t) {
		super(msg, t);
		// TODO Auto-generated constructor stub
	}


}
