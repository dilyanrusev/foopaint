package uk.ac.standrews.cs5001.foopaint.data;

public class Ellipse extends BaseBoxedShape {
	public Ellipse(int x, int y, int width, int height) {
		super(x, y ,width, height);
	}
	
	public Ellipse(Rectangle other) {
		super(other);
	}
}
