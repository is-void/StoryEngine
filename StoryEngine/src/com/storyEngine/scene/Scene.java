package com.storyEngine.scene;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Scanner;

import javax.swing.filechooser.FileSystemView;

import com.storyEngine.Instance;
import com.storyEngine.utils.CONFIG;
import com.storyEngine.utils.FileTools;

public class Scene 
{
	public static HashMap<String, Scene> scenes = new HashMap<String, Scene>();
	public static String rootDirectory = null;
	File sceneFile;
	String name;
	String text = "";
	int order;
	
	public Scene(String name, String sceneDirectory)
	{
		sceneFile = new File(sceneDirectory + File.separator + name + ".SCENE");
		this.name = name;
		while(scenes.containsKey(this.name))
		{
			System.out.print("conflict, so name will be changed");
			this.name = this.name + "_New";
		}
		scenes.put(this.name, this);
		
		if(sceneFile.exists())
		{
			System.out.println("File exists: " + sceneFile.exists());
			System.out.println("File readable: " + sceneFile.canRead());
			System.out.println("File size: " + sceneFile.length() + " bytes");
			System.out.println("File absolute path: " + sceneFile.getAbsolutePath());
			try {
				BufferedReader br = new BufferedReader(new FileReader(sceneFile));

				String line;
				while(( line = br.readLine())!=null)
				{
					text += line + "\n";
					System.out.println("try" + text);
				}
				text = text.trim();
					
				br.close();
			
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else
		{
			try {
				sceneFile.createNewFile();
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				
				e.printStackTrace();
			}
		}
		
		
		
	}
	public static void initMap(String sceneDirectory)
	{
		File sceneFile = new File(sceneDirectory);
		for(File f : sceneFile.listFiles())
		{
			System.out.println("\n\n"+f + "\n\n");
			Scene s = new Scene(FileTools.TrimExtension(f.getName()), sceneDirectory);
			scenes.put(s.name, s);
		}
	}
	public void setText(String string)
	{
		this.text = string;
	}
	
	public String getText()
	{
		return text;
	}
	public String getName()
	{
		return name;
	}
	
	public void setName(String name)
	{
		scenes.remove(this.name);
		this.name = name;
		while(scenes.containsKey(this.name))
		{
			System.out.print("conflict, so name will be changed");
			this.name = this.name + "_New";
		}
		scenes.put(this.name, this);
		sceneFile.renameTo(new File(rootDirectory + File.pathSeparator + name + ".SCENE"));
		
		
	}
	
	
	public File getFile()
	{
		return sceneFile;
	}
	
	public void save()
	{
		
		
		try {
			FileWriter writer;
			if(sceneFile.delete())
				sceneFile.createNewFile();
			
			writer = new FileWriter(this.sceneFile);
			writer.write(text);
			Instance.Config.setValue(CONFIG.DEFAULTSCENE, name);
			Instance.SaveConfig();
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Failed to save scene");
		}
		System.out.println("Saved at " + sceneFile.getAbsolutePath());
		
		
	}

}

