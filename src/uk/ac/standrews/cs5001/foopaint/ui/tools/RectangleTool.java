package uk.ac.standrews.cs5001.foopaint.ui.tools;

import java.awt.Graphics;
import java.awt.geom.Point2D;

import uk.ac.standrews.cs5001.foopaint.data.DrawableItem;
import uk.ac.standrews.cs5001.foopaint.data.Rectangle;

/**
 * Tool that represents a rectangle drawn only by its contour
 * @author <110017972>
 *
 */
class RectangleTool extends BaseBoxTool<Rectangle> implements SelectableTool {
	/** Point where the mouse was when movement started */
	private Point2D moveOrigin;
	/** Initial top-left when movement started */
	private int xShapeOrigin;
	/** Initial top-left when movement started */
	private int yShapeOrigin;
	
	/**
	 * Create a new Rectangle tool
	 */
	public RectangleTool() {
		super(false);
	}
	
	@Override
	public void render(Graphics grfx) {
		grfx.setColor(Convert.brushToColour(this.getData().getBrush()));
		grfx.drawRect(this.getData().getX(), this.getData().getY(), this.getData().getWidth(), this.getData().getHeight());
	}

	@Override
	public Tool copy() {
		RectangleTool other = new RectangleTool();
		other.setData(new Rectangle(this.getData()));
		return other;
	}

	@Override
	protected Rectangle createDataItem(int x, int y, int w, int h) {
		return new Rectangle(x, y, w, h);
	}
	
	@Override
	public void update(DrawableItem drawable) {
		Rectangle another = (Rectangle) drawable;
		this.setData(new Rectangle(another));	
	}

	@Override
	public boolean select(Point2D point) {
		int x = this.getData().getX();
		int y = this.getData().getY();
		int w = this.getData().getWidth();
		int h = this.getData().getHeight();
		
		// m stands for mouse
		int mx = (int) point.getX();
		int my = (int) point.getY();
		
		boolean within = mx >= x && mx <= x + w && my >= y && my <= y + h;
		
		if (within) {
			this.moveOrigin = point;
			this.xShapeOrigin = this.getData().getX();
			this.yShapeOrigin = this.getData().getY();
		}
		
		return within;
	}
	
	@Override
	public void moveTo(Point2D point) {
		int dx = (int) (point.getX() - this.moveOrigin.getX());
		int dy = (int) (point.getY() - this.moveOrigin.getY());
		
		this.getData().setX(this.xShapeOrigin + dx);
		this.getData().setY(this.yShapeOrigin + dy);
	}
		
}