package uk.ac.standrews.cs5001.foopaint.ui.tools;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.geom.Point2D;

import uk.ac.standrews.cs5001.foopaint.data.DrawableItem;
import uk.ac.standrews.cs5001.foopaint.data.Line;

/**
 * Tool that represents a line between two points
 * @author <110017972>
 *
 */
class LineTool implements Tool {
	/** Internal representation */
	private Line data;

	@Override
	public void update(Point2D start, Point2D end, Color color) {
		int x1 = (int) start.getX();
		int y1 = (int) start.getY();
		int x2 = (int) end.getX();
		int y2 = (int) end.getY();
		this.data = new Line(x1, y1, x2, y2);
		this.data.setBrush(Convert.colourToBrush(color, true));
	}
	
	@Override
	public void update(DrawableItem drawable) {
		Line another = (Line) drawable;
		this.data = new Line(another);
	}
	@Override
	public void update(Object toolSpecificData) {
		// do nothing
	}

	@Override
	public void render(Graphics grfx) {
		grfx.setColor(Convert.brushToColour(this.data.getBrush()));
		grfx.drawLine(this.data.getOriginX(), this.data.getOriginY(), this.data.getEndX(), this.data.getEndY());
	}

	@Override
	public DrawableItem getItem() {
		return this.data;
	}

	@Override
	public Tool copy() {
		LineTool other = new LineTool();
		other.data = new Line(this.data);
		return other;
	}	
}