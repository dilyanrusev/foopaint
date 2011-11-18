package uk.ac.standrews.cs5001.foopaint.data;

import java.io.Serializable;

/**
 * Class for storing information about how a shape is drawn 
 * @author <110017972>
 *
 */
public class BrushData implements Serializable {
	/** Serialization ID - to be incremented whenever a new non-transient field is added */
	private static final long serialVersionUID = 1L;
	
	/** Red component */
	int r;
	/** Green component */
	int g;
	/** Blue component */
	int b;
	/** True if shape should be filled with a colour, false if only a contour is needed */
	boolean solid;
	
	/**
	 * Create a new brush data with default values
	 */
	public BrushData() {
		this(0, 0, 0, false);
	}
	
	/**
	 * Create a new brush
	 * @param r Red colour component
	 * @param g Green colour component
	 * @param b Blue colour component
	 * @param solid True if shape should be filled with a colour, false if only a contour is needed
	 */
	public BrushData(int r, int g, int b, boolean solid) {
		this.r = r;
		this.g = g;
		this.b = b;
		this.solid = solid;
	}
	
	/**
	 * Create a copy of another brush
	 * @param other Brush data to copy
	 */
	public BrushData(BrushData other) {
		this(other.r, other.g, other.b, other.solid);
	}
	
	/**
	 * Check if a shape is solid
	 * @return True if shape should be filled with a colour, false if only a contour is needed
	 */
	public boolean isSolid() {
		return this.solid;
	}
	
	/**
	 * Change whether a shape is solid or not
	 * @param isSolid True if shape should be filled with a colour, false if only a contour is needed
	 */
	public void setSolid(boolean isSolid) {
		this.solid = isSolid;
	}
	
	/**
	 * Set colour components
	 * @param r Red colour component
	 * @param g Green colour component
	 * @param b Blue colour component
	 */
	public void setColour(Integer r, Integer g, Integer b) {
		if (r != null) {
			this.r = r;
		}
		if (g != null) {
			this.g = g;
		}
		if (b != null) {
			this.b = b;
		}
	}
	
	/**
	 * Get the red colour component
	 * @return Red
	 */
	public int getRed() {
		return this.r;
	}
	
	/**
	 * Get the green colour component
	 * @return Green
	 */
	public int getGreen() {
		return this.g;
	}
	
	/**
	 * Get the blue colour component
	 * @return Blue
	 */
	public int getBlue() {
		return this.b;
	}
}
