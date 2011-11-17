package uk.ac.standrews.cs5001.foopaint.data;

public interface Subscriber<TData> {
	void notify(Object sender, TData data);
}