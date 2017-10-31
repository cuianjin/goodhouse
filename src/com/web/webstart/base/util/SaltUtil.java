package com.web.webstart.base.util;


import org.apache.shiro.crypto.hash.Md5Hash;

public class SaltUtil {
	
	public static String addSalt(String uPhone,String pwd){
		return new Md5Hash(uPhone,pwd,2).toString();
	}
}
