package evenMorePizza;
import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) {
		
		String [] filenames = {"a_example","b_little_bit_of_everything.in","c_many_ingredients.in","d_many_pizzas.in","e_many_teams.in"};
		String path = "C:\\Users\\User\\Desktop\\hashcode - Google\\Techniques\\Ingredient_Frequency\\"+filenames[4];
		String output = "C:\\Users\\User\\Desktop\\hashcode - Google\\Techniques\\Ingredient_Frequency\\"+"e_many_teams.out";
		
		Grid_Technique grid = new Grid_Technique();
		Dataset dataset = load_dataset(path);
		
		long startTime = System.nanoTime();
		ArrayList<Order> orders =grid.create_order(dataset);
		generatOutput(output,orders);
		
		long endTime   = System.nanoTime();
		long totalTime = endTime - startTime;
		System.out.println("The Total time in Grid Technique");
		System.out.println("In "+filenames[4]+" :"+totalTime);
		
	}

	private static void generatOutput(String output, ArrayList<Order> orders) {
		
		try {
			int total_score = 0;
			PrintWriter pw = null;
			pw = new PrintWriter(output, "UTF-8");
			pw.print(orders.size()+"\n");
			
			for(Order order:orders) {
				pw.print(order.team()+" ");
				for(PizzaStructure piza:order.pizzas) {
					pw.print(piza.pizza_id+" ");
				}
				pw.println();
				total_score+=order.score();
			}
			
			System.out.println("The total score ="+total_score);
			pw.close();
		}catch(Exception e) {
			System.out.println("In output ="+e);
		}
	}

	private static Dataset load_dataset(String path) {
		
		//Input the data
		ArrayList<PizzaStructure> pizzas = new ArrayList<PizzaStructure>() ;
		int bufferSize = 8*1024;
		BufferedReader br = null;
		try {
     		br = new BufferedReader(new FileReader(path),bufferSize);
     		
     		String line = br.readLine();
			String [] firstline = line.split(" ");
			
			int AvailablePizzas = Integer.parseInt(firstline[0]);
			int TeamOf2 = Integer.parseInt(firstline[1]);
			int TeamOf3 = Integer.parseInt(firstline[2]);
			int TeamOf4 =Integer.parseInt(firstline[3]);
			
			
			for(int i=0;i<AvailablePizzas;i++) {
				ArrayList<String> ingr_for_pizza = new ArrayList<>() ;
				String next = br.readLine();
				String [] arrayIngr = next.split(" ");
				for(String ingr:arrayIngr) {
					ingr_for_pizza.add(ingr);
				}
				pizzas.add(new PizzaStructure(i,ingr_for_pizza));

			}
			
			//Return the dataset
			br.close();
			Dataset final_dataset = new Dataset(pizzas,TeamOf2,TeamOf3,TeamOf4);
			return final_dataset;
			
		}catch(Exception e) {
			System.out.println("In input ="+e);
		}
		
		return null;
	}

}
