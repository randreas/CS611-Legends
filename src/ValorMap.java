import java.util.List;
import java.util.Random;

public class ValorMap extends GridMap {
	
	private int laneSize ;
	private int numLanes ;
	private Market market;
	
	public ValorMap(Inventory itemInven) {
		this(8,8,3,2, itemInven);
	}
	
	
	public ValorMap(int rows, int cols, int numLanes, int laneSize, Inventory itemInven) {
		super(rows,cols);
		market = new Market(itemInven);
		this.laneSize = laneSize;
		this.numLanes = numLanes;
		//Set first and last row as nexus
		for(int i = 0; i < getMap()[0].length; i++) {
			getMap()[0][i] = new NexusSpace(0, i);
		}
		for(int i = 0; i < getMap()[rows-1].length; i++) {
			getMap()[rows-1][i] = new NexusSpace(rows-1, i);
		}
		
		//Set up inaccessible cells in between lanes;
		int nextLaneSep = laneSize;
		for(int i = 0; i < getCols(); i++) {
			if(i != nextLaneSep) {
				continue;
			} 
			
			nextLaneSep += laneSize+1;
			for(int j = 0; j < getRows(); j++) {
				getMap()[j][i] = new InaccessibleSpace(j, i);
			}
		}
		
		int totalNumCells = getRows() * getCols();
		int numNexus = 2*getCols() - (laneSize*numLanes);
		int numInaccessible = getRows() * (numLanes-1);
		int numFree = totalNumCells - numNexus - numInaccessible;
		
		int numCave =  (int) (numFree * 0.25);
		int numBush = (int) (numFree * 0.25);
		int numKoulou = (int) (numFree * 0.25);

		Random r = new Random();
		for(int i = 0; i < numCave; i++) {
			int rIdx = r.nextInt(rows);
			int cIdx = r.nextInt(cols);
			boolean isEmpty = true;
			while(isEmpty) {
				if(getMap()[rIdx][cIdx] == null) {
					getMap()[rIdx][cIdx] = new CaveSpace(rIdx,cIdx);
					isEmpty = false;
				} else {
					rIdx = r.nextInt(rows);
					cIdx = r.nextInt(cols);
				}
			}
		}
		
		for(int i = 0; i < numBush; i++) {
			int rIdx = r.nextInt(rows);
			int cIdx = r.nextInt(cols);
			boolean isEmpty = true;
			while(isEmpty) {
				if(getMap()[rIdx][cIdx] == null) {
					getMap()[rIdx][cIdx] = new BushSpace(rIdx,cIdx);
					isEmpty = false;
				} else {
					rIdx = r.nextInt(rows);
					cIdx = r.nextInt(cols);
				}
			}
		}
		
		for(int i = 0; i < numKoulou; i++) {
			int rIdx = r.nextInt(rows);
			int cIdx = r.nextInt(cols);
			boolean isEmpty = true;
			while(isEmpty) {
				if(getMap()[rIdx][cIdx] == null) {
					getMap()[rIdx][cIdx] = new KoulouSpace(rIdx,cIdx);
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
					getMap()[i][j] = new PlainSpace(i,j);
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

	public int getLaneSize() {
		return laneSize;
	}

	public void setLaneSize(int laneSize) {
		this.laneSize = laneSize;
	}

	public int getNumLanes() {
		return numLanes;
	}

	public void setNumLanes(int numLanes) {
		this.numLanes = numLanes;
	}
	
	/*
	 * Function that calls actions when a hero steps onto that cell.
	 * Location l1 - initial location
	 * Location l2 - final location
	 */
	public void mapAction(Hero h, Location l1, Location l2) {
		Space initialSpace = getMap()[l1.getRow()][l1.getCol()];
		if(initialSpace instanceof isBuffableSpace) {
			((isBuffableSpace) initialSpace).exitSpaceDeBuff(h);
		}

		Space finalSpace = getMap()[l2.getRow()][l2.getCol()];
		if(finalSpace instanceof isBuffableSpace) {
			((isBuffableSpace) finalSpace).enterSpaceBuff(h);
		}

		if(finalSpace instanceof NexusSpace) {
			market.shop(h);
		}
	}
}
