import java.util.ArrayList;

public abstract class ValorSpace extends Space{
	
	public ArrayList<Character> chars;
	
	public ValorSpace(String icon, int row, int col) {
		super(icon, row, col);
		chars = new ArrayList<>();
	}
	
	public boolean enterSpace(Character c) {
		if(c instanceof Hero && containHero()) {
			return false;
		} else if(c instanceof Monster && containMonster()) {
			return false;
		}
		chars.add(c);
		return true;
	}
	
	public void exitSpace(Character c) {
		chars.remove(c);
	}
	
	public ArrayList<Character> getChars() {
		return this.chars;
	}
	
	public boolean containHero() {
		for(Character c : chars) {
			if(c instanceof Hero) {
				return true;
			}
		}
		return false;
	}
	
	public boolean containMonster() {
		for(Character c : chars) {
			if(c instanceof Monster) {
				return true;
			}
		}
		return false;
	}

}
