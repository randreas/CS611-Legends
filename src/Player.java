/*
 * Player.java - abstract class that has name and icon of a player
 */
public abstract class Player {
	private String name;
	private String icon;
	
	public Player() {
		setName("Player");
		setIcon("X");
	}
	
	public Player(String name, String icon) {
		setName(name);
		setIcon(icon);
	}
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}
	
	
}
