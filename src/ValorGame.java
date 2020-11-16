import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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
	private List<Monster> monstersOnMap;
	
	/*
	 * Function to start the game
	 */
	public void startGame() {
		initializeMap();
		io.printFullValorMap((ValorMap) getMap());
		//TODO: implement code
		initializePlayer(0);
		gameRound();
		
		
	}
	
	public void initializeMap() {
		String mode;
		boolean isValid = false;
		while(!isValid) {
			System.out.println("Which type of map would you like use?");
			System.out.println("Default (D) - 8x8 Board, 3 Lanes, 2 cells per lane.");
			System.out.println("Custom  (C)");
			mode = io.parseString().toUpperCase();
			if(mode.equals("D")) {
				ValorMap map = new ValorMap();
				super.setMap(map);
				isValid = true;
			} else if (mode.equals("C")) {
				int numLanes = 0;
				int laneSize = 0;
				int numRows = 0;
				boolean isValid2 = false;
				while(!isValid2) {
					System.out.println("Configure number of rows. (4-20)");
					numRows = io.parseInt();
					if(numRows < 4 || numRows > 20) {
						io.printErrorParse();
					} else {
						isValid2 = true;
					}
				}
				isValid2 = false;
				while(!isValid2) {
					System.out.println("Configure number of lanes. (1-5)");
					numLanes = io.parseInt();
					if(numLanes < 1 || numLanes > 5) {
						io.printErrorParse();
					} else {
						isValid2 = true;
					}
				}
				isValid2 = false;
				while(!isValid2) {
					System.out.println("Configure size of lanes. (1-4)");
					laneSize = io.parseInt();
					if(laneSize < 1 || laneSize > 4) {
						io.printErrorParse();
					} else {
						isValid2 = true;
					}
				}
				
				int numCols = (laneSize*numLanes) + (numLanes-1);
				ValorMap map = new ValorMap(numRows,numCols,numLanes,laneSize);
				super.setMap(map);
				
				isValid = true;
			} else {
				isValid = false;
			}
		}
		
		
		
	}
	/*
	 * Function to initialize player
	 */
	public ValorPlayer initializePlayer(int i) {
		System.out.println("Player" + (i+1) + ", please enter your name:");
		String name = io.parseString();
		
		boolean validIcon = false;
		String icon = "";
		while(!validIcon) {
			System.out.println(name +", please enter your desired icon:");
			icon  = io.parseString();
			if(icon.length() > 1) {
				System.err.println(ConsoleColors.RED + "Icon can only 1 character long." + ConsoleColors.RESET);
				continue;
			}
			if(iconList.contains(icon)) {
				System.out.println(ConsoleColors.RED + "Icon is already selected by another player. Please enter a unique icon" + ConsoleColors.RESET);
				continue;
			} else {
				validIcon = true;
				iconList.add(icon);
			}
		}
		
		return new ValorPlayer(name,icon);

	
	}
	
	/*
	 * Function to chooseHeros to add into player
	 */
	public void chooseHeros(ValorPlayer player) {
		System.out.println(player.getName() +", it is time to build your team, you're just a human so I don\'t think you can defeat these monsters");
		
		System.out.println();
		while(player.getHeroes().size() < numHeroesTeam) {
			HeroClass chosenHeroClass = io.parseHeroClass();
			Hero chosenHero = null;
			int index = -1;
			switch(chosenHeroClass) {
			case PALADIN:
				index = io.parseHeroId(paladinList,chosenHeroClass);
				chosenHero = paladinList.get(index);
				paladinList.remove(index);
				break;
			case SORCERER:
				index = io.parseHeroId(sorcererList,chosenHeroClass);
				chosenHero = sorcererList.get(index);
				sorcererList.remove(index);
				break;
			case WARRIOR:
				index = io.parseHeroId(warriorList, chosenHeroClass);
				chosenHero = warriorList.get(index);
				warriorList.remove(index);
				break;
			default:
				break;
			}
			
			Location loc = io.parseInitalLaneLocation((ValorMap) getMap());
			ValorSpace s = (ValorSpace) getMap().getMap()[loc.getRow()][loc.getCol()];
			s.enterSpace(chosenHero);
			player.addHero(chosenHero,loc);
		
			
		}
	}
	
	public void gameRound() {
		
		if(numRound == 1 || numRound % 8 == 0) {
			spawnMonster();
		}
		playerTurn();
		monsterTurn();
		
		numRound++;
		int results = roundResult();
		if(results == 0) {
			gameRound();
		} else if(results == 1) {
			System.out.println("Player Wins");
		} else if(results == 2) {
			System.out.println("Monster Wins");
		} else {
			System.out.println("Tie");
		}
	}
	
	/*
	 * Function to spawn monster
	 * Find the maxLevel of hero and spawns monsters of that same level on to the map 
	 */
	public void spawnMonster() {
		int maxLevel = 0;
		for(Hero h :playerList.get(0).getHeroes().keySet()) {
			if(h.getLevel() > maxLevel) {
				maxLevel = h.getLevel();
			}
		}
		
		List<Monster> filteredList = new ArrayList<Monster>();
		for(Monster m : monsters) {
			if(m.getLevel() == maxLevel) {
				filteredList.add(m);
			}
		}
		
		for(int i = 0; i < ((ValorMap)getMap()).getNumLanes(); i++) {
			Random r = new Random();
			Monster m = (Monster) filteredList.get(r.nextInt(filteredList.size())).clone();
			monstersOnMap.add(m);
			int colSpawn = io.getRandomCellinRow((ValorMap) getMap(), i);
			ValorSpace s = (ValorSpace) getMap().getMap()[0][colSpawn];
			s.enterSpace(m);
		}
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
		for(Monster m : monstersOnMap) {
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
		//check top row if any heroes
		ValorSpace[][] m = (ValorSpace[][]) getMap().getMap();
		boolean heroWin = false;
		boolean monsterWin = false;
		for(ValorSpace s : m[0]) {
			if(s instanceof InaccessibleSpace) {
				continue;
			} 
			if(s.containHero()) {
				heroWin = true;
				break;
			}
		}
		
		
		
		//check bottom row if any monsters
		for(ValorSpace s : m[m.length-1]) {
			if(s instanceof InaccessibleSpace) {
				continue;
			} 
			if(s.containMonster()) {
				monsterWin = true;
				break;
			}
		}
		
		if(monsterWin && heroWin) {
			return 3;
		} else if(monsterWin) {
			return 2;
		} else if(heroWin) {
			return 1;
		} 
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
		
		
		

		
		
		
	}

}
