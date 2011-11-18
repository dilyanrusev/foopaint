package uk.ac.standrews.cs5001.foopaint.ui.tools;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.geom.Point2D;

import uk.ac.standrews.cs5001.foopaint.data.DrawableItem;

/**
 * Link between UI action, drawing and saving. Represents a shape.
 * @author <110017972>
 *
 */
public interface Tool {
	/**
	 * Update the internal state of the tool with data gathered by the UI
	 * @param start First (start, origin) point
	 * @param end Second (end) point
	 * @param color Colour to use for drawing/filling the shape
	 */
	void update(Point2D start, Point2D end, Color color);
	
	/**
	 * Update the internal state of the tool with data read from a file
	 * @param drawable Data required for drawing the shape
	 */
	void update(DrawableItem drawable);
	
	/**
	 * Draw the shape represented by this tool
	 * @param grfx Graphics context to use for rendering
	 */
	void render(Graphics grfx);
	
	/**
	 * Convert the internal state of the tool to a form suitable for serialisation
	 * @return Complete information needed to draw the shape, which is suitable for serialisation
	 */
	DrawableItem getItem();
	
	/**
	 * Create a copy of this tool
	 * @return Replica of this tool
	 */
	Tool copy();
}
