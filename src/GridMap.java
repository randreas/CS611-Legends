/*
 * GridMap.java - gridmap class that has a 2d array of Space for the map
 */
public class GridMap {
	private int rows;
	private int cols;
	private Space[][] map;	
	
	
	
	public GridMap(int rows, int cols) {
		this.rows = rows;
		this.cols = cols;
		map = new Space[rows][cols];
	}
	public GridMap() {
		// TODO Auto-generated constructor stub
		this(8,8);
	}
	public int getCols() {
		return cols;
	}
	public void setCols(int cols) {
		this.cols = cols;
	}
	public int getRows() {
		return rows;
	}
	public void setRows(int rows) {
		this.rows = rows;
	}
	public Space[][] getMap() {
		return map;
	}
	public void setMap(Space[][] map) {
		this.map = map;
	}
	
}
