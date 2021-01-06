import java.util.HashMap;

// Class that defines the Pizza structure

public class Pizza {
	int R ;			 // Row of the pizza
	int C ;			 // Column
	int L;            // Minimum number of each ingredient
	int H;            // Maximum number of cells in each slice
	
	// Hashmap with the the Cell object data type
	HashMap<String, Cell> cells;
	
	int rowlength;
	int collength;
	
	public String getCellKey(int x,int y) {
		return String.format("%0" + rowlength + "d", x) +    String.format("%0" + collength + "d", y);
	}
}
