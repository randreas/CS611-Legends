import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Random;

/*
 * Hero.java - hero abstract class that extends character and implements SpellCaster, Attacker 
 * and Consumerer interfaces for their available actions
 */
public abstract class Hero extends Character implements SpellCaster, Attacker, Consumerer{
	

	private BigDecimal wallet;
	private Inventory inventory;
	private Inventory currEquipment;
	private int exp;
	private Stats mana;
	private Stats dexterity;
	private ioUtility io;
	private final int maxLevel = 10;
	private int minimal_dis_row;

	/*
	 * Hero Constructor
	 */
	public Hero(String name, int level, Stats mana, Stats strength, Stats agility, Stats dexterity, BigDecimal wallet, int exp) {
		super(name, level, strength, new Stats(StatType.DEFENSE, 0), agility, null);
		this.mana = mana;
		this.dexterity = dexterity;
		this.wallet = wallet;
		this.exp = exp;
		this.inventory = new Inventory();
		this.currEquipment = new Inventory();
		this.io = new ioUtility();
		
	}
	
	public BigDecimal getWallet() {
		return wallet;
	}

	public void setWallet(BigDecimal wallet) {
		this.wallet = wallet;
	}

	public Inventory getInventory() {
		return inventory;
	}

	public void setInventory(Inventory inventory) {
		this.inventory = inventory;
	}


	public Inventory getCurrEquipment() {
		return currEquipment;
	}

	public void setCurrEquipment(Inventory currEquipment) {
		this.currEquipment = currEquipment;
	}
	
	public int getMana() {
		return mana.getAmount();
	}

	public void setMana(int mana) {
		this.mana.setAmount(mana);
	}
	
	
	
	public void setExp(int exp) {
		this.exp = exp;
	}
	public int getExp() {
		return exp;
	}
	
	
	/*
	 * Function for hero to gain mana by 2, unless hero is max level
	 */
	public void gainExp() {
		if(getLevel() < getMaxLevel()) {
			this.exp += 2;
			levelUp();
		} else {
			System.out.println("Max Level has been reached");
		}
		
	}
	
	/*
	 * Function for hero to gain mana by i, unless hero is max level
	 */
	public void gainExp(int i) {
		if(getLevel() < getMaxLevel()) {
			this.exp += i;
			levelUp();
		} else {
			System.out.println("Max Level has been reached");
		}
	}
	
	/*
	 * Level up abstract function
	 */
	public abstract void levelUp();
	
	
	/*
	 * Function for hero to gain back half his original hp when he revives
	 */
	public void resurrect() {
		setHp((int)(100*getLevel()*0.5));
	}

	/*
	 * Function for hero to gain back his original hp when he respawns
	 */
	public void respawn() {
		setHp((int)(100*getLevel()));
	}
	/*
	 * Function for hero to regenerate health and mana
	 */
	
	public void regenHpMana() {
		setHp((int) (getHp()*1.1));
		setMana((int) (getMana()*1.1));
	}
	
	
	/*
	 * Function to calculate how much damage hero will do to opponent c
	 */
	public int calculateDmg(Character c) {
		int finalDmg = (int) ((getStrength() - c.getDefense())*0.05);
		return finalDmg;
	}

	/*
	 * Function to calculate how much damage hero will do to opponent c using a spell
	 */
	public int spellDamage(Spell s,Character c) {
		int finalDmg = s.getBaseDmg() + ((getDexterity() - c.getDefense()) /10000) * s.getBaseDmg();
		c.setHp(c.getHp() - (finalDmg));
		return finalDmg;
	}



	public int getDexterity() {
		return dexterity.getAmount();
	}

	public void setDexterity(int amount) {
		this.dexterity.setAmount(amount);
	}
	
	/*
	 * Function to add an item into inventory
	 */
	public void addItemIntoInventory(Item i) {
		inventory.addItem(i);
		System.out.println(getName() + "'s Inventory");
		inventory.printInventory();  
		if(i instanceof isEquipable) {
			System.out.println("Would you like to equip " + i.getName() + "? (Y/N)");
			boolean res = io.parseYesNo();
			if(res) {
				((isEquipable) i).equip(this);
			}
		}
	}
	
	
	/*
	 * Function for hero to attack opponent
	 */
	public boolean attack(Character c) {
		boolean dodged = dodgeAttack(c);
		if(dodged) {
			return false;
		}
		
		if(calculateDmg(c) >= c.getHp()) {
			c.setHp(0);
		} else {
			c.setHp(c.getHp() - calculateDmg(c));	
		}
		
		return true;
	}
	
	
	/*
	 * Function for hero to cast a spell on a opponent
	 */
	public boolean spellCast(Spell s, Character c) {
		boolean dodged = dodgeAttack(c);
		if(dodged) {
			return false;
		}
		s.cast(this, c);
		
		return true;
	}
	
	
	/*
	 * Function for hero to use an item
	 */
	public boolean useItem() {
		if(inventory.isEmpty()) {
			System.out.println("Inventory is empty. Nothing to do or see here");
			return false;
		}
		String res = io.parseItemChoice();
		boolean ret = false;
		switch(res) {
		case "W":
		case "A":
			ret = equipOrUnequip(res);
			break;
		case "P":
			ret = usePotion();
			break;
		case "B":
			ret = false;
			break;
		}
		return ret;
	}
	
	
	/*
	 * Function for hero to use a potion
	 */
	public boolean usePotion() {
		if(getInventory().getPotionMap().size() == 0) {
			System.out.println(ConsoleColors.GREEN + "There are no potions in your inventory." + ConsoleColors.RESET);
			System.out.println();
			return false;
		}
		io.printPotionMap(getInventory().getPotionMap());
		System.out.println("Which potion would you like to use?");
		ArrayList<Potion> list = new ArrayList<>(inventory.getPotionMap().keySet());
		int idx = io.parseChoice(list,1);
		Potion p = list.get(idx);
		if(getInventory().getPotionMap().get(p) == 1) {
			getInventory().getPotionMap().remove(p);
		} else {
			getInventory().getPotionMap().replace(p, getInventory().getPotionMap().get(p) -1);
		}
		
		p.consume(this);
		return true;
				
	}

	/*
	 * Function to increase the stats for a hero by amount
	 */
	public void increaseStats(StatType type, int amount) {
		switch(type) {
		case AGILITY:
			setAgility(getAgility() + amount);
			break;
		case DEFENSE:
			setDefense(getDefense() + amount);
			break;
		case DEXTERITY:
			setDexterity(getDexterity() + amount);
			break;
		case HEALTH:
			setHp(getHp() + amount);
			break;
		case MANA:
			setMana(getMana() + amount);
			break;
		case STRENGTH:
			setStrength(getStrength() + amount);
			break;
		
		}
		
	}
	
	/*
	 * Function to decrease the stats for a hero by amount
	 */
	public void decreaseStats(StatType type, int amount) {
		switch(type) {
		case AGILITY:
			setAgility(getAgility() - amount);
			break;
		case DEFENSE:
			setDefense(getDefense() - amount);
			break;
		case DEXTERITY:
			setDexterity(getDexterity() - amount);
			break;
		case HEALTH:
			setHp(getHp() - amount);
			break;
		case MANA:
			setMana(getMana() - amount);
			break;
		case STRENGTH:
			setStrength(getStrength() - amount);
			break;
		
		}
		
	}
	
	/*
	 * Function to equip/unequip an item 
	 */
	public boolean equipOrUnequip(String res) {
		if(res.equals("W")) {
			if(getInventory().getWeaponList().size() == 0) {
				System.out.println(ConsoleColors.GREEN + "There is no weapons in your inventory" + ConsoleColors.RESET);
				System.out.println();
				return false;
			}
			System.out.println("Would you like to equip/unequip a weapon?");
		} else {
			if(getInventory().getWeaponList().size() == 0) {
				System.out.println(ConsoleColors.GREEN + "There is no armor in your inventory" + ConsoleColors.RESET);
				System.out.println();
				return false;
			}
			System.out.println("Would you like to equip/unequip an armor?");
		}
			
		System.out.println("0 - Equip");
		System.out.println("1 - Unequip");
		System.out.println("2 - Back");
		boolean isValid = false;
		while(!isValid) {
			int i = io.parseInt();
			switch(i) {
			case 0:
				if(res.equals("W")) {
					io.printWeaponList(getInventory().getWeaponList());
					int idx = io.parseChoice(getInventory().getWeaponList(), 1);
					getInventory().getWeaponList().get(idx).equip(this);
				} else {
					io.printArmorList(getInventory().getArmorList());
					int idx = io.parseChoice(getInventory().getArmorList(),1);
					getInventory().getArmorList().get(idx).equip(this);
				}
				isValid = true;
				break;
			case 1:
				if(res.equals("W")) {
					io.printWeaponList(getInventory().getWeaponList());
					int idx = io.parseChoice(getInventory().getWeaponList(),1) -1;
					getInventory().getWeaponList().get(idx).unequip(this);
				} else {
					io.printArmorList(getInventory().getArmorList());
					int idx = io.parseChoice(getInventory().getArmorList(),1) -1;
					getInventory().getArmorList().get(idx).unequip(this);
				}
				isValid = true;
				break;
			case 2:
				useItem();
				isValid = true;
				break;
			default:
				io.printErrorParse();
				break;
			}
		}
		return true;
	}

	/*
	 * Function to calculate chances of dodging attack
	 */
	public boolean dodgeAttack(Character c) {
		int dodgeChance;
		if(c instanceof Hero) {
			dodgeChance = (int)(c.getAgility() * 0.002);
		} else {
			dodgeChance = (int)(c.getAgility() * 0.01);
		}
		Random r = new Random();
		int x = r.nextInt(100);
		return x <= dodgeChance;
	}
	
	/*
	 * Function for hero to choose spells available to him
	 */
	public Spell chooseSpell() {
		if(inventory.getSpellList().size() == 0) {
			System.out.println("No spells available. Please pick another spell");
			return null;
		}
		return inventory.getSpellList().get(io.parseSpellChoice(inventory.getSpellList()));
	}

	/*
	 * Function that returns the enemy list that character c can attack
	 * e.g. c.spotEnemy(world) will return the enemy list that c can attack
	 */
	public ArrayList<Character> spotEnemy(ValorMap world) {
		ArrayList<Character> enemy_list = new ArrayList<Character>();
		Location l = getLocation();
		//Check current space
		if (((ValorSpace)world.getMap()[l.getRow()][l.getCol()]).containHero()) {
			for (Character c :((ValorSpace)world.getMap()[l.getRow()][l.getCol()]).getChars()) {
				if(c instanceof Monster) {
					enemy_list.add((Monster) c);
				}
			}
		}

		//check for front
		if(l.getRow() < world.getRows()-1) {
			if (((ValorSpace)world.getMap()[l.getRow()+1][l.getCol()]).containHero()) {
				for (Character c :((ValorSpace)world.getMap()[l.getRow()+1][l.getCol()]).getChars()) {
					if(c instanceof Monster) {
						enemy_list.add((Monster) c);
					}
				}
			}

			//check diagonal front left
			if(l.getCol() > 0) {
				if (((ValorSpace)world.getMap()[l.getRow()+1][l.getCol()-1]).containHero()) {
					for (Character c :((ValorSpace)world.getMap()[l.getRow()+1][l.getCol()-1]).getChars()) {
						if(c instanceof Monster) {
							enemy_list.add((Monster) c);
						}
					}
				}

			}

			//check diagonal front right
			if(l.getCol() < world.getCols()-1) {
				if (((ValorSpace)world.getMap()[l.getRow()+1][l.getCol()+1]).containHero()) {
					for (Character c :((ValorSpace)world.getMap()[l.getRow()+1][l.getCol()+1]).getChars()) {
						if(c instanceof Monster) {
							enemy_list.add((Monster) c);
						}
					}
				}

			}

		}

		//check left
		if(l.getCol() > 0) {
			if (((ValorSpace)world.getMap()[l.getRow()][l.getCol()-1]).containHero()) {
				for (Character c :((ValorSpace)world.getMap()[l.getRow()][l.getCol()-1]).getChars()) {
					if(c instanceof Monster) {
						enemy_list.add((Monster) c);
					}
				}
			}

		}

		//check right
		if(l.getCol() < world.getCols()-1) {
			if (((ValorSpace)world.getMap()[l.getRow()][l.getCol()+1]).containHero()) {
				for (Character c :((ValorSpace)world.getMap()[l.getRow()][l.getCol()+1]).getChars()) {
					if(c instanceof Monster) {
						enemy_list.add((Monster) c);
					}
				}
			}

		}
		return enemy_list;
	}

	public boolean teleport(Location destination, ValorMap world) {
		if(destination.getRow() < 0 || destination.getCol() < 0 || destination.getRow() >= world.getRows() ||
				destination.getCol() >= world.getCols()) {
			System.out.println("Invalid action! You are teleporting out of the map.");
			return false;
		}
		ValorSpace[][] map = (ValorSpace[][])world.getMap();
		if(map[destination.getRow()][destination.getCol()] instanceof InaccessibleSpace) {
			System.out.println("Invalid action! You can't teleport to a inaccessible place.");
			return false;
		}
		if(map[destination.getRow()][destination.getCol()].getChars().size() >= 2) {
			System.out.println("Invalid action! You can't teleport to a cell which is full.");
			return false;
		}
		if(map[destination.getRow()][destination.getCol()].containHero()) {
			System.out.println("Invalid action! You can't teleport to a cell which already occupied by a hero.");
			return false;
		}
		if(destination.getCurrent_lane() == this.getLocation().getCurrent_lane()) {
			System.out.println("Invalid action! You can't teleport to a cell in the same lane.");
			return false;
		}
		if(destination.getRow() < this.getMinimal_dis_row()) {
			System.out.println("Invalid action! You can't teleport to an unexplored cell.");
			return false;
		}
		for(int i = destination.getRow(); i < world.getRows(); i++) {
			for(int j = destination.getCurrent_lane() * (world.getLaneSize() + 1); j < destination.getCurrent_lane() * (world.getLaneSize() + 1) + world.getLaneSize(); j++) {
				if(((ValorSpace) world.getMap()[i][j]).containMonster()) {
					System.out.println("Invalid action! You can't teleport to a cell which behind the monster.");
					return false;
				}
			}
		}
		this.getLocation().setCurrent_lane(destination.getCurrent_lane());
		this.getLocation().setRow(destination.getRow());
		this.getLocation().setCol(destination.getCol());
		((ValorSpace) world.getMap()[destination.getRow()][destination.getCol()]).enterSpace(this);
		((ValorSpace) world.getMap()[destination.getRow()][destination.getCol()]).exitSpace(this);
		System.out.println("Teleport success!");
		return true;
	}

	public int getMaxLevel() {
		return maxLevel;
	}

	public int getMinimal_dis_row() {
		return minimal_dis_row;
	}

	public void setMinimal_dis_row(int minimal_dis_row) {
		this.minimal_dis_row = minimal_dis_row;
	}

	public boolean enemyBlock(ValorMap world, String direction) {
		if(direction.equals("W")) {
			for(int j = this.getLocation().getCurrent_lane() * (world.getLaneSize() + 1); j < this.getLocation().getCurrent_lane() * (world.getLaneSize() + 1) + world.getLaneSize(); j++) {
				if(((ValorSpace) world.getMap()[this.getLocation().getRow()][j]).containMonster()) {
					return true;
				}
			}
			return false;
		}
		else {
			return false;
		}
	}

}
