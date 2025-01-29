package com.storyEngine.window.scene;

import java.awt.FlowLayout;
import java.awt.event.WindowEvent;
import com.storyEngine.Instance;
import com.storyEngine.PromptWindow;
import com.storyEngine.scene.Scene;
import com.storyEngine.utils.CONFIG;
import com.storyEngine.window.PromptStyle;
import com.storyEngine.window.gui.NameInputField;

public class SceneNamePromptWindow extends PromptWindow {

	/**
	 * 
	 */
	NameInputField field;
	private static final long serialVersionUID = 1L;
	private SceneWindow doc;

	public SceneNamePromptWindow(PromptStyle style, SceneWindow doc) {
		super(style);
		this.doc = doc;
		field = new NameInputField(this);
        windowSetup();
	}

	@Override
	public void recievePrompt(String s, Object prompt) {
		// TODO Auto-generated method 
		Scene scene = new Scene(s, (String) Instance.Config.getValue(CONFIG.DEFAULTSCENEPATH));
		doc.changeScene(scene.getName());
		
		dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSED));
		dispose();
		
	}

	@Override
	public void windowSetup() {
		FlowLayout f = new FlowLayout();
		this.setLayout(f);
		
		add(field.textField);
		add(field.selectButton);
		revalidate();
		repaint();

	}

}
