

public class BookScanning {

	public static void main(String[] args) {
		
		String input_files [] = {"a_example","b_read_on","c_incunabula","d_tough_choices","e_so_many_books","f_libraries_of_the_world"};
		
		for(int i=0;i<input_files.length;i++) {
			
			String path ="C:\\Users\\User\\Documents\\HashCode2021\\Practice Problems\\Book Scanning Problem\\Inputs\\"+input_files[i];
			Simulation sim = new Simulation(path);
			
			sim.readInput();
			sim.PrintOutput();
		}

	}

}
