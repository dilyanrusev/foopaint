package uk.ac.standrews.cs5001.foopaint.ui.draw.impl;

import uk.ac.standrews.cs5001.foopaint.data.BaseBoxedShape;
import uk.ac.standrews.cs5001.foopaint.data.Ellipse;
import uk.ac.standrews.cs5001.foopaint.ui.draw.Tool;
import uk.ac.standrews.cs5001.foopaint.ui.draw.ToolIDs;

public class SolidEllipseTool extends BaseBoxedInvertibleTool {
	public SolidEllipseTool() {
		super(true);
	}
	
	@Override
	public Tool copy() {
		return new SolidEllipseTool().copyFrom(this);
	}

	@Override
	protected BaseBoxedShape createItem(int x, int y, int w, int h) {
		return new Ellipse(x, y, w, h);
	}

	@Override
	public ToolIDs getID() {
		return ToolIDs.DRAW_SOLID_ELLIPSE;
	}

}
