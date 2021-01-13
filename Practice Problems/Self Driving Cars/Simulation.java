package driveRides;

import java.util.List;

public class Simulation {
	
	int row;
	int col;
	int countVehicle;
	int countRides;
	int bonus;
	int time;
	
	List<Ride> rides;
	List<Vehicle> vehicleList;
	
	//Constructor
	public Simulation(int row,int col,int countVehicle,int countRides,int bonus,int time) {
		this.row = row;
		this.col = col;
		this.countVehicle = countVehicle;
		this.countRides = countRides;
		this.bonus = bonus;
		this.time = time;
		
	}
	
	public void addRides(Ride ride) {
		rides.add(ride);
	}
	
	
}
