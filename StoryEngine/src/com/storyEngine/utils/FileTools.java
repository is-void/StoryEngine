package com.storyEngine.utils;

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


