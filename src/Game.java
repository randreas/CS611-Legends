/*
 * Game.java - abstract class that just has basic name and number of players
 */
public abstract class Game {
	private String name;
	private int numPlayers;
	
	public Game(String name, int numPlayers) {
		this.name = name;
		this.numPlayers = numPlayers;
		
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getNumPlayers() {
		return numPlayers;
	}
	public void setNumPlayers(int numPlayers) {
		this.numPlayers = numPlayers;
	}
	
	
}
