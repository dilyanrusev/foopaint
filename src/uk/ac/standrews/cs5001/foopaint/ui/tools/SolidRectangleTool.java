package uk.ac.standrews.cs5001.foopaint.ui.tools;

import java.awt.Graphics;

import uk.ac.standrews.cs5001.foopaint.data.DrawableItem;
import uk.ac.standrews.cs5001.foopaint.data.Rectangle;

/**
 * Tool that represents a rectangle filled with colour
 * @author <110017972>
 *
 */
class SolidRectangleTool extends BaseBoxTool<Rectangle> {

	/**
	 * Create a new solid rectangle
	 */
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
	
	@Override
	public void update(DrawableItem drawable) {
		Rectangle another = (Rectangle) drawable;
		this.setData(new Rectangle(another));	
	}
	
}