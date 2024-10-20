package com.storyEngine.window.gui;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JTextField;
import javax.swing.filechooser.FileSystemView;

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
				window.recivePrompt(textField.getText(), FilePromptType.NAME);
			}
				
			
		}
		
	}
	
	@SuppressWarnings("unused")
	private File fileSelection(JFileChooser fileSelect)
	{
		fileSelect.setFileSelectionMode(JFileChooser.FILES_ONLY);
		switch(fileSelect.showOpenDialog(null))
		{
			case JFileChooser.APPROVE_OPTION :
				return fileSelect.getSelectedFile().isDirectory() != true && fileSelect.getSelectedFile().canWrite() ? fileSelect.getSelectedFile() :  null;
			default :
				return null;
				
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
