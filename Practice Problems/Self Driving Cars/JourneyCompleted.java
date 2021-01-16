package driveRides;

public class JourneyCompleted {
	
	Ride ride;
	Vehicle vehicle;
	int score;   // The total score to be returned
	int Totaltime;
	
	JourneyCompleted(Ride ride,Vehicle vehicle,int score,int Totaltime){
		
		this.ride = ride;
		this.vehicle = vehicle;
		this.score = score;
		this.Totaltime = Totaltime;
	}
	
	public double calculate() {
		return score/Totaltime;
	}
}
