import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;

public class Working {
	
	String filename;
	Pizza pizza;
	
	PizzaCuter cutter;

	
	public Working(String filename) {
		this.filename = filename;
		
	}
	
	public void ReadInput() {
		
		int bufferSize = 8*1024;
		BufferedReader br = null;
		
		// Read the Files and assign the values
		try {
			
			br = new BufferedReader(new FileReader(this.filename + ".in"),bufferSize);
			try {
				String line = br.readLine();
				String [] firstline = line.split(" ");
				
				pizza = new Pizza();
				
				// Assign values to R,C,L and H
				pizza.R = Integer.parseInt(firstline[0]);
				pizza.C = Integer.parseInt(firstline[1]);
				
				pizza.rowlength = Integer.toString(pizza.R).length();
			    pizza.collength = Integer.toString(pizza.C).length();
			    
			    pizza.L = Integer.parseInt(firstline[2]);
			    
			    pizza.H = Integer.parseInt(firstline[3]);
			    
			    //Pizza cells are Hashmaps
			    pizza.cells = new HashMap<>();
			   
			    
			    for(int i=0;i<pizza.R;i++) {
			    	
			    	String sentence = br.readLine();
			    	for(int j=0;j<pizza.C;j++) {
			    		String cellHashKey = pizza.getCellKey(i, j);
			    		
			    		//Each Pizza Has cells
			    		Cell cell = new Cell();
			    		cell.x = i;
			    		cell.y = j;
			    		cell.ingredient = sentence.charAt(j);
			    		
			    		pizza.cells.put(cellHashKey,cell);
			    		
			    	}
			    	
			    }
			    
				
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		} catch (FileNotFoundException e) {
	
			e.printStackTrace();
		}
		
	}
	public void solve() {
		PizzaCuter cutPizza = new PizzaCuter(pizza);
		cutPizza.cutPizza();
	}
	
	public void PrintOutput() {
		
		PrintWriter pw = null;
		try {
			
			pw = new PrintWriter(this.filename + ".out", "UTF-8");
			int Size = PizzaCuter.resultSlices.size();
			pw.print(Size);
			pw.println();
			
			for(int j=0;j< Size;j++) {
				Slice slice = PizzaCuter.resultSlices.get(j);
				pw.print(slice.r1+" "+slice.c1+" "+slice.r2+" "+slice.c2);
				pw.println();
			}
			
			pw.close();
			
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
	}
}
