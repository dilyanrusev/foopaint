package uk.ac.standrews.cs5001.foopaint.ui.draw;

import java.awt.Color;

import uk.ac.standrews.cs5001.foopaint.data.BrushData;

public class Tools {
	public static BrushData colourToBrush(Color c, boolean solid) {
		BrushData d = new BrushData();
		d.setColour(c.getRed(), c.getGreen(), c.getBlue());
		d.setSolid(solid);
		return d;
	}
	
	public static Color brushToColour(BrushData b) {
		Color c = new Color(b.getRed(), b.getGreen(), b.getBlue());
		return c;
	}
	
	public static BrushData drawStateToBrush(DrawState s, boolean solid) {
		return colourToBrush(s.getColour(), solid);
	}
	
	public static int calcWidth(DrawState state) {
		int w = Math.abs(state.getStartX() - state.getEndX());
		return w;
	}
	
	public static int calcHeight(DrawState state) {
		int h = Math.abs(state.getStartY() - state.getEndY());
		return h;
	}
	
	public static int calcOriginX(DrawState state) {
		int dx = state.getEndX() - state.getStartX();
				
		int x = state.getStartX();
				
		if (dx < 0)
			x += dx;
		
		return x;
	}
	
	public static int calcOriginY(DrawState state) {
		int dy = state.getEndY() - state.getStartY();
		
		int y = state.getStartY();
				
		if (dy < 0)
			y += dy;
		
		return y;
	}
}
