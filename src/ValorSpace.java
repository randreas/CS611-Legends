import java.util.ArrayList;

public abstract class ValorSpace extends Space{
	
	public ArrayList<Character> chars;
	
	public ValorSpace(String icon, int row, int col) {
		super(icon, row, col);
		chars = new ArrayList<Character>();
	}
	
	public boolean enterSpace(Character c) {
		if(c instanceof Hero && containHero()) {
			return false;
		}
		chars.add(c);
		return true;
	}
	
	public void exitSpace(Character c) {
		
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

}
