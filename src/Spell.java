import java.math.BigDecimal;

/*
 * Spell.java - abstract class that extends item and implements isCastable
 * 
 */
public abstract class Spell extends Item implements isCastable{
	
	private int manaCost;
	private int baseDmg;
	
	public Spell(String name, BigDecimal price, int minLevelReq, int baseDmg, int manaCost) {
		super(name, price, minLevelReq);
		// TODO Auto-generated constructor stub
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
}
