package driveRides;

public class SelfDrivingCars {

	public static void main(String[] args) {
		
		String [] filenames = {"e_high_bonus"};//"b_should_be_easy","c_no_hurry","d_metropolis","e_high_bonus"};
		
		
		for(int i=0;i<filenames.length;i++) {
			String pathname = "C:\\Users\\User\\eclipse-workspace\\Greedy Algorithms\\src\\driveRides\\inputs\\"+filenames[i];
			
			System.out.println(filenames[i]);
			MainSimulation sim = new MainSimulation(pathname);
			sim.readInput();
			sim.solve();
			sim.PrintOutput();
		}

	}

}
