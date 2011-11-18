package uk.ac.standrews.cs5001.foopaint.ui;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.*;


public class MenuPanel extends JToolBar implements ActionListener {
		
	private static final long serialVersionUID = 1L;
	
	private ValueChangedEvent<ActionIDs> actionChangedEvent;
	private Component btnUndo;
	private Component btnRedo;	
	private Component btnSave;

	public MenuPanel() {
		this.actionChangedEvent = new ValueChangedEvent<ActionIDs>();
		this.buildLayout();
		
		History.get().addHistoryChangedListener(new Observer() {

			@Override
			public void update(Observable o, Object arg) {
				if (btnUndo != null) {
					btnUndo.setEnabled(History.get().canUndo());
				}
				if (btnRedo != null) {
					btnRedo.setEnabled(History.get().canRedo());
				}
				if (btnSave != null) {
					btnSave.setEnabled(History.get().isModified());					
				}				
			}
			
		});
	}
	
	public void addActionChangedListener(Observer listener) {
		this.actionChangedEvent.addObserver(listener);
	}
	
	protected void buildLayout() {
		this.setFloatable(false);
		for (MenuOptions item: MenuOptions.values()) {
			JButton button = this.createButton(item);	
			if (item == MenuOptions.EDIT_UNDO)
				this.btnUndo = button;
			if (item == MenuOptions.EDIT_REDO)
				this.btnRedo = button;
			if (item == MenuOptions.FILE_SAVE)
				this.btnSave = button;
			button.setActionCommand(Integer.toString(item.getID().ordinal()));
			button.addActionListener(this);
			this.add(button);
		}
	}
	
	protected JButton createButton(MenuOptions forOption) {
		return new MenubarItem(forOption.getPath(), forOption.getText());
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {		
		Integer idx = Integer.valueOf(e.getActionCommand());		
		ActionIDs id = ActionIDs.values()[idx];
		this.actionChangedEvent.raiseEvent(id);				
	}
	
	protected static enum MenuOptions {
		FILE_NEW ("data/filenew.png", "New", ActionIDs.CREATE_NEW)
		, FILE_OPEN ("data/fileopen.png", "Open", ActionIDs.OPEN)
		, FILE_SAVE ("data/filesave.png", "Save", ActionIDs.SAVE)
		, FILE_SAVE_AS ("data/filesaveas.png", "Save As", ActionIDs.SAVE_AS)
		, EDIT_UNDO ("data/undo.png", "Undo", ActionIDs.UNDO)
		, EDIT_REDO ("data/redo.png", "Redo", ActionIDs.REDO)
		, APPLICATION_EXIT ("data/application_exit.png", "Exit", ActionIDs.EXIT)
		;
		
		private String path;
		private String text;
		private ActionIDs id;
		
		MenuOptions(String path, String text, ActionIDs id) {
			this.path = path;
			this.text = text;
			this.id = id;
		}
		
		public String getPath() {
			return this.path;
		}
		
		public String getText() {
			return this.text;
		}
		
		public ActionIDs getID() {
			return this.id;
		}
	}
}
