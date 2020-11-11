/*
 * Dragon.java - dragon class that extends monster
 * Creates a new dragon object
 */
public class Dragon extends Monster{
	private final MonsterSpecies species = MonsterSpecies.DRAGON;
	public Dragon(String name, int level, Stats strength, Stats defense, Stats agility) {
		super(name, level, strength, defense, agility);
		// TODO Auto-generated constructor stub
	}

	public Dragon(Dragon dragon) {
		super(dragon);
	}

	public MonsterSpecies getSpecies() {
		// TODO Auto-generated method stub
		return this.species;
	}

	/*
	 * function to clone the monster
	 */
	public Monster clone() {
		return new Dragon(this);
	}

	
}
