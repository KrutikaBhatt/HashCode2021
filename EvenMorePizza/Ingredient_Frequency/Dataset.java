package evenMorePizza;
import java.util.*;


public class Dataset {
	
	
	ArrayList<PizzaStructure> pizzas;
	int two_team;
	int three_team;
	int four_team;
	int total_teams =0;
	
	Dataset(ArrayList<PizzaStructure> pizzas,int two_team,int three_team,int four_team){
		this.pizzas = pizzas;
		this.two_team = two_team;
		this.three_team = three_team;
		this.four_team = four_team;
		this.total_teams =two_team+three_team+four_team;
	}
	
	
	
	Order create_order() throws Exception {
		Order order =null;
		if(two_team>0) {
			System.out.println("Two team ");
			Team team = new Team(2);
			order = new Order(team);
			
			Decrease_two(two_team);
		}
		else if(three_team>0) {
			System.out.println("Three team");
			order = new Order(new Team(3));
			Decrease_three(three_team);
		}
		else if(four_team >0) {
			System.out.println("Four team");
			order = new Order(new Team(4));
			Decrease_four(four_team);
		}
		else
		{
			System.out.println("Teams are over");
			throw new Exception("The teams are over");
		}
		return order;
		//If teams are over ,it will return null;
		
	}



	private void Decrease_four(int four_team2) {
		this.four_team = four_team2-1;
		
	}



	private void Decrease_three(int three_team2) {
		this.three_team = three_team2-1;
		
	}



	private void Decrease_two(int two_team2) {
		this.two_team = two_team2-1;
		
	}



	 
}
