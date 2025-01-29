package com.storyEngine.utils;

import com.storyEngine.Instance;

public class CONFIG 
{
	public final static String ISNEWUSER = "isNewUser";
	public final static String DEFAULTPROJECT = "defaultProject";
	public final static String DEFAULTPROJECTPATH = "defaultProjectPath";
	public final static String DEFAULTGENERIC = "default";
	public final static String DEFAULTSCENEPATH = "defaultScenePath";
	public final static String DEFAULTSCENE = "defaultScene";
	public final static String NULL = "null";
	
	
	
	
	public final static String CONFIGFILEDEFAULTS = "isNewUser: true\r\n"
			+ "defaultProject: null\r\n"
			+ "defaultScene: null\r\n"
			+ "defaultProjectPath: default\r\n"
			+ "defaultScenePath: default";
	
	public static String GETSCENEORDER(String project)
	{
		return "***SCENEORDER***" + project;
		
	}
	
	public static String GETSCENEORDER()
	{
		return "***SCENEORDER***" + Instance.Config.getValue(DEFAULTPROJECT);
		
	}
	
}
