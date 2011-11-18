package uk.ac.standrews.cs5001.foopaint.data;

import java.io.Serializable;

/**
 * Base class for all vector-based graphics
 * @author <110017972>
 *
 */
public class VectorShape extends DrawableItem implements Serializable {
	private static final long serialVersionUID = 1L;
	
	/** Brush (e.g. colour, isSolid) */
	private BrushData brush;
	
	/** Create a new vector-based graphics data with default values */
	public VectorShape() {
		this.brush = new BrushData();
	}
	
	/** Create a copy of another vector-based graphics data
	 * @param other Instance of the same class to copy 
	 */
	public VectorShape(VectorShape other) {
		this.brush = new BrushData(other.brush);
	}
	
	/**
	 * Retrieve brush data
	 * @return Brush data
	 */
	public BrushData getBrush() {
		return this.brush;
	}
	
	/**
	 * Set the brush data
	 * @param brush New brush
	 */
	public void setBrush(BrushData brush) {
		this.brush = brush;
	}
}
