package uk.ac.standrews.cs5001.foopaint.ui.tools;

import java.awt.Graphics;

import uk.ac.standrews.cs5001.foopaint.data.DrawableItem;
import uk.ac.standrews.cs5001.foopaint.data.Ellipse;

/**
 * Tool that represents an ellipse filled with solid colour
 * @author <110017972>
 *
 */
class SolidEllipseTool extends BaseBoxTool<Ellipse> {

	/**
	 * Create a new solid ellipse
	 */
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
		
	@Override
	public void update(DrawableItem drawable) {
		Ellipse another = (Ellipse) drawable;
		this.setData(new Ellipse(another));	
	}
	
}