package com.storyEngine.utils;

import java.io.FileNotFoundException;
import java.lang.reflect.Array;
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
			System.out.println(line);
			String[] values = FileTools.LineVariableSplitter(line);
			map.set(values[0], FileTools.TypeFromString(values[1]));
			System.out.print(map);
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
		System.out.println(map.get(key));
	}
	
	public T get(String key)
	{
		return map.get(key);
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
