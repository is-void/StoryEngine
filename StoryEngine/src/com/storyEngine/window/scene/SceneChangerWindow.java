package com.storyEngine.window.scene;


import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import com.storyEngine.Instance;
import com.storyEngine.scene.Scene;
import com.storyEngine.utils.CONFIG;
public class SceneChangerWindow extends JFrame 
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public JList<Scene> scenes;
	public JButton confirm;
	private DefaultListModel<Scene> listModel;
	JPanel content;
	
	private SceneWindow sceneWindow;
	
	public SceneChangerWindow(SceneWindow win)
	{
		super("change Scene"); //Creates a new window with the specified title
		this.setBounds(500, 300, 400, 100);	//Sets the bounds of the object	
		
        setResizable(true);
        sceneWindow = win;
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        content = new JPanel();
        confirm = new JButton("Confirm");
        confirm.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				switchSceneAndClose();
			}

			
        	
        });
        
       
		initScenes();
		content.add(scenes);
		this.setLayout(new GridLayout());
		this.add(content);
		
		this.add(confirm);
		
		
		 content.addKeyListener(new KeyAdapter() {
	        	@Override
	        	public void keyPressed(KeyEvent e) {
	        		if(e.getKeyCode() == KeyEvent.VK_ENTER)
	        		{
	        			switchSceneAndClose();
	        		}
	        		if(e.getKeyCode() == KeyEvent.VK_UP)
	        		{
	        			if(scenes.getSelectedIndex() > 0)
	        				scenes.setSelectedIndex(scenes.getSelectedIndex()-1);
	        		}
	        		if(e.getKeyCode() == KeyEvent.VK_DOWN)
	        		{
	        			if(scenes.getSelectedIndex() < scenes.getModel().getSize()-1 )
	        				scenes.setSelectedIndex(scenes.getSelectedIndex()+1);
	        		}
	        	}
	        	
	        });
		revalidate();
		this.pack();
        this.setVisible(true);
		
	}
	private void switchSceneAndClose() {
		// TODO Auto-generated method stub
		if(scenes.getSelectedIndex() == -1)
		{
			dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
			dispose();
			return;
		}
		
		sceneWindow.changeScene(scenes.getSelectedValue().getName());
		dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
		dispose();
	}
	@SuppressWarnings("unchecked")
	protected void GetScenesInOrder()
	{
		ArrayList<String> raw = new ArrayList<String>();
		listModel = new DefaultListModel<Scene>();
		raw.addAll( (ArrayList<String>) Instance.Config.getValue(CONFIG.GETSCENEORDER((String) Instance.Config.getValue(CONFIG.DEFAULTPROJECT))));
		for (String string : raw) 
		{
			listModel.addElement(Scene.scenes.get(string));
		}
		scenes = new JList<Scene>(listModel);
	}
	
	protected void SaveAndClose() {
		// TODO Auto-generated method stub
		
		try {
			String values = "[";
			
			for(int i = 0; i < scenes.getModel().getSize(); i++ )
			{
				values += scenes.getModel().getElementAt(i).getName();
				if(i != scenes.getModel().getSize()-1)
				{
					values += ", ";
				}
			}
			values += "]";
			Instance.Config.setValue(CONFIG.GETSCENEORDER(Instance.Config.getValue(CONFIG.DEFAULTPROJECT).toString()), values);
			Instance.SaveConfig();
			dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
			dispose();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	void initScenes()
	{
		
		
		if(Instance.Config.hasValue("***SCENEORDER***" + Instance.Config.getValue(CONFIG.DEFAULTPROJECT)))
		{
			GetScenesInOrder();
		} else
		{
			listModel = new DefaultListModel<Scene>();
			listModel.addAll(Scene.scenes.values());
			scenes = new JList<Scene>(listModel);
			scenes.setSelectedValue(sceneWindow.scene, true);
		}
		
	}
	
	

}

