import java.util.*;

/*
 * ValorPlayer.java - class that creates a valor player and extends Player
 */
public class ValorPlayer extends Player {
	//Hashmap to track the hero and where his original lane is.
	private ArrayList<Hero> heroes;
	
	public ValorPlayer() {
		super();
		heroes = new ArrayList<> ();
		
	}
	
	public ValorPlayer(String name, String icon) {
		// TODO Auto-generated constructor stub
		super(name,icon);
		heroes = new ArrayList<> ();
		
	}

	//Function to add a hero into the map
	public void addHero(Hero h) {
		heroes.add(h);
	}


	
	public  ArrayList<Hero>  getHeroes() {
		return this.heroes;
	}

	/*
	 * Function for player to back to original lane and to nexus
	 */
	public boolean back(Hero h, ValorMap map) {
		Location currLoc = h.getLocation();
		if(currLoc.getCurrent_lane() == h.getLocation().getHome_lane() && currLoc.getRow() == 0) {
			//If hero is currently is in home lane and in base, cannot back
			return false;
		}
		int ogLane = h.getLocation().getHome_lane();
		ioUtility io = new ioUtility();
		Location nexusLoc = io.getNexusLocation(map,ogLane);
		if(((ValorSpace) map.getMap()[nexusLoc.getRow()][nexusLoc.getCol()]).containHero()) {
			return false;
		}
		((ValorSpace)map.getMap()[currLoc.getRow()][currLoc.getCol()]).exitSpace(h);
		((ValorSpace)map.getMap()[nexusLoc.getRow()][nexusLoc.getCol()]).enterSpace(h);
		h.setLocation(nexusLoc);
		map.mapAction(h,currLoc, nexusLoc);
		return true;
	}

	/*
	 * Function for player to move on the ValorMap
	 */
	public boolean move(Hero h, Location l, String dir, ValorMap map) {
		int rowLoc = l.getRow();
		int colLoc = l.getCol();
		if(dir.equals("W")) {
			if((rowLoc - 1) >= 0) {
				if(map.getMap()[rowLoc - 1][colLoc] instanceof InaccessibleSpace) {
					System.out.println("That area is inaccessible. Please go another way");
					return false;
				} else {
					if(h.enemyBlock(map, "W")) {
						System.out.println("You have to kill the monster to go up. Please go another way");
						return false;
					}
					rowLoc--;
					if(((ValorSpace) map.getMap()[rowLoc][colLoc]).containHero()) {
						System.out.println("Too many heroes in one cell. Please go another way");
						return false;
					}
					if(((ValorSpace) map.getMap()[rowLoc][colLoc]).getChars().size() == 2) {
						System.out.println("Too many creatures in one cell. Please go another way");
						return false;
					}
					map.getMap()[rowLoc + 1][colLoc].resetSpace();
					((ValorSpace) map.getMap()[rowLoc + 1][colLoc]).exitSpace(h);
					((ValorSpace) map.getMap()[rowLoc][colLoc]).enterSpace(h);
					h.getLocation().setRow(rowLoc);
					h.getLocation().setCol(colLoc);
					if(rowLoc < h.getMinimal_dis_row()) {
						h.setMinimal_dis_row(rowLoc);
					}
					return true;
				}

			} else {
				System.out.println("You cannot go any further. Please choose a valid movement input.");
				return false;
			}
		} else if(dir.equals("S")) {
			if((rowLoc + 1) < map.getRows()) {
				if(map.getMap()[rowLoc + 1][colLoc] instanceof InaccessibleSpace) {
					System.out.println("That area is inaccessible. Please go another way");
					return false;
				} else {
					rowLoc++;
					if(((ValorSpace) map.getMap()[rowLoc][colLoc]).containHero()) {
						System.out.println("Too many heroes in one cell. Please go another way");
						return false;
					}
					if(((ValorSpace) map.getMap()[rowLoc][colLoc]).getChars().size() == 2) {
						System.out.println("Too many creatures in one cell. Please go another way");
						return false;
					}
					map.getMap()[rowLoc - 1][colLoc].resetSpace();
					((ValorSpace) map.getMap()[rowLoc - 1][colLoc]).exitSpace(h);
					((ValorSpace) map.getMap()[rowLoc][colLoc]).enterSpace(h);
					h.getLocation().setRow(rowLoc);
					h.getLocation().setCol(colLoc);
					return true;
				}
			} else {
				System.out.println("You cannot go any further. Please choose a valid movement input.");
				return false;
			}
		} else if(dir.equals("A")) {
			if(colLoc - 1 >= 0) {
				if(map.getMap()[rowLoc][colLoc -1] instanceof InaccessibleSpace) {
					System.out.println("That area is inaccessible. Please go another way");
					return false;
				} else {
					colLoc--;
					if(((ValorSpace) map.getMap()[rowLoc][colLoc]).containHero()) {
						System.out.println("Too many heroes in one cell. Please go another way");
						return false;
					}
					if(((ValorSpace) map.getMap()[rowLoc][colLoc]).getChars().size() == 2) {
						System.out.println("Too many creatures in one cell. Please go another way");
						return false;
					}
					map.getMap()[rowLoc][colLoc + 1].resetSpace();
					((ValorSpace) map.getMap()[rowLoc][colLoc + 1]).exitSpace(h);
					((ValorSpace) map.getMap()[rowLoc][colLoc]).enterSpace(h);
					h.getLocation().setRow(rowLoc);
					h.getLocation().setCol(colLoc);
					return true;
				}
			} else {
				System.out.println("You cannot go any further. Please choose a valid movement input.");
				return false;
			}
		} else if(dir.equals("D")) {
			if(colLoc + 1 < map.getCols()) {
				if(map.getMap()[rowLoc][colLoc+1] instanceof InaccessibleSpace) {
					System.out.println("That area is inaccessible. Please go another way");
					return false;
				} else {
					colLoc++;
					if(((ValorSpace) map.getMap()[rowLoc][colLoc]).containHero()) {
						System.out.println("Too many heroes in one cell. Please go another way");
						return false;
					}
					if(((ValorSpace) map.getMap()[rowLoc][colLoc]).getChars().size() == 2) {
						System.out.println("Too many creatures in one cell. Please go another way");
						return false;
					}
					map.getMap()[rowLoc][colLoc - 1].resetSpace();
					((ValorSpace) map.getMap()[rowLoc][colLoc - 1]).exitSpace(h);
					((ValorSpace) map.getMap()[rowLoc][colLoc]).enterSpace(h);
					h.getLocation().setRow(rowLoc);
					h.getLocation().setCol(colLoc);
					return true;
				}
			} else {
				System.out.println("You cannot go any further. Please choose a valid movement input.");
				return false;
			}
		}



		return true;


	}

	public void printPlayer() {
		System.out.println(getName() + "'s Team:");
		ioUtility io = new ioUtility();
		io.printHeroes(heroes);
	}
}
