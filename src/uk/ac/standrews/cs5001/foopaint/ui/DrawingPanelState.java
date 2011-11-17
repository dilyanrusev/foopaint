package uk.ac.standrews.cs5001.foopaint.ui;

import java.awt.geom.Point2D;

import uk.ac.standrews.cs5001.foopaint.ui.draw.BaseDrawState;
import uk.ac.standrews.cs5001.foopaint.ui.draw.DrawState;

class DrawingPanelState extends BaseDrawState {
	private boolean dragging;
	
	public DrawingPanelState() {
		this.dragging = false;
	}
	
	public DrawState copy() {
		return new SimpleState(this);
	}
	
	public boolean isDragging() {
		return this.dragging;
	}
	
	public void setDragging(boolean isDragging) {
		this.dragging = true;
	}
	
	public void startDragging(Point2D origin) {
		this.dragging = true;
		this.setStart(origin);
		this.setEnd(origin);
	}
	
	public void dragTo(Point2D newEnd) {
		this.setEnd(newEnd);
	}
	
	public void stopDragging(Point2D end) {
		this.dragging = false;
		this.setEnd(end);
	}
	
	private static class SimpleState extends BaseDrawState {
		public SimpleState(DrawingPanelState other) {
			super(other);
			//this.setColour(Color.RED);
		}
		
		private SimpleState(SimpleState other) {
			super(other);
		}
		
		@Override
		public DrawState copy() {
			return new SimpleState(this);
		}	
		
		@Override
		public String toString() {
			return super.toString();
//			return String.format("[{%d, %d}, {%d, %d}, colour={%d,%d,%d}]", 
//					this.getStart().getX(), this.getStart().getY(), 
//					this.getEnd().getX(), this.getEnd().getY(), 
//					this.getColour().getRed(), this.getColour().getGreen(), this.getColour().getBlue());
		}
	}
}