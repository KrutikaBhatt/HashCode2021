package driveRides;

import java.util.ArrayList;
import java.util.List;

public class Simulation {
	
	int row;
	int col;
	int countVehicle;
	int countRides;
	int bonus;
	int time;
	
	List<Ride> rides ;
	List<Vehicle> vehicleList;
	
	//Constructor
	public Simulation(int row,int col,int countVehicle,int countRides,int bonus,int time, List<Vehicle> vehicleList) {
		this.row = row;
		this.col = col;
		this.countVehicle = countVehicle;
		this.countRides = countRides;
		this.bonus = bonus;
		this.time = time;
		this.vehicleList = vehicleList;
	
		// Debug issue :
		// Issue comes as null pointer exception when addRides as the rides is not initializes
		//While calling the constructor
		this.rides = new ArrayList<>(countRides);
	}
	
	public void addRides(Ride ride) {
		rides.add(ride);
	}
	
	 public List<Ride> getRides() {
	        return rides;
	    }
	
	
}
