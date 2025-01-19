package com.storyEngine;

import java.io.File;
import java.util.Scanner;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

import com.storyEngine.scene.Scene;
import com.storyEngine.utils.CONFIG;
import com.storyEngine.utils.VariableListFromFile;
import com.storyEngine.window.scene.SceneWindow;

public class Instance 
{

	public static VariableListFromFile Config;
	public static Launcher launcher;
	public static final String projectOrigin = Instance.class.getProtectionDomain().getCodeSource().getLocation().getPath().substring(0, Instance.class.getProtectionDomain().getCodeSource().getLocation().getPath().length() - 4);
	public static String configOrigin;
	public static final String binPath = Instance.class.getProtectionDomain().getCodeSource().getLocation().getPath();
	Scanner reader = new Scanner("config.info");
	public static void main(String[] args) 
	{
		
		 
		String p = projectOrigin;
		p = p + "config.info";
		configOrigin = p;
		System.out.println(p);
		Config = new VariableListFromFile(p); 
		launcher = new Launcher();
		launcher.run();
		
	}
	
	public static void CreateProject(String name)
	{
		File directory = new File(projectOrigin + "Projects" + File.separator + name);
		File projectSettings = new File(projectOrigin + "Projects" + File.separator + name + File.separator + "projectsettings.dat");
		System.out.println(directory);
		System.out.println(projectOrigin + "\n" +new File(projectOrigin).exists());
		Config.setValue(CONFIG.DEFAULTPROJECTPATH, projectOrigin + "Projects");
		boolean directoryCreated = directory.mkdir();
		  
        if (directoryCreated) { 
            System.out.println("Directory created successfully at: " + directory); 
            Instance.CreateSceneDirectory(name);
            try {
				projectSettings.createNewFile();
				
				LoadProject(name);
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        } else { 
            System.out.println("Failed to create directory. It may already exist at: " + directory); 
        } 
        
		
	}
	
	public static boolean LoadProject(String projectName)
	{
		/*
		File project = new File(projectOrigin + "Projects" + File.separator + projectName);
		return project.exists();
		*/
		try {
			Instance.SaveConfig();
			Instance.LoadConfig();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		System.out.println((String)Instance.Config.getValue(CONFIG.DEFAULTSCENE));
		Scene.initMap(projectOrigin + "Projects" + File.separator + projectName + File.separator + "Scenes");
		if(!((String)Instance.Config.getValue(CONFIG.DEFAULTSCENE)).equals(CONFIG.NULL))
		{
			new SceneWindow((String)Instance.Config.getValue(CONFIG.DEFAULTSCENE), (String) Instance.Config.getValue(CONFIG.DEFAULTSCENEPATH));
		} else if(((String)Instance.Config.getValue(CONFIG.DEFAULTSCENE)).equals(CONFIG.NULL))
		{
			new SceneWindow(projectName, "no scene");
		}
		
		return true;
	}
	
	public static void SaveConfig() throws IOException
	{
		
		
		try
		{
			File config = new File(projectOrigin+ "config.info");
			if(config.delete()) config.createNewFile();
			
			try (Writer writer = new FileWriter(config)) {
				writer.write(Instance.Config.toString());
			}
		} catch(Exception e)
		{
			e.printStackTrace();
			System.out.print("failed to update config file");
		}
		
			
		
	}
	
	public static void LoadConfig()
	{
		Config = new VariableListFromFile(configOrigin);
	}
	
	
	private static void CreateSceneDirectory(String name)
	{
		File scenes = new File(projectOrigin + "Projects" + File.separator + name + File.separator + "Scenes");
		Scene.rootDirectory = scenes.getPath();
		Instance.Config.setValue(CONFIG.DEFAULTSCENEPATH, scenes.getPath());
		scenes.mkdir();
	}
	

}
