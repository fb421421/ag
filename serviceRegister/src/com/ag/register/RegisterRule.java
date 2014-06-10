package com.ag.register;

import org.picketbox.util.StringUtil;

import com.ag.domain.exception.InvalidUserNameFormatException;
import com.ag.domain.exception.NullUserNameException;


public class RegisterRule {
	
	public static String USER_NAME_REGEX="^[0-9a-zA-Z_]{6,12}$";

	public static void validteUserName( String userName ) throws InvalidUserNameFormatException, NullUserNameException{
		
		if(StringUtil.isNullOrEmpty(userName)){
			throw new NullUserNameException();
		}
		
		if(!userName.matches(USER_NAME_REGEX)){
			throw new InvalidUserNameFormatException();
		}
	}
}
