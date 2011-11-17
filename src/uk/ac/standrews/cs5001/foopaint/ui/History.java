package uk.ac.standrews.cs5001.foopaint.ui;

import java.util.Observer;
import java.util.Stack;

import uk.ac.standrews.cs5001.foopaint.data.Document;
import uk.ac.standrews.cs5001.foopaint.data.DrawableItem;
import uk.ac.standrews.cs5001.foopaint.ui.tools.Tool;
import uk.ac.standrews.cs5001.foopaint.ui.tools.ToolFactory;

public class History {
	private static History singleton;
	
	private Stack<Tool> itemsToDraw;	
	private Stack<Tool> actions;
	private Stack<Tool> undoneItems;
	private ValueChangedEvent<Iterable<Tool>> historyChangedEvent;
	private boolean disabled;
	
	static {
		singleton = new History();
	}
	
	private History() {
		this.disabled = false;
		this.itemsToDraw = new Stack<Tool>();
		this.undoneItems = new Stack<Tool>();
		this.actions = new Stack<Tool>();
		this.historyChangedEvent = new ValueChangedEvent<Iterable<Tool>>();
	}
	
	public static History get() {
		return singleton;
	}
	
	public void addHistoryChangedListener(Observer listener) {
		this.historyChangedEvent.addObserver(listener);
	}
	
	private void raiseHistoryChanged() {
		if (!this.disabled) {
			this.historyChangedEvent.raiseEvent(this.getItems());
		}
	}
	
	public void addItem(Tool tool) {
		if (!this.disabled) {
			this.itemsToDraw.add(tool);
			this.actions.add(tool);
			this.raiseHistoryChanged();
		}
	}
	
	public boolean undo() {
		if (!this.disabled && !this.actions.empty()) {
			Tool last = this.actions.pop();
			if (!this.itemsToDraw.empty()) {
				this.itemsToDraw.pop();
			}
			this.undoneItems.push(last);
			this.raiseHistoryChanged();
			return true;
		}
		return false;
	}
	
	public boolean redo() {
		if (!this.disabled && !this.undoneItems.empty()) {
			Tool last = this.undoneItems.pop();
			this.actions.push(last);
			this.itemsToDraw.push(last);			
			this.raiseHistoryChanged();
			return true;
		}
		return false;
	}
	
	public Iterable<Tool> getItems() {
		return this.itemsToDraw;
	}
	
	public Document constructDocument() {
		Document doc = new Document();
		for (Tool tool: this.itemsToDraw) {
			DrawableItem drawable = tool.getItem();
			doc.addItem(drawable);
		}
		return doc;
	}
	
	public void parseDocument(Document doc) {
		this.actions.clear();
		this.itemsToDraw.clear();
		this.undoneItems.clear();
		
		for (DrawableItem drawable: doc) {
			Tool tool = ToolFactory.getToolByModel(drawable);
			if (tool != null) {
				this.itemsToDraw.add(tool);
			}
		}
		this.raiseHistoryChanged();
	}
	
	public void clear() {
		this.actions.clear();
		this.itemsToDraw.clear();
		this.undoneItems.clear();
		this.raiseHistoryChanged();
	}
	
	public boolean isDisabled() {
		return this.disabled;
	}
	
	public void setDisabled(boolean disabled) {
		this.disabled = disabled;
	}
}
