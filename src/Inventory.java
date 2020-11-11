import java.util.*;

/*
 * Inventory.java - contains a list of weapons, armors, spells and potions
 */
public class Inventory {
	private List<Weapon> weaponList;
	private List<Armor> armorList;
	private List<Spell> spellList;
	private Map<Potion,Integer> potionMap;
	
	
	public Inventory() {
		weaponList = new ArrayList<Weapon>();
		armorList = new ArrayList<Armor>();
		spellList = new ArrayList<Spell>();
		potionMap = new HashMap<Potion, Integer>();
	}
	public Inventory(List<Weapon> parseWeapon, List<Armor> parseArmor, List<Spell> parseSpell,
			Map<Potion,Integer> parsePotion) {
		// TODO Auto-generated constructor stub
		this.weaponList = parseWeapon;
		this.armorList = parseArmor;
		this.spellList = parseSpell;
		this.potionMap = parsePotion;
	}
	public List<Weapon> getWeaponList() {
		return weaponList;
	}
	public void setWeaponList(ArrayList<Weapon> weaponList) {
		this.weaponList = weaponList;
	}
	public List<Armor> getArmorList() {
		return armorList;
	}
	public void setArmorList(List<Armor> armorList) {
		this.armorList = armorList;
	}
	public List<Spell> getSpellList() {
		return spellList;
	}
	public void setSpellList(List<Spell> spellList) {
		this.spellList = spellList;
	}
	public Map<Potion,Integer> getPotionMap() {
		return potionMap;
	}
	public void setPotionMap(Map<Potion,Integer> potionMap) {
		this.potionMap = potionMap;
	}
	
	/*
	 * Add an item into the inventory, sort based into which list
	 */
	public void addItem(Item i) {
		if(i instanceof Potion) {
			if(potionMap.containsKey(i)) {
				potionMap.replace((Potion)i, potionMap.get((Potion)i) + 1);
			} else {
				potionMap.put((Potion)i, 1);
			}
		} else if(i instanceof Weapon) {
			weaponList.add((Weapon) i);
		} else if(i instanceof Armor) {
			armorList.add((Armor) i);
		} else if(i instanceof Spell) {
			spellList.add((Spell) i);
		} 
	}
	
	/*
	 * Function to print inventory lists
	 */
	public void printInventory() {
		
		ioUtility io = new ioUtility();
		io.printArmorList(armorList);
		io.printWeaponList(weaponList);
		io.printPotionMap(potionMap);
		io.printSpellList(spellList);
	}
	
	/*
	 * Function to check if all lists are empty
	 */
	public boolean isEmpty() {
		if(armorList.size() == 0 && weaponList.size() == 0 && potionMap.size() == 0 && spellList.size() == 0) {
			return true;
		}
		return false;
	}
	
}
