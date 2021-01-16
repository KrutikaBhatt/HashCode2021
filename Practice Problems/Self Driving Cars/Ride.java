package driveRides;

public class Ride {
	
	int id;
	
	public Position startPosition;
	public Position endPosition;
	
	int startTime;
	int endTime;
	
	
	//Constructor 
	Ride(int id,Position startPosition,Position endPosition, int startTime,int endTime){
		
		this.id = id;
		this.startPosition = startPosition;
		this.endPosition = endPosition;
		this.startTime = startTime;
		this.endTime = endTime;
	}
	
	public int getDistance() {
		return (Math.abs(startPosition.X - endPosition.X) + Math.abs(startPosition.Y - endPosition.Y));
	}
	
	public int getLatestPickup() {
		return endTime - getDistance();
	}

	public int getStartTime() {
		return this.startTime;
	}
}
