import java.util.*;

/*
 * LegendsGame.java - a java file that extends RPGGame creates the game and starts it.
 */
public class LegendsGame extends RPGGame{
	private List<Hero> warriorList;
	private List<Hero> paladinList;
	private List<Hero> sorcererList;
	private List<Monster> monsters;
	private Inventory itemInven; 
	private ioUtility io;
	private List<LegendsPlayer> playerList;
	private int numHeroesTeam = 3;
	private List<String> iconList;
	
	
	/*
	 * Function to start the game
	 */
	public void startGame() {
		
		
		setPlayers();
		
		gameMovement();
		io.playSound("ending");
		System.out.println("Thank you for playing! See you soon!");
		io.printASCIIArt("GOODBYE");
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		io.stopAudio();
	}
	
	/*
	 * Function to set number of players and create them
	 */
	public void setPlayers() {
		setNumPlayers(io.parseNumPlayers());
		setNumHeroinTeam();
		for(int i = 0; i < getNumPlayers(); i++) {
			LegendsPlayer player = initializePlayer(i);
			chooseHeroes(player);
			playerList.add(player);
			
			Space s = ((LegendsMap) getMap()).getRandomAccessibleSpace();
			player.setRowLoc(s.getRow());
			player.setColLoc(s.getCol());
			((LegendsMap) getMap()).getMap()[s.getRow()][s.getCol()].setSpaceOccupied(player.getIcon());
		}
		
	}
	
	/*
	 * Function to initalize the current player.
	 */
	public LegendsPlayer initializePlayer(int i) {
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
		
		return new LegendsPlayer(name,icon);

	
	}
	
	/*
	 * Function to set the number of heroes in a team
	 */
	public void setNumHeroinTeam() {
		boolean validNum = false;
		while(!validNum) {
			System.out.println("Select how many heroes will be in a team. (1-" + numHeroesTeam + ")");
			int num = io.parseInt();
			if(num < 1 || num > numHeroesTeam) {
				System.out.println(ConsoleColors.RED + "Number of heroes in a team has to be more than 0 and less than " + (numHeroesTeam+1) + ConsoleColors.RESET);

			} else {
				this.numHeroesTeam = num;
				validNum = true;
			}
		}
	}
	
	/*
	 * Function for player to choose which heros will be in his team
	 */
	public void chooseHeroes(LegendsPlayer player) {
		
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
			
			player.addHeroes(chosenHero);
		
			
		}
	}
	/*
	 * Function for user to parse which action they would like to perform
	 */
	
	public void gameMovement() {
		for(LegendsPlayer p : playerList) {
			boolean isValidMove = false;
			boolean hasMoved = false;
			boolean firstMove = false;
			boolean turnEnds = false;
			io.playSound("map");
			
			while(p.isActive() && !turnEnds) {
				if(!firstMove) {
					printMap();
					firstMove = true;
				}
				
				System.out.println("It is " + p.getName() + "(" + p.getIcon() + ") turn to move.");
				
				
					String choice  = io.choiceMenu();
					switch(choice) {
					case "W":
					case "A":
					case "S":
					case "D":
						if(!hasMoved) {
							isValidMove = p.move(choice,(LegendsMap) getMap());
							if(isValidMove) {
								((LegendsMap) getMap()).mapAction(p, playerList);
								hasMoved = true;
								printMap();
								
							}
							
							
						} else {
							System.out.println(ConsoleColors.RED + p.getName() + " has moved. You can do other stuff or end your Turn (T)." + ConsoleColors.RESET);
						}
						break;
					case "E":
						for(Hero h : p.getHeroes()) {
							
							System.out.println(h.getName() + "'s Inventory");
							if(h.getInventory().isEmpty()) {
								System.out.println(ConsoleColors.GREEN_BRIGHT + "Inventory is empty. Nothing to do or see here" + ConsoleColors.RESET);
								System.out.println();
							} else {
								h.getInventory().printInventory();
								System.out.println("Do you want to equip or use an Item? (Y/N)");
								boolean yn = io.parseYesNo();
								if(yn) {
									h.useItem();
									
								}
							}
							
						}
						break; 
					case "I":
						p.printPlayer();
						break;
					case "M":
						printMap();
						break;
					case "Q":
						((LegendsMap) getMap()).getMap()[p.getRowLoc()][p.getColLoc()].resetSpace();
						p.setIsActive(false);
						isValidMove = true;
						
						break;
					case "T":
						turnEnds = true;
						break;
					
					}
					
				
			}
			System.out.println( p.getName()+"\'s Turn Ends.");
			
		}
		
		
		
		removeDeadPlayers();
		
		
		boolean isAllinactive = checkAllInActive();
		if(!isAllinactive) {
			gameMovement();
		}
		
	}
	
	/*
	 * Function to print full map with heros stats and legend
	 */
	public void printMap() {
		for(int i = 0; i < playerList.size(); i++) {
			playerList.get(i).printPlayer();
		}
		((LegendsMap) getMap()).printMap();
		io.printLegend(playerList);
	}
	
	/*
	 * Function to remove players that heroes all died
	 */
	public void removeDeadPlayers() {
		for(int i = playerList.size()-1; i >= 0; i--) {
			boolean allDead = true;
			for(Hero h : playerList.get(i).getHeroes()) {
				if(h.getHp() > 0) {
					allDead = false;
					break;
				}
			}
			if(allDead) {
				System.out.println(ConsoleColors.RED +  playerList.get(i).getName() +", all your heroes have died. Ejecting out of game now." + ConsoleColors.RESET);
				playerList.remove(i);
			}
		}
	}
	
	public boolean checkAllInActive () {
		boolean ret = true;
		for(LegendsPlayer p : playerList) {
			if(p.isActive()) {
				ret = false;
				break;
			}
		}
		return ret;
	}
	
	
	/*
	 * Legends Game constructor
	 */
	public LegendsGame() {
		
		super(null,"LegendsGame",1);
		io = new ioUtility();
		io.printWelcomeMessage();
		io.playSound("opening");
		iconList = new ArrayList<String>();
		//adding default icons 
		iconList.add("M");
		iconList.add("#");
		iconList.add(" ");
		Parser p = new Parser();
		warriorList = p.parseWarriors();
		paladinList = p.parsePaladins();
		sorcererList = p.parseSorcerers();
		monsters = p.parseMonsters();
		playerList = new ArrayList<LegendsPlayer>();
		itemInven = new Inventory(p.parseWeapon(),p.parseArmor(),p.parseSpell(), p.parsePotion());
		int setMapSize = io.parseMapSize();
		LegendsMap map = new LegendsMap(setMapSize,setMapSize,itemInven,monsters);
		super.setMap(map);
		
		
		
		
	}
	
	
}
