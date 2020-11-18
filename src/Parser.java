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
		String file = this.getClass().getResource("").getPath() + "ConfigFiles/";

		List<String> l = readFileInList(file + "Armory.txt");

	    Iterator<String> itr = l.iterator(); 
	    
	    List<Armor> armorList = new ArrayList<>();
	    itr.next();
	    while (itr.hasNext()) {	
	    	String data = itr.next();
	    	String[] s = data.split("\\s+");
	    	Armor armor = new Armor(s[0],
					new BigDecimal(s[1]),
					Integer.parseInt(s[2]),
					Integer.parseInt(s[3]));
	    	armorList.add(armor);
	    }
		return armorList;
		
	}
	
	/*
	 * Function to parse file into a list of Weapons
	 */
	public List<Weapon> parseWeapon() {
		String file = this.getClass().getResource("").getPath() + "ConfigFiles/";

		List<String> l = readFileInList(file + "Weaponry.txt");

	    Iterator<String> itr = l.iterator(); 
	    
	    List<Weapon> weaponList = new ArrayList<>();
	    itr.next();
	    while (itr.hasNext()) {	
	    	String data = itr.next();
	    	String[] s = data.split("\\s+");
	    	Weapon weapon = new Weapon(s[0], 
	    			new BigDecimal(s[1]),
					Integer.parseInt(s[2]),
					Integer.parseInt(s[3]),
					Integer.parseInt(s[4]));
	    	weaponList.add(weapon);
	    }
		return weaponList;
		
	}
	
	/*
	 * Function to parse file into a list of Spells
	 */
	public List<Spell> parseSpell() {
		String file = this.getClass().getResource("").getPath() + "ConfigFiles/";

		List<String> l = readFileInList(file + "FireSpells.txt");


	    Iterator<String> itr = l.iterator(); 
	    
	    List<Spell> spellList = new ArrayList<>();
	    itr.next();
	    while (itr.hasNext()) {	
	    	String data = itr.next();
	    	String[] s = data.split("\\s+");	
	    	FireSpell spell = new FireSpell(s[0],
	    			new BigDecimal(s[1]),
					Integer.parseInt(s[2]),
					Integer.parseInt(s[3]),
					Integer.parseInt(s[4]));
	    	spellList.add(spell);
	    }

		l = readFileInList(file + "IceSpells.txt");

	    itr = l.iterator();
	    itr.next();
	    while (itr.hasNext()) {	
	    	String data = itr.next();
	    	String[] s = data.split("\\s+");	
	    	IceSpell spell = new IceSpell(s[0],
	    			new BigDecimal(s[1]),
					Integer.parseInt(s[2]),
					Integer.parseInt(s[3]),
					Integer.parseInt(s[4]));
	    	spellList.add(spell);
	    }

		l = readFileInList(file + "LightningSpells.txt");
	    itr = l.iterator();
	    itr.next();
	    while (itr.hasNext()) {	
	    	String data = itr.next();
	    	String[] s = data.split("\\s+");	
	    	LightningSpell spell = new LightningSpell(s[0],
	    			new BigDecimal(s[1]),
					Integer.parseInt(s[2]),
					Integer.parseInt(s[3]),
					Integer.parseInt(s[4]));
	    	spellList.add(spell);
	    }
		return spellList;
		
	}
	
	/*
	 * Function to parse file into a list of Potions
	 */
	public LinkedHashMap<Potion,Integer> parsePotion() {
		String file = this.getClass().getResource("").getPath() + "ConfigFiles/";

		List<String> l = readFileInList(file + "Potions.txt");

	    Iterator<String> itr = l.iterator();

		LinkedHashMap<Potion,Integer> potionMap = new LinkedHashMap<>();
	    itr.next();
	    while (itr.hasNext()) {	
	    	String data = itr.next();
	    	String[] s = data.split("\\s+");
	    	String[] s4 = s[4].split("/");
	    	ArrayList<StatType> statsList = new ArrayList<>();
	    	for(String ss : s4) {
	    		statsList.add(StatType.valueOf(ss.toUpperCase()));
	    	}
	    	Potion potion = new Potion(s[0],new BigDecimal(s[1]),Integer.parseInt(s[2]),Integer.parseInt(s[3]), statsList);
	    	potionMap.put(potion,0);
	    }
		return potionMap;
		
	}
	
	/*
	 * Function to parse file into a list of Dragons
	 */
	public List<Monster> parseMonsters() {

		String file = this.getClass().getResource("").getPath() + "ConfigFiles/";

		List<String> l = readFileInList(file + "Dragons.txt");
		 
	    List<Monster> monsterList = new ArrayList<>();
	    Iterator<String> itr = l.iterator(); 
	    itr.next();
	    while (itr.hasNext()) {	
	    	String data = itr.next();
	    	String[] s = data.split("\\s+");

	    	Dragon monster = new Dragon(s[0], Integer.parseInt(s[1]),
	    			new Stats(StatType.STRENGTH, Integer.parseInt(s[2])),
	    			new Stats(StatType.DEFENSE, Integer.parseInt(s[3])),
	    			new Stats(StatType.AGILITY, Integer.parseInt(s[4])));
	    	monsterList.add(monster);
	    }
	    
	    
		l = readFileInList(file + "Exoskeletons.txt");
		itr = l.iterator(); 
	    itr.next();
	    while (itr.hasNext()) {	
	    	String data = itr.next();
	    	String[] s = data.split("\\s+");

	    	Exoskeleton monster = new Exoskeleton(s[0], Integer.parseInt(s[1]),
	    			new Stats(StatType.STRENGTH, Integer.parseInt(s[2])),
	    			new Stats(StatType.DEFENSE, Integer.parseInt(s[3])),
	    			new Stats(StatType.AGILITY, Integer.parseInt(s[4])));
	    	monsterList.add(monster);
	    }
	    
	    l = readFileInList(file + "Spirits.txt");
		itr = l.iterator(); 
	    itr.next();
	    while (itr.hasNext()) {	
	    	String data = itr.next();
	    	String[] s = data.split("\\s+");

	    	Spirit monster = new Spirit(s[0], Integer.parseInt(s[1]),
	    			new Stats(StatType.STRENGTH, Integer.parseInt(s[2])),
	    			new Stats(StatType.DEFENSE,Integer.parseInt(s[3])),
	    			new Stats(StatType.AGILITY,Integer.parseInt(s[4])));
	    	monsterList.add(monster);
	    }
		return monsterList;
		
	}
	/*
	 * Function to parse file into a list of Paladins
	 */
	public List<Hero> parsePaladins() {
		String file = this.getClass().getResource("").getPath() + "ConfigFiles/Paladins.txt";

		List<String> l = readFileInList(file);
	    
	    List<Hero> heroList = new ArrayList<>();
		Iterator<String> itr = l.iterator();

		itr.next();
	    while (itr.hasNext()) {	
	    	String data = itr.next();
	    	String[] s = data.split("\\s+");
	    	
	    	Paladin hero = new Paladin(s[0], 1, 
	    			new Stats(StatType.MANA, Integer.parseInt(s[1])),
	    			new Stats(StatType.STRENGTH, Integer.parseInt(s[2])),
	    			new Stats(StatType.AGILITY, Integer.parseInt(s[3])),
	    			new Stats(StatType.DEXTERITY, Integer.parseInt(s[4])),
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
		String file = this.getClass().getResource("").getPath() + "ConfigFiles/Sorcerers.txt";

		List<String> l = readFileInList(file);
		  
	    Iterator<String> itr = l.iterator(); 
	    
	    List<Hero> heroList = new ArrayList<>();
	    itr.next();
	    while (itr.hasNext()) {	
	    	String data = itr.next();
	    	String[] s = data.split("\\s+");

	    	Sorcerer hero = new Sorcerer(s[0], 1, 
	    			new Stats(StatType.MANA, Integer.parseInt(s[1])),
	    			new Stats(StatType.STRENGTH, Integer.parseInt(s[2])),
	    			new Stats(StatType.AGILITY, Integer.parseInt(s[3])),
	    			new Stats(StatType.DEXTERITY, Integer.parseInt(s[4])),
	    			new BigDecimal(s[5]),
					Integer.parseInt(s[6]));
	    	heroList.add(hero);
	    }
	    return heroList;
	}
	
	/*
	 * Function to parse file into a list of Warriors
	 */
	public List<Hero> parseWarriors() {
		String file = this.getClass().getResource("").getPath() + "ConfigFiles/Warriors.txt";
		System.out.println(file);
		List<String> l = readFileInList(file);
		  
	    Iterator<String> itr = l.iterator(); 
	    
	    List<Hero> heroList = new ArrayList<>();
	    itr.next();
	    while (itr.hasNext()) {	
	    	String data = itr.next();
	    	String[] s = data.split("\\s+");
	    
	    	Warrior hero = new Warrior(s[0], 1, 
	    			new Stats(StatType.MANA, Integer.parseInt(s[1])),
	    			new Stats(StatType.STRENGTH, Integer.parseInt(s[2])),
	    			new Stats(StatType.AGILITY, Integer.parseInt(s[3])),
	    			new Stats(StatType.DEXTERITY, Integer.parseInt(s[4])),
	    			new BigDecimal(s[5]),
					Integer.parseInt(s[6]));
	    	heroList.add(hero);
	    }
	    return heroList;
	}
	    
	   
}
