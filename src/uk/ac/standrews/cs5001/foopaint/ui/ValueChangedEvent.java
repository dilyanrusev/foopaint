package uk.ac.standrews.cs5001.foopaint.ui;

import java.util.Observable;

public class ValueChangedEvent<T> extends Observable {	
	public void raiseEvent(T newValue) {
		this.setChanged();
		this.notifyObservers(newValue);
	}
}
