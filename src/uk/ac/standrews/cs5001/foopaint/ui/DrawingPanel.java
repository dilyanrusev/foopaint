package uk.ac.standrews.cs5001.foopaint.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.geom.Point2D;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JColorChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import uk.ac.standrews.cs5001.foopaint.ui.tools.Tool;
import uk.ac.standrews.cs5001.foopaint.ui.tools.ToolFactory;

public class DrawingPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	
	private Iterable<Tool> archivedTools;
	private Tool currentTool;
	private ToolChangeHandler toolChangeHandler;
	private Point2D start;
	private Point2D end;
	private Color colour;
	private boolean dragging;
	
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
		
		this.start = new Point(0, 0);
		this.end = new Point(0, 0);
		this.colour = Color.BLACK;
		this.dragging = false;
		this.currentTool = null;
		
		History.get().addHistoryChangedListener(new HistoryChangedHandler(this));
	}
	
	public Observer getToolChangeHandler() {
		return this.toolChangeHandler;
	}
	
	@Override
	protected void processMouseEvent(MouseEvent e) {
		super.processMouseEvent(e);
				
		if (e.getID() == MouseEvent.MOUSE_PRESSED) {
			this.startDragging(e.getPoint());
		}
		else if (e.getID() == MouseEvent.MOUSE_RELEASED) {			
			this.stopDragging(e.getPoint());			
		}
	}
	
	@Override
	protected void processMouseMotionEvent(MouseEvent e) {
		super.processMouseMotionEvent(e);
		
		if (e.getID() == MouseEvent.MOUSE_DRAGGED && this.dragging) {
			this.dragTo(e.getPoint());			
		}
	}
	
	@Override
	protected void processMouseWheelEvent(MouseWheelEvent e) {
		// TODO Auto-generated method stub
		super.processMouseWheelEvent(e);
	}
	
	private void startDragging(Point2D origin) {
		this.dragging = true;
		this.start = origin;;
		this.end = origin;
	}
	
	private void dragTo(Point2D newEnd) {
		this.end = newEnd;
		
		if (this.currentTool != null) {
			this.currentTool.update(this.start, this.end, this.colour);
			this.repaint();	
		}
	}
	
	private void stopDragging(Point2D end) {
		this.dragging = false;
		this.end = end;
		
		if (this.currentTool != null) {
			this.currentTool.update(this.start, this.end, this.colour);
			Tool archived = this.currentTool.copy();
			History.get().addItem(archived);
		}
	}
	
	@Override
	public void paint(Graphics g) {
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		
		if (this.archivedTools != null) {
			for (Tool archived: this.archivedTools) {
				archived.render(g);
			}
		}		
		
		if (this.currentTool != null && this.dragging) {			
			this.currentTool.render(g);
		}
	}
	
	void onToolChanged(ToolIDs newTool) {
		switch (newTool) {
		case PICK_COLOUR:
			this.colour = JColorChooser.showDialog(this, "Pick a colour", this.colour);
			break;
		case DRAW_IMPORTED_IMAGE:		
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
		//JOptionPane.showMessageDialog(this, "History changed");
		this.archivedTools = arg1;
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
