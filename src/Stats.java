/*
 * Stats.java - stats class for each stat that a character has 
 * Amount and type
 */
public class Stats {
	private int amount;
	private StatType type;
	
	public Stats(StatType type, int amount) {
		this.type = type;
		this.amount = amount;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public StatType getType() {
		return type;
	}
	public void setType(StatType type) {
		this.type = type;
	}
	
	
}
