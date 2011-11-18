package uk.ac.standrews.cs5001.foopaint.data;

public interface BoxedShape {

	/**
	 * Set the left positional coordinate component
	 * @param x Left
	 */
	void setX(int x);

	/**
	 * Get the left positional coordinate component
	 * @return Left
	 */
	int getX();

	/**
	 * Set the top positional coordinate component
	 * @param y Top
	 */
	void setY(int y);

	/**
	 * Get the top positional coordinate component
	 * @return Top
	 */
	int getY();

	/**
	 * Set shape width
	 * @param width Shape width
	 */
	void setWidth(int width);

	/**
	 * Get shape width
	 * @return Shape width
	 */
	int getWidth();

	/**
	 * Set shape height
	 * @param height Shape height
	 */
	void setHeight(int height);

	/**
	 * Get shape height
	 * @return Shape height
	 */
	int getHeight();

}