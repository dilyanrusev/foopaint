package uk.ac.standrews.cs5001.foopaint.ui.draw.impl;

import java.awt.Graphics;

public class SolidEllipseRenderer extends BaseBoxedInvertibleRenderer {

	@Override
	protected void render(Graphics grfx, int x, int y, int w, int h) {
		grfx.fillOval(x, y, w, x);
	}

}
