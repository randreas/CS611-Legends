import java.util.*;

/*
 * Commonspace.java - extends Space class in the map
 */
public class CommonSpace extends Space {
	private boolean isSafe = false;
	
	public CommonSpace(int row, int col) {
		super(" ", row, col);
		// TODO Auto-generated constructor stub
	}
	
	/*
	 * Checks if the space is considered safe
	 */
	public boolean checkSafe() {
		rollDice();
		return isSafe;
	}

	/*
	 * Random chance to check if space is safe
	 */
	public void rollDice() {
		Random r = new Random();
		if(r.nextInt(2) == 1) {
			this.isSafe = true;
		} else {
			this.isSafe = false;
		}
	}
	
	
}
