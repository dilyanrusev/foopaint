package uk.ac.standrews.cs5001.foopaint.ui;

import uk.ac.standrews.cs5001.foopaint.data.Rectangle;
import uk.ac.standrews.cs5001.foopaint.data.Ellipse;
import uk.ac.standrews.cs5001.foopaint.data.Line;

public enum ToolIDs {
	SELECT_OBJECT (null, false),
	PICK_COLOUR (null, true),
	DRAW_ELLIPSE (Ellipse.class, false),
	DRAW_SOLID_ELLIPSE (Ellipse.class, true),
	DRAW_RECTANGLE (Rectangle.class, false),
	DRAW_SOLID_RECTANGLE (Rectangle.class, true),
	DRAW_LINE (Line.class, true),
	DRAW_IMPORTED_IMAGE (null, true)
	;
	
	ToolIDs(Class<?> modelType, boolean isSolid) {
		this.modelType = modelType;
		this.solid = isSolid;
	}
	
	private Class<?> modelType;
	private boolean solid;
	
	public Class<?> getModelType() {
		return this.modelType;
	}
	
	public boolean isSolidShape() {
		return this.solid;
	}
}
