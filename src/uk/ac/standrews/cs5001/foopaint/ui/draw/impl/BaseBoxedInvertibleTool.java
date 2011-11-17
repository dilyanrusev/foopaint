package uk.ac.standrews.cs5001.foopaint.ui.draw.impl;

import uk.ac.standrews.cs5001.foopaint.data.BaseBoxedShape;
import uk.ac.standrews.cs5001.foopaint.data.DrawableItem;
import uk.ac.standrews.cs5001.foopaint.ui.draw.Tools;

public abstract class BaseBoxedInvertibleTool extends BaseTool {
	private boolean solid;
	
	public BaseBoxedInvertibleTool(boolean solid) {
		this.solid = solid;
	}
	
	@Override
	public DrawableItem getItem() {
		int x = Tools.calcOriginX(this.getData());
		int y = Tools.calcOriginY(this.getData());
		int w = Tools.calcWidth(this.getData());
		int h = Tools.calcHeight(this.getData());
		BaseBoxedShape item =  this.createItem(x, y, w, h);
		item.setBrush(Tools.drawStateToBrush(this.getData(), this.isSolid()));
		return item;
	}
	
	protected boolean isSolid() {
		return this.solid;
	}
	
	protected abstract BaseBoxedShape createItem(int x, int y, int w, int h);
}
