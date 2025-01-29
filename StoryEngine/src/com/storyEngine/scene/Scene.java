package com.storyEngine.scene;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import com.storyEngine.Instance;
import com.storyEngine.utils.CONFIG;
import com.storyEngine.utils.Debug;
import com.storyEngine.utils.FileTools;

public class Scene 
{
	public static HashMap<String, Scene> scenes = new HashMap<String, Scene>();
	public static String rootDirectory = null;
	File sceneFile;
	String name;
	String text = "";
	int order;
	
	@SuppressWarnings("unchecked")
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
			Debug.Log("File exists: " + sceneFile.exists(), this.getClass());
			Debug.Log("File readable: " + sceneFile.canRead(), this.getClass());
			Debug.Log("File size: " + sceneFile.length() + " bytes", this.getClass());
			Debug.Log("File absolute path: " + sceneFile.getAbsolutePath(), this.getClass());
			try {
				BufferedReader br = new BufferedReader(new FileReader(sceneFile));

				String line;
				while(( line = br.readLine())!=null)
				{
					text += line + "\n";
					Debug.Log("try" + text, this.getClass());
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
				Debug.Log("Creating a new file at: " + sceneFile.getPath(), this.getClass());
				sceneFile.createNewFile();
				ArrayList<String> vals;
				if(Instance.Config.hasValue(CONFIG.GETSCENEORDER()))
				{
					Object v = (ArrayList<String>)(Instance.Config.getValue(CONFIG.GETSCENEORDER()));
					vals = (ArrayList<String>) v;
				} else
				{
					vals = new ArrayList<String>();
				}
				
				
				
				if(vals == null)
				{
					vals = new ArrayList<String>();
				}
				
				vals.add(name);
				Instance.Config.setValue(CONFIG.GETSCENEORDER(), vals);
				Instance.SaveConfig();
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				
				e.printStackTrace();
			}
		}
		
		
		
	}
	public static void initMap(String sceneDirectory)
	{
		Debug.Log(sceneDirectory, Scene.class);
		
		File sceneFile = new File(sceneDirectory);
		
		if(!sceneFile.exists())
		{
			System.out.print("CHECK");
			sceneFile.mkdir();
		}
			
		
		for(File f : sceneFile.listFiles())
		{
			Debug.Log(f.getName(), Scene.class);
			Debug.Log("\n\n"+f + "\n\n", Scene.class);
			Scene s = new Scene(FileTools.TrimExtension(f.getName()), sceneDirectory);
			scenes.put(s.name, s);
		}
		System.out.print("DONE INITIALIZING SCENES");
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
			Debug.Log("Failed to save scene", this.getClass());
		}
		Debug.Log("Saved at " + sceneFile.getAbsolutePath(), this.getClass());
		
		
	}
	
	public void rename(String newName)
	{
		String entry = Instance.Config.getValue(CONFIG.GETSCENEORDER()).toString();
		String oldName = name;
		setName(newName);
		entry = entry.replace(oldName, newName);
		Instance.Config.setValue(CONFIG.GETSCENEORDER(), entry);
	}
	
	@Override
	public String toString()
	{
		return name;
		
	}

}

