package uk.ac.standrews.cs5001.foopaint.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observer;

import javax.swing.*;


public class ToolsPanel extends JToolBar implements ActionListener {
	private static final long serialVersionUID = 1L;
	private ValueChangedEvent<ToolIDs> toolChagedEvent;
	
	public ToolsPanel() {
		this.buildLayout();
		this.toolChagedEvent = new ValueChangedEvent<ToolIDs>();
	}
	
	public void addToolChangeListener(Observer listener) {
		this.toolChagedEvent.addObserver(listener);
	}
	
	protected void buildLayout() {
		this.setFloatable(false);
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		for (ToolOptions option: ToolOptions.values()) {
			JButton button = this.createButton(option);
			button.setActionCommand(Integer.toString(option.getID().ordinal()));
			button.addActionListener(this);
			this.add(button);
		}
	}
	
	protected JButton createButton(ToolOptions forOption) {
		return new ToolbarItem(forOption.getPath(), forOption.getText());
	}
		
	@Override
	public void actionPerformed(ActionEvent e) {
		int idx = Integer.valueOf(e.getActionCommand());
		ToolIDs id = ToolIDs.values()[idx];
		this.toolChagedEvent.raiseEvent(id);
	}
	
	protected static enum ToolOptions {
		ACTION_SELECT ("data/finish.png", "Select", ToolIDs.SELECT_OBJECT)
		, ACTION_PICK_COLOUR ("data/colorpicker.png", "Pick Colour", ToolIDs.PICK_COLOUR)
		, ACTION_IMPORT ("data/fileimport.png", "Import Image", ToolIDs.DRAW_IMPORTED_IMAGE)
		, ACTION_LINE ("data/hi48-action-tool_line.png", "Line", ToolIDs.DRAW_LINE)
		, ACTION_RECTANGLE ("data/hi48-action-tool_rect_selection.png", "Rectangle", ToolIDs.DRAW_RECTANGLE)
		, ACTION_SOLID_RECTANGLE ("data/hi48-action-tool_rectangle.png", "Solid Rectangle", ToolIDs.DRAW_SOLID_RECTANGLE)
		, ACTION_ELLIPSE ("data/hi48-action-tool_elliptical_selection.png", "Ellipse", ToolIDs.DRAW_ELLIPSE)
		, ACTION_SOLID_ELLIPSE ("data/hi48-action-tool_ellipse.png", "Solid Ellipse", ToolIDs.DRAW_SOLID_ELLIPSE)				
		;
		
		private String path;
		private String text;
		private ToolIDs id;
		
		ToolOptions(String path, String text, ToolIDs id) {
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
		
		public ToolIDs getID() {
			return this.id;
		}
	}	
}

