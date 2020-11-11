import java.util.*;

/*
 * LegendsPlayer.java - extends Player class, contains a list of heroes
 * and current location and isActive
 */
public class LegendsPlayer extends Player{
	
	private ArrayList<Hero> heroes;
	private int rowLoc;
	private int colLoc;
	private boolean isActive;
	public LegendsPlayer() {
		super();
		heroes = new ArrayList<Hero> ();
		isActive = true;
	}
	
	public LegendsPlayer(String name, String icon) {
		super(name,icon);
		heroes = new ArrayList<Hero> ();
		isActive = true;
	}
	
	public ArrayList<Hero> getHeroes() {
		return heroes;
	}
	public void setHeroes(ArrayList<Hero> heroes) {
		this.heroes = heroes;
	}
	
	/*
	 * Adds a hero into the heroList
	 */
	public void addHeroes(Hero hero) {
		heroes.add(hero);
	}
	
	/*
	 * Function that prints player information, and heroes he has
	 */
	public void printPlayer() {
		System.out.println(getName() + "'s Team:");
		ioUtility io = new ioUtility();
		io.printHeroes(heroes);
	}

	public int getRowLoc() {
		return rowLoc;
	}

	public void setRowLoc(int rowLoc) {
		this.rowLoc = rowLoc;
	}

	public int getColLoc() {
		return colLoc;
	}

	public void setColLoc(int colLoc) {
		this.colLoc = colLoc;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setIsActive(boolean isActive) {
		this.isActive = isActive;
	}
	
	/*
	 * Function for player to move on the LegendsMap
	 */
	public boolean move(String dir, LegendsMap map) {
		
		if(dir.equals("W")) {
			if((rowLoc - 1) >= 0) {
				if(map.getMap()[rowLoc - 1][colLoc] instanceof BlockedSpace) {
					System.out.println("That area is inaccessible. Please go another way");
					return false;
				} else {
					map.getMap()[rowLoc][colLoc].resetSpace();
					rowLoc--;
				}
				
			} else {
				System.out.println("You cannot go any further. Please choose a valid movement input.");
				return false;
			}
		} else if(dir.equals("S")) {
			if((rowLoc + 1) < map.getRows()) {
				if(map.getMap()[rowLoc + 1][colLoc] instanceof BlockedSpace) {
					System.out.println("That area is inaccessible. Please go another way");
					return false;
				} else {
					map.getMap()[rowLoc][colLoc].resetSpace();
					rowLoc++;
				}
			} else {
				System.out.println("You cannot go any further. Please choose a valid movement input.");
				return false;
			}
		} else if(dir.equals("A")) {
			if(colLoc - 1 >= 0) {
				if(map.getMap()[rowLoc][colLoc -1] instanceof BlockedSpace) {
					System.out.println("That area is inaccessible. Please go another way");
					return false;
				} else {
					map.getMap()[rowLoc][colLoc].resetSpace();
					colLoc--;
				}
			} else {
				System.out.println("You cannot go any further. Please choose a valid movement input.");
				return false;
			}
		} else if(dir.equals("D")) {
			if(colLoc + 1 < map.getCols()) {
				if(map.getMap()[rowLoc][colLoc+1] instanceof BlockedSpace) {
					System.out.println("That area is inaccessible. Please go another way");
					return false;
				} else {
					map.getMap()[rowLoc][colLoc].resetSpace();
					colLoc++;
				}
			} else {
				System.out.println("You cannot go any further. Please choose a valid movement input.");
				return false;
			}
		}
		
		
		
		return true;

	}
	
}
