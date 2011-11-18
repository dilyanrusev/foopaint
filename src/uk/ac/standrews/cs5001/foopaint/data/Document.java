package uk.ac.standrews.cs5001.foopaint.data;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Root of the DOM-tree for all shapes
 * @author <110017972>
 *
 */
public class Document implements Serializable, Iterable<DrawableItem> {
	/** Serialization version ID */
	private static final long serialVersionUID = 2L;
	
	/** Ordered list of items in the document */
	private List<DrawableItem> items;
	
	/** Create an empty document with no shapes */
	public Document() {
		this.items = new ArrayList<DrawableItem>();
	}
	
	/**
	 * Create a new document by reading its contents from a file name
	 * @param fileName Name (path) of the file to load
	 * @throws IOException If there is any error due to reading the document from the file system
	 */
	public Document(String fileName) throws IOException {
		this.items = new ArrayList<DrawableItem>();
		
		ObjectInputStream in = null;
		try {
			in = new ObjectInputStream(new FileInputStream(fileName));
			
			try {
				Document doc = (Document) in.readObject();
				this.setItems(doc.items);				
			} catch (ClassNotFoundException e) {
				throw new IOException("Corrupt file", e);
			} catch (ClassCastException e) {
				throw new IOException("Corrupt file", e);
			}			
		}
		finally {
			if (in != null) {
				in.close();
			}
		}
	}
	
	/**
	 * Save the document's contents to a file
	 * @param fileName Name of a file to write to
	 * @throws IOException Whenever an error occurs while writing to the file
	 */
	public void save(String fileName) throws IOException {	
		ObjectOutputStream out = null;
		try {
			out = new ObjectOutputStream(new FileOutputStream(fileName));
			out.writeObject(this);
		}
		finally {
			if (out != null) {
				out.close();
			}
		}
	}
	
	/**
	 * Retrieve an ordered collection of shapes to draw on the screen
	 * @return Ordered collection of shapes in this documents
	 */
	public Iterable<DrawableItem> getItems() {
		return this.items;
	}
	
	/**
	 * Remove all items from the document
	 */
	public void clearItems() {
		this.items.clear();
	}
	
	/**
	 * Replace items currently in the document
	 * @param items New set of items
	 */
	public void setItems(Iterable<DrawableItem> items) {
		this.clearItems();
		this.addItems(items);
	}
	
	/**
	 * Add items to the document
	 * @param items Items to add
	 */
	public void addItems(Iterable<DrawableItem> items) {
		for (DrawableItem drawable: items) {
			this.addItem(drawable);
		}
	}
	
	/**
	 * Add a single item to the document
	 * @param item Shape to add
	 */
	public void addItem(DrawableItem item) {
		this.items.add(item);
	}
	
	/**
	 * Remove an item from the document
	 * @param item Shape to remove
	 */
	public void removeItem(DrawableItem item) {
		this.items.remove(item);
	}

	@Override
	public Iterator<DrawableItem> iterator() {
		return this.items.iterator();
	}
}
