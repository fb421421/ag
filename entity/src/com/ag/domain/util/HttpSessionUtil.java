package com.ag.domain.util;

import javax.servlet.http.HttpServletRequest;

public class HttpSessionUtil {
	
	static public Object getAttribute(HttpServletRequest request , String name ){
		return request.getSession(true).getAttribute(name);
	}
	
	static public void setAttribute( HttpServletRequest request , String name , Object value){
		request.getSession(true).setAttribute(name,value);
	}
}
