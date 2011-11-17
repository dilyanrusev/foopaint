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

public class Document implements Serializable, Iterable<DrawableItem> {
	private static final long serialVersionUID = 1L;
	
	private List<DrawableItem> items;
	private String originalFileName;
	
	public Document() {
		this.items = new ArrayList<DrawableItem>();
		this.originalFileName = null;
	}
	
	public Document(String fileName) throws IOException {
		this.originalFileName = fileName;
		this.items = new ArrayList<DrawableItem>();
		
		ObjectInputStream in = null;
		try {
			in = new ObjectInputStream(new FileInputStream(fileName));
			
			try {
				Document doc = (Document) in.readObject();
				this.setItems(doc.items);
				this.originalFileName = doc.originalFileName;
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
	
	public void save(String fileName) throws IOException {	
		//FileOutputStream out = new FileOutputStream(fileName);
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
	
	public void save() throws IOException {
		this.save(this.originalFileName);
	}
	
	public Iterable<DrawableItem> getItems() {
		return null;
	}
	
	public void clearItems() {
		this.items.clear();
	}
	
	public void setItems(Iterable<DrawableItem> items) {
		this.clearItems();
		this.addItems(items);
	}
	
	public void addItems(Iterable<DrawableItem> items) {
		for (DrawableItem drawable: items) {
			this.addItem(drawable);
		}
	}
	
	public void addItem(DrawableItem item) {
		this.items.add(item);
	}
	
	public void removeItem(DrawableItem item) {
		this.items.remove(item);
	}

	@Override
	public Iterator<DrawableItem> iterator() {
		return this.items.iterator();
	}
}
