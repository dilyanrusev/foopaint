package uk.ac.standrews.cs5001.foopaint.ui.draw.impl;

import java.awt.Graphics;

import uk.ac.standrews.cs5001.foopaint.ui.draw.DrawState;
import uk.ac.standrews.cs5001.foopaint.ui.draw.Tools;

public abstract class BaseBoxedInvertibleRenderer extends BaseRenderer {

	@Override
	protected void render(Graphics grfx, DrawState state) {
		int x = Tools.calcOriginX(state);
		int y = Tools.calcOriginY(state);
		int w = Tools.calcWidth(state);
		int h = Tools.calcWidth(state);
		
		this.render(grfx, x, y, w, h);
	}
	
	protected abstract void render(Graphics grfx, int x, int y, int w, int h);
}
