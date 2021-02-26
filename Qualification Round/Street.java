package qualification_round;

public class Street {
	 public int id;
	 public Street(String _Name, int _toCross, RoadNode _goesFrom, RoadNode _goesTo) {
	        Name = _Name;
	        toCross = _toCross;
	        goesFrom = _goesFrom;
	        goesTo = _goesTo;
	    }
	    public String Name;
	    public int toCross;
	    public RoadNode goesFrom;
	    public RoadNode goesTo;
	
	
}
