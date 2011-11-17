package uk.ac.standrews.cs5001.foopaint.ui.draw.impl;

import java.awt.Graphics;

import uk.ac.standrews.cs5001.foopaint.ui.draw.DrawState;

public class LineRenderer extends BaseRenderer
{
	@Override
	public void render(Graphics grfx, DrawState state) {
		int x1 = state.getStartX();
		int y1 = state.getStartY();
		int x2 = state.getEndX();
		int y2 = state.getEndY();
		
		//System.out.printf("Drawing %s line {%d, %d} to {%d, %d}\n", state.toString(), x1, y1, x2, y2);
		grfx.drawLine(x1, y1, x2, y2);
	}
}