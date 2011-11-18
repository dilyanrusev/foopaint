package uk.ac.standrews.cs5001.foopaint.ui;

import java.awt.BorderLayout;
import java.awt.Toolkit;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import uk.ac.standrews.cs5001.foopaint.data.Document;


public class MainWindow extends JFrame {
	private static final long serialVersionUID = 1L;
	private JFileChooser fileChooser;
	private String lastFileName;

	public MainWindow() {
		this.setNativeLookAndFeel();
		this.fileChooser = this.constructFileChooser();
		this.buildLayout();
		History.get().addHistoryChangedListener(new HistoryChangeHandler(this));
		this.setTitle("Foo Paint");
		this.setIconImage(Toolkit.getDefaultToolkit().getImage("data/fill_color.png"));
		//this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.enableEvents(WindowEvent.WINDOW_CLOSING);
	}
	
	protected void buildLayout() {
		// create panels
		MenuPanel menu = new MenuPanel();
		ToolsPanel tools = new ToolsPanel();
		DrawingPanel drawing = new DrawingPanel();
		
		// wire events
		tools.addToolChangeListener(drawing.getToolChangeHandler());	
		menu.addActionChangedListener(new ActionChangeHandler(this));		
		
		// fire events for default values
		History.get().raiseHistoryChanged();
		tools.selectDefaultTool();
			
		// setup layout
		this.getContentPane().setLayout(new BorderLayout());
		
		this.getContentPane().add(menu, BorderLayout.NORTH);
		this.getContentPane().add(tools, BorderLayout.WEST);
		this.getContentPane().add(drawing, BorderLayout.CENTER);
		
		// place, size and view
		this.pack();
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
	
	private void setNativeLookAndFeel() {
		String lookAndFeelName = UIManager.getSystemLookAndFeelClassName();
		try {
			UIManager.setLookAndFeel(lookAndFeelName);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
	}
	
	private JFileChooser constructFileChooser() {
		JFileChooser chooser = new JFileChooser(System.getProperty("user.home"));
		
		FileFilter fooFilter = new FileNameExtensionFilter("Foo Paint drawings (*.foo)", "foo");
		
		chooser.addChoosableFileFilter(fooFilter);
		chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		
		return chooser;
	}
	
	public void onHistoryChanged() {
		
	}
	
	public void onActionChanged(ActionIDs id) {
		switch (id) {
		case EXIT:
			this.doFileExit();
			break;
		case UNDO:
			History.get().undo();
			break;
		case REDO:
			History.get().redo();
			break;
		case CREATE_NEW:
			this.doFileNew();
			break;
		case OPEN:
			this.doFileOpen();
			break;
		case SAVE:
			this.doFileSave();
			break;
		case SAVE_AS:
			this.doFileSaveAs();
			break;
		default:
			JOptionPane.showMessageDialog(this, "Not implemented: " + id);
			break;
		}
	}
	
	@Override
	protected void processWindowEvent(WindowEvent e) {
		if (e.getID() == WindowEvent.WINDOW_CLOSING && History.get().isModified()) {
			int answer = JOptionPane.showConfirmDialog(this, 
					"Closing the application will discard your changes. Do you want to save first?",
					"Save before quitting", JOptionPane.YES_NO_CANCEL_OPTION);
			
			switch (answer) {
			case JOptionPane.YES_OPTION:
				this.doFileSave();
				System.exit(0);
				break;
			case JOptionPane.NO_OPTION:
				System.exit(0);
				return;
			case JOptionPane.CANCEL_OPTION:
				// do nothing
				break;
			}
		}
		else {
			super.processWindowEvent(e);
		}
	}
	
	private void doFileExit() {
		this.raiseWindowClosing();
	}

	private void doFileNew() {
		boolean proceed = true;
		if (History.get().isModified()) {
			int answer = JOptionPane.showConfirmDialog(this, 
					"Opening a new document will discard your changes. Do you want to save first?",
					"Save", JOptionPane.YES_NO_CANCEL_OPTION);
			
			switch (answer) {
			case JOptionPane.YES_OPTION:
				this.doFileSave();
				break;
			case JOptionPane.NO_OPTION:
				break;
			case JOptionPane.CANCEL_OPTION:
				proceed = false;
				break;
			}
		}
		
		if (proceed) {
			History.get().clear();
			History.get().setModified(false);
			this.lastFileName = null;
		}
	}

	private void doFileOpen() {
		boolean proceed = true;
		if (History.get().isModified() && 
				!this.askUser("Opening a new document will discard your changes. Continue?")) {
			proceed = false;
		}
		if (proceed && this.fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
			this.lastFileName = this.fileChooser.getSelectedFile().toString();
			this.loadStateFromFile(this.lastFileName);
		}
	}
	
	private void doFileSave() {
		if (this.lastFileName != null) {
			this.saveCurrentState(this.lastFileName);
		}
		else {
			this.doFileSaveAs();
		}
	}
	
	private void doFileSaveAs() {
		if (this.fileChooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
			this.lastFileName = this.fileChooser.getSelectedFile().toString();
			this.saveCurrentState(this.lastFileName);
		}
	}
	
	protected boolean askUser(String message) {
		return this.askUser(message, "Drawing modified");
	}
	
	protected boolean askUser(String message, String title) {
		if (History.get().isModified()) {
			int answer = JOptionPane.showConfirmDialog(this, message, title, JOptionPane.YES_NO_OPTION);
			return JOptionPane.YES_OPTION == answer;
		}
		else {
			return true;
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
			History.get().setModified(false);
		}
		catch (IOException e) {
			JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);			
		}		
	}
	
	protected void raiseWindowClosing() {
		WindowEvent wev = new WindowEvent(this, WindowEvent.WINDOW_CLOSING);
        Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(wev);
	}
	
	private static class HistoryChangeHandler implements Observer {
		private MainWindow parent;
		
		public HistoryChangeHandler(MainWindow parent) {
			this.parent = parent;
		}
		
		@Override
		public void update(Observable o, Object arg) {
			this.parent.onHistoryChanged();
		} 
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
