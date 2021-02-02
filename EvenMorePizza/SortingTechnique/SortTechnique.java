package evenMorePizza;
import java.util.*;

public class SortTechnique {
	 //Take inputs as Dataset and Output as List of Arrays
	
	ArrayList<Order> create_order(Dataset dataset){
		
		ArrayList<Order> orders = new ArrayList<>();
		ArrayList<PizzaStructure> Sortedpizza= new ArrayList<>();  
		
		for(int i=0;i<dataset.pizzas.size()-1;i++) {
			for(int j=0;j<dataset.pizzas.size()-i-1;j++) {
				
				if(dataset.pizzas.get(j).ingredient.size() <= dataset.pizzas.get(j+1).ingredient.size()) {
					PizzaStructure temp = dataset.pizzas.get(j);
					dataset.pizzas.set(j,dataset.pizzas.get(j+1));
					dataset.pizzas.set(j+1,temp);
				}
			}
		}
		
		//Store the sorted pizzas in SortedPizza
		Order order = null;
		Sortedpizza =dataset.pizzas;
		for(PizzaStructure i: Sortedpizza) {
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
