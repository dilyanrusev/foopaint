package uk.ac.standrews.cs5001.foopaint.data;

public class Line extends VectorShape {
	private int xOrigin;
	private int yOrigin;
	private int xEnd;
	private int yEnd;
	
	public Line(Line other) {
		this.xOrigin = other.xOrigin;
		this.yOrigin = other.yOrigin;
		this.xEnd = other.xEnd;
		this.yEnd = other.yEnd;
	}
	
	public Line(int xOrigin, int yOrigin, int xEnd, int yEnd) {
		this.xOrigin = xOrigin;
		this.yOrigin = yOrigin;
		this.xEnd = xEnd;
		this.yEnd = yEnd;
	}
	
	public int getOriginX() {
		return this.xOrigin;
	}
	
	public int getOriginY() {
		return this.yOrigin;
	}
	
	public int getEndX() {
		return this.xEnd;
	}
	
	public int getEndY() {
		return this.yEnd;
	}
}