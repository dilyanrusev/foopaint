package uk.ac.standrews.cs5001.foopaint.ui.draw;

import java.awt.Color;
import java.awt.Point;
import java.awt.geom.Point2D;

public abstract class BaseDrawState implements DrawState {
	private Point2D start;
	private Point2D end;
	private Color colour;
	
	public BaseDrawState() {
		this.start = new Point(0, 0);
		this.end = new Point(0, 0);
		this.colour = Color.BLACK;
	}
	
	public BaseDrawState(BaseDrawState other) {
		this.start = other.start;
		this.end = other.end;
		this.colour = other.colour;
	}
	
	
	/* (non-Javadoc)
	 * @see uk.ac.standrews.cs5001.foopaint.ui.draw.DrawState#getStartX()
	 */
	@Override
	public int getStartX() {
		return (int) this.start.getX();
	}
	
	/* (non-Javadoc)
	 * @see uk.ac.standrews.cs5001.foopaint.ui.draw.DrawState#getStartY()
	 */
	@Override
	public int getStartY() {
		return (int) this.start.getY();
	}
	
	/* (non-Javadoc)
	 * @see uk.ac.standrews.cs5001.foopaint.ui.draw.DrawState#getEndX()
	 */
	@Override
	public int getEndX() {
		return (int) this.end.getX();
	}
	
	/* (non-Javadoc)
	 * @see uk.ac.standrews.cs5001.foopaint.ui.draw.DrawState#getEndY()
	 */
	@Override
	public int getEndY() {
		return (int) this.end.getY();
	}
	
	/* (non-Javadoc)
	 * @see uk.ac.standrews.cs5001.foopaint.ui.draw.DrawState#getStart()
	 */
	@Override
	public Point2D getStart() {
		return this.start;
	}
	
	/* (non-Javadoc)
	 * @see uk.ac.standrews.cs5001.foopaint.ui.draw.DrawState#getEnd()
	 */
	@Override
	public Point2D getEnd() {
		return this.end;
	}
	
	/* (non-Javadoc)
	 * @see uk.ac.standrews.cs5001.foopaint.ui.draw.DrawState#getColour()
	 */
	@Override
	public Color getColour() {
		return this.colour;
	}
	
	public void setColour(Color c) {
		this.colour = c;
	}
	
	public void setStart(Point2D pt) {
		this.start = pt;
	}
	
	public void setEnd(Point2D pt) {
		this.end = pt;
	}

}
