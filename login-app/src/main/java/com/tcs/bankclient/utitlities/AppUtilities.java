package com.tcs.bankclient.utitlities;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Pattern;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tcs.bankclient.model.User;

public class AppUtilities {
	public static String stringEncription(String str)
	{
		int i;
		String result="";
	    for(i=0;i<str.length();i++)
	    {
	        char ch=str.charAt(i);
	        int x=ch;
	        x=x+8;
	        result=result+((char)x);
	        
	    }
	    return result;
	}
	
	public static String stringDecription(String str)
	{
		int i;
		String result="";
	    for(i=0;i<str.length();i++)
	    {
	        char ch=str.charAt(i);
	        int x=ch;
	        x=x-8;
	        result=result+((char)x);
	        
	    }
	    return result;
	}

	public static String jsonToString(Object record) throws Exception {
		String jsonStr="";
		 ObjectMapper Obj = new ObjectMapper(); 
		  
	        try 
	        { 
	            jsonStr = Obj.writeValueAsString(record); 
	        } 
	  
	        catch (IOException e) { 
	           throw new Exception(); 
	        } 
	        return jsonStr;
		
	}

	public static String userEncrypt(String username) {
		
		String userid=username.substring(0,3)+username.substring(username.length()-2)+Integer.toString((int)( Math.random()*100));
		
		return userid;
	}

	public static String validationParameters(User user) throws ParseException
	{
		
		String result="";
				
		
		String s=user.getDob();
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		Date d = sdf.parse(s);
		Calendar c = Calendar.getInstance();
		c.setTime(d);
		int year = c.get(Calendar.YEAR);
		int month = c.get(Calendar.MONTH) + 1;
		int date = c.get(Calendar.DATE);
		LocalDate l1 = LocalDate.of(year, month, date);
		LocalDate now1 = LocalDate.now();
		Period diff1 = Period.between(l1, now1);
		
		if(!(Pattern.matches("^(3[01]|[12][0-9]|0[1-9])-(1[0-2]|0[1-9])-[0-9]{4}$", user.getDob())))
		{
			result=result+"Not a valid date format /";
		}
		if(diff1.getYears()<18) {
			result=result+"User not old enough /";
		}
		if(!(Pattern.matches("^[\\p{L} .'-]+$", user.getUsername())))
		{
			result=result+"Not a Valid User name /";
		}
		if(!(Pattern.matches("((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{6,20})",user.getPassword())))
		{
			result=result+"Not a Valid Password /";
		}
		if(!(Pattern.matches("^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$",user.getEmail())))
		{
			result=result+"Not a Valid Email /";
		}
		if(!(Pattern.matches("\\d{10}",user.getMobile()))) //change
		{
			result=result+"Not a Valid Mobile no /";
		}
		
		if(result.equals(""))
		{
			result="Verification Passed";
		}
		
		return result;
		
	}
	
	public static String validationPassword(String password) throws ParseException
	{
		String errorMessage="Passed";
		if(!(Pattern.matches("((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{6,20})",password)))
		{
			errorMessage="Not a Valid Password /";
		}
		return errorMessage;
	}
}
