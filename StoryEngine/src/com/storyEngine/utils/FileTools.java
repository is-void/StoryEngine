package com.storyEngine.utils;

import java.util.ArrayList;

public class FileTools 
{
	public static String[] LineVariableSplitter(String str) 
	{
		return str.split(": ");
	}
	
	public static Object TypeFromString(String str)
	{
		if(str == "null")
			return null;
		if(str.charAt(0) == '[' && str.charAt(str.length()-1) == ']')
		{
			ArrayList<String> arr = new ArrayList<String>();
			str = str.substring(1);
			while(str.indexOf(',') != -1)
			{
				arr.add(str.substring(0, str.indexOf(',')));
				str = str.substring(str.indexOf(',') + 2);
				
			}
			
			if(str.indexOf(']') != 0)
			{
				arr.add(str.substring(0, str.length()-1));
			}
			return arr;
		}
		
		switch(str)
		{
			case "true":
				return true;
			case "false":
				return false;
			default:
				
		}
		try {
			return Integer.parseInt(str);
		} catch(NumberFormatException e)
		{
			try{return Double.parseDouble(str);}
			catch(NumberFormatException r)
			{
				return str;
			}
		}
	}
	
	public static String StringFromType(Object obj)
	{
		if(obj == null)
			return "null";
		
		if(obj.getClass() == boolean.class)
			return (boolean)obj == true ? "true": "false";
		return "" + obj.toString();
	}
	
	public static String TrimExtension(String s)
	{
		int pos = s.lastIndexOf(".");
		return pos > 0 ? s.substring(0, pos) : s;
	}
}


