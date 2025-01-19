package com.storyEngine.window.scene;

import java.awt.BorderLayout;
import java.io.IOException;

import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import com.storyEngine.Instance;
import com.storyEngine.scene.Scene;
import com.storyEngine.utils.CONFIG;
import com.storyEngine.window.DocumentTextArea;
import com.storyEngine.window.FilePromptWindow;
import com.storyEngine.window.PromptStyle;

public class SceneWindow extends JFrame
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
	Scene scene;
	
	public SceneWindow(String name, String scenePath)
	{
		
		
		super("\"" + name + "\"" + " in " + (String)Instance.Config.getValue(CONFIG.DEFAULTPROJECT)+ " - PreAlpha v0.1");
		System.out.println("\n\n" + Scene.scenes.get(name).getText());
		
		setSize(1650,1080);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		
		this.setLayout(new BorderLayout());
		
		menuBar = new SceneDocumentMenu(this);
		
		

		documentPanel = new JPanel();	
		textEditor = new DocumentTextArea();
		documentPanel.add(textEditor);
		documentPanel.setBounds(getBounds());
		
		setJMenuBar(menuBar);
		add(textEditor, BorderLayout.CENTER);
		add(new JPanel(), BorderLayout.WEST);
		add(new JPanel(), BorderLayout.EAST);
		
		if(scenePath == "no scene")
		{
			textEditor.setText("Welcome to your new project!" +
					"\n\n Press Scene + New to create a scene, or Scene + Open to Load a scene");
			textEditor.setEditable(false);
		} else
		{
			scene = Scene.scenes.get(name);
			textEditor.setText(Scene.scenes.get(name).getText());
		}
		
		
		//pack();
		repaint();
	    revalidate();
	    
	    this.addWindowListener(new java.awt.event.WindowAdapter() {
    	    @Override
    	    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
    	    	System.out.print("Check");
    	        try {
					Instance.SaveConfig();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
    	    }
    	});
	    setVisible(true);
	}
	
	public void saveCurrentDoc()
	{
		scene.setText(textEditor.getText());
		scene.save();
		System.out.println("Saved at " + "");
		repaint();
		revalidate();
	}
	
	public void newScene()
	{
		new SceneNamePromptWindow(new PromptStyle("Create New Project ", 400, 400, javax.swing.WindowConstants.DISPOSE_ON_CLOSE, "File Location", 400, 400), this);
	}
	
	public void changeScene(String sceneName)
	{
		if(textEditor.isEditable())
			scene.save();
		
		if(!(Scene.scenes.get(sceneName) == null))
		{
			Instance.Config.setValue(CONFIG.DEFAULTSCENE, sceneName);
			scene = Scene.scenes.get(sceneName);
			textEditor.setText(scene.getText());
			textEditor.setEditable(true);
			this.setTitle("\"" + sceneName + "\"" + " in " + (String)Instance.Config.getValue(CONFIG.DEFAULTPROJECT)+ " - PreAlpha v0.1");
			repaint();
			revalidate();
		}
		System.out.println("No such scene exists");
	}

}
