import java.io.*;
import java.net.*;
import java.util.*;
import FactoryResources.*;


public class PartsManagerHandler extends HandleAClient {
	
	
	public PartsManagerHandler(Socket socket, Server server){		
		super(socket,server);
		System.out.println("Parts Handler spawned");
	}
	
	
	public void run(){
		while(true){
			
			System.out.println("in the run method");
			
			try{
				Object partsList = new Object();
				partsList = input.readObject();
				ArrayList<Part> parts = new ArrayList<Part>();
				parts = (ArrayList<Part>) partsList;
				for(int i=0; i<parts.size();i++)
					System.out.println(parts.get(i).getName());
				server.setPartsList(parts);		
				System.out.println("server was updated");

				
			}
			catch(Exception ex){
				ex.printStackTrace();
			}
			
		}
		
	}

}
