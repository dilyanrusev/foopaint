package uk.ac.standrews.cs5001.foopaint.data;

import java.util.LinkedList;
import java.util.List;

/**
 * Implementation of the Subject in the Observer pattern
 * @author <110017972>
 *
 * @param <TData> Type of data used for pushing
 */
public class Event<TData> implements Subject<TData> {
	/** List of subscribers */
	private List<Subscriber<TData>> observers;
	/** Object used for pulling data */
	private Object container;
	
	/**
	 * Create an event with external object used for pulling data
	 * @param container Object that subscribers will use to pull data
	 */
	public Event(Object container) {
		this.initialize(container);
	}
	
	/** 
	 * Create an event where the object for pulling data is the event itself
	 */
	public Event() {
		this.initialize(this);
	}
	
	/**
	 * Initialise the event
	 * @param container Object for pulling data
	 */
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
