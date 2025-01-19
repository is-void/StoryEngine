package com.storyEngine;

import com.storyEngine.utils.CONFIG;
import com.storyEngine.window.PromptStyle;
import com.storyEngine.window.FilePromptWindow;

public class Launcher {

	
	public void run() 
	{
		
		if((boolean)Instance.Config.getValue(CONFIG.ISNEWUSER) == true)
		{
			new FilePromptWindow(new PromptStyle("Create New Project ", 400, 400, javax.swing.WindowConstants.DISPOSE_ON_CLOSE, "File Location", 400, 400));
		} else
		{
			LoadProject((String) Instance.Config.getValue(CONFIG.DEFAULTPROJECT));
		}
		

	}
	
	public void LoadProject(String project)
	{
		Instance.LoadProject(project);
	}

}
