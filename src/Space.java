/*
 * Space.java - abstract class that has an icon and checks if its occupied and location
 */
public abstract class Space {
	private String icon;
	private boolean occupied;
	private int row;
	private int col;
	private String ogIcon;
	public Space(String icon, int row, int col) {
		setIcon(icon);
		setOccupied(false);
		this.row = row;
		this.col = col;
		this.ogIcon = icon;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public boolean isOccupied() {
		return occupied;
	}

	public void setOccupied(boolean isOccupied) {
		this.occupied = isOccupied;
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

	public String getOgIcon() {
		return ogIcon;
	}
	
	/*
	 * Function to reset space to set it to not occupied and revert icon
	 */
	public void resetSpace() {
		setIcon(ogIcon);
		setOccupied(false);
	}
	
	/*
	 * Function set the space to occupied
	 */
	public void setSpaceOccupied(String icon) {
		setIcon(icon);
		setOccupied(true);
	
	}
}
