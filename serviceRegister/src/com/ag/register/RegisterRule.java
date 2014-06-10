package com.ag.register;

import javax.servlet.http.HttpServletRequest;

import org.picketbox.util.StringUtil;

import com.ag.domain.exception.InvalidCheckCodeException;
import com.ag.domain.exception.InvalidUserNameFormatException;
import com.ag.domain.exception.NullCheckCodeException;
import com.ag.domain.exception.NullPasswordException;
import com.ag.domain.exception.NullUserNameException;
import com.ag.domain.util.HttpSessionUtil;


public class RegisterRule {
	
	public static String USER_NAME_REGEX="^[0-9a-zA-Z_]{6,12}$";
	
	public static void validteCheckCode( HttpServletRequest request , String checkCode ) throws InvalidCheckCodeException, NullCheckCodeException{
		
		if(StringUtil.isNullOrEmpty(checkCode)){
			throw new NullCheckCodeException();
		}
		
		  if(!checkCode.equals(HttpSessionUtil.getAttribute(request,"checkCode"))){
			  throw new InvalidCheckCodeException();
		  }
	}

	public static void validteUserName( String userName ) throws InvalidUserNameFormatException, NullUserNameException{
		
		if(StringUtil.isNullOrEmpty(userName)){
			throw new NullUserNameException();
		}
		
		if(!userName.matches(USER_NAME_REGEX)){
			throw new InvalidUserNameFormatException();
		}
	}
	
	public static void validtePassword( String password ) throws NullPasswordException{
		
		if(StringUtil.isNullOrEmpty(password)){
			throw new NullPasswordException();
		}
	}
}
