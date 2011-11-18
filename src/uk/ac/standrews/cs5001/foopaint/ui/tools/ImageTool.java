package uk.ac.standrews.cs5001.foopaint.ui.tools;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.geom.Point2D;
import java.awt.image.ImageObserver;
import java.io.File;

import uk.ac.standrews.cs5001.foopaint.data.DrawableItem;
import uk.ac.standrews.cs5001.foopaint.data.ImageData;

/**
 * Tool that can draw imported images
 * @author <110017972>
 *
 */
public class ImageTool implements Tool {
	/** serializable data */
	private ImageData data;
	/** actual binary image to draw */
	private Image image;
	private ImageObserver imageObserver;

	/**
	 * Create a new tool
	 */
	public ImageTool() {
		this.data = new ImageData();
		this.image = null;
		this.imageObserver = null;
	}
	
	/**
	 * Copy another instance of the image tool
	 * @param other Instance to copy from
	 */
	public ImageTool(ImageTool other) {
		this.data = new ImageData(other.data);
		// binary data is shared
		this.image = other.image;
		this.imageObserver = other.imageObserver;
	}
	
	@Override
	public void update(Point2D start, Point2D end, Color color) {
		int x = (int)end.getX();
		int y = (int)end.getY();
		this.data.setPosition(x, y);
		this.data.setErrorBrush(Convert.colourToBrush(color, false));
	}

	@Override
	public void update(DrawableItem drawable) {
		if (drawable instanceof ImageData) {
			this.data = new ImageData((ImageData)drawable);
			this.loadImage();
		}
	}
	

	/**
	 * Set image source and/or ImageObserver (most Swing components implement it; preferably the one that passes Graphics to this tool)
	 * @param toolSpecificData One of the following: String, File, Object[] { String, ImageObserver }, Object[] { File, ImageObserver }
	 */
	@Override
	public void initialize(Object toolSpecificData) {
		Object path = toolSpecificData;
		Object observer = toolSpecificData;
		
		if (toolSpecificData instanceof Object[]) {
			Object[] params = (Object[])toolSpecificData;
			if (params.length >= 1)
				path = params[0];
			if (params.length >= 2)
				observer = params[1];
		}
		
		if (path instanceof String) {
			this.data.setPath((String)path);
			this.loadImage();
		}
		if (path instanceof File) {
			File newPath = (File) path;
			this.data.setPath(newPath.toString());
			this.loadImage();
		}
		if (observer instanceof ImageObserver) {
			this.imageObserver = (ImageObserver) observer;
		}
	}
	
	/**
	 * Load binary image using current state
	 */
	private void loadImage() {
		this.image = Toolkit.getDefaultToolkit().createImage(this.data.getPath());
	}

	@Override
	public void render(Graphics grfx) {
		if (this.image != null) {
			grfx.drawImage(this.image, this.data.getX(), this.data.getY(), this.imageObserver);
		}
		else {
			grfx.setColor(Convert.brushToColour(this.data.getErrorBrush()));
			grfx.drawString("No image was loaded", this.data.getX(), this.data.getX());
		}
	}

	@Override
	public DrawableItem getItem() {
		return this.data;
	}

	@Override
	public Tool copy() {
		return new ImageTool(this);
	}
	
}