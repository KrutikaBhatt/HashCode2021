import java.util.ArrayList;

// The main File with the solving part with Algorithm

public class Main_Solving {
	
	int totalDays;
	ArrayList<Library> library;
	
	int day = 0; // Start from Day0
	
	//Initially no books from library is shipping
	boolean IsShipping = false;		
	int libraryCount=0;
	
	ArrayList<Library> libraryScanning = new ArrayList<>();
	ArrayList<Book> bookOrder = new ArrayList<>();
	
	public Main_Solving(ArrayList<Library> library,int totalDays) {
		this.library = library;
		this.totalDays = totalDays;
		
	}
	
	public void startSolving() {
		
		Library nowShipping = null ;
		
		for(int i=0;i<totalDays;i++) {
			
			//Finding the Library with more points and scan it
			if(!IsShipping) {
				
				// Go through all the libraries and get library with max Score
				getPossiblePoints();
				
				// Assign with the best Library
				nowShipping = BestLibrary();
				LibraryPriorityQueue(nowShipping);
				
				//Decrease the signUpTime
				nowShipping.setSignUpTime(nowShipping.getSignUpTime() -1);
				IsShipping = true;
			}
			else {
				 // it is currently shipping, so pop the books, make them visited
				//Check if Signup Time is over
				if(nowShipping.signupTime == 0) {
					libraryScanning.add(nowShipping);
					IsShipping = false;
				}
				
				//Else decrease the Signup time
				else {
					nowShipping.setSignUpTime(nowShipping.getSignUpTime() -1);
				}
			}
			
			for(Library l : libraryScanning) {
				// Loop through all scanned books .Also if the Priority Queue is not empty
				for(int j=0;j<l.getScannedBooks() && !l.pq.isEmpty();j++) {
					
					Book temp = l.pq.poll();
					bookOrder.add(temp);
					l.submission.add(temp);
					
				}
			}
			
			
		}
	}

	private void LibraryPriorityQueue(Library nowShipping) {
		Book [] bookArray = nowShipping.getBookList();
		for(int i=0;i<bookArray.length && i<(totalDays -(day+ nowShipping.getSignUpTime() +1));i++) {
			
			if(!bookArray[i].IsBookVisited()) {
				nowShipping.pq.add(bookArray[i]);
				bookArray[i].MarkVisited(true);
			}
		}
		
	}

	private Library BestLibrary() {
		// Get the library with best possible points
		
		int max = library.get(0).getPointsCount();
		int lib =0;
		
		for(int i=1;i<library.size();i++) {
			if(max < library.get(i).getPointsCount()) {
				lib =i;   // Get the index;
				max =  library.get(i).getPointsCount();
			}
		}
		
		// Mark the Library as visited
		library.get(lib).setVisited(true);
		// Once done remove the library from the ArrayList
		Library l = library.get(lib);
		library.remove(lib);
		
		return l;
	}

	private void getPossiblePoints() {
		// Get the Library with more number of points
		
		for(int lib=0; lib< library.size();lib++) {
			
			int score =0;
			if(!library.get(lib).isVisited()) {
				// If the library is not visited
				Book [] bookArray = library.get(lib).getBookList();
				for(int i=0;i<bookArray.length && i<(totalDays -(day+ library.get(lib).getSignUpTime() +1));i++) {
					
					if(!bookArray[i].IsBookVisited()) {
						score+= bookArray[i].getScore();
					}
				}
				
				library.get(lib).setPossiblePoints(score/library.get(lib).getSignUpTime());
			}
		}
		
	}
}
