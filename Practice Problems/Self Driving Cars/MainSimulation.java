package driveRides;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


//In this class ,We have used HashMap to map a list of rides to the Vehicle ID
class Result_Ride{
	
	 Map<Integer, List<Integer>> FinalVehicleRides = new HashMap<>();
	 Simulation sim;
	 
	 Result_Ride(Simulation sim){
		 this.sim = sim;
	 }
	 
	 public void AddResultRides(Integer VehicleID,Integer RideID) {
		 List<Integer> rides =  FinalVehicleRides.getOrDefault(VehicleID, new ArrayList<>());
		 rides.add(RideID);
		 FinalVehicleRides.put(VehicleID, rides);
	 }
	 
	 public Map<Integer, List<Integer>>  getHashMap() {
		 
		 //System.out.println(FinalVehicleRides.size());
		 return FinalVehicleRides;
	 }
}

public class MainSimulation {
	
	Result_Ride rr;
	String filename;
	
	Simulation sim = null;
	MainSimulation(String filename){
		this.filename = filename;
	}
	
	
	
	public void readInput() {
		int bufferSize = 8*1024;
		BufferedReader br = null;
		
		try {
			
			br = new BufferedReader(new FileReader(this.filename + ".in"),bufferSize);
			String line = br.readLine();
			String [] firstline = line.split(" ");
			
			int R = Integer.parseInt(firstline[0]);   // Row
			int C = Integer.parseInt(firstline[1]);   //Column
			int numberVehicles = Integer.parseInt(firstline[2]);    //Number of Vehicles
			int numRides = Integer.parseInt(firstline[3]);
			int perRideBonus = Integer.parseInt(firstline[4]);
			int steps = Integer.parseInt(firstline[5]);
			
			System.out.println(R+" "+C+" "+numberVehicles+" Number of rides "+numRides);
			List<Vehicle> vehicleList = new ArrayList<>();
			for(int i=0;i<numberVehicles;i++) {
				vehicleList.add(new Vehicle(i));
			}
			
			System.out.println("Vehicle list created -"+vehicleList.size());
			//Add all the data above in simulation
			sim = new Simulation(R,C,numberVehicles,numRides,perRideBonus,steps,vehicleList);
			
			
			for(int i=0;i<numRides;i++) {
				
				String nextLine = br.readLine();
				String [] getInfo = nextLine.split(" ");
				int a = Integer.parseInt(getInfo[0]);    //Starting x coord
				int b = Integer.parseInt(getInfo[1]);    // Starting y coord
				int x = Integer.parseInt(getInfo[2]);    // Finish intersection x-coord
				int y = Integer.parseInt(getInfo[3]);    //Finish intersection y-coord
				
				//Earliest Start
				int earlyStart = Integer.parseInt(getInfo[4]);
				//Latest Finish
				int latestFinish = Integer.parseInt(getInfo[5]);
				
				//System.out.println("a="+a+" "+b+" "+x+" "+y+" "+earlyStart+" "+latestFinish);
				Ride riding = new Ride(i,new Position(a,b),new Position(x,y),earlyStart,latestFinish);
				
				sim.addRides(riding);
				
			}
			
			
		}catch(Exception e) {
			System.out.println(e);
		}
	}
	
	public void solve() {
		
		List<JourneyCompleted> final_result = new ArrayList<>();
		List<Ride> rides = sim.getRides();
		
		JourneyCompleted bestJourney = null;
		
		if(sim.bonus >0) {
			rides.sort((r1, r2) -> r1.getStartTime() < r2.getStartTime() ? -1 : r1.getStartTime() > r2.getStartTime() ? 1 : 0);
		}
		else {
			rides.sort((r1,r2) ->(r1.getDistance()>r2.getDistance())?-1 : r1.getDistance() < r2.getDistance()?1:0);
		}
		
		while(sim.rides.size()>0) {
			
			List<JourneyCompleted> possible = new ArrayList<>();
			
			for(Vehicle v :sim.vehicleList) {
			   for(Ride ride :rides) {
				   
				   JourneyCompleted journey = v.generateJourney(ride, sim, sim.time);
				   
				   //Add the valid journeys to Possible Journey List
				   if(journey!=null) {
					   possible.add(journey);
				   }
			   }
	
			}
			
			if(possible.isEmpty() == false) {
				
				bestJourney = possible.get(0);
				double BestcalculatedValue = bestJourney.calculate();
				
				for(int i=1;i<possible.size();i++) {
					if(possible.get(i).calculate() > BestcalculatedValue)
						bestJourney = possible.get(i);
				}
			
			
			
			final_result.add(bestJourney);
			
			//Set the position of vehicle to the endPosition of the ride
			bestJourney.vehicle.setPosition(bestJourney.ride.endPosition);
			
			int nextStartTime = bestJourney.vehicle.nextStartTime;
			bestJourney.vehicle.setNextStartTime(bestJourney.Totaltime + nextStartTime);
			
			rides.remove(bestJourney.ride);
		}else {
			break;
		}
		}
		
		rr = new Result_Ride(sim);
		final_result.forEach(journey ->rr.AddResultRides(journey.vehicle.id +1, journey.ride.id));
		
	}
	
	public void PrintOutput() {
		try {
		PrintWriter pw = null;
		pw = new PrintWriter(this.filename + ".out", "UTF-8");
		int CountVehicle = sim.countVehicle;
		
		 Map<Integer, List<Integer>> finalVehicle = rr.getHashMap();
		 System.out.println("Count Vehicle :"+CountVehicle+" Hashmap Size :"+finalVehicle.size());
		 
		
		for (int i=CountVehicle;i>=1;i--) {
			
			
			if(finalVehicle.containsKey(i)) {
				
				List<Integer> rides = finalVehicle.get(i);
				
					pw.print(rides.size());
					//System.out.println(rides.size());
					pw.printf(" %s\n", rides.stream().map(id -> id.toString()).collect(Collectors.joining(" ")));
					
			}
			else {
			
			}
		}
		
		pw.close();
		}catch(Exception e) {
			System.out.println(e);
		}
	}
}
