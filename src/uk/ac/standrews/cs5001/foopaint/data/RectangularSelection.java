package uk.ac.standrews.cs5001.foopaint.data;

public class RectangularSelection implements Selection {
	private int x;
	private int y;
	private int w;
	private int h;
	
	public boolean contains(int x, int y) {
		return false;
	}
}
