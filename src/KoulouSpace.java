/*
 * KoulouSpace.java - class that creates a koulou space that buffs heroes strength
 */
public class KoulouSpace extends ValorSpace implements isBuffableSpace{
	
	private int buffAmount;
	
	public KoulouSpace(int row, int col) {
		super("K", row, col);
	}

	/*
	 * Function to buff hero strength by 10%
	 */
	public void enterSpaceBuff(Hero h) {
		this.buffAmount = (int) (h.getStrength()*0.10);
		h.increaseStats(StatType.STRENGTH, buffAmount);
	}
	
	/*
	 * Function to buff hero strength by 0.10
	 */
	public void exitSpaceDeBuff(Hero h) {
		h.decreaseStats(StatType.STRENGTH, buffAmount);
	}

	public int getBuffAmount() {
		return buffAmount;
	}

	public void setBuffAmount(int buffAmount) {
		this.buffAmount = buffAmount;
	}

	

}
