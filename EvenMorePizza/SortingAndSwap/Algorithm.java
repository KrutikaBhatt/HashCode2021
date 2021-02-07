package hashcode2021;
import java.util.*;

public class Algorithm {
	
	Output BaseSolution(LoadQuestion Ld) {
		
		//Builded a Comparator Interface in PizzaStructure to sort them in descending order 
		List<PizzaStructure> pizzas = Ld.Pizzas;
		Collections.sort(pizzas, Collections.reverseOrder());
		
		List<Delivery> fianl_deliveries = new ArrayList<Delivery>();
		int [] teamSize = new int [] {0,0,Ld.Team2,Ld.Team3,Ld.Team4};
		
		while(pizzas.size()>=2) {
			
			int maxSize =0;
			//Start from 4 Teams ...2 Team se start karke kuch nahi ho raha he
			for(int i=4;i>=2;i--) {
				
				if(teamSize[i] >0) {
					maxSize =i;
					break;
				}
			}
			
			if(maxSize ==0) {
				//No teams left
				break;
			}
			
			List<PizzaStructure> current = PrepareDelivery(pizzas,maxSize);
			
			//No team left at this size
			if(teamSize[current.size()] ==0) {
				
				// Increase delivery size with the pizzas with the least ingredients
				while(pizzas.size() >0) {
					
					int nextPizza = pizzas.size() -1;
					current.add(pizzas.get(nextPizza));
					pizzas.remove(nextPizza);
					
					if(teamSize[current.size()] >0)
						break;
				}
			}
			
			teamSize[current.size()] --;
			fianl_deliveries.add(new Delivery(current));
		}
		
		return new Output(fianl_deliveries,pizzas);
	}

	private static List<PizzaStructure> PrepareDelivery(List<PizzaStructure> pizzas, int maxSize) {
		
		HashSet<Integer> DeliveryIngred = new HashSet<Integer>();
		
		List<PizzaStructure> currentDelivery = ChooseFirsts(pizzas);
		
		for(PizzaStructure i: currentDelivery) {
			DeliveryIngred.addAll(i.Ingredients);
		}
		
		while(currentDelivery.size() < maxSize) {
			
			int nextPizza = Integer.MIN_VALUE;
			int PizzaOverlap = 0;
			int nextNewIngred =0;
			
			for(int i=0;i<pizzas.size();i++) {
				
				if(pizzas.get(i).IngredientCount()<nextNewIngred) {
					break;
				}
				
				int IntersectionSize = Intersection(DeliveryIngred,pizzas.get(i).Ingredients);
				int newIngredient = pizzas.get(i).IngredientCount() - IntersectionSize;
				
				if((newIngredient >nextNewIngred)||((newIngredient == nextNewIngred) && (IntersectionSize <PizzaOverlap))) {
					
					nextPizza = i;
					PizzaOverlap =IntersectionSize;
					nextNewIngred = newIngredient;
					
					if(PizzaOverlap == 0)
						break;
				}
			}
			
			//Cannot add further pizzas
			if(nextNewIngred == 0) {
				break;
			}
			
			currentDelivery.add(pizzas.get(nextPizza));
			DeliveryIngred.addAll(pizzas.get(nextPizza).Ingredients);
			pizzas.remove(nextPizza);
		}
		
		return currentDelivery;
	}

	private static int Intersection(HashSet<Integer> deliveryIngred, HashSet<Integer> ingredients) {
		
		HashSet<Integer> small;
		HashSet<Integer> big;
		
		if(deliveryIngred.size() < ingredients.size()) {
			small = deliveryIngred;
			big = ingredients;
		}
		
		else {
			small = ingredients;
			big = deliveryIngred;
		}
		
		int score =0;
		for(Integer i :small) {
			if(big.contains(i))
				score++;
		}
		
		return score;
	}

	private static List<PizzaStructure> ChooseFirsts(List<PizzaStructure> pizzas) {
		
		List<PizzaStructure> delivery = new ArrayList<PizzaStructure>();
        if (pizzas.size() == 0)
            return delivery;

        delivery.add(pizzas.get(0));
        pizzas.remove(0);

        return delivery;
	}
	
	//Creating a more Optimized Solution 
	void RecursiveSolution(Output output){
		
		while(true) {
			while(true) {
				
			   boolean done = false;
			   //(PairSwap = 1,SwapBetweenDelivery=2 ,ItUsefPizzas = 3)
			   done = done || RecursiveUtil(1,output,2000);  //Pair Swap done
			   done = done || RecursiveUtil(2,output,2000);
			   done = done || RecursiveUtil(3,output,2000);
			   
			   if(!done)
				   break;
			}
			
			if(!RecursiveUtil(4,output,20000))
				break;
		}
	}
	boolean RecursiveUtil(int optimization,Output solution,int maxDelivery) {
		
		if(optimization == 1) {
			
			boolean done =false;
			while(PairSwap(solution,maxDelivery)) {
				System.out.println("The Pair Swap Score :"+TotalScore(solution.Deliveries));
				done =true;
			}
			
			return done;
		}
		else if(optimization ==2) {
			boolean done =false;
			while(SwapBetweenDelivery(solution,maxDelivery)) {
				System.out.println("The Swap Between Deliveries Score :"+TotalScore(solution.Deliveries));
				done =true;
			}
			
			return done;
		}
		else if(optimization == 3){
			boolean done =false;
			while(ItUsedPizzas(solution,maxDelivery)) {
				System.out.println("The Using Pizzas Score :"+TotalScore(solution.Deliveries));
				done =true;
			}
			
			return done;
		}
		else {
			boolean done =false;
			while(CheckBetweenDelivery(solution,maxDelivery)) {
				System.out.println("The CheckBetweenDeliverys Score :"+TotalScore(solution.Deliveries));
				done =true;
			}
			
			return done;
		}
	}
	static int TotalScore(List<Delivery> deliveries) {
		
		//Score according to mentioned in Problem statement
		int score=0;
		for(Delivery delivery :deliveries) {
			score+=delivery.score;
		}
		return score;
	}

	boolean PairSwap(Output solution, int maxDelivery) {
		
		List<Delivery> delivery = solution.Deliveries;
		boolean done =false;
		
		for(int i=0;(i<delivery.size())&&(i<maxDelivery);i++) {
			
			Delivery delive1 = delivery.get(i);
			for(int j=i+1;(j < delivery.size())&&(j < maxDelivery);j++) {
				
				Delivery delive2 = delivery.get(j);
				if((delive1.Count() < 4)&& (delive2.Count() < 4)) {
					continue;
				}
				
				boolean Swapdelivery = DeliverySwapPizzas(delive1,delive2);
				done = done || Swapdelivery;
				
			}
		}
		
		return done;
	}
	
	static boolean  DeliverySwapPizzas(Delivery deliveryI, Delivery deliveryJ) {
		
		boolean done = false;
		
		int ScoreBefore = deliveryI.score + deliveryJ.score;
		
		for(int PairIndex1 =0;PairIndex1<deliveryI.Pairs.size();PairIndex1++) {
			
			for(int PairIndex2=0;PairIndex2<deliveryJ.Pairs.size();PairIndex2++) {
				
				//System.out.println("PairIndex :"+PairIndex1 +" PairIndex2 :"+PairIndex2);
				//Debugged - Showed IndexOutof bound
				
				int ScoreAfter1 = deliveryI.DeliveryHashSetExcludingPairPizze.get(PairIndex1).length + deliveryJ.DeliveryHashSetPairPizza.get(PairIndex2).length -
						IntersectSize(deliveryI.DeliveryHashSetExcludingPairPizze.get(PairIndex1),deliveryJ.DeliveryHashSetPairPizza.get(PairIndex2));
				
				ScoreAfter1 *= ScoreAfter1;
				
				//Get the score 2
				int ScoreAfter2 = deliveryJ.DeliveryHashSetExcludingPairPizze.get(PairIndex2).length + deliveryI.DeliveryHashSetPairPizza.get(PairIndex1).length -
						IntersectSize(deliveryJ.DeliveryHashSetExcludingPairPizze.get(PairIndex2), deliveryI.DeliveryHashSetPairPizza.get(PairIndex1));
				
				ScoreAfter2 *= ScoreAfter2;
				
				//If new scores are greater than before scores
				if(ScoreAfter1 +ScoreAfter2 >ScoreBefore) {
					
					//Swap them
					DeliverySwap(deliveryI.DeliveryPizzas, deliveryI.Pairs.get(PairIndex1)[0], deliveryJ.DeliveryPizzas,deliveryJ.Pairs.get(PairIndex2)[0]);
					DeliverySwap(deliveryI.DeliveryPizzas, deliveryI.Pairs.get(PairIndex1)[1], deliveryJ.DeliveryPizzas,deliveryJ.Pairs.get(PairIndex2)[1]);
					
					deliveryI.Solve();
					deliveryJ.Solve();
					
					done =true;
				}
			}
			
		}
		
		return done;
		
	}

	

	private static void DeliverySwap(List<PizzaStructure> list1, Integer item1,List<PizzaStructure> list2, Integer item2) {
		
		PizzaStructure temp = list1.get(item1);
		list1.set(item1, list2.get(item2));
		list2.set(item2, temp);
		
	}

	private static int IntersectSize(int[] a, int[]  b) {
		
		int SIZE =0;
		int itemA =0;
		int itemB =0;
		
		while((itemA<a.length) && (itemB<b.length)) {
			
			if(a[itemA] <b[itemB]) {
				itemA ++;
				
			}
			else if(a[itemA] > b[itemB])
				itemB++;
			
			else if(a[itemA] == b[itemB]) {
				
				SIZE++;
				itemA++;
				itemB++;
				
			}
			
		}
		return SIZE;
	}
	
	
	static boolean SwapBetweenDelivery(Output output,int maxdelivery) {
		
		List<Delivery> deliveries = output.Deliveries;
		boolean done =false;
		for(int i=0;(i<deliveries.size())&&(i<maxdelivery);i++) {
			
			for(int j=i+1;(j<deliveries.size())&&(j<maxdelivery);j++) {
				
				Delivery delivery1 = deliveries.get(i);
				Delivery delivery2 = deliveries.get(j);
				
				boolean optimized = BetweenDeliveriesUtil(delivery1,delivery2);
				done = done || optimized;
			}
		}
		
		return done;
	}
	
	static boolean BetweenDeliveriesUtil(Delivery delivery1,Delivery delivery2) {
		
		boolean done =false;
		int scoreBefore = delivery1.score +delivery2.score;
		
		for(int index1=0;index1<delivery1.Count();index1++) {
			
			for(int index2=0;index2<delivery2.Count();index2++) {
				
				PizzaStructure pizza1 =delivery1.DeliveryPizzas.get(index1);
				PizzaStructure pizza2 = delivery2.DeliveryPizzas.get(index2);
				
				 int scoreAfter1 =delivery1.ExcludeCurrentPizza.get(index1).length + pizza2.Ingredients.size() -IntersectSize(delivery1.ExcludeCurrentPizza.get(index1), pizza2.IngrArray);
                 scoreAfter1 *= scoreAfter1;

	             int scoreAfter2 =delivery2.ExcludeCurrentPizza.get(index2).length + pizza1.Ingredients.size() -
	                        IntersectSize(delivery2.ExcludeCurrentPizza.get(index2), pizza1.IngrArray);
	             scoreAfter2 *= scoreAfter2;
	             
	             if(scoreAfter1+scoreAfter2 >scoreBefore) {
	            	 
	            	 delivery1.DeliveryPizzas.set(index1, pizza2);
	            	 delivery1.Solve();
	            	 delivery2.DeliveryPizzas.set(index2, pizza1);
	            	 delivery2.Solve();
	            	 done =true;
	             }
			}
			
		}
		return done;
			
	}
	
	//Here we compare the unused pizzas to that of pizzas considered in delivery
	static boolean ItUsedPizzas(Output output,int maxdelivery) {
		
		if(output.Deliveries.size() > maxdelivery) {
			return false;
		}
		
		boolean done=false;
		
		for(Delivery delivery :output.Deliveries) {
			
			for(int index=0;index<output.unused.size();index++) {
				
				PizzaStructure UnusedPizza = output.unused.get(index);
				
				for(int j=0;j<delivery.Count();j++) {
					
					if(delivery.ExcludeCurrentPizza.get(j).length + UnusedPizza.Ingredients.size() < delivery.Ingre_Count) {
						continue;
					}
					
					int scoreBefore = delivery.score;
					int scoreAfter = delivery.ExcludeCurrentPizza.get(j).length + UnusedPizza.Ingredients.size() -IntersectSize(delivery.ExcludeCurrentPizza.get(j), UnusedPizza.IngrArray);
				    
					scoreAfter*=scoreAfter;
					
					if(scoreAfter > scoreBefore) {
						//Swap and calculate
						PizzaStructure temp = delivery.DeliveryPizzas.get(j);
						delivery.DeliveryPizzas.set(j,UnusedPizza);
						delivery.Solve();
						
						output.unused.set(index, temp);
						UnusedPizza = temp;
						done =true;
					}
				}
			}
		}
		
		return done;
	}
	
	//Move between Deliveries and check
	boolean CheckBetweenDelivery(Output output,int maxdelivery) {
		
		List<Delivery> delivery = output.Deliveries;
		if(delivery.size() > maxdelivery)
			return false;
		
		boolean done=false;
		
		for(int i=0;i<delivery.size();i++) {
			
			for(int j=i+1;j<delivery.size();j++) {
				
				Delivery delivery1 = delivery.get(i);
				Delivery delivery2 = delivery.get(j);
				
				boolean deliveryDone = MovingBetweenDeliveryUtil(delivery1,delivery2);
				done = done ||deliveryDone;
			}
		}
		
		return done;
	}
	
	static boolean MovingBetweenDeliveryUtil(Delivery delivery1,Delivery delivery2) {
		
		Delivery big;
		Delivery small;
		
		//Check which one is big and which one is small
		if(delivery1.Count() > delivery2.Count()) {
			big = delivery1;
			small = delivery2;
		}
		else {
			big = delivery2;
			small = delivery1; 
		}
		
		if(big.Count() - small.Count()!=1) {
			return false;
		}
		
		int scoreBefore =big.score + small.score;
		
		for(int i=0;i<big.Count();i++) {
			
			int scoreLarge = big.ExcludeCurrentPizza.get(i).length;
			scoreLarge*=scoreLarge;
			
			int scoreAfterSmall = small.Ingre_Count + big.DeliveryPizzas.get(i).IngredientCount() -IntersectSize(small.IngredientArray, big.DeliveryPizzas.get(i).IngrArray);
	        scoreAfterSmall *= scoreAfterSmall;
	        
	        if(scoreAfterSmall+scoreLarge >scoreBefore) {
	        	small.DeliveryPizzas.add(big.DeliveryPizzas.get(i));
	        	big.DeliveryPizzas.remove(i);
	        	small.Solve();
	        	big.Solve();
	        	return true;
	        }
		}
		return false;
	}
}
