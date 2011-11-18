package uk.ac.standrews.cs5001.foopaint.ui.tools;

import java.awt.Color;

import uk.ac.standrews.cs5001.foopaint.data.BrushData;

/**
 * Utility class for converting to and from data objects
 * @author <110017972>
 *
 */
class Convert {
	/**
	 * Prevent external instantiation
	 */
	private Convert() {}
	
	/**
	 * Convert java.aw.Color to a brush
	 * @param c Colour to convert
	 * @param solid True if shape should be filled with that colour, false if the colour is used only for contour
	 * @return Brush data ready for serialization
	 */
	public static BrushData colourToBrush(Color c, boolean solid) {
		BrushData d = new BrushData();
		d.setColour(c.getRed(), c.getGreen(), c.getBlue());
		d.setSolid(solid);
		return d;
	}
	
	/**
	 * Convert a brush to java.awt.Color
	 * @param b Brush whose colour component to extract
	 * @return Colour used by the brush for either filling or drawing the shape
	 */
	public static Color brushToColour(BrushData b) {
		Color c = new Color(b.getRed(), b.getGreen(), b.getBlue());
		return c;
	}
}
