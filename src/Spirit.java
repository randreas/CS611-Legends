/*
 * Spirit.java - Spirit class that extends monster
 * Creates a new Spirit object
 */
public class Spirit extends Monster{
	private final MonsterSpecies species = MonsterSpecies.SPIRIT;
	public Spirit(String name, int level, Stats strength, Stats defense, Stats agility) {
		super(name, level, strength, defense, agility);
	}

	public Spirit(Spirit spirit) {
		super(spirit);
	}

	public MonsterSpecies getSpecies() {
		return this.species;
	}
	
	/*
	 * function to clone the monster
	 */
	public Monster clone() {
		return new Spirit(this);
	}
}
