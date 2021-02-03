package evenMorePizza;
import java.util.*;

public class Counter<T> {
	
	final Map<T, Integer> counts = new HashMap<>();
	 public void add(T t) {
	        counts.merge(t, 1, Integer::sum);
	    }

	    public int count(T t) {
	        return counts.getOrDefault(t, 0);
	    }
	    
	  public int size() {
		  return counts.size();
	  }
}
