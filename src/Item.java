import java.math.BigDecimal;

/*
 * Item.java - abstract class which has basic parameters of an object
 */
public abstract class Item {
	private String name;
	private BigDecimal price;
	private int minLevelReq;
	
	public Item(String name, BigDecimal price, int minLevelReq) {
		this.name = name;
		this.price = price;
		this.minLevelReq = minLevelReq;
	}
	
	public Item(Item item) {
		// TODO Auto-generated constructor stub
		if(item != null) {
			this.name = item.getName();
			this.price = item.getPrice();
			this.minLevelReq = item.getMinLevelReq();
		}
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public int getMinLevelReq() {
		return minLevelReq;
	}
	public void setMinLevelReq(int minLevelReq) {
		this.minLevelReq = minLevelReq;
	}
	
	/*
	 * Function that increases stats of a hero base on the item.
	 */
	public void statsIncrease(Hero h, StatType type, int a) {
		switch(type) {
		case AGILITY:
			h.setAgility(h.getAgility() + a);
			break;
		case DEFENSE:
			h.setDefense(h.getDefense() + a);
			break;
		case DEXTERITY:
			h.setDexterity(h.getDexterity() + a);
			break;
		case HEALTH:
			h.setHp(h.getHp() + a);
			break;
		case MANA:
			h.setMana(h.getMana() + a);
			break;
		case STRENGTH:
			h.setStrength(h.getStrength() + a);
			break;
		
		}
	}
	
	/*
	 * Function that decreases stats of a hero base on the item.
	 */
	public void statsDecrease(Hero h, StatType type, int a) {
		switch(type) {
		case AGILITY:
			h.setAgility(h.getAgility() - a);
			break;
		case DEFENSE:
			h.setDefense(h.getDefense() - a);
			break;
		case DEXTERITY:
			h.setDexterity(h.getDexterity() - a);
			break;
		case HEALTH:
			h.setHp(h.getHp() - a);
			break;
		case MANA:
			h.setMana(h.getMana() - a);
			break;
		case STRENGTH:
			h.setStrength(h.getStrength() - a);
			break;
		
		}
	}
}
