package uk.ac.standrews.cs5001.foopaint.ui.draw.impl;

import uk.ac.standrews.cs5001.foopaint.data.BaseBoxedShape;
import uk.ac.standrews.cs5001.foopaint.data.Rectangle;
import uk.ac.standrews.cs5001.foopaint.ui.draw.Tool;
import uk.ac.standrews.cs5001.foopaint.ui.draw.ToolIDs;

public class SolidRectangleTool extends BaseBoxedInvertibleTool {
	public SolidRectangleTool() {
		super(true);
	}
	
	@Override
	public ToolIDs getID() {
		return ToolIDs.DRAW_SOLID_RECTANGLE;
	}

	@Override
	protected BaseBoxedShape createItem(int x, int y, int w, int h) {
		return new Rectangle(x, y, w, h);
	}

	@Override
	public Tool copy() {
		return new SolidRectangleTool().copyFrom(this);
	}
}
