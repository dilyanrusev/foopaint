package uk.ac.standrews.cs5001.foopaint.ui;

import java.awt.BorderLayout;
import java.awt.Toolkit;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

import javax.swing.*;

import uk.ac.standrews.cs5001.foopaint.data.Document;


public class MainWindow extends JFrame {
	private static final long serialVersionUID = 1L;
	private JFileChooser fileChooser;
	private String lastFileName;

	public MainWindow() {
		this.fileChooser = new JFileChooser(new File(System.getProperty("user.home")));
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
		case OPEN:
			if (this.fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
				this.lastFileName = this.fileChooser.getSelectedFile().toString();
				this.loadStateFromFile(this.lastFileName);
			}
			break;
		case SAVE:
			if (this.lastFileName != null) {
				this.saveCurrentState(this.lastFileName);
				break;
			}
			// else - fall through (to SAVE_AS)
		case SAVE_AS:
			if (this.fileChooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
				this.lastFileName = this.fileChooser.getSelectedFile().toString();
				this.saveCurrentState(this.lastFileName);
			}
			break;
		default:
			JOptionPane.showMessageDialog(this, "Not implemented: " + id);
			break;
		}
	}
	
	protected void loadStateFromFile(String fileName) {		
		try {
			Document doc = new Document(fileName);
			History.get().parseDocument(doc);
			this.lastFileName = fileName;
		}
		catch (IOException e) {
			JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);			
		}		
	}
	
	protected void saveCurrentState(String fileName) {		
		try {
			Document doc = History.get().constructDocument();
			doc.save(fileName);
			this.lastFileName = fileName;
		}
		catch (IOException e) {
			JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);			
		}		
	}
	
	protected void terminate() {
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
