package uk.ac.standrews.cs5001.foopaint.data;

//import java.io.IOException;
//import java.io.ObjectStreamException;

public abstract class BaseBoxedShape extends VectorShape {
	private static final long serialVersionUID = 1L;
	
	private int x;
	private int y;
	private int width;
	private int height;
	
	public BaseBoxedShape(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
	
	public BaseBoxedShape(BaseBoxedShape other) {
		super(other);
		this.x = other.x;
		this.y = other.y;
		this.width = other.width;
		this.height = other.height;
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	public int getX() {
		return this.x;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
	public int getY() {
		return this.y;
	}
	
	public void setWidth(int width) {
		this.width = width;
	}
	
	public int getWidth() {
		return this.width;
	}
	
	public void setHeight(int height) {
		this.height = height;
	}
	
	public int getHeight() {
		return this.height;
	}
	
//	private void writeObject(java.io.ObjectOutputStream out) throws IOException {
//		out.writeInt(this.x);
//		out.writeInt(this.y);
//		out.writeInt(this.width);
//		out.writeInt(this.height);
//	}
//		
//	private void readObject(java.io.ObjectInputStream in)  throws IOException, ClassNotFoundException {
//		this.x = in.readInt();
//		this.y = in.readInt();
//		this.width = in.readInt();
//		this.height = in.readInt();
//	}
//		
//	
//	private void readObjectNoData() throws ObjectStreamException {
//		
//	}
}
