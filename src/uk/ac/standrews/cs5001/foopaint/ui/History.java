package uk.ac.standrews.cs5001.foopaint.ui;

import java.util.LinkedList;
import java.util.Observer;
import java.util.Queue;
import java.util.Stack;

import uk.ac.standrews.cs5001.foopaint.ui.tools.Tool;

public class History {
	private static History singleton;
	
	private Stack<Tool> itemsToDraw;	
	private Stack<Tool> undoneItems;
	private ValueChangedEvent<Iterable<Tool>> historyChangedEvent;
	
	static {
		singleton = new History();
	}
	
	private History() {
		this.itemsToDraw = new Stack<Tool>();
		this.undoneItems = new Stack<Tool>();
		this.historyChangedEvent = new ValueChangedEvent<Iterable<Tool>>();
	}
	
	public static History get() {
		return singleton;
	}
	
	public void addHistoryChangedListener(Observer listener) {
		this.historyChangedEvent.addObserver(listener);
	}
	
	private void raiseHistoryChanged() {
		this.historyChangedEvent.raiseEvent(this.getItems());		
	}
	
	public void addItem(Tool tool) {
		this.itemsToDraw.add(tool);
		this.raiseHistoryChanged();
	}
	
	public boolean undo() {
		if (!this.itemsToDraw.empty()) {
			Tool last = this.itemsToDraw.pop();
			this.undoneItems.push(last);
			this.raiseHistoryChanged();
			return true;
		}
		return false;
	}
	
	public boolean redo() {
		if (!this.undoneItems.empty()) {
			Tool last = this.undoneItems.pop();
			this.itemsToDraw.push(last);
			this.raiseHistoryChanged();
			return true;
		}
		return false;
	}
	
	public Iterable<Tool> getItems() {
		return this.itemsToDraw;
	}
}
