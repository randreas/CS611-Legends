import java.util.*;

public class ValorPlayer extends Player {
	//Hashmap to track the hero and where his original lane is.
	private HashMap<Hero, Integer> heroes;
	public ValorPlayer() {
		super();
		heroes = new HashMap<Hero,Integer> ();
		
	}
	
	//Function to add a hero into the map
	public void addHero(Hero h, int lane) {
		heroes.put(h, lane);
	}
}
