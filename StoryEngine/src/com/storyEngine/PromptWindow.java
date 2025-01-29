package com.storyEngine;

import javax.swing.JFrame;
import javax.swing.JPanel;
import com.storyEngine.window.PromptStyle;

public abstract class PromptWindow extends JFrame
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected JPanel panel;
	public abstract void recievePrompt(String s, Object prompt);
	
	public PromptWindow(PromptStyle style)
	{
		super(style.title); //Creates a new window with the specified title
		this.setBounds(500, 300, 400, 100);	//Sets the bounds of the object	
		
		this.setVisible(true); //intial set up
        setResizable(false);

		this.setDefaultCloseOperation(style.closeOperation);
        
        revalidate();
	}
	//I want to check if there is already a project with the specified name and if there is don't allow the user to create another project.
	
	public abstract void windowSetup();




}
