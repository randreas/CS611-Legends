import java.util.ArrayList;
import java.util.Random;

/*
 * Monster.java - extends character and implements attacker interface
 */
public abstract class Monster extends Character implements Attacker{
	
	public Monster(String name, int level, Stats strength, Stats defense, Stats agility) {
		super(name, level, strength, defense, agility, null);
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
	/*
	 * Function that returns the enemy list that character c can attack
	 * e.g. c.spotEnemy(world) will return the enemy list that c can attack
	 */
	public ArrayList<Character> spotEnemy(ValorMap world) {
		ArrayList<Character> enemy_list = new ArrayList<>();
		Location current_location = this.getLocation();
		for(int i = current_location.getRow() - 1; i <= current_location.getRow() + 1; i++) {
			for(int j = current_location.getRow() - 1; j <= current_location.getRow() + 1; j++) {
				if(i < 0 || j < 0 || i >= world.getRows() || j > world.getCols()) {
					continue;
				}
				ValorSpace[][] map = (ValorSpace[][]) world.getMap();
				if(map[i][j].getChars().size() > 0) {
					for(Character c : map[i][j].getChars()) {
						if(c instanceof Hero) {
							enemy_list.add(c);
						}
					}
				}
			}
		}
		return enemy_list;
	}
	public abstract Monster clone();
}
