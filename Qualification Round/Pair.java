package qualification_round;

public class Pair {
	
	 public Pair(Street street, int count){
         this.street = street;
         this.count = count;
     }

     public Street street;
     public int count;

     public String toString()
     {
    	 
         if(count == 0)
             return "";
         else 
             return  ""+street.Name+" "+count;
     }
 }

