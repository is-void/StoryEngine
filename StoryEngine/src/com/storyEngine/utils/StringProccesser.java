package com.storyEngine.utils;

public class StringProccesser 
{
	public static boolean ContainsAnyOfCharacter(String s, CharSequence c)
	{
		char[] arr = s.toCharArray();
		for(int d = 0; d < c.length(); d++)
		{
			for(int x = 0; x < arr.length; x++)
			{
				 if((int)arr[x] - (int)c.charAt(d) == 0)
				 {
					 return true;
				 }
		
			}
		}
		return false;
	}
}
