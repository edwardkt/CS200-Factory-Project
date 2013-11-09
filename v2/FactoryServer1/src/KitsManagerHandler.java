import java.io.*;
import java.net.*;
import java.util.ArrayList;
import FactoryResources.*;

public class KitsManagerHandler extends HandleAClient {

	public KitsManagerHandler(Socket socket, Server server){		
		super(socket,server);
		try{			
			server.sendPartsList();
			System.out.println("Kits Handler spawned");	
		}
		catch(Exception ex){
			
		}
	}
	
	public void run(){
		while(true){
			
			try{
				ArrayList<Kit> kits = (ArrayList<Kit> )input.readObject();
				server.setKitsList(kits);
				System.out.println("received kits");
				server.factoryState.setKits(kits);
				server.sendFactoryState();
			}
			catch(Exception ex){
				ex.printStackTrace();
				break;
			}			
		}
	}
}


	
	
