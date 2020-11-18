/*
 * Location.java - class that keep tracks where a unit is on the map.
 */
public class Location {
	//Original Lane, do not change if TP out
	private int home_lane;
	private int current_lane;
	
	//Row and Col of the current position based on the map
	private int row;
	private int col;

	public Location(int home_lane, int current_lane, int row, int col) {
		this.setHome_lane(home_lane);
		this.setCurrent_lane(current_lane);
		this.setRow(row);
		this.setCol(col);
	}

	public int getHome_lane() {
		return home_lane;
	}

	public void setHome_lane(int home_lane) {
		this.home_lane = home_lane;
	}

	public int getCurrent_lane() {
		return current_lane;
	}

	public void setCurrent_lane(int current_lane) {
		this.current_lane = current_lane;
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
