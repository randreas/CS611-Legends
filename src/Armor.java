import java.math.BigDecimal;

/*
 * Armor.java - armor class that extends item and implements isEquipable
 */
public class Armor extends Item implements isEquipable {
	
	private boolean equipped;
	private int damagePrevention;
	
	public Armor(String name, BigDecimal price, int minLevelReq, int damagePrevention) {
		super(name, price, minLevelReq);
		this.damagePrevention = damagePrevention;
		this.equipped = false;
	}
	
	
	public Armor(Armor armor) {
		super(armor);
		this.equipped = false;
		this.damagePrevention = armor.getDamagePrevention();
		
	}


	public int getDamagePrevention() {
		return damagePrevention;
	}

	public void setDamagePrevention(int damagePrevention) {
		this.damagePrevention = damagePrevention;
	}

	public void setEquipped(boolean b) {
		this.equipped = b;
	}
	public boolean isEquipped() {
		return equipped;
	}
	
	/*
	 * Function to unequip armor for hero
	 */
	public void unequip(Hero h) {
		// TODO Auto-generated method stub
		for(Armor a : h.getInventory().getArmorList()) {
			if(a.equipped && a.getName().equals(this.getName())) {
				setEquipped(false);
				statsDecrease(h, StatType.DEFENSE, getDamagePrevention());
			}
		}
	}


	/*
	 * Function to equip armor for hero
	 */
	public void equip(Hero h) {
		// TODO Auto-generated method stub
		for(Armor a : h.getInventory().getArmorList()) {
			if(a.getName().equals(this.getName())) {
				a.setEquipped(true);
				a.statsIncrease(h, StatType.DEFENSE, getDamagePrevention());
			} else {
				a.unequip(h);
			}
		}
	}
	
	/*
	 * Function to clone this instance of armor
	 */
	public Armor clone() {
		return new Armor(this);
	}
}
