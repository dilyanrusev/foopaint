package uk.ac.standrews.cs5001.foopaint.data;

import java.util.LinkedList;
import java.util.List;

public class Event<TData> implements Subject<TData> {
	private List<Subscriber<TData>> observers;
	private Object container;
	
	public Event(Object container) {
		this.initialize(container);
	}
	
	public Event() {
		this.initialize(this);
	}
	
	private void initialize(Object container) {
		this.container = container;
		this.observers = new LinkedList<Subscriber<TData>>();
	}

	
	@Override
	public void notifyAll(TData data) {
		for (Subscriber<TData> observer: this.observers) {
			observer.notify(this.container, data);
		}
	}

	@Override
	public void register(Subscriber<TData> handler) {
		this.observers.add(handler);
	}

	@Override
	public void unregister(Subscriber<TData> handler) {
		this.observers.remove(handler);
	}
}
