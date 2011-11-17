package uk.ac.standrews.cs5001.foopaint.data;

import java.io.Serializable;

public class Rectangle extends BaseBoxedShape implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public Rectangle() {
		this(0, 0, 0, 0);
	}
	
	public Rectangle(int x, int y, int width, int height) {
		super(x, y ,width, height);
	}
	
	public Rectangle(Rectangle other) {
		super(other);
	}	
}
