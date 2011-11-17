package uk.ac.standrews.cs5001.foopaint.ui.draw;

import java.awt.Graphics;

public interface Renderer {
	void renderObject(Graphics grfx, DrawState state);
}
