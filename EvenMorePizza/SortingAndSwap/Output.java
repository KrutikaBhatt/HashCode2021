package hashcode2021;
import java.util.*;

//Here we hold all the deliveries List and the unused Pizzas 

public class Output {
	
	List<Delivery> Deliveries;
	List<PizzaStructure> unused;
	
	Output(List <Delivery> Deliveries, List<PizzaStructure> unused){
		this.Deliveries = Deliveries;
		this.unused = unused;
		
	}
}
