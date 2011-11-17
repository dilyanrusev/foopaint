package uk.ac.standrews.cs5001.foopaint.ui.tools;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.geom.Point2D;

import uk.ac.standrews.cs5001.foopaint.data.DrawableItem;
import uk.ac.standrews.cs5001.foopaint.data.Ellipse;
import uk.ac.standrews.cs5001.foopaint.data.Line;
import uk.ac.standrews.cs5001.foopaint.data.Rectangle;
import uk.ac.standrews.cs5001.foopaint.data.VectorShape;

public interface Tool {
	void update(Point2D start, Point2D end, Color color);	
	void render(Graphics grfx);
	DrawableItem getItem();
	Tool copy();
}

class LineTool implements Tool {
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

abstract class BaseBoxTool<TData> implements Tool {
	private TData data;
	private boolean solid;
	
	public BaseBoxTool(boolean solid) {
		this.solid = solid;
	}
	
	@Override
	public void update(Point2D start, Point2D end, Color color) {
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

	protected abstract TData createDataItem(int x, int y, int w, int h);
	
	protected TData getData() {
		return this.data;
	}
	
	protected void setData(TData data) {
		this.data = data;
	}
}

class RectangleTool extends BaseBoxTool<Rectangle> {

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
		
}

class SolidRectangleTool extends BaseBoxTool<Rectangle> {

	public SolidRectangleTool() {
		super(true);
	}
	
	@Override
	public void render(Graphics grfx) {
		grfx.setColor(Convert.brushToColour(this.getData().getBrush()));
		grfx.fillRect(this.getData().getX(), this.getData().getY(), this.getData().getWidth(), this.getData().getHeight());
	}

	@Override
	public Tool copy() {
		SolidRectangleTool other = new SolidRectangleTool();
		other.setData(new Rectangle(this.getData()));
		return other;
	}

	@Override
	protected Rectangle createDataItem(int x, int y, int w, int h) {
		return new Rectangle(x, y, w, h);
	}
	
}

class EllipseTool extends BaseBoxTool<Ellipse> {

	public EllipseTool() {
		super(false);
	}
	
	@Override
	public void render(Graphics grfx) {
		grfx.setColor(Convert.brushToColour(this.getData().getBrush()));
		grfx.drawOval(this.getData().getX(), this.getData().getY(), this.getData().getWidth(), this.getData().getHeight());
	}

	@Override
	public Tool copy() {
		EllipseTool other = new EllipseTool();
		other.setData(new Ellipse(this.getData()));
		return other;
	}

	@Override
	protected Ellipse createDataItem(int x, int y, int w, int h) {
		return new Ellipse(x, y, w, h);
	}
		
}

class SolidEllipseTool extends BaseBoxTool<Ellipse> {

	public SolidEllipseTool() {
		super(true);
	}
	
	@Override
	public void render(Graphics grfx) {
		grfx.setColor(Convert.brushToColour(this.getData().getBrush()));
		grfx.fillOval(this.getData().getX(), this.getData().getY(), this.getData().getWidth(), this.getData().getHeight());
	}

	@Override
	public Tool copy() {
		SolidEllipseTool other = new SolidEllipseTool();
		other.setData(new Ellipse(this.getData()));
		return other;
	}

	@Override
	protected Ellipse createDataItem(int x, int y, int w, int h) {
		return new Ellipse(x, y, w, h);
	}
		
}
