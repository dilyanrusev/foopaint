package uk.ac.standrews.cs5001.foopaint.ui.tools;

import java.awt.geom.Point2D;

import uk.ac.standrews.cs5001.foopaint.data.BoxedShape;

/**
 * Implements selection/movement
 * @author <11001792>
 *
 */
class BoxSelectionHelper {	
	/** Point where the mouse was when movement started */
	private Point2D moveOrigin;
	/** Initial top-left when movement started */
	private int xShapeOrigin;
	/** Initial top-left when movement started */
	private int yShapeOrigin;
	
	public boolean select(Point2D point, BoxedShape box) {	
		int x = box.getX();
		int y = box.getY();
		int w = box.getWidth();
		int h = box.getHeight();
		
		// m stands for mouse
		int mx = (int) point.getX();
		int my = (int) point.getY();
		
		boolean within = mx >= x && mx <= x + w && my >= y && my <= y + h;
		
		if (within) {
			this.moveOrigin = point;
			this.xShapeOrigin = box.getX();
			this.yShapeOrigin = box.getY();
		}
		
		return within;
	}
	
	public void moveTo(Point2D point, BoxedShape box) {
		int dx = (int) (point.getX() - this.moveOrigin.getX());
		int dy = (int) (point.getY() - this.moveOrigin.getY());
		
		box.setX(this.xShapeOrigin + dx);
		box.setY(this.yShapeOrigin + dy);
	}
}