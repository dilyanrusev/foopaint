package uk.ac.standrews.cs5001.foopaint.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;
import java.io.File;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JColorChooser;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

import uk.ac.standrews.cs5001.foopaint.ui.tools.SelectableTool;
import uk.ac.standrews.cs5001.foopaint.ui.tools.Tool;
import uk.ac.standrews.cs5001.foopaint.ui.tools.ToolFactory;

public class DrawingPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	
	private Iterable<Tool> archivedTools;
	private Tool currentTool;
	private ToolChangeHandler toolChangeHandler;
	private Point2D start;
	private Point2D end;
	private Point2D lastPoint;
	private Color colour;
	private boolean dragging;
	private boolean selecting;
	private Mode mode;
	private JFileChooser fileChooser;
	
	public DrawingPanel() {
		this.toolChangeHandler = new ToolChangeHandler(this);		
		this.setPreferredSize(new Dimension(640, 480));	
		this.enableEvents(MouseEvent.MOUSE_PRESSED | MouseEvent.MOUSE_RELEASED | MouseEvent.MOUSE_DRAGGED | MouseEvent.MOUSE_MOVED);		
		this.start = new Point(0, 0);
		this.end = new Point(0, 0);
		this.colour = Color.BLACK;
		this.dragging = false;
		this.selecting = false;
		this.currentTool = null;
		this.fileChooser = this.constructFileChooser();
		this.mode = Mode.Immediate;
		
		History.get().addHistoryChangedListener(new HistoryChangedHandler(this));
	}
	
	private JFileChooser constructFileChooser() {
		JFileChooser chooser = new JFileChooser(System.getProperty("user.home"));
		
		FileFilter fooFilter = new FileNameExtensionFilter("Supported image files (*.gif, *.jpeg and *.png)", "gif", "jpeng", "jpg", "png");
		
		chooser.addChoosableFileFilter(fooFilter);
		chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		
		return chooser;
	}
	
	public Observer getToolChangeHandler() {
		return this.toolChangeHandler;
	}
	
	@Override
	protected void processMouseEvent(MouseEvent e) {
		super.processMouseEvent(e);
				
		
		if (e.getID() == MouseEvent.MOUSE_PRESSED) {
			this.lastPoint = e.getPoint();
			this.dump("mpress");			
			switch (this.mode) {
			case Immediate:				
				if (this.currentTool != null) {
					this.startDragging(e.getPoint());
				}			
				else {
					JOptionPane.showMessageDialog(this,	"Please, select a tool first (Toolbox is to the left)");
				}
				break;
				
			case Selection:
				this.startSelecting(e.getPoint());
				break;
			}
		}
		else if (e.getID() == MouseEvent.MOUSE_RELEASED) {
			this.lastPoint = e.getPoint();
			this.dump("mrel");
			switch (this.mode) {
			case Immediate:
				if (this.dragging) {
					this.stopDragging(e.getPoint());
				}
				break;
				
			case Selection:
				if (this.selecting) {
					this.stopSelecting(e.getPoint());
				}
				break;
			}
						
		}
	}
	
	@Override
	protected void processMouseMotionEvent(MouseEvent e) {
		super.processMouseMotionEvent(e);		
		
		if (e.getID() == MouseEvent.MOUSE_DRAGGED) {
			this.lastPoint = e.getPoint();
			this.dump("mdrag");
			switch (this.mode) {
			case Immediate:
				if (this.dragging) {
					this.dragTo(e.getPoint());
				}
				break;
			case Selection:
				if (this.selecting) {
					this.moveSelection(e.getPoint());
				}
				break;
			}						
		}
		else if (e.getID() == MouseEvent.MOUSE_MOVED) {
			this.lastPoint = e.getPoint();
		}
	}
	
	private void startSelecting(Point2D start) {		
		SelectableTool selected = this.findSelectable(start);
		if (selected != null) {
			this.currentTool = selected;
			this.selecting = true;
			this.repaint();
		}
		else {
			this.selecting = false;
		}
	}
	
	private SelectableTool findSelectable(Point2D atPoint) {
		for (Tool tool: History.get().getItems()) {
			if (tool instanceof SelectableTool) {
				SelectableTool selectable = (SelectableTool) tool;
				if (selectable.select(atPoint)) {
					this.dump("found selectable");
					return selectable;
				}
			}
		}
		return null;
	}
	
	private void moveSelection(Point2D newPt) {
		((SelectableTool) this.currentTool).moveTo(newPt);
		this.repaint();
	}
	
	private void stopSelecting(Point2D newPt) {
		((SelectableTool) this.currentTool).moveTo(newPt);
		this.dump("stop selection");
		this.selecting = false;
		this.repaint();
	}
	
	private void startDragging(Point2D origin) {
		this.dragging = true;
		this.start = origin;;
		this.end = origin;
		if (this.currentTool != null) {
			this.currentTool.update(start, end, this.colour);
			this.repaint();
		}
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
		
		switch (this.mode) {
		case Immediate:
			if (this.currentTool != null && this.dragging) {			
				this.currentTool.render(g);
			}
			break;
		case Selection:
			break;
		}
		
		
	}
	
	private void dump(String msg) {
		String at = "{}";
		if (this.lastPoint != null) {
			// we calc them as ints always, so int is more useful to print
			at = String.format("{%d, %d}", (int) this.lastPoint.getX(), (int) this.lastPoint.getY());
		}		
		System.out.printf("%s, %s at %s dragging=%s, selecting=%s\n",
				msg, this.mode, at, this.dragging, this.selecting);
	}
	
	private void setMode(Mode newMode, Point2D atPoint) {
		System.out.println("setMode(" + newMode +") at " + atPoint);
		if (newMode != this.mode) {
			switch (this.mode) {
			case Immediate:
				if (this.dragging)
					this.stopDragging(atPoint);
				break;
			case Selection:
				if (this.selecting)
					this.stopSelecting(atPoint);
				break;
			}
			this.mode = newMode;
		}
	}
	
	void onToolChanged(ToolIDs newTool) {
		switch (newTool) {
		case PICK_COLOUR:
			this.colour = JColorChooser.showDialog(this, "Pick a colour", this.colour);
			this.setMode(Mode.Immediate, this.lastPoint);
			break;
		
		case DRAW_IMPORTED_IMAGE:
			if (this.fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
				File imgFile = this.fileChooser.getSelectedFile();
				this.currentTool = ToolFactory.getToolByID(newTool);
				this.currentTool.initialize(new Object[] {imgFile, this});
				this.setMode(Mode.Immediate, this.lastPoint);
			}
			break;
		
		case SELECT_OBJECT:
			this.setMode(Mode.Selection, this.lastPoint);
			break;
		
		default:
			this.currentTool = ToolFactory.getToolByID(newTool);
			this.setMode(Mode.Immediate, this.lastPoint);
			this.repaint();
			break;
		}		
	}
	
	void onHistoryChanged(Iterable<Tool> arg1) {		
		//JOptionPane.showMessageDialog(this, "History changed");
		this.archivedTools = arg1;
		this.repaint();
	}
	
	private static enum Mode {
		Immediate,
		Selection
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
