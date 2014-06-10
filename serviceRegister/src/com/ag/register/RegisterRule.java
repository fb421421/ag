package com.ag.register;

import org.picketbox.util.StringUtil;

import com.ag.register.exception.InvalidUserNameFormatException;

public class RegisterRule {
	
	public static String USER_NAME_REGEX="^[0-9a-zA-Z_]{6,12}$";

	public static void validteUserName( String userName ) throws InvalidUserNameFormatException{
		
		if(StringUtil.isNullOrEmpty(userName)){
			throw new InvalidUserNameFormatException();
		}
		
		if(!userName.matches(USER_NAME_REGEX)){
			throw new InvalidUserNameFormatException();
		}
	}
}
