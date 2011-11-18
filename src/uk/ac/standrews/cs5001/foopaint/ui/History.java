package uk.ac.standrews.cs5001.foopaint.ui;

import java.util.Observer;
import java.util.Stack;

import uk.ac.standrews.cs5001.foopaint.data.Document;
import uk.ac.standrews.cs5001.foopaint.data.DrawableItem;
import uk.ac.standrews.cs5001.foopaint.ui.tools.Tool;
import uk.ac.standrews.cs5001.foopaint.ui.tools.ToolFactory;

public class History {
	private static History singleton;
	
	private boolean modified;
	private Stack<Tool> itemsToDraw;	
	private Stack<Tool> actions;
	private Stack<Tool> undoneItems;
	private ValueChangedEvent<Iterable<Tool>> historyChangedEvent;
	private boolean disabled;
	
	static {
		singleton = new History();
	}
	
	private History() {
		this.modified = false;
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
	
	public void removeHistoryChangedListener(Observer listener) {
		this.historyChangedEvent.deleteObserver(listener);
	}
	
	public void raiseHistoryChanged() {
		if (!this.disabled) {
			this.historyChangedEvent.raiseEvent(this.getItems());
		}
	}
	
	public void addItem(Tool tool) {
		if (!this.disabled) {
			this.itemsToDraw.add(tool);
			this.actions.add(tool);
			this.modified = true;
			this.raiseHistoryChanged();
		}
	}
	
	public boolean undo() {
		if (this.canUndo()) {
			Tool last = this.actions.pop();
			if (!this.itemsToDraw.empty()) {
				this.itemsToDraw.pop();
			}
			this.undoneItems.push(last);
			this.modified = true;
			this.raiseHistoryChanged();
			return true;
		}
		return false;
	}

	public boolean redo() {
		if (this.canRedo()) {
			Tool last = this.undoneItems.pop();
			this.actions.push(last);
			this.itemsToDraw.push(last);	
			this.modified = true;
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
		this.modified = false;
		this.raiseHistoryChanged();
	}
	
	public void clear() {
		this.actions.clear();
		this.itemsToDraw.clear();
		this.undoneItems.clear();
		this.modified = true;
		this.raiseHistoryChanged();
	}
	
	public boolean hasItemsToDraw() {
		return !this.itemsToDraw.empty();
	}
	
	public boolean canUndo() {
		return !this.disabled && !this.actions.empty();
	}	
	
	public boolean canRedo() {
		return !this.disabled && !this.undoneItems.empty();
	}
	
	public boolean isDisabled() {
		return this.disabled;
	}
	
	public void setDisabled(boolean disabled) {
		this.disabled = disabled;
	}
	
	public boolean isModified() {
		return this.modified;
	}
	
	public void setModified(boolean modified) {
		this.modified = modified;
	}
}
