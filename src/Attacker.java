import java.util.ArrayList;

/*
 * Attacker.java - interface for attacker for classes that can attack
 */
public interface Attacker {
	public abstract int calculateDmg(Character c);
	
	public abstract boolean dodgeAttack(Character c);

	/*
	 * Function that returns the enemy list that character c can attack
	 * e.g. c.spotEnemy(world) will return the enemy list that c can attack
	 */
	public abstract ArrayList<Character> spotEnemy(ValorMap world);
}
