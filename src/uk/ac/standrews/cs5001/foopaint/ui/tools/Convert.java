package uk.ac.standrews.cs5001.foopaint.ui.tools;

import java.awt.Color;

import uk.ac.standrews.cs5001.foopaint.data.BrushData;

class Convert {
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
}
