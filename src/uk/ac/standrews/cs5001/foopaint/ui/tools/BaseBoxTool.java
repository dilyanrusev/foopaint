package uk.ac.standrews.cs5001.foopaint.ui.tools;

import java.awt.Color;
import java.awt.geom.Point2D;

import uk.ac.standrews.cs5001.foopaint.data.BoxedShape;
import uk.ac.standrews.cs5001.foopaint.data.DrawableItem;
import uk.ac.standrews.cs5001.foopaint.data.VectorShape;

/**
 * Base class for tools that can be defined by a bounding rectangle
 * @author <110017972>
 *
 * @param <TData> Type of the internal shape representation
 */
abstract class BaseBoxTool<TData> implements SelectableTool {
	/** Internal representation */
	private TData data;
	/** True if the shape is filled with colour, false if only a contour is drawn instead */
	private boolean solid;
	/** Point where the mouse was when movement started */
	private Point2D moveOrigin;
	/** Initial top-left when movement started */
	private int xShapeOrigin;
	/** Initial top-left when movement started */
	private int yShapeOrigin;
	
	/**
	 * Create a new tool
	 * @param solid True if the shape is filled with colour, false if only a contour is drawn instead
	 */
	public BaseBoxTool(boolean solid) {
		this.solid = solid;
	}
	
	@Override
	public void initialize(Object toolSpecificData) {
		// by default, do nothing
	}
	
	@Override
	public void update(Point2D start, Point2D end, Color color) {
		/*
		 * These calculations are necessary, because the Graphics object
		 * accepts {TopLeft, Dimensions} for input when dealing 
		 * with Ovals and Rectangles.
		 * 
		 * Here is a short mathematical explanation
		 * S - Starting point (where mouse was originally pressed); Sx, Sy - components
		 * S' - (top,left) point needed by Graphics; S'x, S'y - components
		 * E - Ending point (where mouse was released); Ex, Ey - components
		 * 
		 * dx = Ex - Sx
		 * dy = Ey - Sy
		 * w = |dx|
		 * h = |dy|
		 * 
		 * There are four ways S and E can be in relation to each other:
		 * 1) dx >= 0, dy >=0 ==> S'(Sx, Sy)
		 *    S==S'
		 *    *============
		 *    |           |
		 *    ============*
		 *                E
		 * 2) dx >= 0, dy < 0 ==> S'(Sx, Sy - h) == S'(Sx, Sy + dy)
		 *    S' 
		 *    *============
		 *    |           |
		 *    *===========*
		 *    S           E
		 * 
		 * 3) dx < 0, dy >= 0 ==> S'(Sx - w, Sy) == S'(Sx + dx, Sy)
		 *    S'          S
		 *    *===========*
		 *    |           |
		 *    *============
		 *    E
		 *    
		 * 4) dx < 0, dy < 0 ==> E == S'(Sx - w, Sy - h) == S'(Sx + dx, Sy + dy)
		 *    E==S'       
		 *    *===========
		 *    |          |
		 *    ===========*
		 *               S
		 */
		
		int x1 = (int) start.getX();
		int y1 = (int) start.getY();
		int x2 = (int) end.getX();
		int y2 = (int) end.getY();
		
		int dx = x2 - x1;
		int dy = y2 - y1;
		int w = Math.abs(dx);
		int h = Math.abs(dy);
		
		int x = x1;
		if (dx < 0)
			x += dx;
		int y = y1;
		if (dy < 0)
			y += dy;
		
		this.data = this.createDataItem(x, y, w, h);
		if (data instanceof VectorShape) {
			VectorShape vector = (VectorShape) data;
			vector.setBrush(Convert.colourToBrush(color, this.solid));
		}		
	}
	
	@Override
	public DrawableItem getItem() {
		if (this.data instanceof DrawableItem) {
			return (DrawableItem) this.data;
		}
		return null;
	}
	
	@Override
	public boolean select(Point2D point) {
		DrawableItem shape = this.getItem();
		if (shape instanceof BoxedShape) {
			BoxedShape box = (BoxedShape) shape;
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
		else {
			return false;
		}
	}
	
	@Override
	public void moveTo(Point2D point) {
		DrawableItem shape = this.getItem();
		if (shape instanceof BoxedShape) {
			BoxedShape box = (BoxedShape) shape;
			int dx = (int) (point.getX() - this.moveOrigin.getX());
			int dy = (int) (point.getY() - this.moveOrigin.getY());
			
			box.setX(this.xShapeOrigin + dx);
			box.setY(this.yShapeOrigin + dy);
		}		
	}

	/**
	 * Create an instance of the internal data type
	 * @param x Horizontal component of the Top-Left coordinate
	 * @param y Vertical component of the Top-Left coordinate
	 * @param w Width
	 * @param h Height
	 * @return Internal data type representation
	 */
	protected abstract TData createDataItem(int x, int y, int w, int h);
	
	/**
	 * Get an instance of the internal data representation
	 * @return Internal data representation
	 */
	protected TData getData() {
		return this.data;
	}
	
	/**
	 * Set the internal data representation
	 * @param data New value for the internal data representation
	 */
	protected void setData(TData data) {
		this.data = data;
	}
} 

class BoxSelectionHelper {
	BoxedShape shape;
}