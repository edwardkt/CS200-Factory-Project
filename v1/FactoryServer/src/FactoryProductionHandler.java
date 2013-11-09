import java.io.*;
import java.net.*;
import java.util.*;
import FactoryResources.*;

public class FactoryProductionHandler extends HandleAClient {
	
	public FactoryProductionHandler(Socket socket, Server server){
		super(socket, server);
		try{
			output.writeObject(server.getKitsList());	//sends kits to FPM
			Read read = new Read();
			new Thread(read).start();
			
		}
		catch(Exception ex){
			
		}
		
		
	}
	
	public void run(){
		System.out.println("in factory production manager run()");
		while(true){
			try{
			}
			catch(Exception ex){
				
			}	
		}
	}
	
	public class Read implements Runnable{

		public void run(){
			System.out.println("in fpm handler read run()");
			while(true){
				try{
					FactoryUpdater update = (FactoryUpdater)input.readObject();
					//System.out.println("lane no server " + update.getBoxUpdater().getLane());
					
					System.out.println("received");
					server.sendOrderToGRM(update.getBoxUpdater());					
				}
				catch(Exception ex){
					ex.printStackTrace();
					break;
				}
			}
		}
		
	}


}
