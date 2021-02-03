package evenMorePizza;
import java.util.*;


public class IngredientFrequency {
	
	//Method that 
	ArrayList<Order> create_order(Dataset dataset){
		
		final Counter<String> All_ingr = new Counter<>();
		//ArrayList<Integer> frequency = new ArrayList<Integer>();
		
		for(PizzaStructure pizza :dataset.pizzas ) {
			for(String ingr : pizza.ingredient) {
				All_ingr.add(ingr);
			}
		}
		ArrayList<Integer> pizza_score = new ArrayList<>();
		
		for(PizzaStructure pizza : dataset.pizzas) {
			int sum=0;
			for(String item :pizza.ingredient) {
				sum+= All_ingr.count(item);
			}
			pizza_score.add(sum);
		}
		
		//Sort the pizzas according to pizza_score
		System.out.println("Sorting ....");
		for(int i=0;i<dataset.pizzas.size()-1;i++) {
			for(int j=0;j<dataset.pizzas.size()-i-1;j++) {
				
				if(pizza_score.get(j) > pizza_score.get(j+1)) {
					PizzaStructure temp = dataset.pizzas.get(j);
					dataset.pizzas.set(j,dataset.pizzas.get(j+1));
					dataset.pizzas.set(j+1,temp);
				}
			}
		}
		ArrayList<PizzaStructure> Listpizzas =dataset.pizzas;
		ArrayList<Order> orders = new ArrayList<Order>();
		
		//Store the sorted pizzas in SortedPizza
		Order order = null;
		for(PizzaStructure i: Listpizzas) {
			if(order==null) {
				try {
					order = dataset.create_order();
				}
				catch(Exception e){
					break;
				}
			}
			order.addPizza(i);
			if(order.isFull()) {
				orders.add(order);
				order = null;
			}
		}
		
		return orders;
	}
}
