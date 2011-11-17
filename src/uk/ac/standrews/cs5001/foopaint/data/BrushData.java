package uk.ac.standrews.cs5001.foopaint.data;

public class BrushData {
	int r;
	int g;
	int b;
	boolean solid;
	
	public BrushData() {
		this(0, 0, 0, false);
	}
	
	public BrushData(int r, int g, int b, boolean solid) {
		this.r = r;
		this.g = g;
		this.b = b;
		this.solid = solid;
	}
	
	public BrushData(BrushData other) {
		this(other.r, other.g, other.b, other.solid);
	}
	
	public boolean isSolid() {
		return this.solid;
	}
	
	public void setSolid(boolean isSolid) {
		this.solid = isSolid;
	}
	
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
	
	public int getRed() {
		return this.r;
	}
	
	public int getGreen() {
		return this.g;
	}
	
	public int getBlue() {
		return this.b;
	}
}
