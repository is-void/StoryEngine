package com.storyEngine.window;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

public class DocumentMenu extends JMenuBar implements ActionListener
{
	JMenu file,edit,help;    
	JMenuItem cut,copy,paste,selectAll;   
	JMenuItem save, open, save_as;
	MainDocumentWindow document;
	
	public DocumentMenu(MainDocumentWindow doc)
	{
		super();
		document = doc;
		edit = new JMenu("Edit");
		file = new JMenu("File");
		help = new JMenu("Help");
		
		cut = new JMenuItem("cut");
		copy = new JMenuItem("copy");
		paste = new JMenuItem("paste");
		selectAll = new JMenuItem("selectAll");
		
		save = new JMenuItem("save");
		open = new JMenuItem("open");
		save_as = new JMenuItem("save as");
		
		edit.add(copy);
		edit.add(cut);
		edit.add(paste);
		edit.add(selectAll);
		
		file.add(save);
		file.add(open);
		file.add(save_as);
		
		this.add(file);
		this.add(edit);
		this.add(help);
		selectAll.addActionListener(this);
		copy.addActionListener(this);
		paste.addActionListener(this);
		cut.addActionListener(this);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == cut)
		{
			document.textEditor.cut();
		}
		if(e.getSource() == copy)
		{
			document.textEditor.copy();
		}
		if(e.getSource() == paste)
		{
			document.textEditor.paste();
		}
		if(e.getSource() == selectAll)
		{
			document.textEditor.selectAll();
		}
		if(e.getSource() == save)
		{
			document.saveCurrentDoc();
		}
		
	}
}
