package uk.ac.standrews.cs5001.foopaint.data;

import java.io.Serializable;

public class Ellipse extends BaseBoxedShape implements Serializable {
	private static final long serialVersionUID = 1L;

	public Ellipse() {
		this(0, 0, 0, 0);
	}
	
	public Ellipse(int x, int y, int width, int height) {
		super(x, y ,width, height);
	}
	
	public Ellipse(Ellipse other) {
		super(other);
	}
}
