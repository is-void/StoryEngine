package com.storyEngine.window;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.event.MouseInputAdapter;

import com.storyEngine.Instance;
import com.storyEngine.scene.Scene;
import com.storyEngine.utils.CONFIG;
public class SceneListerWindow extends JFrame
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public JList<Scene> scenes;
	public JButton confirm;
	private DefaultListModel<Scene> listModel;
	JPanel content;
	
	public SceneListerWindow()
	{
		super("order scenes"); //Creates a new window with the specified title
		this.setBounds(500, 300, 400, 100);	//Sets the bounds of the object	
		
        setResizable(true);

		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        content = new JPanel();
        confirm = new JButton("Confirm");
        confirm.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				SaveAndClose();
				
			}
        	
        });
        content.addKeyListener(new KeyAdapter() {
        	@Override
        	public void keyPressed(KeyEvent e) {
        		if(e.getKeyCode() == KeyEvent.VK_ENTER)
        		{
        			SaveAndClose();
        		}
        		
        	}
        	
        });
		initScenes();
		content.add(scenes);
		this.setLayout(new GridLayout());
		this.add(content);
		this.add(confirm);
		revalidate();
		this.pack();
        this.setVisible(true);
		
	}
	
	@SuppressWarnings("unchecked")
	protected void GetScenesInOrder()
	{
		ArrayList<String> raw = new ArrayList<String>();
		listModel = new DefaultListModel<Scene>();
		
		raw.addAll( (ArrayList<String>) (Instance.Config.getValue(CONFIG.GETSCENEORDER((String) Instance.Config.getValue(CONFIG.DEFAULTPROJECT)))));
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
		}
		scenes.addMouseListener(new ListMouseAdapter());
		
	}
	
	private class ListMouseAdapter extends MouseInputAdapter {
	    private int dragSourceIndex;

	    @Override
	    public void mousePressed(MouseEvent e) {
	        if (e.getButton() == MouseEvent.BUTTON1) {
	            dragSourceIndex = scenes.getSelectedIndex();
	            System.out.println(dragSourceIndex);
	        }
	    }

	    @Override
	    public void mouseReleased(MouseEvent e) 
	    {
	    	/*if(dragSourceIndex == -1)
	    		return;
	    		*/
	    	
	    	int dragTargetIndex = scenes.getSelectedIndex();
	    	System.out.println("Source: " + dragSourceIndex + "\nTarget: " + dragTargetIndex);
	    	
	    	Scene dragElement = (Scene) listModel.get(dragSourceIndex);
	    	
	    	if(e.getPoint().y > scenes.getBounds().getMaxY())
	    	{
	    		listModel.remove(dragSourceIndex);
	    		listModel.addElement(dragElement);	
	    	} else if(e.getPoint().y < scenes.getBounds().getMinY())
	    	{
	    		listModel.remove(dragSourceIndex);
	    		listModel.add(0, dragElement);	
	    	} else
	    	{
	    		listModel.remove(dragSourceIndex);
	    		listModel.add(dragTargetIndex, dragElement);
	    	}
	    	
	    	
	    }
	    
	    
	    

	   
	    
	    
	}

}

