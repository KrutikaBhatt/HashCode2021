package qualification_round;

public class Solution1 {
	
	public Information info;
	
	Solution1(Information info){
		this.info = info;
		
	}
	
	public String Solve() {
		
		for(RoadNode node : info.nodes) {
			node.CarSchedule = new Street[info.time];
		}
		
		for(Car car :info.cars) {
			
			System.out.println(car.Id);
			int Time = 0;
            for(int i=1; i<car.path_followed.size(); i++){
               Time += car.path_followed.get(i).toCross;
            }
            if(Time > info.time){
                continue;
            }

            // first street - begin at end
            int timer = 1;
            Street street = car.path_followed.get(0);
            RoadNode node = street.goesTo;
            
            node.AddingToSchedule(street, timer);

            // next streets
            for(int i=1; i<car.path_followed.size(); i++){
                street = car.path_followed.get(i);
                node = street.goesTo;
                timer += street.toCross;
                
                System.out.println(street.Name +" "+ (timer-1));
                node.AddingToSchedule(street, timer);
            }
		}
		
		String s = "";
        int count = 0;
        for(RoadNode node : info.nodes)
        {
            String n = node.GetSchedule();
            if(n != ""){
                count ++;
                s += n;
            } 
        }
        String returningString = count+""+s;
        return returningString;
	}
}
