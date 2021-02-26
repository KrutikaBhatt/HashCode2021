package qualification_round;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

public class Running_file {

	public static void main(String[] args) {
		String [] filename = {"a","b","c","d","e","f"};
		
		
		for(int i=0;i<1;i++) {
			String path ="C:\\Users\\User\\Downloads\\Qualification\\"+filename[i]+".txt";
			Reader readInput = new Reader(path);
			
			Information info = readInput.ReadInput(path);
			
			Solution1 solution = new Solution1(info);
			String s = solution.Solve();
			//lq.PrintOutput();
			
			PrintWriter pw = null;
			try {
				pw = new PrintWriter(filename[i] + ".out", "UTF-8");
				pw.print(s);
				pw.close();
			} catch (FileNotFoundException | UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		/*
		for(int i=0;i<1;i++) {
			String path ="C:\\Users\\User\\Downloads\\Qualification\\"+filename[i]+".txt";
			Reader readInput = new Reader(path);
			
			Information info = readInput.ReadInput(path);
			
			Solution2 solution2 = new Solution2();
			String s = solution2.solve(info);
			//lq.PrintOutput();
			
			PrintWriter pw = null;
			try {
				pw = new PrintWriter(filename[i] + "_.out", "UTF-8");
				pw.print(s);
				pw.close();
			} catch (FileNotFoundException | UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}*/
		
		
		

	}

}
