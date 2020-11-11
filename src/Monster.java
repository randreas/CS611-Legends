import java.util.Random;

/*
 * Monster.java - extends character and implements attacker interface
 */
public abstract class Monster extends Character implements Attacker{
	
	public Monster(String name, int level, Stats strength, Stats defense, Stats agility) {
		super(name, level, strength, defense, agility);
	}

	

	public Monster(Monster monster) {
		super(monster);
		
	}
	
	public abstract MonsterSpecies getSpecies();

	/*
	 * Function to calculate how much damage hero will do to opponent c
	 */
	public int  calculateDmg(Character c) {
		int finalDmg = (int)((getStrength() - c.getDefense()) * 0.05);
		return finalDmg;
		
	}
	
	/*
	 * Function for hero to attack opponent
	 */
	public boolean attack(Character c) {
		boolean dodged = dodgeAttack(c);
		if(dodged) {
			return false;
		}
		
		c.setHp(c.getHp() - calculateDmg(c));	
		return true;
	}
	
	/*
	 * Function for hero to choose spells available to him
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
		if(x <= dodgeChance) {
			return true;
		}
		return false;
	}
	
	public abstract Monster clone();
}
