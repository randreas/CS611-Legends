/*
 * CaveSpace.java - class that creates a cave space that buffs heroes agility
 */
public class CaveSpace extends ValorSpace implements isBuffableSpace{
	private int buffAmount;
	
	public CaveSpace( int row, int col) {
		super("C", row, col);
	}

	/*
	 * Function to buff hero agility by 10%
	 */
	public void enterSpaceBuff(Hero h) {
		this.buffAmount = (int) (h.getAgility()*0.10);
		h.increaseStats(StatType.AGILITY, buffAmount);
		
	}
	
	/*
	 * Function to buff hero agility by 0.10
	 */
	public void exitSpaceDeBuff(Hero h) {
		h.decreaseStats(StatType.AGILITY, buffAmount);
	}

	public int getBuffAmount() {
		return buffAmount;
	}
}
