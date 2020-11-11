import java.math.BigDecimal;
/*
 * LightningSpell.java - extends Spell class 
 */
public class LightningSpell extends Spell {
	private final SpellType type = SpellType.LIGHTNING;

	public LightningSpell(String name, BigDecimal price, int minLevelReq, int baseDmg, int manaCost) {
		super(name, price, minLevelReq, baseDmg, manaCost);
	}

	public SpellType getType() {
		// TODO Auto-generated method stub
		return this.type;
	}
	/*
	 * Function that allows caster to cast Lightningspell on opponent
	 */
	public void cast(Hero caster, Character opponent) {
		caster.setMana(caster.getMana() - getManaCost());
		caster.spellDamage(this, opponent);
		opponent.setAgility((int)(opponent.getAgility() * 0.9));
		
		
	}

	


}
