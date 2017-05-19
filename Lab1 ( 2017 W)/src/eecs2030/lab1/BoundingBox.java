package eecs2030.lab1;

/**
 * A bounding box describes a rectangular area on the 2D
 * plane. One corner of the bounding box is considered to
 * be the position of the box and has coordinates (x, y).
 * The other corner of the box has coordinates
 * (x + width, y + height) where width is the width of
 * the box and height is the height of the box. Neither the width
 * nor the height of the box can be less than zero. 
 * 
 * @author EECS2030 Winter 2016-17
 *
 */
public class BoundingBox {
	private int x;
	private int y;
	private int width;
	private int height;

	/**
	 * Initialize this bounding box so that its position is (x, y), its width is
	 * 0, and its height is 0.
	 */
	public BoundingBox() {
		this.x = 0;
		this.y = 0;
		this.width = 0;
		this.height = 0;
	}

	/**
	 * Initialize this bounding box so that its position is (x, y) and it has
	 * the given width and height.
	 * 
	 * @param x the x coordinate of the position of the box
	 * @param y the y coordinate of the position of the box
	 * @param width the width of the box
	 * @param height the height of the box
	 * @throws IllegalArgumentException
	 *             if width is less than 0 or height is less than 0
	 */
	public BoundingBox(int x, int y, int width, int height) {
		this.setSize(width, height);
		this.x = x;
		this.y = y;
	}

	/**
	 * Initialize this bounding box so that it has the same position, width, and
	 * height as the given bounding box.
	 * 
	 * @param other
	 *            the bounding box to copy
	 */
	public BoundingBox(BoundingBox other) {
		this.x = other.x;
		this.y = other.y;
		this.width = other.width;
		this.height = other.height;
	}

	/**
	 * Returns the x coordinate of the position of this bounding box.
	 * 
	 * @return the x coordinate of the position of this bounding box
	 */
	public int getX() {
		return this.x;
	}

	/**
	 * Returns the the y coordinate of the position of this bounding box.
	 * 
	 * @return the y coordinate of the position of this bounding box
	 */
	public int getY() {
		return this.y;
	}

	/**
	 * Returns the width of this bounding box.
	 * 
	 * @return the width of this bounding box
	 */
	public int getWidth() {
		return this.width;
	}

	/**
	 * Returns the height of this bounding box.
	 * 
	 * @return the height of this bounding box
	 */
	public int getHeight() {
		return this.height;
	}

	/**
	 * Sets the position of this bounding box to (x, y).
	 * 
	 * @param x
	 *            the x coordinate to set
	 * @param y
	 *            the y coordinate to set
	 */
	public void setLocation(int x, int y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * Sets the width and height of this bounding box.
	 * 
	 * @param width
	 *            the width to set
	 * @param height
	 *            the height to set
	 * @throws IllegalArgumentException
	 *             if width is less than 0 or height is less than 0
	 */
	public void setSize(int width, int height) {
		if (width < 0) {
			throw new IllegalArgumentException("width is less than zero");
		}
		if (height < 0) {
			throw new IllegalArgumentException("height is less than zero");
		}
		this.width = width;
		this.height = height;
	}

	/**
	 * Returns a hash code for this bounding box.
	 * 
	 * @return a hash code for this bounding box
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + height;
		result = prime * result + width;
		result = prime * result + x;
		result = prime * result + y;
		return result;
	}

	/**
	 * Compares this string to the specified object. The result is true if and
	 * only if the argument is not <code>null</code> and is a
	 * <code>BoundingBox</code> object that has the same position, width, and
	 * height as this object.
	 * 
	 * @param obj
	 *          the object to compare this bounding box against
	 * @return true if the given object represents a bounding box equivalent to
	 *          this bounding box, false otherwise
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (this.getClass() != obj.getClass()) {
			return false;
		}
		BoundingBox other = (BoundingBox) obj;
		if (height != other.height) {
			return false;
		}
		if (width != other.width) {
			return false;
		}
		if (x != other.x) {
			return false;
		}
		if (y != other.y) {
			return false;
		}
		return true;
	}

	/**
	 * Returns a string representation of this bounding box.
	 * The returned string contains the position, width, and
	 * height of this bounding box. For example:
	 * 
	 * <pre>
	 * BoundingBox b = new BoundingBox(-1, 5, 3, 2);
	 * String s = b.toString();
	 * </pre>
	 * 
	 * <p>
	 * The string <code>s</code> is equal to:
	 * 
	 * <pre>
	 * "position = (-1, 5), width = 3, height = 2)"
	 * </pre>
	 * 
	 * 
	 * @return a string representation of this bounding box
	 */
	@Override
	public String toString() {
		return String.format("position = (%d, %d), width = %d, height = %d)", this.getX(), this.getY(), this.getWidth(),
				this.getHeight());
	}

}
