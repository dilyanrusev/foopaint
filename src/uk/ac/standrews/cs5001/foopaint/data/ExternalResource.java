package uk.ac.standrews.cs5001.foopaint.data;

import java.io.Serializable;

/**
 * Class that stores information about external resources (e.g. images)
 * @author <110017972>
 *
 */
public class ExternalResource extends DrawableItem implements Serializable {
	/** Increased when new fields are added */
	private static final long serialVersionUID = 2L;
	
	/** Path on the file system to the file containing the external resource */
	private String path;
	
	/**
	 * Create an external resource with empty path
	 */
	public ExternalResource() {
		this((String)null);
	}
	
	/**
	 * Create an external resource
	 * @param path Path on the file system to the file containing the external resource
	 */
	public ExternalResource(String path) {
		this.path = path;
	}
	
	/**
	 * Create a copy of another external resource
	 * @param other Resource to copy
	 */
	public ExternalResource(ExternalResource other) {
		this.path = other.path;
	}
	
	/**
	 * Get path on the file system to the file containing the external resource
	 * @return Path
	 */
	public String getPath() {
		return this.path;
	}
	
	/**
	 * Set the resource's path
	 * @param newPath Path on the file system to the file containing the external resource
	 */
	public void setPath(String newPath) {
		this.path = newPath;
	}
}
