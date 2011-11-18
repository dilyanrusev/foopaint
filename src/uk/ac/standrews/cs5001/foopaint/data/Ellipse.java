package uk.ac.standrews.cs5001.foopaint.data;

import java.io.Serializable;

/**
 * Class that stores information about how to draw an ellipse
 * @author <110017972>
 *
 */
public class Ellipse extends BaseBoxedShape implements Serializable {
	/** Incremented when new serializable fields are added */
	private static final long serialVersionUID = 1L;

	/**
	 * Create a new ellipse with default values
	 */
	public Ellipse() {
		this(0, 0, 0, 0);
	}
	
	/**
	 * Create a new ellipse
	 * @param x Left coordinate component of the encompassing rectangle
	 * @param y Top coordinate component of the encompassing rectangle
	 * @param width Width of the encompassing rectangle
	 * @param height Height of the encompassing rectangle
	 */
	public Ellipse(int x, int y, int width, int height) {
		super(x, y ,width, height);
	}
	
	/**
	 * Create a copy of another ellipse
	 * @param other Ellipse to copy from
	 */
	public Ellipse(Ellipse other) {
		super(other);
	}
}
