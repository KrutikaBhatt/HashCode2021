import java.util.ArrayList;
import java.util.HashMap;

public class PizzaCuter {

	Pizza pizza;
	
	// Create a ArrayList of Slice object
	static ArrayList<Slice> resultSlices = new ArrayList<>();
	
	//Constructor
	public PizzaCuter(Pizza pizza){
		
		this.pizza = pizza;
	}
	
	public void cutPizza() {
		
		int perMiniSlice = pizza.H/2;
		
		//Traversing the Pizza from row ->left to right
		//    column ->Top to bottom
		
		for(int i=0;i<pizza.R;i++) {
			for(int j=0;j<pizza.C;j++) {
				
				String cellKey = pizza.getCellKey(i, j);
				Cell cell = pizza.cells.get(cellKey);
				
				// We create the ArrayList with Slices
				ArrayList<Cell> sliceCells = getMiniSlice(cell.x,cell.y,perMiniSlice);
				
				if(j+1>=pizza.C) {
					if(checkSufficient(sliceCells)) {
						insertSlice(sliceCells);
				        continue;
					}
				}    
				
				//Arraylist for the next Slice
				ArrayList<Cell> nextSlice = getMiniSlice(i,j+1,perMiniSlice);
				
				//If current Slice passes and the next miniSlice also passes,
				//then the current miniSlice is added as a slice to the list
				if(checkSufficient(sliceCells)) {
					if(checkSufficient(nextSlice))
					{
						insertSlice(sliceCells);
					}
					//If the next miniSlice doesn’t meet the slice requirement, then it’s cells are added to the current miniSlice
					// and it is added as a slice to the cutSlices list
					else {
						sliceCells.addAll(nextSlice);
						insertSlice(sliceCells);
						j++;
					}
				}
				
				//If current Slice do not pass but the next MiniSlice pass 
				//The current miniSlice Cells are added to the next miniSlice and it is added as a slice to the list
				else {
					
					if(checkSufficient(nextSlice)) {
						sliceCells.addAll(nextSlice);
						insertSlice(sliceCells);
						j++;
					}
					// If current and nextSlice do not pass,Just add the nextMiniSlice to currentSlices
					else {
						sliceCells.addAll(nextSlice);
						
						// Check for Sufficiency again
						if(checkSufficient(sliceCells)) {
							insertSlice(sliceCells);
							j++;
						}
						
						//Nothing then just continue
						continue;
					}
					
				}
						
			}
			i+=perMiniSlice - 1;
		}
	}

	private void insertSlice(ArrayList<Cell> sliceCells) {
		// Mark the sliceCells - cutoff =True
		
		for(int i=0;i< sliceCells.size();i++) {
			Cell cell = sliceCells.get(i);
			cell.cutoff = true;
			String hashcell = pizza.getCellKey(cell.x, cell.y);
			pizza.cells.put(hashcell, cell);
		}
		
		
		Cell firstcell = getLeastCell(sliceCells);
		Cell lastcell = getMaxCell(sliceCells);
		Slice slice = new Slice();
		
		slice.r1 = firstcell.x;
		slice.c1 = firstcell.y;
		slice.r2 = lastcell.x;
		slice.c2 = lastcell.y;
		
		// Add to resultSices
		resultSlices.add(slice);
		
		
	}

	private Cell getMaxCell(ArrayList<Cell> slicedCells) {
		int maxCellNumber = 0;
        int size = slicedCells.size();
        Cell maxCell = null;
        for (int i = 0; i < size; i++) {
            Cell cell = slicedCells.get(i);
            String cellKey = pizza.getCellKey(cell.x, cell.y);
            int cellKeyIntValue = Integer.parseInt(cellKey);
            if (cellKeyIntValue > maxCellNumber) {
                maxCellNumber = cellKeyIntValue;
                maxCell = cell;
            }
        }
        return maxCell;
    }
	

	private Cell getLeastCell(ArrayList<Cell> sliceCells) {
		int minimumCellNumber = Integer.MAX_VALUE;
        int size = sliceCells.size();
        Cell leastCell = null;
        for (int i = 0; i < size; i++) {
            Cell cell = sliceCells.get(i);
            String cellKey = pizza.getCellKey(cell.x, cell.y);
            int cellKeyIntValue = Integer.parseInt(cellKey);
            if (cellKeyIntValue < minimumCellNumber) {
                minimumCellNumber = cellKeyIntValue;
                leastCell = cell;
            }
        }
        return leastCell;
	}

	

	private boolean checkSufficient(ArrayList<Cell> sliceCells) {
		
		boolean SliceSize  = sliceCells.size() <= pizza.H;
		boolean check = NotPreviouslyCut(sliceCells) && minimumIngredient(sliceCells) && SliceSize;
		return check;
	}

	private boolean minimumIngredient(ArrayList<Cell> sliceCells) {
		
		int tomato=0;
		int mush = 0;
		for(int i=0;i<sliceCells.size();i++) {
			
			Cell cell =sliceCells.get(i);
			if(cell.ingredient == 'T')
				tomato++;
			else
				mush++;
			
		}
		
		if(tomato>= pizza.L && mush>=pizza.L)
			return true;
		
		else
			return false;
		
	}

	private boolean NotPreviouslyCut(ArrayList<Cell> sliceCells) {
		// Check if Cell are previously cutted by the cutoff variable in cell
		// If cells are already cutted i.e, cell.cutOff = True ... Then return false
		
		for(int i=0;i<sliceCells.size();i++) {
			Cell cell = sliceCells.get(i);
			if(cell == null)
				return false;
			if(cell.cutoff)
				return false;
			
		}
		return true;
	}

	private ArrayList<Cell> getMiniSlice(int x, int y, int perMiniSlice) {
		
		HashMap<String,Cell> cellsToSlice = new HashMap<>();
		
		for(int i=0;i<perMiniSlice;i++) {
			int m =x+i;
			int n = y;
			
			if(m >= pizza.R || y>= pizza.C) {
				continue;
			}
			
			String hashcell = pizza.getCellKey(m, n);
			Cell cell = pizza.cells.get(hashcell);
			
			cellsToSlice.put(hashcell, cell);
			
		}
		return new ArrayList<>(cellsToSlice.values());
	}
	
}
