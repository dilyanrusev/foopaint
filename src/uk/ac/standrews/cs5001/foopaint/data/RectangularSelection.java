package uk.ac.standrews.cs5001.foopaint.data;

import java.io.Serializable;

public class RectangularSelection implements Selection, Serializable {
	private static final long serialVersionUID = 1L;
	
	private int x;
	private int y;
	private int w;
	private int h;
	
	public boolean contains(int x, int y) {
		return false;
	}
}
