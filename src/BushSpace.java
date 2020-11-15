/*
 * BushSpace.java - class that creates a bush space that buffs heroes dexterity
 */
public class BushSpace  extends ValorSpace implements isBuffableSpace{
	private int buffAmount;
	
	public BushSpace( int row, int col) {
		super("B", row, col);
	}

	/*
	 * Function to buff hero strength by 10%
	 */
	public void enterSpaceBuff(Hero h) {
		this.buffAmount = (int) (h.getDexterity()*0.10);
		h.increaseStats(StatType.DEXTERITY, buffAmount);
	}
	
	/*
	 * Function to buff hero strength by 0.10
	 */
	public void exitSpaceDeBuff(Hero h) {
		h.decreaseStats(StatType.DEXTERITY, buffAmount);
	}

	public int getBuffAmount() {
		return buffAmount;
	}

	public void setBuffAmount(int buffAmount) {
		this.buffAmount = buffAmount;
	}

}
