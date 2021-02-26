package qualification_round;

import java.util.ArrayList;
import java.util.List;
import java.util.*;

public class RoadNode {
	 int id;
	 public List<Street> streetIn = new ArrayList<Street>();
	 public List<Street> streetOut = new ArrayList<Street>();
	 public List<Integer> times = new ArrayList<Integer>();
	 
	 public Street[] CarSchedule;
	 
	 /* There was error on the below function:
	  * Exception in thread "main" java.lang.ArrayIndexOutOfBoundsException: Index 7 out of bounds for length 6
	  * Solved it
	  */
	 
	 public void AddingToSchedule(Street street, int time){
		 for(int i=time; i<=CarSchedule.length; i++){
	            if(CarSchedule[i - 1] == null){
	            	CarSchedule[i - 1] = street;
	                break;
	            }
	        }
	    }
	    public void PrintSchedule(){
	        System.out.println(this.id);
	        for(int i=0;i<CarSchedule.length;i++) {
	        	System.out.println(i+"  "+CarSchedule[i].toString());
	        }
	    }
	    public String GetSchedule(){

	       
	        String s = "";
	        s += this.id+"\n";

	        List<Pair> pairs = new ArrayList<Pair>();
	        Pair temp = null;
	        for(int i=0; i<CarSchedule.length -1; i++){
	            Street street = CarSchedule[i];
	            if(street != null){
	                // p = pairs.Where(t => t.street == street).;
	            	
	            	for(Pair p:pairs) {
	            		if(p.street.equals(street)) {
	            			temp = p;
	            		}
	            	}
	                if(temp!= null){
	                    temp.count++;
	                }
	                else{
	                    pairs.add(new Pair(street, 1));
	                }
	            }
	        }

	        if(pairs.size() == 0)
	            return "";

	        s += pairs.size();
	        for(Pair pair : pairs){
	            s += "\n"+pair.toString();
	        }

	        return s;
	    }
}
