package uk.ac.standrews.cs5001.foopaint.data;

public class Rectangle extends BaseBoxedShape {
	public Rectangle(int x, int y, int width, int height) {
		super(x, y ,width, height);
	}
	
	public Rectangle(Rectangle other) {
		super(other);
	}	
}
