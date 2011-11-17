package uk.ac.standrews.cs5001.foopaint.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import uk.ac.standrews.cs5001.foopaint.ui.draw.History;
import uk.ac.standrews.cs5001.foopaint.ui.draw.Tool;
import uk.ac.standrews.cs5001.foopaint.ui.draw.ToolFactory;
import uk.ac.standrews.cs5001.foopaint.ui.draw.ToolIDs;

public class DrawingPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	
	private DrawingPanelState state;
	private Tool currentTool;	
	private ToolChangeHandler toolChangeHandler;
	
	public DrawingPanel() {
		this.toolChangeHandler = new ToolChangeHandler(this);		
		this.setPreferredSize(new Dimension(640, 480));	
		this.enableEvents(
				MouseEvent.MOUSE_PRESSED
				| MouseEvent.MOUSE_RELEASED
				| MouseEvent.MOUSE_MOVED
				| MouseEvent.MOUSE_WHEEL
				| MouseEvent.MOUSE_DRAGGED
		);
		
		this.state = new DrawingPanelState();
		this.currentTool = ToolFactory.getToolByID(ToolIDs.DRAW_LINE);
		
		History.get().addQueueChangedListener(new HistoryChangedHandler(this));
	}
	
	public Observer getToolChangeHandler() {
		return this.toolChangeHandler;
	}
	
	@Override
	protected void processMouseEvent(MouseEvent e) {
		super.processMouseEvent(e);
				
		if (e.getID() == MouseEvent.MOUSE_PRESSED) {
		//	System.out.println("Dragging start");
			this.state.startDragging(e.getPoint());
			//this.repaint();
		}
		else if (e.getID() == MouseEvent.MOUSE_RELEASED) {
			//System.out.println("Dragging stop");
			this.state.stopDragging(e.getPoint());
			this.currentTool.setData(this.state);
			History.get().addItem(this.currentTool.copy());
			//this.repaint();
		}
	}
	
	@Override
	protected void processMouseMotionEvent(MouseEvent e) {
		super.processMouseMotionEvent(e);
		
		if (e.getID() == MouseEvent.MOUSE_DRAGGED) {			
			if (this.state.isDragging()) {
				//System.out.println("move");
				this.state.dragTo(e.getPoint());
				this.repaint();
			}
		}
	}
	
	@Override
	protected void processMouseWheelEvent(MouseWheelEvent e) {
		// TODO Auto-generated method stub
		super.processMouseWheelEvent(e);
	}
	
	@Override
	public void paint(Graphics g) {
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		
		for (Tool oldItem: History.get().getItems()) {
			oldItem.render(g);
		}
		
		if (this.currentTool != null && this.state.isDragging()) {			
			this.currentTool.render(g, this.state);
		}
	}
	
	void onToolChanged(ToolIDs newTool) {
		switch (newTool) {
		case DRAW_IMPORTED_IMAGE:
		case PICK_COLOUR:
		case SELECT_OBJECT:
			JOptionPane.showMessageDialog(this, "Not implemented: " + newTool);
			break;
		
		default:
			this.currentTool = ToolFactory.getToolByID(newTool);
			this.repaint();
			break;
		}		
	}
	
	void onHistoryChanged(Iterable<Tool> arg1) {
		this.repaint();
	}
	
	private static class ToolChangeHandler implements Observer {
		private DrawingPanel parent;
		
		public ToolChangeHandler(DrawingPanel parent) {
			this.parent = parent;
		}
		
		@Override
		public void update(Observable sender, Object data) {
			if (data instanceof ToolIDs) {
				ToolIDs newId = (ToolIDs) data;
				this.parent.onToolChanged(newId);
			}
		}		
	}
	
	private static class HistoryChangedHandler implements Observer {
		private DrawingPanel parent;
		
		public HistoryChangedHandler(DrawingPanel parent) {
			this.parent = parent;
		}
		
		@SuppressWarnings("unchecked")
		@Override
		public void update(Observable arg0, Object arg1) {
			this.parent.onHistoryChanged((Iterable<Tool>)arg1);
		}
	}

	
}
