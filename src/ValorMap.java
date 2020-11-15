import java.util.List;
import java.util.Random;

public class ValorMap extends GridMap {
	
	private int laneSize = 2;
	private int numLanes = 3;
	
	public ValorMap() {
		this(8,8);
		setRows(8);
		setCols(8);	
		
	}
	
	public ValorMap(int rows, int cols) {
		super(rows,cols);
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
				getMap()[j][i] = new BlockedSpace(j, i);
			}
		}
		
		int totalNumCells = getRows() * getCols();
		int numNexus = 2*getCols() - (laneSize*numLanes);
		int numInaccessible = getRows() * (numLanes-1);
		int numFree = totalNumCells - numNexus - numInaccessible;
		
		int numCave =  (int) (numFree * 0.25);
		int numBush = (int) (numFree * 0.25);;
		int numKoulou = (int) (numFree * 0.25);
		int numPlain = numFree - numCave - numBush - numKoulou;
		
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
	
	
}
