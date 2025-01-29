package com.storyEngine.window.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JButton;
import com.storyEngine.PromptType;
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
		textField.add(selectButton);
		textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e)
			{
				if(e.getKeyCode() == KeyEvent.VK_ENTER)
				{
					selectAction();
				}
			}
		});
		
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == selectButton)
		{
			
			selectAction();
		}
		
	}
	
	public void selectAction()
	{
		window.recievePrompt(textField.getText(), PromptType.Prompts.Name);
	}
	
	
				
}
