/*
 * Location.java - class that keep tracks where a unit is on the map.
 */
public class Location {
	//Original Lane, do not change if TP out
	private int lane;
	
	//Row and Col of the current position based on the map
	private int row;
	private int col;
	
	public Location(int lane, int row, int col) {
		this.setLane(lane);
		this.setRow(row);
		this.setCol(col);
	}

	public int getLane() {
		return lane;
	}

	public void setLane(int lane) {
		this.lane = lane;
	}

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public int getCol() {
		return col;
	}

	public void setCol(int col) {
		this.col = col;
	}
}
