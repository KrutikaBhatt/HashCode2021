// Class that defines the basic structure for Book 

public class Book implements Comparable<Book>{
	
	private int id;
	private int score;
	private boolean visited;
	
	// Base Constructor
	Book(int id,int score){
		
		this.id = id;
		this.score = score;
		
	}
	
	public int getId() {
		return this.id;
	}
	
	public int getScore() {
		return this.score;
	}
	
	public boolean IsBookVisited() {
		return this.visited;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public void setScore(int score) {
		this.score = score;
	}
	
	public void MarkVisited(boolean visited) {
		this.visited = visited;
	}
	
	// Comparing two Books on basis of the score alloted to them
	@Override
	public int compareTo(Book other) {
		return other.score - score;
	}

	

}
