import java.util.*;

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
	private LinkedHashMap<Monster,Location> monstersOnMap;
	
	/*
	 * Function to start the game
	 */
	public void startGame() {
		initializeMap();
		//TODO: implement code
		playerList.add(initializePlayer(0));
		chooseHeros(playerList.get(0));
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
		System.out.println(player.getName() +", it is time to build your team, you're just a human so I don't think you can defeat these monsters");
		
		System.out.println();
		while(player.getHeroes().size() < numHeroesTeam) {
			HeroClass chosenHeroClass = io.parseHeroClass();
			Hero chosenHero = null;
			int index;
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
		io.printFullValorMap((ValorMap) getMap());
		playerTurn();
		System.out.println("===============================================");
		monsterTurn();
		io.printFullValorMap((ValorMap) getMap());
		numRound++;
		int results = roundResult();
		if(results == 0) {
		//	gameRound();
			System.out.println("No winner");
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
			Monster m = filteredList.get(r.nextInt(filteredList.size())).clone();

			int colSpawn = io.getRandomCellinRow((ValorMap) getMap(), i+1);
			ValorSpace s = (ValorSpace) getMap().getMap()[0][colSpawn];
			Location loc = new Location(i+1,0,colSpawn);
			monstersOnMap.put(m,loc);
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
		for(Monster m : monstersOnMap.keySet()) {
			ArrayList<Hero> heroesAvailable = checkMonsterVicinity(m);
			if(heroesAvailable.size() == 0) {
				//if no available heroes to attack, move forward/Down
				//TODO: implement code
				Location loc = monstersOnMap.get(m);
				int row = loc.getRow();
				ValorSpace s1 = (ValorSpace)getMap().getMap()[loc.getRow()][loc.getCol()];
				ValorSpace s2 = (ValorSpace)getMap().getMap()[loc.getRow()+1][loc.getCol()];
				if(s2.containMonster()) {
					continue;
				} else {
					s1.exitSpace(m);
					s2.enterSpace(m);
				}

			} else {
				//Randomly attack a hero in the list
				//TODO: implement code
				Random r = new Random();
				int heroIdx = r.nextInt(heroesAvailable.size());
				boolean hit = m.attack(heroesAvailable.get(heroIdx));
				if(hit) {
					io.printAttackScene(m, heroesAvailable.get(heroIdx), m.calculateDmg(heroesAvailable.get(heroIdx)));
				} else {
					io.printDodgeScene(heroesAvailable.get(heroIdx));
				}
				characterDie(heroesAvailable.get(heroIdx));
			}
		}


	}

	/*
	 * Function that calls actions is a character has died.
	 */
	public void characterDie(Character c) {
		if(c.getHp() <= 0) {
			if(c instanceof Hero) {
				//If hero dies, spawn back at nexus full health
				//TODO: implement code

			} else if (c instanceof Monster) {
				monstersOnMap.remove(c);
			}
		}

	}


	/*
	 * Function to check which heroes chosen monster can attack
	 */
	public ArrayList<Hero> checkMonsterVicinity(Monster m) {
		//TODO: implement code
		ArrayList<Hero> heros = new ArrayList<>();
		Location l = monstersOnMap.get(m);
		//Check current space
		if (((ValorSpace)getMap().getMap()[l.getRow()][l.getCol()]).containHero()) {
			for (Character c :((ValorSpace)getMap().getMap()[l.getRow()][l.getCol()]).getChars()) {
				if(c instanceof Hero) {
					heros.add((Hero) c);
				}
			}
		}

		//check for front
		if(l.getRow() < getMap().getRows()-1) {
			if (((ValorSpace)getMap().getMap()[l.getRow()+1][l.getCol()]).containHero()) {
				for (Character c :((ValorSpace)getMap().getMap()[l.getRow()+1][l.getCol()]).getChars()) {
					if(c instanceof Hero) {
						heros.add((Hero) c);
					}
				}
			}

			//check diagonal front left
			if(l.getCol() > 0) {
				if (((ValorSpace)getMap().getMap()[l.getRow()+1][l.getCol()-1]).containHero()) {
					for (Character c :((ValorSpace)getMap().getMap()[l.getRow()+1][l.getCol()-1]).getChars()) {
						if(c instanceof Hero) {
							heros.add((Hero) c);
						}
					}
				}

			}

			//check diagonal front right
			if(l.getCol() < getMap().getCols()-1) {
				if (((ValorSpace)getMap().getMap()[l.getRow()+1][l.getCol()+1]).containHero()) {
					for (Character c :((ValorSpace)getMap().getMap()[l.getRow()+1][l.getCol()+1]).getChars()) {
						if(c instanceof Hero) {
							heros.add((Hero) c);
						}
					}
				}

			}

		}

		//check left
		if(l.getCol() > 0) {
			if (((ValorSpace)getMap().getMap()[l.getRow()][l.getCol()-1]).containHero()) {
				for (Character c :((ValorSpace)getMap().getMap()[l.getRow()][l.getCol()-1]).getChars()) {
					if(c instanceof Hero) {
						heros.add((Hero) c);
					}
				}
			}

		}

		//check right
		if(l.getCol() < getMap().getCols()-1) {
			if (((ValorSpace)getMap().getMap()[l.getRow()][l.getCol()+1]).containHero()) {
				for (Character c :((ValorSpace)getMap().getMap()[l.getRow()][l.getCol()+1]).getChars()) {
					if(c instanceof Hero) {
						heros.add((Hero) c);
					}
				}
			}

		}

		return heros;
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
		ValorMap map = (ValorMap) getMap();
		Space[][] m =  map.getMap();
		boolean heroWin = false;
		boolean monsterWin = false;
		for(Space s :  m[0]) {
			if(s instanceof InaccessibleSpace) {
				continue;
			} 
			if(((ValorSpace)s).containHero()) {
				heroWin = true;
				break;
			}
		}
		
		
		
		//check bottom row if any monsters
		for(Space s : m[m.length-1]) {
			if(s instanceof InaccessibleSpace) {
				continue;
			} 
			if(((ValorSpace)s).containMonster()) {
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
		iconList = new ArrayList<>();
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
		playerList = new ArrayList<>();
		monstersOnMap = new LinkedHashMap<>();
		

		
		
		
	}

}
