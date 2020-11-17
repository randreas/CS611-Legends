import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.*;
import java.math.BigDecimal;
import java.util.*;

/*
 * Parser.java - parser class that reads files and parsers them into correct classes
 */
public class Parser {
	
	/*
	 * Function to parse file into a list of strings
	 */
	public List<String> readFileInList(String fileName) { 
		List<String> lines = Collections.emptyList(); 
		try { 
			lines =  Files.readAllLines(Paths.get(fileName), StandardCharsets.UTF_8); 
		} catch (IOException e) { 
			System.out.println("Please enter the correct filepath");
			e.printStackTrace(); 
		} 
		return lines; 
	} 
	
	/*
	 * Function to parse file into a list of armors
	 */
	public List<Armor> parseArmor() {
		List<String> l = readFileInList("ConfigFiles/rmory.txt");
		  
	    Iterator<String> itr = l.iterator(); 
	    
	    List<Armor> armorList = new ArrayList<Armor>();
	    itr.next();
	    while (itr.hasNext()) {	
	    	String data = itr.next();
	    	String[] s = data.split("\\s+");
	    	Armor armor = new Armor(s[0],new BigDecimal(s[1]),new Integer(s[2]),new Integer(s[3]));
	    	armorList.add(armor);
	    }
		return armorList;
		
	}
	
	/*
	 * Function to parse file into a list of Weapons
	 */
	public List<Weapon> parseWeapon() {
		List<String> l = readFileInList("ConfigFiles/Weaponry.txt");
		  
	    Iterator<String> itr = l.iterator(); 
	    
	    List<Weapon> weaponList = new ArrayList<Weapon>();
	    itr.next();
	    while (itr.hasNext()) {	
	    	String data = itr.next();
	    	String[] s = data.split("\\s+");
	    	Weapon weapon = new Weapon(s[0], 
	    			new BigDecimal(s[1]),
	    			new Integer(s[2]),
	    			new Integer(s[3]),
	    			new Integer(s[4]));
	    	weaponList.add(weapon);
	    }
		return weaponList;
		
	}
	
	/*
	 * Function to parse file into a list of Spells
	 */
	public List<Spell> parseSpell() {
		List<String> l = readFileInList("ConfigFiles/FireSpells.txt");
		  
	    Iterator<String> itr = l.iterator(); 
	    
	    List<Spell> spellList = new ArrayList<Spell>();
	    itr.next();
	    while (itr.hasNext()) {	
	    	String data = itr.next();
	    	String[] s = data.split("\\s+");	
	    	FireSpell spell = new FireSpell(s[0],
	    			new BigDecimal(s[1]),
	    			new Integer(s[2]),
	    			new Integer(s[3]), 
	    			new Integer(s[4]));
	    	spellList.add(spell);
	    }
	    
	    l = readFileInList("ConfigFiles/IceSpells.txt");
	    itr = l.iterator(); 
	    itr.next();
	    while (itr.hasNext()) {	
	    	String data = itr.next();
	    	String[] s = data.split("\\s+");	
	    	IceSpell spell = new IceSpell(s[0],
	    			new BigDecimal(s[1]),
	    			new Integer(s[2]),
	    			new Integer(s[3]), 
	    			new Integer(s[4]));
	    	spellList.add(spell);
	    }
	    
	    l = readFileInList("ConfigFiles/LightningSpells.txt");
	    itr = l.iterator(); 
	    itr.next();
	    while (itr.hasNext()) {	
	    	String data = itr.next();
	    	String[] s = data.split("\\s+");	
	    	LightningSpell spell = new LightningSpell(s[0],
	    			new BigDecimal(s[1]),
	    			new Integer(s[2]),
	    			new Integer(s[3]), 
	    			new Integer(s[4]));
	    	spellList.add(spell);
	    }
		return spellList;
		
	}
	
	/*
	 * Function to parse file into a list of Potions
	 */
	public Map<Potion,Integer> parsePotion() {
		List<String> l = readFileInList("ConfigFiles/Potions.txt");
		  
	    Iterator<String> itr = l.iterator(); 
	    
	    Map<Potion,Integer> potionMap = new HashMap<Potion,Integer>();
	    itr.next();
	    while (itr.hasNext()) {	
	    	String data = itr.next();
	    	String[] s = data.split("\\s+");
	    	String[] s4 = s[4].split("/");
	    	ArrayList<StatType> statsList = new ArrayList<StatType>();
	    	for(String ss : s4) {
	    		statsList.add(StatType.valueOf(ss.toUpperCase()));
	    	}
	    	Potion potion = new Potion(s[0],new BigDecimal(s[1]),new Integer(s[2]),new Integer(s[3]), statsList);
	    	potionMap.put(potion,0);
	    }
		return potionMap;
		
	}
	
	/*
	 * Function to parse file into a list of Dragons
	 */
	public List<Monster> parseMonsters() {
		List<String> l = readFileInList("ConfigFiles/Dragons.txt");
		 
	    List<Monster> monsterList = new ArrayList<Monster>();
	    Iterator<String> itr = l.iterator(); 
	    itr.next();
	    while (itr.hasNext()) {	
	    	String data = itr.next();
	    	String[] s = data.split("\\s+");

	    	Dragon monster = new Dragon(s[0], new Integer(s[1]), 
	    			new Stats(StatType.STRENGTH, new Integer(s[2])), 
	    			new Stats(StatType.DEFENSE, new Integer(s[3])), 
	    			new Stats(StatType.AGILITY, new Integer(s[4])));
	    	monsterList.add(monster);
	    }
	    
	    
		l = readFileInList("ConfigFiles/Exoskeletons.txt");
		itr = l.iterator(); 
	    itr.next();
	    while (itr.hasNext()) {	
	    	String data = itr.next();
	    	String[] s = data.split("\\s+");

	    	Exoskeleton monster = new Exoskeleton(s[0], new Integer(s[1]), 
	    			new Stats(StatType.STRENGTH, new Integer(s[2])),
	    			new Stats(StatType.DEFENSE, new Integer(s[3])), 
	    			new Stats(StatType.AGILITY, new Integer(s[4])));
	    	monsterList.add(monster);
	    }
	    
	    l = readFileInList("ConfigFiles/Spirits.txt");
		itr = l.iterator(); 
	    itr.next();
	    while (itr.hasNext()) {	
	    	String data = itr.next();
	    	String[] s = data.split("\\s+");

	    	Spirit monster = new Spirit(s[0], new Integer(s[1]), 
	    			new Stats(StatType.STRENGTH, new Integer(s[2])),
	    			new Stats(StatType.DEFENSE, new Integer(s[3])), 
	    			new Stats(StatType.AGILITY, new Integer(s[4])));
	    	monsterList.add(monster);
	    }
		return monsterList;
		
	}
	/*
	 * Function to parse file into a list of Paladins
	 */
	public List<Hero> parsePaladins() {
		List<String> l = readFileInList("ConfigFiles/Paladins.txt");
		  
	    Iterator<String> itr = l.iterator(); 
	    
	    List<Hero> heroList = new ArrayList<Hero>();
	    itr.next();
	    while (itr.hasNext()) {	
	    	String data = itr.next();
	    	String[] s = data.split("\\s+");
	    	
	    	Paladin hero = new Paladin(s[0], 1, 
	    			new Stats(StatType.MANA, new Integer(s[1])), 
	    			new Stats(StatType.STRENGTH, new Integer(s[2])), 
	    			new Stats(StatType.AGILITY, Integer.valueOf(s[3])),
	    			new Stats(StatType.DEXTERITY, Integer.valueOf(s[4])),
	    			new BigDecimal(s[5]), 
	    			Integer.parseInt(s[6]));
	    	heroList.add(hero);
	    }
	    return heroList;
	}
	
	/*
	 * Function to parse file into a list of Sorcerers
	 */
	public List<Hero> parseSorcerers() {
		List<String> l = readFileInList("ConfigFiles\\Sorcerers.txt"); 
		  
	    Iterator<String> itr = l.iterator(); 
	    
	    List<Hero> heroList = new ArrayList<Hero>();
	    itr.next();
	    while (itr.hasNext()) {	
	    	String data = itr.next();
	    	String[] s = data.split("\\s+");

	    	Sorcerer hero = new Sorcerer(s[0], 1, 
	    			new Stats(StatType.MANA, new Integer(s[1])), 
	    			new Stats(StatType.STRENGTH, new Integer(s[2])), 
	    			new Stats(StatType.AGILITY, new Integer(s[3])), 
	    			new Stats(StatType.DEXTERITY, new Integer(s[4])), 
	    			new BigDecimal(s[5]), 
	    			new Integer(s[6]));
	    	heroList.add(hero);
	    }
	    return heroList;
	}
	
	/*
	 * Function to parse file into a list of Warriors
	 */
	public List<Hero> parseWarriors() {
		List<String> l = readFileInList("ConfigFiles\\Warriors.txt"); 
		  
	    Iterator<String> itr = l.iterator(); 
	    
	    List<Hero> heroList = new ArrayList<Hero>();
	    itr.next();
	    while (itr.hasNext()) {	
	    	String data = itr.next();
	    	String[] s = data.split("\\s+");
	    
	    	Warrior hero = new Warrior(s[0], 1, 
	    			new Stats(StatType.MANA, new Integer(s[1])), 
	    			new Stats(StatType.STRENGTH, new Integer(s[2])), 
	    			new Stats(StatType.AGILITY, new Integer(s[3])), 
	    			new Stats(StatType.DEXTERITY, new Integer(s[4])), 
	    			new BigDecimal(s[5]), 
	    			new Integer(s[6]));
	    	heroList.add(hero);
	    }
	    return heroList;
	}
	    
	   
}
