package evenMorePizza;
import java.util.*;


public class PizzaStructure {
	
	int pizza_id;
	ArrayList<String> ingredient;
	
	
	//Constructor
	PizzaStructure(int pizza_id,ArrayList<String>ingredient){
		this.pizza_id = pizza_id;
		this.ingredient = ingredient;
		
	}
	
	public String repr() {
		String statment = this.pizza_id+" "+this.ingredient.toString();
		return statment;
	}
	
	public int getId() {
		return this.pizza_id;
	}
}


