package com.niit.validation;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidateEmployeeData {
	public static boolean validateEmail(String email)
	{
		Pattern pattern=Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$",Pattern.CASE_INSENSITIVE);
		Matcher matcher=pattern.matcher(email);
		
		if(matcher.find())
			return true;
		else
			return false;
	}
	public static boolean validateContact(String contact)
	{
		Pattern pattern=Pattern.compile("\\d{10}",Pattern.CASE_INSENSITIVE);
		Matcher matcher=pattern.matcher(contact);
		
		if(matcher.find())
			return true;
		else
			return false;
	}

}
