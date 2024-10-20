package com.storyEngine;

import java.io.File;
import java.util.Scanner;

import javax.swing.JFrame;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.Writer;

import com.storyEngine.utils.VariableListFromFile;
import com.storyEngine.window.DocumentMenu;
import com.storyEngine.window.MainDocumentWindow;
import com.storyEngine.window.WindowStyle;

public class Instance 
{

	public static VariableListFromFile Config;
	public static Launcher launcher;
	public static final String projectOrigin = Instance.class.getProtectionDomain().getCodeSource().getLocation().getPath().substring(0, Instance.class.getProtectionDomain().getCodeSource().getLocation().getPath().length() - 4);
	public static final String binPath = Instance.class.getProtectionDomain().getCodeSource().getLocation().getPath();
	Scanner reader = new Scanner("config.info");
	public static void main(String[] args) 
	{
		
		 
		String p = projectOrigin;
		p = p + "config.info";
		System.out.println(p);
		Config = new VariableListFromFile(p + ""); 
		launcher = new Launcher();
		launcher.run();
		
	}
	
	public static void CreateProject(String name)
	{
		File directory = new File(projectOrigin + "Projects" + File.separator + name);
		File chapters = new File(projectOrigin + "Projects" + File.separator + name + File.separator + "Chapters");
		File projectSettings = new File(projectOrigin + "Projects" + File.separator + name + File.separator + "projectsettings.dat");
		System.out.println(directory);
		System.out.println(projectOrigin + "\n" +new File(projectOrigin).exists());
		
		boolean directoryCreated = directory.mkdir();
		  
        if (directoryCreated) { 
            System.out.println("Directory created successfully at: " + directory); 
            chapters.mkdir();
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
		new MainDocumentWindow(projectName);
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
	

}
