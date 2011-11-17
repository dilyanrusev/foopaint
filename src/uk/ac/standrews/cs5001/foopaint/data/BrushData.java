package uk.ac.standrews.cs5001.foopaint.data;

public class BrushData {
	int r;
	int g;
	int b;
	boolean solid;
	
	public BrushData() {
		this.r = 0;
		this.g = 0;
		this.b = 0;
		this.solid = false;
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
