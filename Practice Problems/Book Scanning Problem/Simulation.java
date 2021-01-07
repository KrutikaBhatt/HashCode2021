import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class Simulation {
	
	String filename;
	Main_Solving main ;
	
	
	Simulation(String filename){
		this.filename = filename;
		
	}
	
	public void readInput() {
		
		int bufferSize = 8*1024;
		BufferedReader br = null;
		
		System.out.println("File name :"+this.filename);
		
		try {
			br = new BufferedReader(new FileReader(this.filename + ".txt"),bufferSize);
			String line = br.readLine();
			String [] firstline = line.split(" ");
			
			int numBooks = Integer.parseInt(firstline[0]);
			int numLibraries = Integer.parseInt(firstline[1]);
			int numDays = Integer.parseInt(firstline[2]);
			
			String [] secondline = br.readLine().split(" ");
			Book [] books = new Book[numBooks];
			
			for(int i=0;i<books.length;i++) {
				
				int score = Integer.parseInt(secondline[i]);
				books[i] = new Book(i,score);
			}
			
			// Library
			
			ArrayList<Library> library = new ArrayList<>();
			int count =0;
			
			String loopline ="";
			while((loopline =br.readLine())!=null) {
				
				String [] temp = loopline.split(" ");
				
				// This is a bit debugging issue
				// Without the loop the runtime error shown is 
				//Exception in thread "main" java.lang.NumberFormatException: For input string: ""
				if(temp[0].equals("")) {
					System.out.println("Breaking ...");
					break;
				}
				int amountBooks = Integer.parseInt(temp[0]);
				int signupTime = Integer.parseInt(temp[1]);
				int shipping = Integer.parseInt(temp[2]);
				
				line = br.readLine();
				temp = line.split(" ");
				
				Book [] bookID = new Book[amountBooks];
				for(int i=0;i<amountBooks;i++) {
					bookID[i] = books[Integer.parseInt(temp[i])];
					
				}
				
				Library lib = new Library(count,bookID,signupTime,shipping);
				library.add(lib);
				count ++;
				
			}
			
			//Now the SOLVing Part
			main = new Main_Solving(library,numDays);
			main.startSolving();
			
		}catch(IOException e) {
			System.out.println(e);
		}
		
	}
	
	public void PrintOutput() {
		PrintWriter pw = null;
		int total =0;
		try {
			
			pw = new PrintWriter(this.filename + ".out", "UTF-8");
			System.out.println("Printing file -"+this.filename);
			System.out.println(main.libraryScanning.size());
			pw.print(main.libraryScanning.size());
			pw.println();
			
			for(int i=0;i<main.libraryScanning.size();i++) {
				pw.print(main.libraryScanning.get(i).LibraryID+ " "+main.libraryScanning.get(i).submission.size());
				pw.println("");
				
				for(int j=0;j<main.libraryScanning.get(i).submission.size();j++) {
					pw.print(main.libraryScanning.get(i).submission.get(j).getId()+" ");
					total += main.libraryScanning.get(i).submission.get(j).getScore();
					
				}
				
				pw.println();
				
			}
			
			pw.print("Total ="+total);
			pw.close();
			System.out.println("Total :"+total);
			
		}catch(Exception e) {
			System.out.println("In Printing :"+e);
		}
	}
	
	
}
