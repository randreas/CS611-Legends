import java.math.BigDecimal;
/*
 * IceSpell.java - extends Spell class 
 */
public class IceSpell extends Spell{
	private final SpellType type = SpellType.ICE;

	public IceSpell(String name, BigDecimal price, int minLevelReq, int baseDmg, int manaCost) {
		super(name, price, minLevelReq, baseDmg, manaCost);
		// TODO Auto-generated constructor stub
	}
	
	public SpellType getType() {
		return this.type;
	}
	/*
	 * Function that allows caster to cast icespell on opponent
	 */
	public void cast(Hero caster, Character opponent) {
		caster.setMana(caster.getMana() - getManaCost());
		caster.spellDamage(this, opponent);
		opponent.setStrength((int)(opponent.getStrength() * 0.9));
		
		
	}
}
