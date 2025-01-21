package com.storyEngine.window;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;

import com.storyEngine.Instance;
import com.storyEngine.PromptWindow;
import com.storyEngine.utils.CONFIG;
import com.storyEngine.window.gui.FileLocationTextField;

@SuppressWarnings("serial")
public class FilePromptWindow extends PromptWindow 
{
	public enum FilePromptType
	{
		NAME,
		DIRECTORY,
		ERROR
	}

	FileLocationTextField field;
	
	public FilePromptWindow(PromptStyle style)
	{
		super(style); //Sets the dimensions and basic behaviour of the program
		
		field = new FileLocationTextField(this);
        windowSetup();
        //pack();
        this.addWindowListener(new java.awt.event.WindowAdapter() {
    	    @Override
    	    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
    	    	System.out.print("Check");
    	        try {
					Instance.SaveConfig();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
    	    }
    	});
	}
	
	public void windowSetup()
	{
		FlowLayout f = new FlowLayout();
		this.setLayout(f);
		
		add(field.textField);
		add(field.fileSelectButton);
		add(field.selectButton);
		revalidate();
		repaint();
	}
	
	

	@Override
	public void recivePrompt(String s, Object t) {
		FilePromptType type = (FilePromptType)t;
		
		if(type == FilePromptType.DIRECTORY)
		{
			System.out.println(s);
			Instance.Config.setValue(CONFIG.DEFAULTPROJECT, s.substring(s.lastIndexOf(File.separator)+1, s.length()));
			Instance.Config.setValue(CONFIG.DEFAULTPROJECTPATH, s);
			Instance.Config.setValue(CONFIG.ISNEWUSER, false);
			dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSED));
			try {
				Instance.SaveConfig();
				Instance.LoadProject(s);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		} else if(type == FilePromptType.NAME )
		{
			System.out.println("\nSecond Check\n");
			Instance.Config.setValue(CONFIG.DEFAULTPROJECT, s);
			Instance.Config.setValue(CONFIG.DEFAULTPROJECTPATH, CONFIG.DEFAULTGENERIC);
			Instance.Config.setValue(CONFIG.ISNEWUSER, false);
			Instance.CreateProject(s);
			if(Instance.LoadProject(s))
			{
				dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSED));
			}
		}
		
		
		
	}
	
	
	
	
	
	

}
