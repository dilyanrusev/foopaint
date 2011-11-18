package uk.ac.standrews.cs5001.foopaint.data;

import java.io.Serializable;

/**
 * Class for storing information to draw a line between two points
 * @author <110017972>
 *
 */
public class Line extends VectorShape implements Serializable {
	/** Serialization version, incremented when adding new fields */
	private static final long serialVersionUID = 1L;
	
	/** Horizontal component of the first (origin) point */
	private int xOrigin;
	/** Vertical component of the first (origin) point */
	private int yOrigin;
	/** Horizontal component of the second (end) point */
	private int xEnd;
	/** Vertical component of the second (end) point */
	private int yEnd;
	
	/**
	 * Create a copy of another line
	 * @param other Line to copy from
	 */
	public Line(Line other) {
		super(other);
		this.xOrigin = other.xOrigin;
		this.yOrigin = other.yOrigin;
		this.xEnd = other.xEnd;
		this.yEnd = other.yEnd;
	}
	
	/**
	 * Create a new line
	 * @param xOrigin Horizontal component of the first (origin) point
	 * @param yOrigin Vertical component of the first (origin) point
	 * @param xEnd Horizontal component of the second (end) point
	 * @param yEnd Vertical component of the second (end) point
	 */
	public Line(int xOrigin, int yOrigin, int xEnd, int yEnd) {
		this.xOrigin = xOrigin;
		this.yOrigin = yOrigin;
		this.xEnd = xEnd;
		this.yEnd = yEnd;
	}
	
	/**
	 * Get the horizontal component of the first (origin) point
	 * @return Horizontal component of the first (origin) point
	 */
	public int getOriginX() {
		return this.xOrigin;
	}
	
	/**
	 * Get the vertical component of the first (origin) point
	 * @return Vertical component of the first (origin) point
	 */
	public int getOriginY() {
		return this.yOrigin;
	}
	
	/**
	 * Get the horizontal component of the second (end) point
	 * @return Horizontal component of the second (end) point
	 */
	public int getEndX() {
		return this.xEnd;
	}
	
	/**
	 * Get the vertical component of the second (end) point
	 * @return Vertical component of the second (end) point
	 */
	public int getEndY() {
		return this.yEnd;
	}
}
