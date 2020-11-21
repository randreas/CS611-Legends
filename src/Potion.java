import java.math.BigDecimal;
import java.util.ArrayList;

/*
 * Potion.java - extends Item and implements isConsumable for players to use.
 */
public class Potion extends Item implements isConsumable, isSellable{
	
	private ArrayList<StatType> types;
	private int amountIncrease;
	private BigDecimal price;
	
	public Potion(String name, BigDecimal price, int minLevelReq, int amountIncrease, ArrayList<StatType> types) {
		super(name, minLevelReq);
		// TODO Auto-generated constructor stub
		this.amountIncrease = amountIncrease;
		this.types = types;
	}
	
	public ArrayList<StatType> getTypes() {
		return types;
	}
	public void setType(ArrayList<StatType> types) {
		this.types = types;
	}
	public int getAmount() {
		return amountIncrease;
	}
	public void setAmount(int amount) {
		this.amountIncrease = amount;
	}

	/*
	 * Function that increases stat based on the type 
	 */
	public void consume(Character c) {
		// TODO Auto-generated method stub
		if(c instanceof Consumerer) {
			for(StatType type : getTypes()) {
				((Consumerer)c).increaseStats(type,getAmount());
			}
		}
		
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public BigDecimal getSellPrice() {
		return price.multiply(new BigDecimal("0.5"));
	}
}
