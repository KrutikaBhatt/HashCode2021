import java.io.*;
import java.util.*;




public class Hashcode2019_Pizza {
	// Files for Input and Output
	static File input_folder = new File("C:\\Users\\User\\eclipse-workspace\\Greedy Algorithms\\src\\input\\");
	public static File[] InputFiles = input_folder.listFiles();
	
	static File output_folder = new File("C:\\Users\\User\\eclipse-workspace\\Greedy Algorithms\\src\\output\\");
	public static File[] OutputFiles = output_folder.listFiles();
	
	// Variables 
	public static int R;            // Number of rows
	public static int C;            // No. of columns
	public static int L;            // Minimum number of each ingredient
	public static int H;            // Maximum number of cells in each slice
	
	static int N = 10000;
	static char [][] pizza = null;   // Pizza 
	
	
	
	// Defining a cumulative sum Array
	
	static int [][] sum = null;

	static class Slice{
		int r1,r2,c1,c2;
		
		Slice(){}  // Empty Constructor
		
		// Constructor to define the row start,row end,column start and column end
		Slice(int r1,int r2,int c1,int c2){
			this.r1 = r1;
			this.r2 = r2;
			this.c1 = c1;
			this.c2 = c2;
		}
		
		int size() {
			int row = (r2-r1) + 1;
			int column = (c2-c1) +1;
			
			return row*column;
		}
		
		int mushroom_cells() {
			
			
			/*Due to this loop the Time Complexity is O(R*C*R*C)
			We will use the cumulative sum which reduces the time Complexity to O(R*C)
			
			int s =0;
			for(int i = r1;i<=r2;i++) {
				for(int j=c1;j<=c2;j++) {
					if(pizza[i][j] == 'M')
						s++;
				}
			}
			return s;
			*/
			
			int sum_of_grid = sum[r2][c2] - (c1!=0 ? sum[r2][c1-1] :0) - (r1!=0 ? sum[r1-2][c2] :0) +(r1!=0 && c1!=0 ?sum[r1-1][c1-1]:0);
			return sum_of_grid;
			
		}
		
		// This function returns true if the slice is correct according to the input parameters
		boolean checkSlice() {
			int Mushrooms = mushroom_cells();
			int Tomatoes = size() - Mushrooms;
			
			//Check if Mushrooms and Tomatoes are under given ranges
			return (Tomatoes >=L && Mushrooms >=L && (Tomatoes+Mushrooms)<=H);
		}
	}
	
	// Defining a  vector solution
	static Vector <Slice> solution = new Vector<Slice>();
	
	public static void readInput(File inputFile) {
		
		int bufferSize = 8*1024;
		BufferedReader bufferedReader = null;
		
		try {
			bufferedReader = new BufferedReader(new FileReader(inputFile), bufferSize);
			String line = bufferedReader.readLine();
		    String[] firstLine = line.split(" ");
		    
		    // Since first line me sab kuch he
		    R = Integer.parseInt(firstLine[0]);
		    C = Integer.parseInt(firstLine[1]);
		    
		    L = Integer.parseInt(firstLine[2]);
		    
		    H = Integer.parseInt(firstLine[3]);
		    
		    pizza =new char [R][C];
		    sum = new int [R][C];
		    String temp[] = new String[R];
		    
		    
		    
		    System.out.println("R :"+R+" C :"+C+" L :"+L+" H :"+H);
		    // Clear the solution vector
		    solution.clear();
		  
		    
		    for(int i=0;i<R;i++) {
		    	
		    	//String l = bufferedReader.readLine();
		    	//char [] arr = l.toCharArray();
		    	temp[i] =  bufferedReader.readLine();
		    	 
		    	for(int j=0;j<C;j++) {
		    		
		    		char ingredient = temp[i].charAt(j);
		    		pizza[i][j] = ingredient;
		    		
		    		if(pizza[i][j] == 'M')
		    			sum[i][j] =1;
		    		else
		    			sum[i][j] = 0;
		    	
		    	}
		    }
		    
		    // Cumulative sum
		    for(int i=0;i<R;i++) {
		    	for(int j=0;j<C;j++) {
		    		sum[i][j] = sum[i][j] 
		    				+ (i !=0 ? sum[i-1][j] :0) 
		    				+(j!=0 ? sum[i][j-1]:0) 
		    				- (i!=0 && j!=0 ?sum[i-1][j-1] :0);
		    		
		    		
		    	}
		    }
		    
		}catch(Exception e)
		 {
			System.out.println("Input me Isssue "+e);
		 }
		
		}
	
	public static void printOutput(File outputFile) {
		 PrintWriter writer = null;
		 
		 try {
			 writer = new PrintWriter(outputFile, "UTF-8");
			 
			 writer.print(solution.size());
			 writer.println();
			 
			 for(int i=0;i<solution.size();i++) {
				 writer.print(solution.get(i).r1+" "+solution.get(i).r2+" "+solution.get(i).c1+" "+solution.get(i).c2);
				 writer.println();
			 }
			 writer.close();
		 }
		 catch(FileNotFoundException e) {
			 System.out.println("Catch Block 1 issue");
			 System.out.println(e);
		 }
		 catch(UnsupportedEncodingException e)
		 {
			 System.out.println("Catch Block 2 issue");
			 System.out.println(e);
		 }

	}
	
	public static int solveColumn(int r1,int r2,Vector<Slice> sol) {
		int c1 =0;
		int sum = 0;
		while(c1<C) {
			int c2 = c1;
			boolean isPreviousOk = false;
			while(c2<C) {
				
				Slice slice = new Slice(r1,r2,c1,c2);
				if(slice.checkSlice()) {
					isPreviousOk = true;
				}
				else if(isPreviousOk) {
					sol.add(new Slice(r1,c1,r2,c2-1));
					sum = sum + sol.lastElement().size();
					break;
				}
				c2++;
			}
			if(!isPreviousOk) {
				c1++;
			}
			else {
				c1=c2;
			}
			
		}
		
		return sum;
	}
	
	static void solve () {
		int r1 = 0;
		while(r1<R) {
			int r2 = r1;
			int best = Integer.MIN_VALUE;
			Vector <Slice> temp =  new Vector<Slice> ();
			while(r2<R) {
				Vector<Slice> v = new Vector<Slice>();
				int res = solveColumn(r1,r2,v);
				if(res<best) {
					r2 -- ;
					break;
				}
				best = res;
				temp = v;
				r2 ++;
				
			}
			for(int i=0;i<temp.size();i++) {
				solution.add(temp.get(i));
			}
			r1 = r2+1;
			
		}
	}
	public static void main(String[] args) {
		
		try {
			
			for(int i=0;i<InputFiles.length;i++) {
				BufferedReader br = new BufferedReader(new FileReader(InputFiles[i]));
				System.out.println(InputFiles[i]);
				
				readInput(InputFiles[i]);
				solve();
				printOutput(OutputFiles[i]);
				
				
			}
			
		}  
		catch (Exception e) {
			System.out.println(e);
		}
		
		

	}

}
