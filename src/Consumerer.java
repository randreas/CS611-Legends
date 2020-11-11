/*
 * Consumerer.java - interface for characters to use consumble items like potions
 */
public interface Consumerer {
	public abstract boolean usePotion();
	public abstract void increaseStats(StatType type, int amount);
}
