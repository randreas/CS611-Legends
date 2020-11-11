import java.math.BigDecimal;

/*
 * FireSpell.java - extends Spell class 
 */
public class FireSpell extends Spell {
	
	private SpellType type = SpellType.FIRE;
	
	public FireSpell(String name, BigDecimal price, int minLevelReq, int baseDmg, int manaCost) {
		super(name, price, minLevelReq, baseDmg, manaCost);
	}
	public SpellType getType() {
		return this.type;
	}
	
	/*
	 * Function that allows caster to cast firespell on opponent
	 */
	public void cast(Hero caster, Character opponent) {
		caster.setMana(caster.getMana() - getManaCost());
		caster.spellDamage(this, opponent);
		opponent.setDefense((int)(opponent.getDefense() * 0.9));
			
	}
}
