/*
 * Character.java - abstract class for legends characters 
 */
public abstract class Character {
	private String name;
	private int level;
	private Stats hp;
	private Stats strength;
	private Stats defense;
	private Stats agility;
	private Location location;
	
	
	public Character(String name, int level, Stats strength, Stats defense, Stats agility, Location location) {
		this.name = name;
		this.level = level;
		this.strength = strength;
		this.defense = defense;
		this.agility = agility;
		this.hp = new Stats(StatType.HEALTH, 0);
		this.location = location;
		setHpLevel(level);
	}
	public Character(Character c) {
		// TODO Auto-generated constructor stub
		if(c != null) {
			this.name = c.getName();
			this.level = c.getLevel();
			this.strength = new Stats(StatType.STRENGTH,c.getStrength());
			this.defense =new Stats(StatType.DEFENSE,c.getDefense()); 
			this.agility = new Stats(StatType.AGILITY,c.getAgility());
			this.hp = new Stats(StatType.HEALTH, 0);
			setHpLevel(level);
		}
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public int getHp() {
		return hp.getAmount();
	}
	public void setHp(int hpAmt) {
		this.hp.setAmount(hpAmt);
	}
	
	public void setHpLevel(int levl) {
		setHp(levl * 100);
	}
	
	public int getStrength() {
		return strength.getAmount();
	}
	public void setStrength(int amount) {
		this.strength.setAmount(amount);
	}
	public int getDefense() {
		return defense.getAmount();
	}
	public void setDefense(int amount) {
		this.defense.setAmount(amount);
	}
	public int getAgility() {
		return agility.getAmount();
	}
	public void setAgility(int amount) {
		this.agility.setAmount(amount);
	}
	public Location getLocation() { return this.location;};
	public void setLocation(Location l) { this.location = l; };
	public void setRow(int row) { this.location.setRow(row);}
	public void setCol(int col) { this.location.setCol(col);}
	public void setInitialLane(int lane) { this.location.setHome_lane(lane);
		this.location.setCurrent_lane(lane);}
	public void setCurrentLane(int lane) { this.location.setCurrent_lane(lane);}
	
	
}
