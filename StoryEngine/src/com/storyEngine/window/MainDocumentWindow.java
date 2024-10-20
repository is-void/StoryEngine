package com.storyEngine.window;

import java.awt.BorderLayout;

import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class MainDocumentWindow extends JFrame
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4639932549442764716L;
	JMenuBar menuBar;
	JPanel panel;
	JPanel menuPanel;
	JPanel documentPanel;
	JMenuItem item1;
	JEditorPane textEditor;
	
	public MainDocumentWindow(String name)
	{
		super("\"" + name + "\"" + " Project - PreAlpha v0.05");
		setSize(1650,1080);
		System.out.print("CheckSSSS");
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setVisible(true);
		this.setLayout(new BorderLayout());
		
		menuBar = new DocumentMenu(this);
		
		

		documentPanel = new JPanel();	
		textEditor = new DocumentTextArea();
		documentPanel.add(textEditor);
		documentPanel.setBounds(getBounds());
		
		setJMenuBar(menuBar);
		add(textEditor, BorderLayout.CENTER);
		add(new JPanel(), BorderLayout.WEST);
		add(new JPanel(), BorderLayout.EAST);
		
		
		//pack();
	    revalidate();
	    
		
	}
	
	public void saveCurrentDoc()
	{
		/*
		 * if(currentDocumentIndex == -1)
		 * 	return;
		 * textEditor.loadDocumentIntoFile(chapters.get(i).getDocumentFile());
		 * 
		 */
	}

}
