import java.math.BigDecimal;

/*
 * Weapon.java - Weapon clas that extends item and implements isEquipable
 */
public class Weapon extends Item implements isEquipable{
	
	private int handWield;
	private int weaponDmg;
	private boolean equipped;
	
	public Weapon(String name, BigDecimal price, int minLevelReq, int weaponDmg, int handWield) {
		super(name, price, minLevelReq);
		// TODO Auto-generated constructor stub
		this.weaponDmg = weaponDmg;
		this.handWield = handWield;
		this.equipped = false;
	}
	
	public Weapon(Weapon weapon) {
		super(weapon);
		this.weaponDmg = weapon.getWeaponDmg();
		this.handWield = weapon.getHandWield();
		this.equipped = false;
	}

	public int getWeaponDmg() {
		return weaponDmg;
	}
	public void setWeaponDmg(int weaponDmg) {
		this.weaponDmg = weaponDmg;
	}

	public int getHandWield() {
		return handWield;
	}

	public void setHandWield(int handWield) {
		this.handWield = handWield;
	}
	
	public void setEquipped(boolean b) {
		this.equipped = b;
	}
	public boolean isEquipped() {
		return equipped;
	}
	
	/*
	 * Function to unequip weapon for hero
	 */
	public void unequip(Hero h) {
		// TODO Auto-generated method stub
		for(Weapon w : h.getInventory().getWeaponList()) {
			if(w.equipped && w.getName().equals(this.getName())) {
				setEquipped(false);
				statsDecrease(h, StatType.STRENGTH, getWeaponDmg());
			}
		}
		
	}

	/*
	 * Function to equip weapon for hero
	 */
	public void equip(Hero h) {
		for(Weapon w : h.getInventory().getWeaponList()) {
			if(w.getName().equals(this.getName())) {
				w.setEquipped(true);
				w.statsIncrease(h, StatType.STRENGTH, getWeaponDmg());
			} else {
				w.unequip(h);
			}
		}
	}
	
	/*
	 * Function to clone this instance of weapon
	 */
	public Weapon clone() {
		return new Weapon(this);
	}
	
}
