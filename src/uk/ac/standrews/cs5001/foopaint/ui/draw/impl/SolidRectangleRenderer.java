package uk.ac.standrews.cs5001.foopaint.ui.draw.impl;

import java.awt.Graphics;

public class SolidRectangleRenderer extends BaseBoxedInvertibleRenderer {

	@Override
	protected void render(Graphics grfx, int x, int y, int w, int h) {
		grfx.fillRect(x, y, w, h);
	}

}
