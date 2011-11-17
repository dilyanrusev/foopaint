package uk.ac.standrews.cs5001.foopaint.ui.draw.impl;

import java.awt.Color;
import java.awt.Graphics;

import uk.ac.standrews.cs5001.foopaint.ui.draw.DrawState;
import uk.ac.standrews.cs5001.foopaint.ui.draw.Renderer;

public abstract class BaseRenderer implements Renderer {

	@Override
	public void renderObject(Graphics grfx, DrawState state) {
		Color originalColour = grfx.getColor();		
		grfx.setColor(state.getColour());
		
		this.render(grfx, state);
		
		grfx.setColor(originalColour);
	}
	
	protected abstract void render(Graphics grfx, DrawState state);
}
