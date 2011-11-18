package uk.ac.standrews.cs5001.foopaint;

import javax.swing.SwingUtilities;

import uk.ac.standrews.cs5001.foopaint.ui.MainWindow;

/**
 * Entry point for the application
 * @author <110017972>
 *
 */
public class App {
	/**
	 * Program entry point
	 * @param args CLI arguments - ignored
	 */
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				new MainWindow();
			}
			
		});
	}
}
