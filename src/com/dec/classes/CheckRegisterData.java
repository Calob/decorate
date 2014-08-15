package com.dec.classes;

import java.text.ParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * check the posted data from register.jsp 
 *
 */
public class CheckRegisterData {
	
	public static boolean isValid(String username, String password, String password2, String email, String identityNumber) throws ParseException
	{	
		boolean isValid = true;

		isValid = isValid(email) && (Identity.checkIDCard(identityNumber));
		System.out.println(Identity.checkIDCard(identityNumber));
        System.out.println(isValid);
		if(username==null || username=="") {
			isValid = false;
		}
		if(!(password.equals(password2))){
			isValid = false;
		}
		if(password.length()<6 || password.length()>13) {
			isValid = false;
		}
	
		return isValid;
	}

	public static boolean isValid(String password, String password2, String email)
	{	
		boolean isValid = true;
		
		isValid = isValid(email);
		if(!(password.equals(password2))){
			isValid = false;
		}
		if(password.length()<6 || password.length()>13) {
			isValid = false;
		}		
		
		return isValid;
	}
	public static boolean isValid(String mail){  
	    String regex = "\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*";  
	    Pattern   p   =   Pattern.compile(regex);  
	    Matcher   m   =   p.matcher(mail);  
	    return m.find();  
	}  
}
