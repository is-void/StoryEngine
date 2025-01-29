package com.storyEngine.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class VariableListFromFile 
{
	private Scanner scanner;
	private GenericHashMap<Object> map = new GenericHashMap<Object>();
	private String path;
	
	public VariableListFromFile(String path)
	{
		File f = new File(path);
		if(!f.exists())
		{
			try {
				Writer writer = new FileWriter(f);
				writer.write(CONFIG.CONFIGFILEDEFAULTS);
				writer.close();
				f.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		try {
			scanner = new Scanner(new java.io.File(path));
			this.path = path;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		while(scanner.hasNextLine())
		{
			String line = scanner.nextLine();
			Debug.Log(line, getClass());
			String[] values = FileTools.LineVariableSplitter(line);
			map.set(values[0], FileTools.TypeFromString(values[1]));
			Debug.Log(map.toString(), getClass());
		}
		scanner.close();
	}
	
	public GenericHashMap<Object> getMap(){
		return map;
	}
	
	public String getPath()
	{
		return path;
	}
	public Object getValue(String key)
	{
		return map.get(key);
	}
	
	public void setValue(String key, Object value)
	{
		map.set(key, value);
	}
	
	public boolean hasValue(String key)
	{
		return map.hasValue(key);
	}
	
	@Override
	public String toString()
	{
		return map.toString();
	}
	

}

class GenericHashMap<T>
{
	private HashMap<String, T> map = new HashMap<String, T>();
	public void set(String key, T val)
	{  
		System.out.print("Before " + map.get(key) + "   After: ");
		map.put(key, val);
		Debug.Log(map.get(key).toString(), this.getClass());
	}
	
	public T get(String key)
	{
		return map.get(key);
	}
	
	public boolean hasValue(String key)
	{
		return map.containsKey(key);
		
	}
	
	@Override
	public String toString()
	{
		String output = new String();
		Object[] arr = map.keySet().toArray();
		Arrays.sort(arr);
		for(Object s : arr)
		{
			output += s + ": " + FileTools.StringFromType(map.get(s)) + "\n"; 
		}
		output = output.substring(0, output.lastIndexOf("\n"));
		return output;
		
	}
}
