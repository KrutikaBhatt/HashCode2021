package hashcode2021;
import java.util.*;

//This class assigns Id and a HashSet of ingredients to a Pizza 
public class PizzaStructure {
	
	int Id;
	HashSet<Integer> Ingredients;
	Object[] IngrArray;
	
	//Constructor
	public PizzaStructure(int id,HashSet<Integer> Ingredients) {
		this.Id = id;
		this.Ingredients = Ingredients;
		
		List<Integer> list = new ArrayList<>(Ingredients);
		Collections.sort(list);
		//Making the same Array
		
		IngrArray = list.toArray();
	}
	
	int getId() {
		return this.Id;
	}
	
	HashSet<Integer> getHashset(){
		return this.Ingredients;
	}
	
	Object[] getIngredientArray() {
		return this.IngrArray;
	}
	
	int IngredientCount() {
		return this.Ingredients.size();
	}
}
