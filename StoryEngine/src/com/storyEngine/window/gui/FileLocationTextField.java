package com.storyEngine.window.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JPanel;

import com.storyEngine.Instance;
import com.storyEngine.utils.StringProccesser;
import com.storyEngine.window.FilePromptWindow;
import com.storyEngine.window.FilePromptWindow.FilePromptType;

public class FileLocationTextField implements ActionListener 
{
	
	public HintTextField textField;
	public JButton fileSelectButton;
	public JButton selectButton;
	FilePromptWindow window;
	public FileLocationTextField(FilePromptWindow window )
	{
		this.window = window;
		textField = new HintTextField(12, "name or directory");
		fileSelectButton = new JButton("File Explorer");
		fileSelectButton.addActionListener(this);
		selectButton = new JButton("Select");
		selectButton.addActionListener(this);
		window.add(textField);
		window.add(fileSelectButton);
		window.add(selectButton);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == fileSelectButton)
		{
			JFileChooser fileSelect = new JFileChooser();
			File f = directorySelection(fileSelect);
			
			if(f != null)
				textField.setText(f.getPath());
			
		}
		if(e.getSource() == selectButton)
		{
			if(new File(textField.getText()).isDirectory())
				window.recivePrompt(textField.getText(), FilePromptType.DIRECTORY);
			else if(textField.getText().equals("name or directory") || textField.getText().length() <= 1 || StringProccesser.ContainsAnyOfCharacter(textField.getText(), "#!@#~$%^&*()_+|}{\\:;\"?/>.<,"))
				window.recivePrompt(textField.getText(), FilePromptType.ERROR);
			else
			{
				System.out.print("CHECK");
				File d = new File(Instance.projectOrigin);
				for(String f : d.list())
				{
					if(f.equals(textField.getText()))
					{
						window.recivePrompt(textField.getText(), FilePromptType.ERROR);
						return;
					}
				}
				window.recivePrompt(textField.getText(), FilePromptType.NAME);
			}
				
			
		}
		
	}
	
	
	private File directorySelection(JFileChooser fileSelect)
	{
		fileSelect.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

		switch(fileSelect.showOpenDialog(null))
		{
			case JFileChooser.APPROVE_OPTION :
				return fileSelect.getSelectedFile().isDirectory() == true && fileSelect.getSelectedFile().canWrite() ? fileSelect.getSelectedFile() :  null;
			default :
				return null;
			
				
		}
	}
}
