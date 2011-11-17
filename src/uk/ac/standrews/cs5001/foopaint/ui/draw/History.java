package uk.ac.standrews.cs5001.foopaint.ui.draw;

import java.util.LinkedList;
import java.util.Observer;
import java.util.Queue;

import uk.ac.standrews.cs5001.foopaint.ui.ValueChangedEvent;

public class History {
	private static History singleton;
	
	private Queue<Tool> itemsToDraw;	
	private Queue<Tool> undoneItems;
	private ValueChangedEvent<Iterable<Tool>> queueChangedEvent;
	
	static {
		singleton = new History();
	}
	
	private History() {
		this.itemsToDraw = new LinkedList<Tool>();
		this.undoneItems = new LinkedList<Tool>();
		this.queueChangedEvent = new ValueChangedEvent<Iterable<Tool>>();
	}
	
	public static History get() {
		return singleton;
	}
	
	public void addQueueChangedListener(Observer listener) {
		this.queueChangedEvent.addObserver(listener);
	}
	
	private void raiseQueueChanged() {
		this.queueChangedEvent.notifyObservers(this.getItems());
	}
	
	public void addItem(Tool tool) {
		this.itemsToDraw.add(tool);
		this.raiseQueueChanged();
	}
	
	public boolean undo() {
		Tool last = this.itemsToDraw.poll();
		if (last != null) {
			this.undoneItems.add(last);
			this.raiseQueueChanged();
			return true;
		}
		else {
			return false;
		}
	}
	
	public boolean redo() {
		Tool last = this.undoneItems.poll();
		if (last != null) {
			this.itemsToDraw.add(last);
			this.raiseQueueChanged();
			return true;
		}
		else {
			return false;
		}
	}
	
	public Iterable<Tool> getItems() {
		return this.itemsToDraw;
	}
}
