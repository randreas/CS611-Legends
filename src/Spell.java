import java.math.BigDecimal;

/*
 * Spell.java - abstract class that extends item and implements isCastable
 * 
 */
public abstract class Spell extends Item implements isCastable, isSellable{
	private BigDecimal price;
	private int manaCost;
	private int baseDmg;
	
	public Spell(String name, BigDecimal price, int minLevelReq, int baseDmg, int manaCost) {
		super(name, minLevelReq);
		// TODO Auto-generated constructor stub
		this.price = price;
		this.baseDmg = baseDmg;
		this.manaCost = manaCost;
	}
	
	public int getBaseDmg() {
		return baseDmg;
	}
	public void setBaseDmg(int baseDmg) {
		this.baseDmg = baseDmg;
	}
	public int getManaCost() {
		return manaCost;
	}
	public void setManaCost(int manaCost) {
		this.manaCost = manaCost;
	}
	public abstract SpellType getType();

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
