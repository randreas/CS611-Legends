/*
 * RPGGame.java - extends Game
 * has a GridMap since all RPGGames needs a map
 */
public class RPGGame extends Game{
	
	private GridMap map;
	
	RPGGame(String name, int numPlayers) {
		super(name, numPlayers);
		setMap(new GridMap());
	}

	
	RPGGame(GridMap map,String name, int numPlayers) {
		super(name, numPlayers);
		this.setMap(map);
		// TODO Auto-generated constructor stub
	}


	public GridMap getMap() {
		return map;
	}


	public void setMap(GridMap map) {
		this.map = map;
	}

}
