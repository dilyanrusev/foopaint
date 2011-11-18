package uk.ac.standrews.cs5001.foopaint.data;

/**
 * Class for storing data needed to draw an image on screen
 * @author <110017972>
 *
 */
public class ImageData extends ExternalResource {
	/** Increased when new fields are added */
	private static final long serialVersionUID = 2L;
	
	/** Horizontal component of the top left point defining the position of the image on screen */
	private int x;
	/** Vertical component of the top left point defining the position of the image on screen */
	private int y;
	/** Brush to use if there is an error */
	private BrushData errorBrush;
	
	/**
	 * Create empty image data
	 */
	public ImageData() {
		this((String)null);
	}
	
	/**
	 * Create an external resource
	 * @param path Path on the file system to the file containing the external resource
	 */
	public ImageData(String path) {
		super(path);
		this.x = 0;
		this.y = 0;
		this.errorBrush = new BrushData();
	}
	
	/**
	 * Create a copy of another external resource
	 * @param other Resource to copy
	 */
	public ImageData(ImageData other) {
		super(other);
		this.x = other.x;
		this.y = other.y;
		this.errorBrush = new BrushData(other.errorBrush);
	}
	
	/**
	 * Set the position of the image on screen
	 * @param x Horizontal component
	 * @param y Vertical component
	 */
	public void setPosition(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	/**
	 * Set position coordinate's horizontal component
	 * @param x Position coordinate's horizontal component
	 */
	public void setX(int x) {
		this.x = x;
	}
	
	/**
	 * Set position coordinate's vertical component
	 * @param y Position coordinate's horizontal component
	 */
	public void setY(int y) {
		this.y = y;
	}
	
	/**
	 * Get image position coordinate's horizontal component
	 * @return Position coordinate's horizontal component
	 */
	public int getX() {
		return this.x;
	}
	
	/**
	 * Get image position coordinate's vertical component
	 * @return Position coordinate's vertical component
	 */
	public int getY() {
		return this.y;
	}
	
	/**
	 * Set brush to use when indicating error
	 * @param brush Brush used for drawing an error message
	 */
	public void setErrorBrush(BrushData brush) {
		this.errorBrush = brush;
	}
	
	/**
	 * Get brush to use when drawing error message
	 * @return Brush
	 */
	public BrushData getErrorBrush() {
		return this.errorBrush;
	}
}
