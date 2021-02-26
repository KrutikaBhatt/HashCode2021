package qualification_round;

public class Information {
	public int time;
	public int points;
	
	public RoadNode[] nodes;
	public Street[] streets;
	public Car[] cars;
	
	 public Street gettingStreetName(String name) {
		 for(Street s :streets) {
			 if(s.Name.equals(name)) {
				 return s;
			 }
		 }
		 return null;
	    }
}
