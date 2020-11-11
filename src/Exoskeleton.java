/*
 * Exoskeleton.java - Exoskeleton class that extends monster
 * Creates a new Exoskeleton object
 */
public class Exoskeleton extends Monster {
	private final MonsterSpecies species = MonsterSpecies.EXOSKELETON;
	public Exoskeleton(String name, int level, Stats strength, Stats defense, Stats agility) {
		super(name, level, strength, defense, agility);
	}

	public Exoskeleton(Exoskeleton exoskeleton) {
		super(exoskeleton);
	}

	public MonsterSpecies getSpecies() {
		return this.species;
	}
	/*
	 * function to clone the monster
	 */
	public Monster clone() {
		return new Exoskeleton(this);
	}
}
