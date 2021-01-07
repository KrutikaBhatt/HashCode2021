import java.util.ArrayList;
import java.util.PriorityQueue;

// Class That defines the basic structure of Library

public class Library {
	
	Book [] bookList;
	int LibraryID;
	int signupTime;
	int scannedBooks;
	int pointCounts;
	
	int bookSize;
	boolean visited;
	
	public ArrayList<Book> submission = new ArrayList<Book>();
	
	// Build a Priority Queue of the books
	public PriorityQueue<Book> pq = new PriorityQueue<Book>();
	
	// Base Constructor
	Library(int LibraryID,Book [] bookList,int signupTime,int scannedBooks){
		this.LibraryID = LibraryID;
		this.bookList = bookList;
		this.signupTime = signupTime;
		this.scannedBooks = scannedBooks;
		
		pointCounts =0;
		visited = false;
	}
	
	public Book [] getBookList() {
		return this.bookList;
	}
	
	public void setBookList(Book [] givenBookList) {
		this.bookList = givenBookList;
		
	}
	public int getSignUpTime() {
		return this.signupTime;
	}
	
	public void setSignUpTime(int time) {
		this.signupTime = time;
	}
	
	public int getScannedBooks() {
		return this.scannedBooks;
	}
	
	public void setScannedBook(int scanned) {
		this.scannedBooks = scanned;
	}
	
	public int getPointsCount() {
        return pointCounts;
    }

    public void setPossiblePoints(int possiblePoints) {
        this.pointCounts = possiblePoints;
    }

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }
	
	

}
