import java.io.*;
import java.net.*;
import java.util.*;
import FactoryResources.*;


public class PandKHandler extends HandleAClient{
	
	
	public PandKHandler(Socket socket, Server server){
		super(socket,server);
		System.out.println("PandK Handler spawned");
		try{
			output.writeObject(server.getNestStatus());	// send starting status for nests.
			System.out.println("nest status sent");
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
	}
		
	public void run(){
		System.out.println("in the run method of PandK client");
		while(true){
		}
	}
}



