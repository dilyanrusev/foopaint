package uk.ac.standrews.cs5001.foopaint.data;

import java.io.Serializable;

public class VectorShape extends DrawableItem implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private BrushData brush;
	
	public VectorShape() {
		this.brush = new BrushData();
	}
	
	public VectorShape(VectorShape other) {
		this.brush = new BrushData(other.brush);
	}
	
	public BrushData getBrush() {
		return this.brush;
	}
	
	public void setBrush(BrushData brush) {
		this.brush = brush;
	}
}
