package com.storyEngine.window.events;

public abstract class Event {
	private Object data;
	public Event(Object data)
	{
		this.data = data;
		start();
	}
	
	public abstract void start();
}
