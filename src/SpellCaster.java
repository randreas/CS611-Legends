/*
 * SpellCaster.java - interface that allows characters to cast spells
 */
public interface SpellCaster {
	public boolean spellCast(Spell s, Character c);
	public int spellDamage(Spell s,Character c);
}
