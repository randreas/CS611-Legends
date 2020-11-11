import java.util.*;
import java.util.List;
import java.util.Map.Entry;

import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.nio.charset.Charset;

/*
 * ioUtility.java - a java file that handles a majority of all the inputs and outputs for legends
 */

public class ioUtility {
	private static Scanner scanner = new Scanner(System.in);
	private static AudioStream audioStream = null;
	public ioUtility() {
		
	}
	
	/*
	 * Function to parseString
	 */
	public String parseString() {
		String string = scanner.next();
		return string;
	}
	
	/*
	 * Function to print welcome message to users
	 */
	public void printWelcomeMessage() {
		printASCIIArt("WELCOME");
		String string = "¯\\\\_(ツ)_/¯";
		Charset.forName("UTF-8").encode(string);
		System.out.println("\t<------------------------- Welcome to the World of Legends and Monsters ---------------------->");
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
	 * Function for user to parse number of players in Legends Game.
	 */
	public int parseNumPlayers() {
		boolean validParse = false;
		System.out.println("How many players are playing Legends of Heroes? (1-4)");
		
		while(!validParse) {
			int num = parseInt();
			if(num < 1 || num > 4) {
				printErrorParse();
			} else {
				validParse = true;
				return num;
			}
		}
		return -1;
		
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
	 * Function for users to parse heroclass chosen.
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
	 * Function to print heroes list based on heroclass
	 */
	public void printHeroes(List<Hero> list,HeroClass heroClass) {
		System.out.println(heroClass.toString() + " List");
		printHeroes(list);
		
	}
	
	
	/*
	 * Function to print heroes list 
	 */
	public void printHeroes(List<Hero> list) {
		System.out.println("ID\tName\t\t\tLevel\tHP\tMana\tStrength\tDefense\t\tAgility\t\tDex\tMoney\tExp");
		System.out.println("===================================================================================================================");
		int id = 1;
		for(Hero h : list) {
		
			System.out.printf(id++ + "\t" + h.getName());
			for(int i = 0; i < 24-h.getName().length(); i++) {
				System.out.printf(" ");
			}
			System.out.printf(h.getLevel() + "\t" + h.getHp() + "\t" + h.getMana() + "\t"+ h.getStrength() + "\t\t" 
					+ h.getDefense() + "\t\t"
					+ h.getAgility()
					+ "\t\t" + h.getDexterity() + "\t" + h.getWallet().toString() + "\t"+ h.getExp());
		
			
			System.out.println();
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
		System.out.println("ID\tName\t\tPrice\tMin Level \tDamage\tHands to Wield");
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
		System.out.println("ID\tName\t\t\tPrice\tMin Level \tDamage Prevention");
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
		System.out.println("ID\tName\t\tPrice\tMin Level\tIncrease\tAttributes");
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
		System.out.println("ID\tName\t\t\tHP\tDamage\tDefense\tDodge Chance");
		System.out.println("============================================================================================");
		int id = 1;
		for(Monster m : list) {
			System.out.printf(id++ + "\t" + m.getName());
			for(int i = 0; i < 24-m.getName().length(); i++) {
				System.out.printf(" ");
			}
			System.out.printf( m.getHp() + "\t" + m.getStrength() + "\t" + m.getDefense() + "\t"
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
		System.out.println(c1.getName() + " attacked " + c2.getName() + ". Dealt " + amount + " dmg");
	}
	
	/*
	 * Function to print spell scene and damage dealt
	 */
	public void printSpellScene(Character c1 , Character c2, Spell s, int amount) {
		System.out.println(c1.getName() + " used " + s.getName() + " on " + c2.getName() + ". Dealt " + amount + " dmg");
	}
	
	/*
	 * Function to print dodge scene
	 */
	public void printDodgeScene(Character c) {
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
        g.setFont(new Font("SansSerif", Font.CENTER_BASELINE, 16));

        Graphics2D graphics = (Graphics2D) g;
        graphics.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
                RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        graphics.drawString(string, 10, 20);

        //save this image
        //ImageIO.write(image, "png", new File("/users/mkyong/ascii-art.png"));

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
	public void playSound(String sound)  {
		 
		    // open the sound file as a Java input stream
		String soundFile = null;
		switch (sound) {
		case "opening":
			soundFile = "ConfigFiles\\opening.wav";
			break;
		case "ending":
			soundFile = "ConfigFiles\\ending.wav";
			break;
		case "shop":
			soundFile = "ConfigFiles\\shop.wav";
			break;
		case "monsterbattle":
			soundFile = "ConfigFiles\\monsterbattle.wav";
			break;
		case "battlewin":
			soundFile = "ConfigFiles\\monstervictory.wav";
			break;
		case "trainerbattle":
			soundFile = "ConfigFiles\\trainerBattle.wav";
			break;
		case "trainerbattlewin":
			soundFile = "ConfigFiles\\trainerVictory.wav";
			break;
		case "map":
			soundFile = "ConfigFiles\\map.wav";
			break;
		}
			
		     
		    InputStream in = null;
			try {
				in = new FileInputStream(soundFile);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		
		   
			if(audioStream != null ) {
				AudioPlayer.player.stop(audioStream);
			}
			 // create an audiostream from the inputstream
			try {
				audioStream = new AudioStream(in);
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		    // play the audio clip with the audioplayer class
			
		    AudioPlayer.player.start(audioStream);
		   
	}
	
	/*
	 * Function to stop audio 4
	 */
	public void stopAudio() {
		if(audioStream != null ) {
			AudioPlayer.player.stop(audioStream);
		}
	}
	
	/*
	 * Function to print legend for the map
	 */
	public void printLegend(List<LegendsPlayer> playerList) {
		System.out.print(ConsoleColors.BLACK_BRIGHT);
		System.out.println(" --- Legend --- ");
	
		System.out.println(" M = Market");
		System.out.println(" # = Blocked Space");
		System.out.println(" <Blank> = Common Space");
		for(LegendsPlayer p : playerList) {
			System.out.println(" " + p.getIcon() +  " = " + p.getName() + " Current Location=(" + (p.getRowLoc()+1) + "," + (p.getColLoc()+1) + ")");
		}
		System.out.println();
		System.out.println();
		System.out.print(ConsoleColors.RESET);
	}
	
	/*
	 * Function for user to parse map size to create proper size of map
	 */
	public int parseMapSize() {
		// TODO Auto-generated method stub
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
	
	
}
