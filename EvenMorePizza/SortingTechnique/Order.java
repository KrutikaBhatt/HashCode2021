package evenMorePizza;
import java.util.*;

public class Order {
	
	static int unique_score ;
	Team teamType;
	ArrayList<PizzaStructure> pizzas = new ArrayList<>();
	Order(Team teamType,ArrayList<PizzaStructure> pizzas){
		
		this.teamType = teamType;
		if(pizzas.isEmpty()) {
			this.pizzas = new ArrayList<>();
		}
		else {
			this.pizzas = pizzas;
		}
		
	}
	
	Order(Team teamType){
		this.teamType = teamType;
		if(this.pizzas.isEmpty() || this.pizzas == null)
			this.pizzas = new ArrayList<>();
	}
	void addPizza(PizzaStructure pizza) {
		this.pizzas.add(pizza);
	}
	
	int team() {
		return teamType.teamvalue;
	}
	
	Set<String> unique_ingredients() {
		Set<String> unique_ingr = new HashSet<String>();
		for(int i=0;i<pizzas.size();i++) {
			unique_ingr.addAll(pizzas.get(i).ingredient);
		}
		unique_score = unique_ingr.size();
		return unique_ingr;
	}
	
	double score() {
		unique_ingredients();
		return Math.pow(unique_score,2);
	}
	
	boolean isFull() {
		return (pizzas.size() == teamType.teamvalue);
	}
	
	int get_pizzaLen() {
		return pizzas.size();
	}
	
	Iterator<PizzaStructure> Pizza_Iterable(){
		Iterator<PizzaStructure> iterator = pizzas.iterator();
		return iterator;
	}
	
	
}

