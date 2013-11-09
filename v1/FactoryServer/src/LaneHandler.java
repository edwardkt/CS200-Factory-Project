import java.io.*;
import java.net.*;
import java.util.*;
import FactoryResources.*;

public class LaneHandler extends HandleAClient {
	//ArrayList<Integer> gantryRobotOrder = new ArrayList<Integer>();
	
	
	public LaneHandler(Socket socket, Server server){	
		super(socket,server);
		//populate list for test
		/*for(int i=0; i<8; i++){
			gantryRobotOrder.add(i);
		}*/
		System.out.println("Lane Handler spawned");
	}
	
	public void run(){
		System.out.println("in the run method of lane client");
		while(true){					
			/*try{				
				//output.writeObject(gantryRobotOrder);				
				System.out.println("order sent");				
			}
			catch(Exception ex){
				ex.printStackTrace();
			}*/
		}
	}
}
