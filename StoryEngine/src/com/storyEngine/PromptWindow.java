package com.storyEngine;

import javax.swing.JFrame;

import com.storyEngine.window.FilePromptWindow.FilePromptType;

public abstract class PromptWindow extends JFrame
{
	public abstract void recivePrompt(String s, FilePromptType type);
	
	public PromptWindow(String title)
	{
		super(title);
	}



}
