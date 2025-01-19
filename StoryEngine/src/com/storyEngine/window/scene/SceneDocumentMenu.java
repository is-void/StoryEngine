package com.storyEngine.window.scene;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

public class SceneDocumentMenu extends JMenuBar implements ActionListener
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JMenu file,scene,edit,help;    
	JMenuItem cut,copy,paste,selectAll;   
	JMenuItem save, open, save_as;
	JMenuItem newScene, deleteScene;
	SceneWindow document;
	
	public SceneDocumentMenu(SceneWindow doc)
	{
		super();
		document = doc;
		edit = new JMenu("Edit");
		file = new JMenu("File");
		help = new JMenu("Help");
		scene = new JMenu("Scene");
		
		cut = new JMenuItem("cut");
		copy = new JMenuItem("copy");
		paste = new JMenuItem("paste");
		selectAll = new JMenuItem("selectAll");
		
		save = new JMenuItem("save");
		open = new JMenuItem("open");
		save_as = new JMenuItem("save as");
		
		newScene = new JMenuItem("new scene");
		deleteScene = new JMenuItem("delete scene");
		
		edit.add(copy);
		edit.add(cut);
		edit.add(paste);
		edit.add(selectAll);
		
		file.add(save);
		file.add(open);
		file.add(save_as);
		
		scene.add(newScene);
		scene.add(deleteScene);
		
		this.add(file);
		this.add(edit);
		this.add(help);
		this.add(scene);
		
		save.addActionListener(this);
		selectAll.addActionListener(this);
		copy.addActionListener(this);
		paste.addActionListener(this);
		cut.addActionListener(this);
		
		newScene.addActionListener(this);
		
		repaint();
		revalidate();

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
		if(e.getSource() == newScene)
		{
			document.newScene();
		}
		
	}
}
