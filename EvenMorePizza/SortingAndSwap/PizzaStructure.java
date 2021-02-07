package hashcode2021;
import java.util.*;

//This class assigns Id and a HashSet of ingredients to a Pizza 
public class PizzaStructure implements Comparable<PizzaStructure> {
	
	int Id;
	HashSet<Integer> Ingredients;
	int[] IngrArray;
	
	//Constructor
	public PizzaStructure(int id,HashSet<Integer> Ingredients) {
		this.Id = id;
		this.Ingredients = Ingredients;
		
		List<Integer> list = new ArrayList<>(Ingredients);
		Collections.sort(list);
		//Making the same Array
		
		IngrArray = toArray(list);
	}
	
	private int[] toArray(List<Integer> list) {
		int [] array = new int[list.size()];
		for(int i=0;i<list.size();i++) {
			array[i] = list.get(i);
		}
		return array;
	}

	int getId() {
		return this.Id;
	}
	
	HashSet<Integer> getHashset(){
		return this.Ingredients;
	}
	
	int[] getIngredientArray() {
		return this.IngrArray;
	}
	
	Integer IngredientCount() {
		return this.Ingredients.size();
	}

	@Override
	public int compareTo(PizzaStructure o) {
		return this.IngredientCount().compareTo(o.IngredientCount());
	}
}
