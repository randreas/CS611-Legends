import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.List;
import java.util.Map.Entry;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;


import java.awt.*;
import java.awt.image.BufferedImage;
import java.math.BigDecimal;

/*
 * ioUtility.java - a java file that handles a majority of all the inputs and outputs for Valor
 */

public class ioUtility {
	private static final Scanner scanner = new Scanner(System.in);
	private static AudioInputStream audioStream = null;
	private static Clip clip = null;

	public ioUtility() {

	}
	
	/*
	 * Function to parseString
	 */
	public String parseString() {

		return scanner.next();
	}
	
	/*
	 * Function to print welcome message to users
	 */
	public void printWelcomeMessage() {
		printASCIIArt("WELCOME");
		String string = "¯\\\\_(ツ)_/¯";
		StandardCharsets.UTF_8.encode(string);
		System.out.println("\t<------------------------------ Welcome to the World of Valor -------------------------------->");
		System.out.println("\t<                                                                                             >");
		System.out.println("\t<    You mysteriously teleported into the mysterious world where gods and monster lived,      >");
		System.out.println("\t<    while coding for your final project that you spent the last 3 weeks procrastinating.     >");
		System.out.println("\t<    What better way to procrastinate even more? Control these gods and slay some beasts.     >");
		System.out.println("\t<    Don't worry. This is not like sword art online where if you die in the game, you die     >");
		System.out.println("\t<    in real life. But if you fail the final project, your parents will disown you." + string +" >");
		System.out.println("\t<    As you read more about this game, you realize you're just wasting time not fighting.     >");
		System.out.println("\t<    So fight Champion, live to see tomorrow and for thats when you will start coding again.  >");
		System.out.println("\t<                                                                                             >");
		System.out.println("\t<--------------------------------------------------------------------------------------------->");

		System.out.println();
		
	}
	
	/*
	 * Function to parse valid integer to user.
	 */
	public int parseInt() {
		boolean validInt = false;
		int num = -1;
		while(!validInt) {
			try {
				num = scanner.nextInt();
				validInt = true;
			} catch (Exception e) {
				System.out.println(ConsoleColors.RED +"Invalid input. Please enter a number" + ConsoleColors.RESET);
				scanner.next();
			}
		}
		return num;
	}
	

	
	/*
	 * Function to parse yes or no options
	 */
	public boolean parseYesNo() {
		boolean ret = false;
		boolean validParse = false;
		while(!validParse) {
			String parse = parseString().toUpperCase();
			switch(parse) {
			case "Y":
				ret = true;
				validParse = true;
				break;
			case "N":
				ret = false;
				validParse = true;
				break;
			default:
				printErrorParse();
				validParse = false;
				break;
			}
		}
		return ret;
	}
	
	/*
	 * Function for users to parse hero class chosen.
	 */
	public HeroClass parseHeroClass() {
		boolean validParse = false;
		while(!validParse) {
			System.out.println("Choose a hero class:");
			System.out.println("Warrior (W)");
			System.out.println("Sorcerer (S)");
			System.out.println("Paladin (P)");
			String heroClass = parseString().toUpperCase();
			switch(heroClass) {
			case "W":
				return HeroClass.WARRIOR;
			case "S":
				return HeroClass.SORCERER;
			case "P":
				return HeroClass.PALADIN;
			default:
				printErrorParse();
				
			}
			
		}
		return null;

	}
	
	/*
	 * Function print generic error message
	 */
	public void printErrorParse() {
		System.out.println(ConsoleColors.RED + "Invalid option. Please re-enter a valid option." + ConsoleColors.RESET);
		
	}
	
	/*
	 * Function to print heroes list based on hero class
	 */
	public void printHeroes(List<Hero> list,HeroClass heroClass) {
		System.out.println(heroClass.toString() + " List");
		printHeroes(list);
		
	}
	
	
	/*
	 * Function to print heroes list 
	 */
	public void printHeroes(List<Hero> list) {
		System.out.println("ID\tName\t\t\t\t\tLevel\t\tHP\t\tMana\t\tStrength\t\tDefense\t\t\tAgility\t\t\tDex\t\tMoney\t\tExp");
		System.out.println("==================================================================================================================================");
		int id = 1;
		for(Hero h : list) {

			System.out.printf(id++ + "\t" + h.getName());
			for(int i = 0; i < 24-h.getName().length(); i++) {
				System.out.printf(" ");
			}
			System.out.println(h.getLevel() + "\t\t\t" + h.getHp() + "\t\t" + h.getMana() + "\t\t\t"+
					h.getStrength() + "\t\t\t\t"
					+ h.getDefense() + "\t\t\t"
					+ h.getAgility()
					+ "\t\t\t" + h.getDexterity() + "\t\t" + h.getWallet().toString() + "\t\t"+ h.getExp());
		
			
			
		}
		System.out.println();
		
	}
	
	/*
	 * Function for user to parse in chosen heroId based on heroclass
	 */
	public int  parseHeroId(List<Hero> list, HeroClass chosenHeroClass) {
		printHeroes(list, chosenHeroClass);
		int i = parseHeroId(list, false);
		return i;
	}
	
	
	/*
	 * Function for user to parse in chosen heroId
	 */
	public int parseHeroId(List<Hero> list, boolean isShop) {
		System.out.println("Select a hero id:");
		boolean validId = false;
		int selectedId = -1;
		while(!validId) {
			selectedId = parseInt();
			if(isShop) {
				if(selectedId < 0 || selectedId > list.size()) {
					printErrorParse();
				} else {
					validId = true;
				}
			} else {
				if(selectedId < 1 || selectedId > list.size()) {
					printErrorParse();
				} else {
					validId = true;
				}
			}
			
		}
		
		return selectedId - 1;
		
	}
	
	/*
	 * Function to print out movement menu and user to parse what they would like to do.
	 */
	public String choiceMenu() {
		System.out.println("What would you like to do?");
		System.out.println("Move (W/A/S/D)");
		System.out.println("Check Hero Info (I)");
		System.out.println("Check Inventory (E)");
		System.out.println("Check Map (M)");
		System.out.println("End Turn (T)");
		System.out.println("Quit (Q)");
		
		String s = parseString().toUpperCase();
		boolean isValidString  = false;
		while(!isValidString) {
			if(s.equals("W") || s.equals("A") || s.equals("S")  || s.equals("D")  || s.equals("I")  
					|| s.equals("E")  || s.equals("M") ||  s.equals("T") ||  s.equals("Q")) {
				isValidString = true;	
			} else {
				printErrorParse();
				s = parseString().toUpperCase();

			}
		}
		
		return s;
	}


	/*
	 * Function to print out movement menu and user to parse what they would like to do.
	 */
	public String choiceValorMenu() {
		System.out.println("What would you like to do?");
		System.out.print(ConsoleColors.BLUE_BOLD_BRIGHT);
		System.out.println("Move (W/A/S/D)");
		System.out.println("Check Hero Info (I)");
		System.out.println("Check Inventory (E)");
		System.out.println("Check Map (M)");
		System.out.println("Attack (G)");
		System.out.println("Cast Spell (H)");
		System.out.println("Teleport (F)");
		System.out.println("Back to Nexus (B)");
		System.out.println("End Turn (T)");
		System.out.println("Quit (Q)");
		System.out.println(ConsoleColors.RESET);

		String s = parseString().toUpperCase();
		boolean isValidString  = false;
		while(!isValidString) {
			if(s.equals("W") || s.equals("A") || s.equals("S")  || s.equals("D")  || s.equals("I")
					|| s.equals("E")  || s.equals("M") ||  s.equals("T") ||  s.equals("Q") || s.equals("F")
					|| s.equals("G")  || s.equals("B")  || s.equals("H") ) {
				isValidString = true;
			} else {
				printErrorParse();
				s = parseString().toUpperCase();

			}
		}

		return s;
	}
	
	/*
	 * Function to print out shop menu and user to parse what they would like to do.
	 */
	public String shopMenu(String str) {
		String s = null;
		boolean isValidString  = false;
		while(!isValidString) {
			System.out.println("What would you like to " + str + "?");
			System.out.println("Armors\t(A)");
			System.out.println("Weapons\t(W)");
			System.out.println("Spells\t(S)");
			System.out.println("Potions\t(P)");
			System.out.println("Exit\t(E)");
			
			s = parseString().toUpperCase();
			if(s.equals("W") || s.equals("A") || s.equals("S")  || s.equals("P") || s.equals("E")) {
				isValidString = true;	
			} else {
				printErrorParse();
				s = parseString().toUpperCase();

			}
		}
		
		return s;
	}
	
	
	/*
	 * Function to print out weapon menu and user to parse what they would like to do.
	 */
	public void printWeaponShop(List<Weapon> list, boolean isBuy) {
		System.out.println("Weapon List");
		//String name, BigDecimal price, int minLevelReq, BigDecimal weaponDmg, int handWield
		System.out.println("ID\tName\t\t\t\tPrice\t\tMin Level \t\tDamage\t\tHands to Wield");
		System.out.println("============================================================================================");
		int id = 1;
		for(Weapon w : list) {
		
			System.out.printf(id++ + "\t" + w.getName() + "\t\t");
			
			if(isBuy) {
				System.out.printf(w.getPrice().toString());
			} else {
				System.out.printf(w.getPrice().multiply(new BigDecimal("0.5")).toString());
			}
			System.out.printf("\t"+ w.getMinLevelReq() + "\t" + w.getWeaponDmg()
					+ "\t\t" + w.getHandWield());
			
			
			System.out.println();
		}
		System.out.println();
		
	}
	
	/*
	 * Function to print out spell menu and user to parse what they would like to do.
	 */
	public void printSpellShop(List<Spell> list, boolean isBuy) {
		System.out.println("Spell List");
		//String name, BigDecimal price, int minLevelReq, BigDecimal weaponDmg, int handWield
		System.out.println("ID\tName\t\t\tPrice\tMin Level \tDamage\tMana Cost\tElement");
		System.out.println("============================================================================================");
		int id = 1;
		for(Spell s : list) {
		
			System.out.printf(id++ + "\t" + s.getName() ); 
			for(int i = 0; i < 24-s.getName().length(); i++) {
				System.out.printf(" ");
			}
			if(isBuy) {
				System.out.printf(s.getPrice().toString());
			} else {
				System.out.printf(s.getPrice().multiply(new BigDecimal("0.5")).toString());
			}
			System.out.printf( "\t"+ s.getMinLevelReq() + "\t\t" + 
			s.getBaseDmg() + "\t" + s.getManaCost() + "\t" + s.getType().toString());
		
			
			System.out.println();
		}
		System.out.println();
		
	}
	/*
	 * Function to print out armor menu and user to parse what they would like to do.
	 */
	public void printArmorShop(List<Armor> list, boolean isBuy) {
		System.out.println("Armor List");
		System.out.println("ID\tName\t\t\t\t\tPrice\t\tMin Level\t\tDamage Prevention");
		System.out.println("============================================================================================");
		int id = 1;
		for(Armor a : list) {
		
			System.out.printf(id++ + "\t" + a.getName());
			for(int i = 0; i < 24-a.getName().length(); i++) {
				System.out.printf(" ");
			}
			
			if(isBuy) {
				System.out.printf(a.getPrice().toString());
			} else {
				System.out.printf(a.getPrice().multiply(new BigDecimal("0.5")).toString());
			}
			System.out.printf( "\t"+ a.getMinLevelReq() + "\t" + a.getDamagePrevention());
	
			System.out.println();
		}
		System.out.println();
		
	}
	/*
	 * Function to print out potion menu and user to parse what they would like to do.
	 */
	public void printPotionShop(List<Potion> list, boolean isBuy) {
		System.out.println("Potion List");
		//String name, BigDecimal price, int minLevelReq, BigDecimal weaponDmg, int handWield
		System.out.println("ID\tName\t\t\t\tPrice\t\tMin Level\t\tIncrease\t\tAttributes");
		System.out.println("============================================================================================");
		int id = 1;
		for(Potion p : list) {
		
			System.out.printf(id++ + "\t" + p.getName() + "\t\t" );
			if(isBuy) {
				System.out.printf(p.getPrice().toString());
			} else {
				System.out.printf(p.getPrice().multiply(new BigDecimal("0.5")).toString());
			}
			System.out.printf("\t"+ p.getMinLevelReq() + "\t" + p.getAmount()	+ "\t");
			for(StatType t : p.getTypes()) {
				System.out.printf(t.toString() + " ");
			}
		
			
			System.out.println();
		}
		System.out.println();
		
	}
	
	

	
	
	/*
	 * Function to print out weapon list 
	 */
	public void printWeaponList(List<Weapon> list) {
		System.out.println("**** Available Weapons ****");
		System.out.println("ID\tName\t\tDamage\tEquipped");
		System.out.println("============================================================================================");
		int id = 1;
		for(Weapon w : list) {		
			System.out.printf(id++ + "\t" + w.getName() + "\t\t"+ w.getWeaponDmg()+ "\t");
			if(w.isEquipped()) {
				System.out.println("Y");
			} else {
				System.out.println("N");
			}
			
		}
		System.out.println();	
	}
	/*
	 * Function to print out spell list 
	 */
	public void printSpellList(List<Spell> list) {
		System.out.println("**** Available Spells ****");
		System.out.println("ID\tName\t\t\tDamage\tMana Cost\tElement");
		System.out.println("============================================================================================");
		int id = 1;
		for(Spell s : list) {
		
			System.out.printf(id++ + "\t" + s.getName());
			for(int i = 0; i < 24-s.getName().length(); i++) {
				System.out.printf(" ");
			}
			System.out.println(s.getBaseDmg() + "\t" + s.getManaCost() + "\t" + s.getType().toString());
		
			
			System.out.println();
		}
		System.out.println();
		
	}
	
	/*
	 * Function to print out armor list 
	 */
	
	public void printArmorList(List<Armor> list) {
		System.out.println("**** Available Armors ****");
		System.out.println("ID\tName\t\tDamage Prevention\tEquipped");
		System.out.println("============================================================================================");
		int id = 1;
		for(Armor a : list) {
		
			System.out.printf(id++ + "\t" + a.getName() + "\t\t" + a.getDamagePrevention() + "\t\t\t");
			if(a.isEquipped()) {
				System.out.println("Y");
			} else {
				System.out.println("N");
			}
		}
		System.out.println();
		
	}
	/*
	 * Function to print out potion list 
	 */
	public void printPotionMap(Map<Potion,Integer> map) {
	
		System.out.println("**** Available Potions ****");
		System.out.println("ID\tName\t\tAmount\tIncrease\tAttributes");
		System.out.println("===========================================================");
		int id = 1;
		
		Iterator<Entry<Potion, Integer>> iter = map.entrySet().iterator();
		while(iter.hasNext()) {
			 Map.Entry<Potion, Integer> pair = iter.next();
			 Potion p = pair.getKey();
			 int amount = pair.getValue();
			 System.out.printf(id++ + "\t" + p.getName() + "\t\t" + amount + "\t"+ p.getAmount()	+ "\t");
			for(StatType t : p.getTypes()) {
				System.out.printf(t.toString() + " ");
			}
			System.out.println();
		}
		
		
		System.out.println();
		
	}
	
	/*
	 * Function to print out battle action list and parse what user would like to do
	 */
	public String  parseBattleAction() {
		System.out.println("What would you like to do?");
		System.out.println("Attack (A)");
		System.out.println("Spell (S)");
		System.out.println("Equip/Unequip/Use Item (E)");
		boolean isValidMove = false;
		String s = null;
		while(!isValidMove) {
			 s = parseString().toUpperCase();
			
			switch(s) {
				case "A":	
				case "S":
				case "E":
				case "P":
					isValidMove = true;
					break;
				default:
					printErrorParse();
					break;
			}
		}
		return s;
		
	}
	/*
	 * Function to print out monster list
	 */
	public void printMonsters(List<Monster> list) {
		//Name/level/damage/defense/dodge chance
		System.out.println("ID\tName\t\t\t\t\tHP\t\tDamage\t\tDefense\t\tDodge Chance");
		System.out.println("============================================================================================");
		int id = 1;
		for(Monster m : list) {
			System.out.printf(id++ + "\t" + m.getName());
			for(int i = 0; i < 24-m.getName().length(); i++) {
				System.out.printf(" ");
			}
			System.out.printf( m.getHp() + "\t\t" + m.getStrength() + "\t\t\t" + m.getDefense() + "\t\t\t"
					+ m.getAgility() );
			
			System.out.println();
		}
		System.out.println();

	}
	
	/*
	 * Function for users to parse which monster to choose to attack
	 */
	public int parseMonsterChoice(List<Monster> list) {
		System.out.println("Which monster would you like to attack?");
		printMonsters(list);
		return parseChoice(list,1);
	}
	
	
	/*
	 * Function for users to parse which spell to cast
	 */
	public int parseSpellChoice(List<Spell> list) {
		System.out.println("Which Spell would you like to cast?");
		printSpellList(list);
		return parseChoice(list,1);
		
		
	}
	
	
	/*
	 * Function for users to parse which spell to cast
	 */
	public int  parseChoice(List<?> list, int min) {
		int index= -1;
		boolean isValid = false;
		while(!isValid) {
			index = parseInt();
			if(index >= min && index <= list.size()) {
				isValid = true;
			} else {
				index = -1;
				printErrorParse();
			}
		}
		return index-1;
	}
	
	/*
	 * Function for users to parse which action to do with equipment
	 */
	public String parseItemChoice() {
		System.out.println("What would you like to do?");
		System.out.println("Equip/Unequip Weapon\t(W)");
		System.out.println("Equip/Unequip Armor\t(A)");
		System.out.println("Use Potion\t(P)");
		System.out.println("Back\t(B)");
		String res = parseString().toUpperCase();
		boolean isValid = false;
		while(!isValid) {
			switch(res) {
			case "W":
			case "A":
			case "P":
			case "B":
				isValid = true;
				break;
			default:
				printErrorParse();
				res = parseString().toUpperCase();
				break;
			}
		}
		
		return res;
		
	}
	
	
	/*
	 * Function to print attack scene and damage dealt
	 */
	public void printAttackScene(Character c1 , Character c2, int amount) {
		printASCIIArt("ATTACK");
		System.out.println(c1.getName() + " attacked " + c2.getName() + ". Dealt " + amount + " dmg");
	}
	
	/*
	 * Function to print spell scene and damage dealt
	 */
	public void printSpellScene(Character c1 , Character c2, Spell s, int amount) {
		printASCIIArt("SPELL CAST");
		System.out.println(c1.getName() + " used " + s.getName() + " on " + c2.getName() + ". Dealt " + amount + " dmg");
	}
	
	/*
	 * Function to print dodge scene
	 */
	public void printDodgeScene(Character c) {
		printASCIIArt("DODGED");
		System.out.println(c.getName() + " dodged the incoming attack with his godlike agility and luck");
		
	}
	
	/*
	 * Function to print ascii art
	 */
	public void printASCIIArt(String string) {
		int width = 500;
        int height = 30;

        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        java.awt.Graphics g =  image.getGraphics();
        g.setFont(new Font("SansSerif", Font.BOLD, 16));

        Graphics2D graphics = (Graphics2D) g;
        graphics.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
                RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        graphics.drawString(string, 10, 20);

        //save this image
        for (int y = 0; y < height; y++) {
            StringBuilder sb = new StringBuilder();
            for (int x = 0; x < width; x++) {

                sb.append(image.getRGB(x, y) == -16777216 ? " " : "$");

            }

            if (sb.toString().trim().isEmpty()) {
                continue;
            }

            System.out.println(sb);
        }
	}
	
	/*
	 * Function to play sound based on the input
	 */
	public void playSound(String sound )  {
		 
		// open the sound file as a Java input stream
		String soundFile = this.getClass().getResource("").getPath();
		soundFile = soundFile.substring(1,soundFile.length()-4) + "src/";

		switch (sound) {
		case "welcome":
			soundFile += "ConfigFiles/Welcome.wav";
			break;
		case "enemyDie":
			soundFile += "ConfigFiles/EnemyDie.wav";
			break;
		case "allyDie":
			soundFile += "ConfigFiles/AllyDie.wav";
			break;
		case "victory":
			soundFile += "ConfigFiles/Victory.wav";
			break;
		case "defeat":
			soundFile += "ConfigFiles/Defeat.wav";
			break;
		case "spawn":
			soundFile += "ConfigFiles/Spawn.wav";
			break;
		}

		if(audioStream != null && clip != null) {
			clip.stop();
		}

		try {
			audioStream = AudioSystem.getAudioInputStream(new File(soundFile).getAbsoluteFile());
			// create clip reference
			clip = AudioSystem.getClip();

			// open audioInputStream to the clip
			clip.open(audioStream);

			clip.start();
		} catch (Exception e) {
			printErrorParse();
		}
		   
	}
	
	/*
	 * Function to stop audio 4
	 */
	public void stopAudio() {
		if(audioStream != null && clip != null) {
			clip.stop();
		}
	}
	

	
	/*
	 * Function for user to parse map size to create proper size of map
	 */
	public int parseMapSize() {
		int size = -1;
		boolean isValid = false;
		while(!isValid) {
			System.out.println("Please enter size of board (4-20)");
			 size = parseInt();
			 if(size >= 4 && size < 21) {
				 isValid = true;
			 } else {
				 System.out.println(ConsoleColors.RED + "Size of board should be between 4 and 20." + ConsoleColors.RESET);
			 }
		}
		return size;
	}

	/*
	 * Function to get print outer cell layout
	 */
	public  String getOuterCellStr(char c){
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < 2; i++) {
            str.append(c).append(" - ");
        }
        str.append(c).append("   ");
        return str.toString();
    }

    /*
	 * Function to get a current cell component layout
	 */
    public  String getInnerCellStr(String component){
        return "| " + component + " |   ";
    }

	/*
	 * Function to print out a current cell component
	 */
	public String getCellComponent(ValorMap map, int row, int col){
		ValorSpace space = (ValorSpace) map.getMap()[row][col];
		ArrayList<Character> chars = space.getChars();
		if(chars.size() == 0) {
			return "     ";
		}
		int emptySpace = 5;
		String ret = "";
		for(Character c : chars) {
			if(c instanceof Hero) {
				ret += "H";
			} else {
				ret += "M";
			}
			emptySpace--;
		}
		
	    for(int i = 0; i < emptySpace; i++) {
	    	ret += " ";
	    }
	    
	    return ret;
    }

	/*
	 * Function to print out inner cell of map
	 */
	public void createInnerCell(ValorMap map, List<StringBuilder> printableMap, int row, int col) {
		String component = getCellComponent(map,row/3, col);
        if (map.getMap()[row/3][col] instanceof InaccessibleSpace)
            component = "X X X";
        printableMap.get(row).append(getInnerCellStr(component));
    }

	/*
	 * Function to create outer cell of map
	 */
	public  void createOutterCell(ValorMap map, List<StringBuilder> printableMap, int row, int col) {
        Space s = map.getMap()[row/3][col];
        
        if(s instanceof NexusSpace) {
        
        	printableMap.get(row).append(getOuterCellStr('N'));
        
        } else if(s instanceof PlainSpace) {
       	 	
        	printableMap.get(row).append(getOuterCellStr('P'));
        
        } else if(s instanceof KoulouSpace) {
        	
        	printableMap.get(row).append(getOuterCellStr('K'));
        
        } else if(s instanceof BushSpace) {
        	
        	printableMap.get(row).append(getOuterCellStr('B'));
        
        } else if(s instanceof CaveSpace) {
        	
        	printableMap.get(row).append(getOuterCellStr('C'));
        
        } else if(s instanceof InaccessibleSpace) {
        	
        	printableMap.get(row).append(getOuterCellStr('#'));
        
        }
		
        
    }
	/*
	 * Function to print out entire valor map.
	 */
	public void printFullValorMap(ValorMap map) {
		int numCols = map.getCols();
		int numRows = map.getRows();
		List<StringBuilder> printableMap = new ArrayList<StringBuilder>();

        for (int row = 0; row < numRows * 3; row++) {
            printableMap.add(new StringBuilder());
            if ((row / 3) % 2 == 0){
                for (int col = 0; col < map.getCols(); col++) {
                    if (row % 2 == 0){
                        createOutterCell(map, printableMap, row, col);
                    }else{
                        createInnerCell(map, printableMap, row, col);
                    }

                    if (col == numCols - 1) {
                        printableMap.get(row).append("\n");
                    }
                } 
            } else{
                for (int col = 0; col < numCols; col++) {
                    if (row % 2 == 1){
                        createOutterCell(map, printableMap, row, col);
                    }else{
                        createInnerCell(map, printableMap, row, col);
                    }

                    if (col == numCols - 1)
                        printableMap.get(row).append("\n");
                }
            }

            if (row % 3 == 2) {
                printableMap.get(row).append("\n");
            }
        }
        
        for (int i = 0; i <numRows * 3; i++) {
            System.out.print(printableMap.get(i));
        }
	}
	

	/*
	 * Function to get location of nexus in the lane. Returns a random location of the nexus.
	 */
	public Location getNexusLocation(ValorMap map, int lane) {
		boolean isValid = false;
		int col = 0;
		while(!isValid) {
			col = getRandomCellinRow(map,lane);

			ValorSpace s = (ValorSpace) map.getMap()[map.getRows()-1][col];
			if(!s.containHero()) {
				isValid = true;
			}
		}


		Location l = new Location(lane,lane,map.getRows()-1, col);
		return l;
	}

	/*
	 * Function to get a random cell in the row.
	 */
	public int getRandomCellinRow(ValorMap map, int lane) {
		Random r = new Random();
		int col = r.nextInt(map.getLaneSize());
		col = ((lane-1)*(map.getLaneSize()+1))+col;
		return col;
	}

	/*
	 * Function for user to parse where he would like to teleport to.
	 */
	public Location parseTeleportLocation(ValorMap world, Hero h) {
		boolean isValid = false;
		int lane = 0;
		while(!isValid) {
			if(h.getLocation().getCurrent_lane() != h.getLocation().getHome_lane()) {
				//If hero is not in original lane, he has to TP back to his original lane
				System.out.println("Hero is teleporting to his home lane.");
				lane = h.getLocation().getHome_lane();
				isValid = true;
			} else {
				System.out.println("Please select the lane of destination. (1-" + world.getNumLanes() + ")" );
				lane = parseInt();
				if(lane < 0 || lane > world.getNumLanes()) {
					printErrorParse();
				} else if (lane == h.getLocation().getCurrent_lane() )  {
					System.out.println("Hero cannot teleport to his own lane");
				}
				else {
					isValid = true;
				}
			}

		}
		isValid = false;
		int row = 0;
		while(!isValid) {
			System.out.println("Please select the row of destination. (1-" + world.getRows() + ")");
			row = parseInt();
			if(row < 0 || row > world.getRows()) {
				printErrorParse();
			} else {
				isValid = true;
			}
		}

		isValid = false;
		int col = 0;
		while(!isValid) {
			System.out.println("Please select the column of destination. (1-" + world.getCols() + ")");
			col = parseInt();
			if(col < 0 || col > world.getCols()) {
				printErrorParse();
			} else {
				isValid = true;
			}
		}
		Location destination = new Location(lane, lane, row, col);
		return destination;
	}

	/*
	 * Function to print out selected hero current location.
	 */
	public void printHeroLocation(Hero h) {
		System.out.println(h.getName() + " Location: ");
		System.out.println("\tLane : " + h.getLocation().getCurrent_lane());
		System.out.println("\tRow : " + (h.getLocation().getRow() + 1));
		System.out.println("\tCol : " + (h.getLocation().getCol() + 1));
	}
	
}
