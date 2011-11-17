package uk.ac.standrews.cs5001.foopaint.data;

public class VectorShape extends DrawableItem {
	private BrushData brush;
	
	public VectorShape() {
		this.brush = new BrushData();
	}
	
	public BrushData getBrush() {
		return this.brush;
	}
	
	public void setBrush(BrushData brush) {
		this.brush = brush;
	}
}
