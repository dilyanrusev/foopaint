package uk.ac.standrews.cs5001.foopaint.data;

//import java.io.IOException;
//import java.io.ObjectStreamException;

/**
 * Base class for vector-based shapes that can be described by their top-left corner, width and height
 * @author <110017972>
 *
 */
public abstract class BaseBoxedShape extends VectorShape {
	/** To be incremented whenever more non-transient fields are added */
	private static final long serialVersionUID = 1L;
	
	/** Left coordinate */
	private int x;
	/** Top coordinate */
	private int y;
	/** Width of the shape */
	private int width;
	/** Height of the shape */
	private int height;
	
	/**
	 * Create a new shape that can be described and encompassed by a box
	 * @param x Left
	 * @param y Top
	 * @param width Width
	 * @param height Height
	 */
	public BaseBoxedShape(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
	
	/**
	 * Create a copy of another boxed shape
	 * @param other Instance of the other boxed shape to copy
	 */
	public BaseBoxedShape(BaseBoxedShape other) {
		super(other);
		this.x = other.x;
		this.y = other.y;
		this.width = other.width;
		this.height = other.height;
	}
	
	/**
	 * Set the left positional coordinate component
	 * @param x Left
	 */
	public void setX(int x) {
		this.x = x;
	}
	
	/**
	 * Get the left positional coordinate component
	 * @return Left
	 */
	public int getX() {
		return this.x;
	}
	
	/**
	 * Set the top positional coordinate component
	 * @param y Top
	 */
	public void setY(int y) {
		this.y = y;
	}
	
	/**
	 * Get the top positional coordinate component
	 * @return Top
	 */
	public int getY() {
		return this.y;
	}
	
	/**
	 * Set shape width
	 * @param width Shape width
	 */
	public void setWidth(int width) {
		this.width = width;
	}
	
	/**
	 * Get shape width
	 * @return Shape width
	 */
	public int getWidth() {
		return this.width;
	}
	
	/**
	 * Set shape height
	 * @param height Shape height
	 */
	public void setHeight(int height) {
		this.height = height;
	}
	
	/**
	 * Get shape height
	 * @return Shape height
	 */
	public int getHeight() {
		return this.height;
	}
}
