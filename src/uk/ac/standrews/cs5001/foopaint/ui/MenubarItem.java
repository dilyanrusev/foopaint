package uk.ac.standrews.cs5001.foopaint.ui;

import java.io.File;
import java.net.MalformedURLException;

import javax.swing.*;

public class MenubarItem extends JButton {
	private static final long serialVersionUID = 0L;

	public MenubarItem(String path, String text) {
		ImageIcon icon = this.loadIcon(path, text);
		if (icon != null) {
			this.setIcon(icon);
		}
		this.buildLayout();
		this.setText(text);
	}
	
	protected void buildLayout() {
		this.setVerticalTextPosition(SwingConstants.BOTTOM);
		this.setHorizontalTextPosition(SwingConstants.CENTER);
	}
	
	protected ImageIcon loadIcon(String path, String description) {
		File basePath = new File(".");
		File localPath = new File(basePath, path);
		try {
			return new ImageIcon(localPath.toURI().toURL(), description);
		} catch (MalformedURLException e) {
			return null;
		}
	}
}
