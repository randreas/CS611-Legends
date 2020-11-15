import java.util.ArrayList;
import java.util.List;

public class ValorGame extends RPGGame {
	
	private List<Hero> warriorList;
	private List<Hero> paladinList;
	private List<Hero> sorcererList;
	private List<Monster> monsters;
	private Inventory itemInven; 
	private ioUtility io;
	private List<ValorPlayer> playerList;
	private int numHeroesTeam = 3;
	private List<String> iconList;
	private int numRound = 1;
	private List<Monster> monsterOnMap;
	
	public void startGame() {
		//TODO: implement code
		
	}
	
	public void gameRound() {
		
		if(numRound == 1 || numRound % 8 == 0) {
			spawnMonster();
		}
		//TODO: implement code
		playerTurn();
		monsterTurn();
		
		
		
		
		numRound++;
		int results = roundResult();
		if(results == 0) {
			gameRound();
		}
	}
	
	/*
	 * Function to spawn monster
	 * Find the maxLevel of hero and spawns monsters of that same level on to the map 
	 */
	public void spawnMonster() {
		//TODO: implement code
	}
	
	
	/*
	 * Function that prompts users what actions to take, check if action is valid, perform action
	 */
	public void playerTurn() {
		//TODO: implement code
	}
	/*
	 * Function to automatically call monsters turn
	 */
	public void monsterTurn() {
		for(Monster m : monsterOnMap) {
			ArrayList<Hero> heroesAvailable = checkMonsterVicinity(m);
			if(heroesAvailable.size() == 0) {
				//if no available heroes to attack, move forward
				//TODO: implement code
			} else {
				//Randomly attack a hero in the list
				//TODO: implement code
			}
		}
	}
	
	/*
	 * Function to check which heroes chosen monster can attack
	 */
	public ArrayList<Hero> checkMonsterVicinity(Monster m) {
		//TODO: implement code
		return null;
	}
	
	/*
	 * Function to check which monsters chosen hero can attack
	 */
	public ArrayList<Monster> checkHeroVicinity(Hero h) {
		//TODO: implement code
		return null;
	}
	
	/*
	 * Function that checks if a player has won, or a monster has won, or a tie, or no winner.
	 * Returns 
	 * 	0 if no winner.
	 * 	1 if player has won
	 * 	2 if monster has won
	 * 	3 if tie
	 */
	public int roundResult() {
		//TODO: implement code
		return 0;
	}
	
	public ValorGame() {
		
		super(null,"ValorGame",1);
		io = new ioUtility();
		io.printWelcomeMessage();
		//io.playSound("opening");
		iconList = new ArrayList<String>();
		//adding default icons 
		iconList.add("M");
		iconList.add("#");
		iconList.add(" ");
		iconList.add("P");
		iconList.add("K");
		iconList.add("B");
		iconList.add("C");
		Parser p = new Parser();
		warriorList = p.parseWarriors();
		paladinList = p.parsePaladins();
		sorcererList = p.parseSorcerers();
		monsters = p.parseMonsters();
		
		ValorMap map = new ValorMap();
		super.setMap(map);
		map.printMap();
		io.printFullValorMap(map);

		
		
		
	}

}
