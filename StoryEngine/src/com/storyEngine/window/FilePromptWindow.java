package com.storyEngine.window;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;

import javax.swing.JPanel;
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
	JPanel panel;
	
	public FilePromptWindow(PromptStyle style)
	{
		
		super(style.title);
		this.setBounds(500, 300, 100, 30);
		panel = new JPanel();
		panel.setLayout(new FlowLayout());
		
	
		
		this.setVisible(true);
        setResizable(false);
        setVisible(true);

		this.setDefaultCloseOperation(style.closeOperation);
        add(panel, BorderLayout.CENTER);
        setUIElements(style);
        
        pack();
        revalidate();
        
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
	
	private void setUIElements(PromptStyle style)
	{
		field = new FileLocationTextField(this);
		
		panel.add(field.textField);
		panel.add(field.fileSelectButton);
		panel.add(field.selectButton);
		

	}

	@Override
	public void recivePrompt(String s, FilePromptType type) {
		
		
		if(type == FilePromptType.DIRECTORY)
		{
			System.out.println(s);
			Instance.Config.setValue(CONFIG.DEFAULTPROJECT, s.substring(s.lastIndexOf(File.separator)+1, s.length()));
			Instance.Config.setValue(CONFIG.DEFAULTPROJECTPATH, s);
			Instance.Config.setValue(CONFIG.ISNEWUSER, false);
			Instance.LoadProject(s);
			
		} else if(type == FilePromptType.NAME )
		{
			System.out.print("Second Check");
			Instance.Config.setValue(CONFIG.DEFAULTPROJECT, s);
			Instance.Config.setValue(CONFIG.DEFAULTPROJECTPATH, CONFIG.DEFAULTGENERIC);
			Instance.Config.setValue(CONFIG.ISNEWUSER, false);
			Instance.CreateProject(s);
			if(Instance.LoadProject(s))
			{
				dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
			}
		}
		
		
		
	}
	
	
	
	
	
	

}
