package uk.ac.standrews.cs5001.foopaint.ui;

import java.awt.BorderLayout;
import java.awt.Toolkit;
import java.awt.event.WindowEvent;
import java.util.Observable;
import java.util.Observer;

import javax.swing.*;


public class MainWindow extends JFrame {
	private static final long serialVersionUID = 1L;

	public MainWindow() {
		this.buildLayout();
		
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setTitle("Foo Paint");
		this.setIconImage(Toolkit.getDefaultToolkit().getImage("data/fill_color.png"));
	}
	
	protected void buildLayout() {
		this.getContentPane().setLayout(new BorderLayout());
		
		MenuPanel menu = new MenuPanel();
		ToolsPanel tools = new ToolsPanel();
		DrawingPanel drawing = new DrawingPanel();
		
		tools.addToolChangeListener(drawing.getToolChangeHandler());	
		menu.addActionChangedListener(new ActionChangeHandler(this));
			
		this.getContentPane().add(menu, BorderLayout.NORTH);
		this.getContentPane().add(tools, BorderLayout.WEST);
		this.getContentPane().add(drawing, BorderLayout.CENTER);
		
		this.pack();
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
	
	public void onActionChanged(ActionIDs id) {
		switch (id) {
		case EXIT:
			this.terminate();
			break;
		case UNDO:
			History.get().undo();
			break;
		case REDO:
			History.get().redo();
			break;
		default:
			JOptionPane.showMessageDialog(this, "Not implemented: " + id);
			break;
		}
	}
	
	private void terminate() {
		WindowEvent wev = new WindowEvent(this, WindowEvent.WINDOW_CLOSING);
        Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(wev);
	}
	
	private static class ActionChangeHandler implements Observer {
		private MainWindow parent;
		
		public ActionChangeHandler(MainWindow parent) {
			this.parent = parent;
		}
		
		@Override
		public void update(Observable o, Object arg) {
			if (arg instanceof ActionIDs) {
				ActionIDs id = (ActionIDs) arg;
				this.parent.onActionChanged(id);
			}
		}
	}
}
