package com.storyEngine.window.scene;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.io.IOException;

import javax.swing.AbstractAction;
import javax.swing.JComponent;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.KeyStroke;

import com.storyEngine.Instance;
import com.storyEngine.scene.Scene;
import com.storyEngine.utils.CONFIG;
import com.storyEngine.utils.Debug;
import com.storyEngine.utils.InputComponent;
import com.storyEngine.window.DocumentTextArea;
import com.storyEngine.window.PromptStyle;
import static javax.swing.ScrollPaneConstants.*;

public class SceneWindow extends JFrame 
{
	/**
	 * 
	 */
	boolean ctrl;
	private static final long serialVersionUID = 4639932549442764716L;
	JMenuBar menuBar;
	JPanel panel;
	JPanel menuPanel;
	JPanel documentPanel;
	JMenuItem item1;
	JEditorPane textEditor;
	JScrollPane pane;
	Scene scene;
	InputComponent input = new InputComponent();
	
	
	
	public SaveDocumentAction saveAction;
	public NewSceneAction sceneAction;
	public OpenSceneAction openAction;
	
	
	public SceneWindow(String name, String scenePath)
	{
		
		
		super("\"" + name + "\"" + " in " + (String)Instance.Config.getValue(CONFIG.DEFAULTPROJECT)+ " - PreAlpha v0.1");
		Debug.Log(name, this.getClass());
		setSize(1650,1080);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		
		this.setLayout(new BorderLayout());
		
		menuBar = new SceneDocumentMenu(this);
		saveAction = new SaveDocumentAction(this);
		sceneAction = new NewSceneAction(this);
		openAction = new OpenSceneAction(this);
		
		input.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("control S"), "CTRL+S");
		input.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("control T"), "CTRL+T");
		input.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("control O"), "CTRL+O");
		
		
		input.getActionMap().put("CTRL+S", saveAction);
		input.getActionMap().put("CTRL+T", sceneAction);
		input.getActionMap().put("CTRL+O", openAction);

		
		add(input);
		
		documentPanel = new JPanel();	
		textEditor = new DocumentTextArea();
		documentPanel.add(textEditor);
		documentPanel.setBounds(getBounds());
		
		setJMenuBar(menuBar);
		pane = new JScrollPane(textEditor);
		pane.createVerticalScrollBar();
		pane.setHorizontalScrollBarPolicy(HORIZONTAL_SCROLLBAR_NEVER);

		add(pane, BorderLayout.CENTER);
		add(new JPanel(), BorderLayout.WEST);
		add(new JPanel(), BorderLayout.EAST);
		
		if(scenePath == "no scene")
		{
			textEditor.setText("Welcome to your new project!" +
					"\n\n Press Scene + New to create a scene, or Scene + Open to Load a scene");
			textEditor.setEditable(false);
		} else
		{
			Debug.Log(name, getClass());
			scene = Scene.scenes.get(name);
			Debug.Log("\n" + scene, this.getClass());
			Debug.Log(Scene.scenes.toString(), this.getClass());
			if(Scene.scenes != null && Scene.scenes.get(name) != null)
				textEditor.setText(Scene.scenes.get(name).getText());
			else
			{
				textEditor.setText("Welcome to your new project!" +
						"\n\n Press Scene + New to create a scene, or Scene + Open to Load a scene");
				textEditor.setEditable(false);
			}
		}
		
		
		//pack();
		repaint();
	    revalidate();
	    
	    this.addWindowListener(new java.awt.event.WindowAdapter() {
    	    @Override
    	    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
    	        try {
    	        	Debug.Log(Instance.Config.getValue(CONFIG.DEFAULTSCENE).toString(), this.getClass());
					Instance.SaveConfig();
					Debug.Log("Closing", this.getClass());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
    	    }
    	});
	    setVisible(true);
	    add(input);
	}
	
	public void saveCurrentDoc()
	{
		scene.setText(textEditor.getText());
		scene.save();
		repaint();
		revalidate();
	}
	
	public void newScene()
	{
		new SceneNamePromptWindow(new PromptStyle("Create New Scene ", 400, 400, javax.swing.WindowConstants.DISPOSE_ON_CLOSE, "File Location", 400, 400), this);
	}
	
	public void openScene()
	{
		new SceneChangerWindow(this);
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
		Debug.Log("No such scene exists", this.getClass());
	}
	

}

class SaveDocumentAction extends AbstractAction
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	SceneWindow window;
	public SaveDocumentAction(SceneWindow window)
	{
		this.window = window;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		window.saveCurrentDoc();
		
	}
	
}

class NewSceneAction extends AbstractAction
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	SceneWindow window;
	public NewSceneAction(SceneWindow window)
	{
		this.window = window;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		window.newScene();
	}
	
}
class OpenSceneAction extends AbstractAction
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	SceneWindow window;
	public OpenSceneAction(SceneWindow window)
	{
		this.window = window;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		window.openScene();
	}
	
}

