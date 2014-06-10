package com.ag.domain.util;

import java.util.UUID;

public class TokenUtil {
	
	public static String createRandomToken(){
		return UUID.randomUUID().toString().replaceAll("-", "");
	}
}
