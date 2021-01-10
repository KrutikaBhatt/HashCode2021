package morePizza2020;

public class morePizza {

	public static void main(String[] args) {
		
		String [] filename = {"a_example","b_small","c_medium","d_quite_big","e_also_big"};
		
		for(int i=0;i<filename.length;i++) {
			
			String path = "C:\\Users\\User\\Documents\\Google-Hash-Code-2020\\Input\\"+filename[i];
			Solution main = new Solution(path);
			main.readInput();
			main.Solve();
			main.PrintOutput();
		}

	}

}
