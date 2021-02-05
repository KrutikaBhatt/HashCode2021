package hashcode2021;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.*;

public class Main {

	public static void main(String[] args) {
		String [] filenames = {"a_example","b_little_bit_of_everything.in","c_many_ingredients.in","d_many_pizzas.in","e_many_teams.in"};
		
		String scoreSheet = "C:\\Users\\User\\Desktop\\hashcode - Google\\Techniques\\NewMethod\\Scores.txt";
		
		for(int i=0;i<filenames.length;i++) {
			String path = "C:\\Users\\User\\Desktop\\hashcode - Google\\Techniques\\NewMethod\\"+filenames[i];
			
			LoadQuestion problem = Read_Input(path);
			
			long start = System.currentTimeMillis();
			
			
			long end = System.currentTimeMillis();
			float sec = (end - start) / 1000F;
			try {
				
				PrintWriter pw = new PrintWriter(scoreSheet, "UTF-8");
				pw.print(filenames[i]+" : ");
				pw.print(sec+"\n");
				
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
			System.out.println("Exception in Input file ");
		}
		return null;
		
	}

}
