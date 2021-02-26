package qualification_round;
import java.util.*;


public class Solution2 {
	
	void solve(Information info) {
		
		List<SCounter> scouterList = new ArrayList<SCounter>();
		
		//This added to is fix the Java null pointer error
		for(int i=0;i<info.streets.length;i++) {
			scouterList.add(new SCounter(0,info.streets[i]));
			
		}
		
		int sum =0;
		 for(Car car : info.cars) {
			 //The path followed in cars Class is the streets that the car will follow
	            for(Street street : car.path_followed) {
	                for(SCounter sc : scouterList){
	                    if(street == sc.street){
	                        sc.count++;
	                        sum++;
	                    }
	                }
	            }
	        }
		 
		 
		 int time_given = info.time;
		 for(RoadNode node: info.nodes) {
			 for(Street street :node.streetIn) {
				 
				 boolean streetFound = false;
				 for(SCounter sc :scouterList) {
					 
					 if(street == sc.street) {
						 if(sc.count == 0) {
							 
						 }
						 streetFound =true;
						 
						 double calculate = time_given;
						 node.times.add(((int)Math.round(calculate/sc.count)));
					 }
				 }
				 
				 if(streetFound == false) {
					 System.out.print("This shouldnt be happening");
				 }
			 }
		 }
		
	}
}
