package evenMorePizza;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.*;

public class MainSolving {
	
	String filename;
	
	
	List<String[]> ingredients = null;
	int bestScore;
	
	MainSolving(String filename){
		this.filename = filename;
		ingredients = new ArrayList<>();
	}
	
	
	//Function to read the input file
	public void ReadInput() {
		
		int bufferSize = 8*1024;
		BufferedReader br = null;
		
		try {
     		br = new BufferedReader(new FileReader(this.filename),bufferSize);
     		
     		String line = br.readLine();
			String [] firstline = line.split(" ");
			
			int AvailablePizzas = Integer.parseInt(firstline[0]);
			int TeamOf2 = Integer.parseInt(firstline[1]);
			int TeamOf3 = Integer.parseInt(firstline[2]);
			int TeamOf4 =Integer.parseInt(firstline[3]);
			
			//Read all the Available Pizzas
			for(int i=0;i<AvailablePizzas;i++)
			{
				String next = br.readLine();
				String [] arrayIngr = next.split(" ");
				String [] ingredient = new String [Integer.parseInt(arrayIngr[0])];
				
				for(int j=0;j<ingredient.length;j++) {
					ingredient[j] = arrayIngr[j+1];
				}
				
				ingredients.add(ingredient);
			}
			
			System.out.println("List Size :"+ingredients.size());
			
			List<Integer> values = new ArrayList<>();   //Since we just have to give the indices
			for(int i=0;i<AvailablePizzas;i++) {
				values.add(i);              //Just the indexes
			}
			
			
			solve(AvailablePizzas,TeamOf2,TeamOf3,TeamOf4,values);
			
			
		}catch(Exception e) {
			System.out.println(e);
		}
	}


	private void solve(int availablePizzas, int teamOf2, int teamOf3, int teamOf4, List<Integer> values) {
		
		//Creating a list that returns all the best scores
		bestScore =0;
		int len3=0;
		int len2 = 0;
		int len4=0;
		List<int []> scoreof2= new ArrayList<>();
		List<int []> scoreof3= new ArrayList<>();
		List<int []> scoreof4= new ArrayList<>();
		int t2 =teamOf2;
		int t3 = teamOf3;
		int t4 = teamOf4;
		
		for(int j=0;j<1000;j++) {
			
		int i=0;
		int score =0;
		int individualScore;
		Collections.shuffle(values, new Random());
		List<int []> bestScoreOfTwo = null;
		bestScoreOfTwo = new ArrayList<>();
		List<int []> bestScoreOfThree =null;
		bestScoreOfThree=new ArrayList<>();
		List<int []> bestScoreOfFour =null;
		bestScoreOfFour = new ArrayList<>();
		teamOf3 = t3;
		teamOf4 = t4;
		teamOf2 = t2;
		while(i<=availablePizzas) // Get all the Pizza
			
		{
			//If 2 memeber team is left and we have pizzas
			
			if(teamOf2>0 && i+2<=availablePizzas) {
				
				teamOf2--;
				
				bestScoreOfTwo.add(new int [] {values.get(i),values.get(i+1)});
			
				individualScore = Union(ingredients.get(values.get(i)),ingredients.get(values.get(i)),null,null);
				i+=2;
				
				
			}
			else if(teamOf3 >0 && i+3<=availablePizzas) {
				teamOf3 --;
				bestScoreOfThree.add(new int[] {values.get(i),values.get(i+1),values.get(i+2)});
				
				individualScore = Union(ingredients.get(values.get(i)),ingredients.get(values.get(i)),ingredients.get(i+2),null);
				i+=3;
				
			}
			
			else if(teamOf4 >0 && i+4 <=availablePizzas) {
				teamOf4 --;
				bestScoreOfFour.add(new int[] {values.get(i),values.get(i+1),values.get(i+2),values.get(i+3)});
				
				individualScore = Union(ingredients.get(values.get(i)),ingredients.get(values.get(i)),ingredients.get(i+2),ingredients.get(i+3));
				i+=4;
				
			}
			else {
				
				break;
			}
			
			score +=Math.pow(individualScore,2);         //As per Problem statement
		}
		
		if(score >bestScore) {
			bestScore = score;
			scoreof2 =bestScoreOfTwo;
			scoreof3 = bestScoreOfThree;
			scoreof4 = bestScoreOfFour;
			System.out.println("Score in Loop :"+bestScore);
			 len2 = scoreof2.size();
			 len3 = scoreof3.size();
			 len4 = scoreof4.size();
			
		}
		}
		
		System.out.println("Score :"+bestScore);
		PrintOutput(scoreof2,scoreof3,scoreof4,len2,len3,len4);
	
	}


	private int Union(String[] strings, String[] strings2,String[] string3,String[] string4) {
		
		Set<String> result = new HashSet<String>();
	    result.addAll(Arrays.asList(strings));
	    result.addAll(Arrays.asList(strings2));
	    if(string3!=null) {
	    result.addAll(Arrays.asList(string3));
	    }
	    if(string4!=null) {
	    result.addAll(Arrays.asList(string4));
	    }
	    return result.toArray(new String[result.size()]).length;
		
	}
	
	
   private void PrintOutput(List<int[]> scoreof2, List<int[]> scoreof3, List<int[]> scoreof4,int tt,int ttt,int tttt) {
	   try {
			PrintWriter pw = null;
			pw = new PrintWriter(this.filename + ".out", "UTF-8");
			pw.print(tt+ttt+tttt);
			pw.println("");
			for(int i=0;i<tt;i++) {
				pw.print("2 ");
				for(int j=0;j<2;j++) {
					pw.print(scoreof2.get(i)[j]+" ");
				}
				pw.println("");
				
			}
			
			
			for(int i=0;i<ttt;i++) {
				pw.print("3 ");
				for(int j=0;j<3;j++) {
					pw.print(scoreof3.get(i)[j]+" ");
				}
				pw.println("");
			}
			
			for(int i=0;i<tttt;i++) {
				pw.print("4 ");
				for(int j=0;j<4;j++) {
					pw.print(scoreof4.get(i)[j]+" ");
				}
				pw.println("");
			}
			
			pw.close();
			System.out.println("Done --"+filename);
			
			
	   }catch(Exception e) {
		   System.out.println(e);
	   }
		
	}
}
