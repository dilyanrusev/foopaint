package uk.ac.standrews.cs5001.foopaint.ui.draw.impl;

import java.awt.Graphics;

import uk.ac.standrews.cs5001.foopaint.ui.draw.DrawState;
import uk.ac.standrews.cs5001.foopaint.ui.draw.Renderer;
import uk.ac.standrews.cs5001.foopaint.ui.draw.Tool;
import uk.ac.standrews.cs5001.foopaint.ui.draw.ToolFactory;
import uk.ac.standrews.cs5001.foopaint.ui.draw.ToolIDs;

public abstract class BaseTool implements Tool {
	private DrawState data;
	
	public BaseTool() {}
	public BaseTool(BaseTool other) {
		this.data = other.data.copy();
	}
	
	public abstract ToolIDs getID();
	
	@Override
	public void render(Graphics grfx, DrawState state) {
		this.data = state.copy();
		this.render(grfx);
	}

	@Override
	public void render(Graphics grfx) {
		Renderer r = ToolFactory.getRendererByID(this.getID());
		r.renderObject(grfx, this.data);
	}
	
	protected DrawState getData() {
		return this.data;
	}
	
	public void setData(DrawState data) {
		this.data = data;
	}
	
	protected Tool copyFrom(BaseTool other) {
		this.setData(other.getData().copy());
		return other;
	}
	
	@Override
	public String toString() {
		return super.toString();
		//return String.format("[%s: %s]", this.getClass().getName(), this.getData().toString());
	}
}
