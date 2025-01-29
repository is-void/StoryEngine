package com.storyEngine.window;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JEditorPane;



public class DocumentTextArea extends JEditorPane implements KeyListener
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DocumentTextArea()
	{
		super();
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	public File loadDocumentIntoFile(File doc) 
	{
		File attempt = new File(doc.getAbsolutePath());
		FileWriter fileWriter;
		try {
			fileWriter = new FileWriter(attempt);
			BufferedWriter bWriter = new BufferedWriter(fileWriter);
			this.write(bWriter);
			doc.delete();
			attempt.createNewFile();
			bWriter.close();
			fileWriter.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return attempt;
		
	}
	
	
	
	
}
