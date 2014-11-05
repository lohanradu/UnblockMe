import java.util.ArrayList;
import java.util.List;

public class Block {
	public static final String HORIZONTAL = "h";
	int id;
	String orientation;
	int length;
	int x;
	int y;

	public Block(int id, String orientation, int x, int y, int length) {
		super();
		this.id = id;
		this.orientation = orientation;
		this.length = length;
		this.x = x;
		this.y = y;
	}

	public boolean intersects(Block other) {
		List<Point> ownPoints = getPoints();
		List<Point> otherPoints = other.getPoints();
		for (Point point : ownPoints) {
			if (otherPoints.contains(point)) {
				return true;
			}

		}
		return false;
	}

	public List<Point> getPoints() {
		List<Point> points = new ArrayList<>();
		if (orientation.equals(HORIZONTAL)) {
			for (int i = 0; i < length; i++) {
				points.add(new Point(x + i, y));
			}
		} else {
			for (int i = 0; i < length; i++) {
				points.add(new Point(x, y + i));
			}
		}
		return points;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getOrientation() {
		return orientation;
	}

	public void setOrientation(String orientation) {
		this.orientation = orientation;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	@Override
	public String toString() {
		return "Block [id=" + id + ", orientation=" + orientation + ", length="
				+ length + ", x=" + x + ", y=" + y + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Block other = (Block) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
	

}
