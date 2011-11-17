package uk.ac.standrews.cs5001.foopaint.ui.draw;

import java.awt.Color;
import java.awt.geom.Point2D;

public interface DrawState {

	int getStartX();

	int getStartY();

	int getEndX();

	int getEndY();

	Point2D getStart();

	Point2D getEnd();

	Color getColour();
	
	DrawState copy();
}