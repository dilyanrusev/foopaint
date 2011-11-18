package uk.ac.standrews.cs5001.foopaint.ui.tools;

import java.awt.geom.Point2D;

/**
 * Marker interface for tools that can be selected with a mouse click
 * @author <110017972>
 *
 */
public interface SelectableTool extends Tool {
	/**
	 * Return true if the point is within the tool
	 * @param point Mouse click point
	 * @return True if tool was selected, false otherwise
	 */
	boolean select(Point2D point);
	
	/**
	 * Change the internal state so that the object should move relative to the point given in select(Point2D)
	 * @param point Point to move to
	 */
	void moveTo(Point2D point);
}