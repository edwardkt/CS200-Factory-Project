import java.io.*;
import java.net.*;
import java.util.*;
import FactoryResources.*;


public class HandleAClient implements Runnable{
	Server server;
	Socket socket;
	protected ObjectOutputStream output;
	protected ObjectInputStream input;
	
	
	public HandleAClient(){}
	
	public HandleAClient(Socket socket, Server server){
		this.server = server;
		this.socket = socket; // initialize socket
		
		output = server.getOutput();
		input = server.getIput();
		
		System.out.println("client handler spawned");		
	}
	
	////////SENDING METHODS////////////////
	
	public void sendFactoryState(){ //send object to factory production manager
		try{
			server.factoryState.setString("from server");
			
			FactoryState fs = new FactoryState();
			fs.setKits(server.factoryState.getKits());
			output.writeObject(fs);				
			
			for(int i=0; i<fs.getKits().size(); i++){
				System.out.println(fs.getKits().get(i).getName());
			}
		}
		catch(Exception ex){
			ex.printStackTrace();
			System.exit(0);
		}
	}
	
	public void sendGROrder(BoxUpdater newOrder){ // called by server.sendOrderToGRM
		try{
			output.writeObject(newOrder);			
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
	
	}
	
	public void sendLanesUpdate(LanesUpdater newOrder){ // called by server.sendLanesStatus
		try{
			System.out.println("in sendLanesUpdater method");
			output.writeObject(newOrder);
			System.out.println("lanes updater sent");
		}
		catch(Exception ex){
			//ex.printStackTrace();
		}
		
	}
	
	///////////////////
	
	//getters
	public ObjectOutputStream getOutput(){
		return output;
	}
	
	public ObjectInputStream getInput(){
		return input;
	}

		
	public void run() {
		
	
	}


}
