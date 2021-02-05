package hashcode2021;
import java.util.*;

public class LoadQuestion {
	
	List<PizzaStructure> Pizzas;
	int Team2;
	int Team3;
	int Team4;
	Map<Integer,List<PizzaStructure>> IngreToPizza;
	
	//Constructor
	LoadQuestion(int Team2,int Team3,int Team4,List<PizzaStructure> Pizzas){
		this.Team2 = Team2;
		this.Team3 = Team3;
		this.Team4 = Team4;
		this.Pizzas = Pizzas;
		
		//Now we assign the Map
		this.IngreToPizza = new HashMap<Integer,List<PizzaStructure>>();
		for(PizzaStructure pizza: Pizzas)
		{
			for(Integer Ingredients : pizza.getHashset()) {
				
				List<PizzaStructure> ingredientPizza = null;
				if(!IngreToPizza.containsKey(Ingredients)) {
					ingredientPizza = new ArrayList<PizzaStructure>();
					this.IngreToPizza.put(Ingredients,ingredientPizza);
				}
				ingredientPizza.add(pizza);
			}
		}
		
	}
	
	public int GetTotalIngredient() {
		int count=0;
		for(List<PizzaStructure> values :IngreToPizza.values()) {
			//count += values.stream().mapToInt(a -> a).sum();
			count += values.size();

		}
		
		return count;
	}
}
