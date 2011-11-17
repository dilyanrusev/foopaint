package uk.ac.standrews.cs5001.foopaint.data;

public interface Subject<TData> {
	void notifyAll(TData data);
	void register(Subscriber<TData> handler);
	void unregister(Subscriber<TData> handler);
}