package uk.ac.standrews.cs5001.foopaint.ui.draw.impl;

import uk.ac.standrews.cs5001.foopaint.data.DrawableItem;
import uk.ac.standrews.cs5001.foopaint.data.Line;
import uk.ac.standrews.cs5001.foopaint.ui.draw.Tool;
import uk.ac.standrews.cs5001.foopaint.ui.draw.ToolIDs;
import uk.ac.standrews.cs5001.foopaint.ui.draw.Tools;

public class LineTool extends BaseTool {	
	@Override
	public ToolIDs getID() {
		return ToolIDs.DRAW_LINE;
	}

	@Override
	public DrawableItem getItem() {
		int x1 = this.getData().getStartX();
		int y1 = this.getData().getStartY();
		int x2 = this.getData().getEndX();
		int y2 = this.getData().getEndY();
		Line l =  new Line(x1, y1, x2, y2);
		l.setBrush(Tools.drawStateToBrush(this.getData(), true));
		return l;
	}

	@Override
	public Tool copy() {
		return new LineTool().copyFrom(this);
	}
}

