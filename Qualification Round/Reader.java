package qualification_round;

import java.io.BufferedReader;
import java.io.FileReader;


public class Reader {
	
	String filename ;
	Reader(String filename){
		this.filename = filename;
		
	}
	
	public Information ReadInput(String path) {
		Information info = new Information();
		
		int bufferSize = 8*1024;
		BufferedReader br = null;
		
		try {
     		br = new BufferedReader(new FileReader(this.filename),bufferSize);
     		String line = br.readLine();
			String [] firstline = line.split(" ");
			
			int simulation_time = Integer.parseInt(firstline[0]);
			int intersections = Integer.parseInt(firstline[1]);
			int streets = Integer.parseInt(firstline[2]);
			int cars = Integer.parseInt(firstline[3]);
			int score = Integer.parseInt(firstline[4]);
			
			System.out.println("Read First Line");
			info.time = simulation_time;
			info.points = score;
			
			RoadNode[] nodes = new RoadNode[intersections];
			
			for(int i=0; i<intersections; i++) {
	            nodes[i] = new RoadNode();
			}
			System.out.println("Node Array :"+nodes.length);
			Street[] streetArray = new Street[streets];
			
			for(int i=0;i<streets;i++) {
				String new_line = br.readLine();
				String [] newlineArray = new_line.split(" ");
				
				int from  = Integer.parseInt(newlineArray[0]);
				int to  = Integer.parseInt(newlineArray[1]);
				String name = newlineArray[2];
				
				streetArray[i] = new Street(name,Integer.parseInt(newlineArray[3]),nodes[from],nodes[to]);
				
				nodes[from].streetOut.add(streetArray[i]);
				nodes[to].streetIn.add(streetArray[i]);
				
				
			}
			
			info.nodes = nodes;
			info.streets = streetArray;
			
			 Car[] carArray = new Car[cars];
			 
			 for(int i=0;i<cars;i++) {
				 carArray[i] = new Car();
				 String car_line = br.readLine();
				 String [] carline = car_line.split(" ");
				 
				 int many = Integer.parseInt(carline[0]);
				 
				 int j=1;
				 while(many>0) {
					 Street s = info.gettingStreetName(carline[j++]);
					 carArray[i].path_followed.add(s);
		                many--;
				 }
						
				}
			
			 info.cars = carArray;
			 System.out.println(info.toString());
			 System.out.println("Reading Input done");
			
		}
		catch(Exception e) {
			e.printStackTrace();
			
		}
		return info;
	}
}
