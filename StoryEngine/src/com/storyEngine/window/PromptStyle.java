package com.storyEngine.window;

public class PromptStyle extends WindowStyle
{


	public String labelText;
	public int labelX, labelY;
	public PromptStyle(String title, int width, int height, int close, String label, int labelX, int labelY) {
		super(title, width, height, close);
		labelText = label;
		this.labelX = labelX;
		this.labelY = labelY;
	}

}
