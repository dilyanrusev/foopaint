package uk.ac.standrews.cs5001.foopaint.data;

/**
 * Interface for the Observer in the Observer pattern
 * @author <110017972>
 *
 * @param <TData> Type of the data which is going to be pushed by the Subject
 */
public interface Subscriber<TData> {
	/**
	 * Notification sent by the Subject that a change of state has occurred
	 * @param sender Item to be used for pulling data
	 * @param data Data which is pushed by the Subject
	 */
	void notify(Object sender, TData data);
}