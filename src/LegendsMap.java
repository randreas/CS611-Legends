import java.util.List;
import java.util.Random;

/*
 * LegendsMap.java - extends GridMap, creates the map for LegendsGame
 */
public class LegendsMap extends GridMap{
	
	
	private List<Monster> monsters;
	private Market market;
	
	public LegendsMap(Inventory itemInven, List<Monster> monsters) {
		this(8,8,itemInven, monsters);
		setRows(8);
		setCols(8);	
		
	}
	
	public LegendsMap(int rows, int cols, Inventory itemInven, List<Monster> monsters) {
		super(rows,cols);
		market = new Market(itemInven);
		int numMarkets = (int) (rows*cols*0.3);
		int numBlocked = (int) (rows*cols*0.2);
		

		this.monsters = monsters;
		Random r = new Random();
		for(int i = 0; i < numMarkets; i++) {
			int rIdx = r.nextInt(rows);
			int cIdx = r.nextInt(cols);
			boolean isEmpty = true;
			while(isEmpty) {
				if(getMap()[rIdx][cIdx] == null) {
					getMap()[rIdx][cIdx] = new MarketSpace(rIdx,cIdx);
					isEmpty = false;
				} else {
					rIdx = r.nextInt(rows);
					cIdx = r.nextInt(cols);
				}
			}
		}
		
		for(int i = 0; i < numBlocked; i++) {
			int rIdx = r.nextInt(rows);
			int cIdx = r.nextInt(cols);
			boolean isEmpty = true;
			while(isEmpty) {
				if(getMap()[rIdx][cIdx] == null) {
					getMap()[rIdx][cIdx] = new BlockedSpace(rIdx,cIdx);
					isEmpty = false;
				} else {
					rIdx = r.nextInt(rows);
					cIdx = r.nextInt(cols);
				}
			}
		}
		
		
		for(int i = 0; i < rows; i++) {
			for(int j = 0; j < cols; j++) {
				if(getMap()[i][j] == null) {
					getMap()[i][j] = new CommonSpace(i,j);
				}
			}
		}
		
	}
	
	

	//Function to print map
	public void printMap() {
		
		
		for(int i = 0; i < getCols(); i++) {
			System.out.printf("==========");
		}
		
		System.out.println();
		
		for(int i = 0; i < getRows(); i++) {
			System.out.println();
			System.out.print(ConsoleColors.BLUE_BOLD_BRIGHT);
			System.out.printf("   ||   ");
			System.out.print(ConsoleColors.RESET);
			for(int j = 0; j < getCols(); j++) {
				if(getMap()[i][j].getIcon().equals(getMap()[i][j].getOgIcon())) {
					System.out.print(ConsoleColors.CYAN_BOLD);
				} else {
					System.out.print(ConsoleColors.YELLOW_BOLD_BRIGHT);
				}
				System.out.print( getMap()[i][j].getIcon());
				System.out.print(ConsoleColors.BLUE_BOLD_BRIGHT);
				System.out.print("   ||   ");
				System.out.print(ConsoleColors.RESET);
			
			}

			System.out.println();
			System.out.printf(" ");
			System.out.println();
			for(int j = 0; j < getCols(); j++) {
				System.out.printf("==========");
			}
			
			System.out.println();
		}
		
	}
	
	
	/*
	 * Function that returns a random accessible tile which is not occupied or inaccessible
	 */
	public Space getRandomAccessibleSpace() {
		Random r = new Random();
		boolean validSpace = false;
		Space s = null;
		while(!validSpace) {
			int row = r.nextInt(getRows());
			int col = r.nextInt(getCols());
			if(!(getMap()[row][col] instanceof BlockedSpace) && !getMap()[row][col].isOccupied()) {
				s = getMap()[row][col];
				validSpace = true;
			} 
		}
		return s;
	}
	



	/*
	 * Function that calls other actions based on where the player landed after he moved
	 */
	public void mapAction(LegendsPlayer p, List<LegendsPlayer> players) {
		// TODO Auto-generated method stub
		String icon = p.getIcon();
		int rowIdx = p.getRowLoc();
		int colIdx = p.getColLoc();
		Space s = getMap()[rowIdx][colIdx];
		if(s.isOccupied()) {
			LegendsPlayer challenger = null;
			int winner = 0;
			for(LegendsPlayer player : players) {
				if(player.getIcon().equals(s.getIcon())) {
					challenger = player;
					PVPBattleRound battle = new PVPBattleRound();
					winner = battle.pvpFight(p, player);
					break;
				}
			}
			if(winner == 2) {
				icon = challenger.getIcon();
			}
		} else {
			if(getMap()[rowIdx][colIdx] instanceof MarketSpace) {
				market.shop(p);
			} else if (getMap()[rowIdx][colIdx] instanceof CommonSpace) {
				CommonSpace c = (CommonSpace) getMap()[rowIdx][colIdx];
				if(c.isOccupied()) {
					//Player Battle
					
					
				} else {
					boolean isSafe = c.checkSafe();
					if(!isSafe) {
						BattleRound battle = new BattleRound(p, monsters);
						battle.fight(p);
						
					} 
				}
				
			}
		}
		
		getMap()[rowIdx][colIdx].setSpaceOccupied(icon);
	}
	
	
	
	
	
}
