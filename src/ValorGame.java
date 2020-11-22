import java.math.BigDecimal;
import java.util.*;

/*
 * ValorGame.java - game class that runs the game functions in order for game to run.
 */
public class ValorGame extends RPGGame {
	
	private List<Hero> warriorList;
	private List<Hero> paladinList;
	private List<Hero> sorcererList;
	private List<Monster> monsters;
	private Inventory itemInven;
	private ioUtility io;
	private ValorPlayer player;
	private int numHeroesTeam = 3;
	private List<String> iconList;
	private int numRound = 1;
	private ArrayList<Monster> monstersOnMap;
	
	/*
	 * Function to start the game
	 */
	public void startGame() {
		initializeMap();
		//TODO: implement code
		initializePlayer(0);
		chooseHeros(player);
		gameRound();
		
		
	}

	/*
	 * Function to initialize valor map to play on.
	 */
	public void initializeMap() {
		String mode;
		boolean isValid = false;
		while(!isValid) {
			System.out.println("Which type of map would you like use?");
			System.out.println("Default (D) - 8x8 Board, 3 Lanes, 2 cells per lane.");
			System.out.println("Custom  (C)");
			mode = io.parseString().toUpperCase();
			if(mode.equals("D")) {
				ValorMap map = new ValorMap(itemInven);
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
				ValorMap map = new ValorMap(numRows,numCols,numLanes,laneSize,itemInven);
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
	public void initializePlayer(int i) {
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
		
		player = new ValorPlayer(name,icon);

	
	}
	
	/*
	 * Function to chooseHeros to add into player
	 */
	public void chooseHeros(ValorPlayer player) {
		System.out.println(player.getName() +", it is time to build your team, you're just a human so I don't think you can defeat these monsters");
		System.out.println("Each lane can only have 1 hero initially.");
		System.out.println();
		for(int i = 0; i < ((ValorMap)getMap()).getNumLanes(); i++) {
			System.out.println("For Lane" + (i+1) + ", please select your hero");
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
			Location loc = io.getNexusLocation((ValorMap) getMap(), i+1);
			chosenHero.setLocation(loc);
			ValorSpace s = (ValorSpace) getMap().getMap()[loc.getRow()][loc.getCol()];
			s.enterSpace(chosenHero);
			player.addHero(chosenHero);

		}
		
	}
	
	public void gameRound() {


		if(numRound == 1 || numRound % 8 == 0) {
			spawnMonster();
		}
		boolean continueGame = playerTurn();
		System.out.println("==================================================================");
		if(continueGame) {
			monsterTurn();
		}

		io.printFullValorMap((ValorMap) getMap());
		numRound++;
		int results = roundResult(continueGame);
		if(results == 0) {

			System.out.println("No winner");
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
		for(Hero h : player.getHeroes()) {
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
			Location loc = new Location(i+1,i+1,0, colSpawn);
			System.out.println("Spawning monster " + m.getName());
			System.out.println("Row " + loc.getRow());
			System.out.println("Col " + loc.getCol());
			m.setLocation(loc);
			monstersOnMap.add(m);
			s.enterSpace(m);
		}
	}
	
	
	/*
	 * Function that prompts users what actions to take, check if action is valid, perform action
	 */
	public boolean playerTurn() {
		player.printPlayer();
		io.printMonsters(monstersOnMap);
		for (Hero h : player.getHeroes()) {
			boolean isValidMove = false;
			boolean hasMoved = false;
			boolean firstMove = false;
			boolean turnEnds = false;
			io.playSound("map");

			while (!turnEnds) {

				if (!firstMove) {
					io.printFullValorMap((ValorMap) getMap());
					firstMove = true;
				}

				System.out.println("It is " + h.getName() + " turn to move.");
				io.printHeroLocation(h);

				String choice = io.choiceValorMenu();
				switch (choice) {
					case "W":
					case "A":
					case "S":
					case "D":
						if (!hasMoved) {
							Location l1 = h.getLocation();
							isValidMove = player.move(h,h.getLocation(), choice, (ValorMap) getMap());

							if (isValidMove) {
								Location l2 = h.getLocation();
								((ValorMap) getMap()).mapAction(h,l1,l2);
								hasMoved = true;
								io.printFullValorMap((ValorMap) getMap());

							}


						} else {
							System.out.println(ConsoleColors.RED + player.getName() + " has moved. You can do other stuff or end your Turn (T)." + ConsoleColors.RESET);
						}
						break;
					case "E":


						System.out.println(h.getName() + "'s Inventory");
						if (h.getInventory().isEmpty()) {
							System.out.println(ConsoleColors.GREEN_BRIGHT + "Inventory is empty. Nothing to do or see here" + ConsoleColors.RESET);
							System.out.println();
						} else {
							h.getInventory().printInventory();
							System.out.println("Do you want to equip or use an Item? (Y/N)");
							boolean yn = io.parseYesNo();
							if (yn) {
								h.useItem();
							}

						}
						break;
					case "I":
						player.printPlayer();
						break;
					case "M":
						io.printFullValorMap((ValorMap) getMap());
						break;
					case "Q":
						System.out.println("Player has surrendered. Monsters win");
						isValidMove = true;
						return false;

					case "T":
						turnEnds = true;
						break;
					case "G":
						//attack
						if (!hasMoved) {
							ArrayList<Character> enemies = h.spotEnemy((ValorMap) getMap());
							if (enemies.size() == 0) {
								System.out.println("No enemies to attack");
							} else {
								h.attackSequence(enemies);
								hasMoved = true;
							}

						} else {
							System.out.println(ConsoleColors.RED + player.getName() + " has moved. You can do other stuff or end your Turn (T)." + ConsoleColors.RESET);
						}
						break;
					case "H":
						//Spell cast
						if (!hasMoved) {
							ArrayList<Character> enemies = h.spotEnemy((ValorMap) getMap());
							if (enemies.size() == 0) {
								System.out.println("No enemies to cast spell");
							} else {
								h.spellSequence(enemies);
								hasMoved = true;
							}

						} else {
							System.out.println(ConsoleColors.RED + player.getName() + " has moved. You can do other stuff or end your Turn (T)." + ConsoleColors.RESET);
						}
						break;
					case "F":
						if (!hasMoved) {
							boolean teleport_success = false;
							while (!teleport_success) {
								Location des = io.parseTeleportLocation((ValorMap) getMap(),h);
								teleport_success = h.teleport(des, (ValorMap) getMap());
								if(h.getMinimal_dis_row() == 1000) {
									break;
								}
							}
							io.printFullValorMap((ValorMap) this.getMap());
						} else {
							System.out.println(ConsoleColors.RED + player.getName() + " has moved. You can do other stuff or end your Turn (T)." + ConsoleColors.RESET);

						}
						break;
					case "B":
						//Back
						if (!hasMoved) {
							boolean back_success = player.back(h, (ValorMap) getMap());
							if(back_success) {
								hasMoved = true;
							} else {
								System.out.println("Hero cannot back while in his original lane and nexus.");
							}
						} else {
							System.out.println(ConsoleColors.RED + player.getName() + " has moved. You can do other stuff or end your Turn (T)." + ConsoleColors.RESET);

						}
						break;
				}


			}

			System.out.println(h.getName() + "'s Turn Ends.");
		}
		return true;
	}


	/*
	 * Function to automatically call monsters turn
	 */
	public void monsterTurn() {
		for(Monster m : monstersOnMap) {
			ArrayList<Hero> heroesAvailable = checkMonsterVicinity(m);
			if(heroesAvailable.size() == 0) {
				//if no available heroes to attack, move forward/Down
				Location loc = m.getLocation();
				ValorSpace s1 = (ValorSpace)getMap().getMap()[loc.getRow()][loc.getCol()];
				ValorSpace s2 = (ValorSpace)getMap().getMap()[loc.getRow()+1][loc.getCol()];
				if(!s2.containMonster()) {
					s1.exitSpace(m);
					m.setRow(loc.getRow()+1);
					s2.enterSpace(m);
				}

			} else {
				//Randomly attack a hero in the list
				Random r = new Random();
				int heroIdx = r.nextInt(heroesAvailable.size());
				boolean hit = m.attack(heroesAvailable.get(heroIdx));
				if(hit) {
					io.printAttackScene(m, heroesAvailable.get(heroIdx), m.calculateDmg(heroesAvailable.get(heroIdx)));
				} else {
					io.printDodgeScene(heroesAvailable.get(heroIdx));
				}
				characterDie(player,m,heroesAvailable.get(heroIdx));
			}
		}


	}




	/*
	 * Function that calls actions is a character has died.
	 * c1 is attacker
	 * c2 is attacked character
	 */
	public void characterDie(ValorPlayer p, Character c1, Character c2) {
		if(c2.getHp() <= 0) {
			if(c2 instanceof Hero) {
				//If hero dies, spawn back at nexus half health, gets to buy item?
				p.back((Hero)c2,(ValorMap)getMap());
				((Hero)c2).respawn();
			} else if (c2 instanceof Monster) {
				//Hero c1 has killed a monster
				monstersOnMap.remove(c2);
				((Hero)c1).gainExp();
				BigDecimal moneyGain = new BigDecimal("100").multiply(new BigDecimal(c1.getLevel()));
				((Hero)c1).setWallet(((Hero)c1).getWallet().add(moneyGain));
			}
		}

	}


	/*
	 * Function to check which heroes chosen monster can attack
	 */
	public ArrayList<Hero> checkMonsterVicinity(Monster m) {
		ArrayList<Hero> heros = new ArrayList<>();
		Location l = m.getLocation();
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
	 */
	public int roundResult(boolean continueGame) {
		if(!continueGame) {
			return 2;
		}
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
			return 1;
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
		monstersOnMap = new ArrayList<>();
		itemInven = new Inventory(p.parseWeapon(),p.parseArmor(),p.parseSpell(), p.parsePotion());





	}

}
