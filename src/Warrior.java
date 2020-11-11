import java.math.BigDecimal;

/*
 * Warrior.java - warrior class that creates warrior object
 * Extends hero class
 */
public class Warrior extends Hero {
	
	private final HeroClass heroClass = HeroClass.WARRIOR;
	public Warrior(String name, int level, Stats mana, Stats strength, Stats agility, Stats dexterity,
			BigDecimal wallet, int exp) {
		super(name, level, mana, strength, agility, dexterity, wallet, exp);
	}
	public HeroClass getHeroClass() {
		return heroClass;
	}
	
	/*
	 * Function to level up a warriro
	 */
	public  void levelUp() {
		while(getExp() >= getLevel() * 10) {
			setExp(getExp() - getLevel() * 10);
			setLevel(getLevel() + 1);
			
			setHpLevel(getLevel());
			setMana((int) (getMana()*1.1));			
			
			
			increaseStats(StatType.AGILITY, (int)(getAgility() * 0.1));
			int str = getStrength();
			for(Weapon w: getInventory().getWeaponList()) {
				if(w.isEquipped()) {
					str -= w.getWeaponDmg();
				}
			}
			increaseStats(StatType.STRENGTH,  (int)(str* 0.1));
			increaseStats(StatType.DEXTERITY, (int)(getDexterity() * 0.05));
				
			
		}
	}
	
}
