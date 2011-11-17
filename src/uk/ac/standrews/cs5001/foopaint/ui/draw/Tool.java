package uk.ac.standrews.cs5001.foopaint.ui.draw;

import java.awt.Graphics;

import uk.ac.standrews.cs5001.foopaint.data.DrawableItem;

public interface Tool {
	void render(Graphics grfx, DrawState state);
	void render(Graphics grfx);
	DrawableItem getItem();
	Tool copy();
	void setData(DrawState data);
}
