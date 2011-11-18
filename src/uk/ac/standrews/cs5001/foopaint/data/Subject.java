package uk.ac.standrews.cs5001.foopaint.data;

/**
 * Interface for Subject in the Observer pattern
 * @author <110017972>
 *
 * @param <TData> Type of the data to be used for pulling data by the subscribers
 */
public interface Subject<TData> {
	/**
	 * Notify all subscribers by pushing data to them
	 * @param data Data which is pushed to all subscribers
	 */
	void notifyAll(TData data);
	
	/**
	 * Add an observer to the list of subscribers
	 * @param handler Observer
	 */
	void register(Subscriber<TData> handler);
	
	/**
	 * Remove an observer from the list of subscribers
	 * @param handler Observer
	 */
	void unregister(Subscriber<TData> handler);
}