import java.io.*;
import java.net.*;
import java.util.*;
import FactoryResources.*;

public class PartsRobotHandler extends HandleAClient {
	//ArrayList<Integer> gantryRobotOrder = new ArrayList<Integer>();
	
	
	public PartsRobotHandler(Socket socket, Server server){	
		super(socket,server);
		//populate list for test
		/*for(int i=0; i<8; i++){
			gantryRobotOrder.add(i);
		}*/
		System.out.println("Parts Robot Handler spawned");
	}
	
	public void run(){
		System.out.println("in the run method of parts robot client");
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
