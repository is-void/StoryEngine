package com.storyEngine.utils;

public class Debug 
{
	static int debugLine = 0;
	public static void Log(String s, Class<?> c)
	{
		System.out.println("[" + debugLine + "][" + c.getName() + "]: " + s);
		debugLine++;
		
	}
}
