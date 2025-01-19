package com.storyEngine.window.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;

import com.storyEngine.Instance;
import com.storyEngine.PromptType;
import com.storyEngine.utils.StringProccesser;
import com.storyEngine.window.FilePromptWindow;
import com.storyEngine.window.FilePromptWindow.FilePromptType;
import com.storyEngine.window.scene.SceneNamePromptWindow;

public class NameInputField implements ActionListener
{
	public JButton selectButton;
	public HintTextField textField;
	SceneNamePromptWindow window;

	public NameInputField(SceneNamePromptWindow window )
	{
		this.window = window;
		textField = new HintTextField(12, "enter name");
		selectButton = new JButton("Select");
		selectButton.addActionListener(this);
		window.add(textField);
		window.add(selectButton);
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == selectButton)
		{
			System.out.print("\nWE DOING THIS RIGHT NOW");
			window.recivePrompt(textField.getText(), PromptType.Prompts.Name);
			
		}
		
	}
	
	
				
}
