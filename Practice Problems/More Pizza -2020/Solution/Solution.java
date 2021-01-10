package morePizza2020;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;


public class Solution {
	String filename;
	int maximum_people;
	int Numberdiffpizza;
	
	// Array List for the number of Slices each Pizza Have
	String [] secondLine = null;
	
	// Solution Indix List
	ArrayList<Integer> SolutionIndex =new ArrayList<>();
	
	public Solution(String filename) {
		this.filename = filename;
	}
	
	public void readInput() {
		
		int bufferSize = 8*1024;
		BufferedReader br = null;
		
		try {
			br = new BufferedReader(new FileReader(this.filename +".in"),bufferSize);
			try {
				
				String line = br.readLine();
				String [] firstLine  = line.split(" ");
				this.maximum_people = Integer.parseInt(firstLine[0]);
				this.Numberdiffpizza = Integer.parseInt(firstLine[1]);
				
				System.out.println("File -"+filename+" Maximum people :"+this.maximum_people+" Different Pizza :"+this.Numberdiffpizza);
				
				String secLine = br.readLine();
				secondLine = secLine.split(" ");
				
				
			}catch (IOException e) {
				e.printStackTrace();
			}
			
					
		}catch(Exception e) {
			System.out.println(e);
		}
	}
	
	public void Solve() {
		
		int sum =0;
		int fullsize = this.secondLine.length;
		int startIndex = fullsize;
		
		int maxScore =0 ;// Stores maximum score generated through out
		
		ArrayList<Integer> SolutionValueList = new ArrayList<>();
		
		ArrayList<Integer> CurrentIndex = new ArrayList<>();
		ArrayList<Integer> CurrentValueList = new ArrayList<>();
		
		while((CurrentIndex.size() > 0 && CurrentIndex.get(0) !=0) || CurrentIndex.size() == 0) {
			
			startIndex --;
			//Traverse from end to start 
			for(int i=startIndex;i>=0;i--) {
				
				int CurrentValue =  Integer.parseInt(this.secondLine[i]);
				int tempSum = sum+CurrentValue;
				
				if(tempSum == this.maximum_people) {
					// If the temporary sum is equal to target that means the perfect solution is found
					
					sum = tempSum;
					CurrentIndex.add(i);
					CurrentValueList.add(CurrentValue);
					break;
					
				}
				else if(tempSum > this.maximum_people) {
					continue;   // If the temporary sum is greater than target then Try next value
	                
				}
				else if(tempSum < this.maximum_people) {
					// If the temporary sum is lesser than target
					
					sum = tempSum;
					CurrentIndex.add(i);
					CurrentValueList.add(CurrentValue);
					continue;
					
				}
							
			}
			if(maxScore < sum) { //# If currently generated solution has the best score
				maxScore = sum ;
				
				for(Integer i :CurrentIndex) {
					this.SolutionIndex.add(i);
				}
				for(Integer i : CurrentValueList) {
					SolutionValueList.add(i);
				}
			}
			
			if(maxScore == this.maximum_people) {
				break;
			}
			
			if(CurrentValueList.size()!= 0) {
				
				int index = CurrentValueList.size() -1;
				int LastVal = CurrentValueList.remove(index);
				sum = sum-LastVal;
			}
			
			if(CurrentIndex.size() !=0) {
				int index = CurrentIndex.size() -1;
				int LastIndex = CurrentIndex.remove(index);
				startIndex = LastIndex;
				
			}
			
			if(CurrentIndex.size() ==0  && (startIndex ==0)) {
				break;
			}
			
		}
		
		System.out.println("Score :"+maxScore);
		
	}
	
	public void PrintOutput() {
		PrintWriter pw =null;
		
		try {
			
			pw = new PrintWriter(this.filename + ".out", "UTF-8");
			pw.print(this.SolutionIndex.size());
			pw.println();
			
			for(int i=0;i<this.SolutionIndex.size();i++) {
				pw.print(this.SolutionIndex.get(i)+" ");
			}
			
			pw.close();
		}catch(Exception e) {
			System.out.println(e);
		}
	}
}
