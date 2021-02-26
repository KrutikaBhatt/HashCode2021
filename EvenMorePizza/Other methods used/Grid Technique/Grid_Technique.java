package evenMorePizza;

import java.util.ArrayList;
import java.util.Set;
import java.util.*;

public class Grid_Technique {
	
	ArrayList<Order> create_order(Dataset dataset){
		
		ArrayList<Order> orders = new ArrayList<>();
		Order order;
		
		System.out.println(dataset.total_teams);
		for(int i=0;i<dataset.total_teams;i++) {
		  	try {
		  		System.out.println("In grid technique ... Creating order");
				order = dataset.create_order();
				orders.add(order);
				System.out.println("Array list  length "+orders.size());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		//Sort the pizzas 
		for(int i=0;i<dataset.pizzas.size()-1;i++) {
			for(int j=0;j<dataset.pizzas.size()-i-1;j++) {
				
				if(dataset.pizzas.get(j).ingredient.size() <= dataset.pizzas.get(j+1).ingredient.size()) {
					PizzaStructure temp = dataset.pizzas.get(j);
					dataset.pizzas.set(j,dataset.pizzas.get(j+1));
					dataset.pizzas.set(j+1,temp);
				}
			}
		}
		ArrayList<PizzaStructure> Listpizza = dataset.pizzas;
		System.out.println("Sorted Pizzas length "+Listpizza.size());
		for(Order i :orders) {
			for(int j=0;j<i.team();j++) {
				
				if(Listpizza == null || Listpizza.isEmpty()) {
					System.out.println("Breaking Grid null");
					break;
				}
				
				int best_pizza_index =0;
				int best_pizza_score = Integer.MIN_VALUE;
				int index=0;
				
				for(PizzaStructure Pizza:Listpizza) {
				
					int score = (int) Math.pow(union(i.unique_ingredients(),Pizza.ingredient),2) -(int) Math.pow(intersection(i.unique_ingredients(),Pizza.ingredient),2) ;
					
					if(score>best_pizza_score) {
						best_pizza_score =score;
						best_pizza_index = index;
					}
					index++;
				}
				System.out.println("Best Index :"+best_pizza_index);
				PizzaStructure best_pizza =Listpizza.remove(best_pizza_index);
				i.addPizza(best_pizza);
				
			}
		}
		
		ArrayList<Order> final_order = new ArrayList<Order>();
		for(Order order1:orders) {
			if(order1.isFull()) {
				System.out.println("The final order is added");
				final_order.add(order1);
			}
		}
		System.out.println("Final order size :"+final_order.size());
		return final_order;
	}

	private int union(Set<String> unique_ingredients, ArrayList<String> ingredient) {
		//Create the hashset of the ArrayList
		Set<String> ingr = new HashSet<String>(ingredient);
		Set<String> Uni_ingr = unique_ingredients;
		Uni_ingr.addAll(ingr);
		return Uni_ingr.size();
		
	}
	
	private int intersection(Set<String> unique_ingredients, ArrayList<String> ingredient) {
		Set<String> ingr = new HashSet<String>(ingredient);
		Set<String> Uni_ingr = unique_ingredients;
		Uni_ingr.retainAll(ingr);
		return Uni_ingr.size();
	}
}
