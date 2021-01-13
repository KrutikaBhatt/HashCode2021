package driveRides;

public class Position {
	int X;
	int Y;
	
	//Constructor
	public Position(int X,int Y) {
		this.X = X;
		this.Y = Y;
	}
	
	public int getDistanceTo(Position pos) {
		return (Math.abs(this.X - pos.X) +Math.abs(this.Y - pos.Y));
	}
}
