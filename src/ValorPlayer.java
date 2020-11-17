import java.util.*;

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
	public void back(Hero h, ValorMap map) {
		Location currLoc = h.getLocation();
		int ogLane = h.getLocation().getLane();
		ioUtility io = new ioUtility();
		Location nexusLoc = io.getNexusLocation(map,ogLane);
		((ValorSpace)map.getMap()[currLoc.getRow()][currLoc.getCol()]).exitSpace(h);
		((ValorSpace)map.getMap()[nexusLoc.getRow()][nexusLoc.getCol()]).enterSpace(h);
		h.setLocation(nexusLoc);
		map.mapAction(h,currLoc, nexusLoc);
	}
	/*
	 * Function for player to move on the ValorMap
	 * TODO: Update Location
	 */
	public boolean move(Hero h, Location l, String dir, ValorMap map) {
		int rowLoc = l.getRow();
		int colLoc = l.getCol();
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
