package uk.ac.standrews.cs5001.foopaint.data;

import java.io.IOException;

public class Document {
	public static Document load(String fileName) throws IOException {
		return null;
	}
	
	public void save(String fileName) throws IOException {		
	}
	
	public Iterable<DrawableItem> getItems() {
		return null;
	}
	
	public void clearItems() {
		
	}
	
	public void addItem(DrawableItem item) {
		
	}
	
	public void removeItem(DrawableItem item) {
		
	}
	
	public <T> T createItem(Class<T> type) {
		return null;
	}
}
