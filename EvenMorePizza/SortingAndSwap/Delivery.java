package hashcode2021;
import java.util.*;

public class Delivery {
	
	List<PizzaStructure> DeliveryPizzas;
	
	//Rest all the variables and lists that will be called in other classes
	//Initializing to avoid Null Pointer Exceptions
	int[] IngredientArray =null;
	int Ingre_Count =0;
	int score =0;
	List<int []> ExcludeCurrentPizza = null;
	List<Integer[]> Pairs= null;
	List<int[]> DeliveryHashSetPairPizza =null;
	List<int[]> DeliveryHashSetExcludingPairPizze =null;
	/* Important Concepts :
	 
	 For a 4 team members, there individual pizzas will be compared in a C(4,2) = 6 way combinations
	 Now we have indices- (0,1,2,3) [4 indices] ,So combinations will be (0,1),(0,2),(0,3),(1,2),(1,3),(2,3)
	 
	 Similarly for a 3 team members, there individual pizzas will be compared in a C(3,2) = 3 way combinations
	 and for a 2 member, the combination is {0,1}
	 
	*/
	
	Map<Integer,ArrayList<Integer[]>> COMBINATIONS = new HashMap<Integer,ArrayList<Integer[]>>();
	
	Delivery(List<PizzaStructure> pizzas){
		this.DeliveryPizzas = new ArrayList<PizzaStructure>(pizzas);
		
		//Add the above logic to Combination Map
		ArrayList<Integer[]> Four_member = new ArrayList<>();
		Four_member.add(new Integer[] {0,1});
		Four_member.add(new Integer[] {0,2});
		Four_member.add(new Integer[] {0,3});
		Four_member.add(new Integer[] {1,2});
		Four_member.add(new Integer[] {1,3});
		Four_member.add(new Integer[] {2,3});
		COMBINATIONS.put(4, Four_member);
		
		ArrayList<Integer[]> Three_member = new ArrayList<>();
		Three_member.add(new Integer[] {0,1});
		Three_member.add(new Integer[] {0,2});
		Three_member.add(new Integer[] {1,2});
		COMBINATIONS.put(3, Three_member);
		
		ArrayList<Integer[]> Two_member = new ArrayList<>();
		Two_member.add(new Integer[] {0,1});
		COMBINATIONS.put(2,Two_member);
		
		Solve();
	}
	
	public int Count() {
		return this.DeliveryPizzas.size();
	}
	
	public void Solve() {
		HashSet<Integer> ingredients = new HashSet<>();
		
		for(PizzaStructure pizza:this.DeliveryPizzas) {
			ingredients.addAll(pizza.Ingredients);
		}
		
		IngredientArray = ArrayFromHashset(ingredients);
		Ingre_Count = ingredients.size();
		
		//Get the score
		score = (int) Math.pow(Ingre_Count, 2);
		
		//Hashset for 1 pizza only
		ExcludeCurrentPizza = new ArrayList<int[]>();
		
		for(int i=0;i<DeliveryPizzas.size();i++) {
			
			HashSet<Integer> TempHashset = new HashSet<Integer>();
			for(int j=0;j<DeliveryPizzas.size();j++) {
				
				//Don't union same Hashsets... Compare with each other
				if(i==j) {
					continue;
				}
				TempHashset.addAll(DeliveryPizzas.get(j).Ingredients);
			}
			//Finally add into the main ExcludeCurrentPizza
			 ExcludeCurrentPizza.add(ArrayFromHashset(TempHashset));
		}
		
		//Build hashset for Pizza Pair
		Pairs= COMBINATIONS.get(this.DeliveryPizzas.size());
		DeliveryHashSetPairPizza = new ArrayList<int[]>();
        DeliveryHashSetExcludingPairPizze = new ArrayList<int[]>();
        
        for(Integer[] pair :Pairs) {
        	
        	//Start Building pairs
        	HashSet<Integer> tempPair = new HashSet<>();
        	HashSet<Integer> tempExcludingPair = new HashSet<>();
        	
        	for(int j=0;j<DeliveryPizzas.size();j++) {
        		if(j == pair[0] || j==pair[1]) {
        			tempPair.addAll(DeliveryPizzas.get(j).Ingredients);
        		}
        		else {
        			tempExcludingPair.addAll(DeliveryPizzas.get(j).Ingredients);
        		}
        	}
        	
        	DeliveryHashSetPairPizza.add(ArrayFromHashset(tempPair));
        	DeliveryHashSetExcludingPairPizze.add(ArrayFromHashset(tempExcludingPair));
        }
	}

	private int[] ArrayFromHashset(HashSet<Integer> ingredients) {
		List<Integer> array = new ArrayList<>(ingredients);
		Collections.sort(array);
		//return array.toArray();   -- Didnt work due to type error (Expected object[])
		int [] newarray = new int [array.size()];
		int index =0;
		for(Integer i:array) {
			newarray[index] = i;
			index++;
		}
		
		return newarray;
	}
	
}
