package hashcode2021;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.*;

public class Main {

	public static void main(String[] args) {
		String [] filenames = {"a_example","b_little_bit_of_everything.in","c_many_ingredients.in","d_many_pizzas.in","e_many_teams.in"};
		
		String scoreSheet = "C:\\Users\\User\\Desktop\\hashcode - Google\\Techniques\\NewMethod\\Scores.txt";
		
		String [] outputFiles = {"a_example.out","b_little_bit_of_everything.out","c_many_ingredients.out","d_many_pizzas.out","e_many_teams.out"};
		
		for(int i=3;i<filenames.length;i++) {
			String path = "C:\\Users\\User\\Desktop\\hashcode - Google\\Techniques\\NewMethod\\"+filenames[i];
			String OutputPath = "C:\\Users\\User\\Desktop\\hashcode - Google\\Techniques\\NewMethod\\"+outputFiles[i];
			
			LoadQuestion problem = Read_Input(path);
			
			long start = System.currentTimeMillis();
			
			//Start solving
		    Algorithm algorithm = new Algorithm();
		    Output output = algorithm.BaseSolution(problem);
		    
		    algorithm.RecursiveSolution(output);
		    int totalScore =Algorithm.TotalScore(output.Deliveries);
			
			long end = System.currentTimeMillis();
			float sec = (end - start) / 1000F;
			//Print Output
		    Print_output(output,OutputPath);
			try {
				
				FileWriter fw = new FileWriter(scoreSheet, true);
				BufferedWriter bw = new BufferedWriter(fw);
				bw.write(filenames[i]+" : ");
				bw.write(sec+" seconds \n");
				bw.write(totalScore+"\n ...............\n");
				bw.close();
				
			}catch(Exception e) {
				System.out.println("In score sheet");
				System.out.println(e);
			}
		}
		 
	}

	

	private static LoadQuestion Read_Input(String path) {
		
		int bufferSize = 8*1024;
		BufferedReader br = null;
		
		try {
			br = new BufferedReader(new FileReader(path),bufferSize);
     		
     		String line = br.readLine();
			String [] firstline = line.split(" ");
			
			int PizzaCount = Integer.parseInt(firstline[0]);
			int TeamOf2 = Integer.parseInt(firstline[1]);
			int TeamOf3 = Integer.parseInt(firstline[2]);
			int TeamOf4 =Integer.parseInt(firstline[3]);
			
			System.out.printf("PizzaCount :%d  2Team :%d  3Team:%d  4Team :%d\n\n",PizzaCount,TeamOf2,TeamOf3,TeamOf4);
			List<PizzaStructure> pizzas = new ArrayList<PizzaStructure>();
			Map<String,Integer> IngredientMap = new HashMap<String,Integer>();
			int nextId=0;
			
			for(int i=0;i<PizzaCount;i++) {
				
				String sr = br.readLine();
				String [] arrayIngr = sr.split(" ");
				int ingrCount = Integer.parseInt(arrayIngr[0]);
				HashSet<Integer> ingredients = new HashSet<>();
				
				for(int j=0;j<ingrCount;j++) {
					
					String ingred1 = arrayIngr[j+1];
					if(!IngredientMap.containsKey(ingred1)) {
						IngredientMap.put(ingred1, nextId++);
					}
					ingredients.add(IngredientMap.get(ingred1));
				}
				
				pizzas.add(new PizzaStructure(i,ingredients));
				
			}
			br.close();
			
			return new LoadQuestion(TeamOf2,TeamOf3,TeamOf4,pizzas);
			
		}catch(Exception e) {
			System.out.println(e);
			e.printStackTrace();
		}
		return null;
		
	}
	
	private static void Print_output(Output output, String outputPath) {
		try {
			PrintWriter pw = new PrintWriter(outputPath, "UTF-8");
			
			pw.print(output.Deliveries.size()+"\n");
			for(Delivery delivery:output.Deliveries) {
				
				pw.print(delivery.Count());
				for(PizzaStructure pizza : delivery.DeliveryPizzas) {
					pw.print(" ");
					pw.print(pizza.Id);
				}
				
				pw.print("\n");
				
			}
			System.out.println("Done :"+outputPath);
			pw.close();
		}catch(Exception e) {
			System.out.println(e);
		}
	}

}
