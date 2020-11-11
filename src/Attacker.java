/*
 * Attacker.java - interface for attacker for classes that can attack
 */
public interface Attacker {
	public abstract int calculateDmg(Character c);
	
	public abstract boolean dodgeAttack(Character c);
}
