package driveRides;

public class Vehicle {
	
	int id;
	
	//At start sare cars [0,0] pe hoge
	 Position initial = new Position(0,0);
	 
	 boolean inUse = false;
	 int nextStartTime = 0;
	 
	 //Constructor
	 Vehicle(int id){
		 this.id = id;
	 }
	 
	 public JourneyCompleted generateJourney(Ride ride , Simulation sim,int maxTime) {
		 
		 int score=0;
		 int waitTime = 0;
		 int totalTime =0;
		 
		 //Get the Pick Up time
		 int latestPickUp = ride.getLatestPickup();
		 int rideStart = ride.startTime;
		 int TravelTime = initial.getDistanceTo(ride.startPosition);
		 int riderDistance = ride.getDistance();
		 
		 //Check if you can pick the person
		 if(latestPickUp >= (nextStartTime + TravelTime)) {
			 score = score+riderDistance;
			 
			 //
			 if(nextStartTime+TravelTime <= rideStart) {
				 score +=sim.bonus;
			 }
			 
			 if(nextStartTime+TravelTime < rideStart) {
				 waitTime = rideStart - nextStartTime - TravelTime;
			 }
			 
			 //Total time is the sum
			 totalTime = waitTime +riderDistance+TravelTime;
			 
			 return new JourneyCompleted(ride,this,score,totalTime);
		 }
		 else {
			 
			 //If you cannot take the ride
			 return null;
		 }
		 
	 }
	 
	 public void setNextStartTime(int startTime) {
		 this.nextStartTime = startTime;
	 }
	 
	 public void setPosition(Position position) {
	        this.initial = position;
	    }
	 
	
}
