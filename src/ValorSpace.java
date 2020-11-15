import java.util.ArrayList;

public abstract class ValorSpace extends Space{
	
	public ArrayList<Character> chars;
	
	public ValorSpace(String icon, int row, int col) {
		super(icon, row, col);
		chars = new ArrayList<Character>();
	}
	
	public void enterSpace(Character c) {
		
	}
	
	public void exitSpace(Character c) {
		
	}
	
	public ArrayList<Character> getChars() {
		return this.chars;
	}


}
