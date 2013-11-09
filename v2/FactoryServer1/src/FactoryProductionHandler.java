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
			ex.printStackTrace();
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
		public void run(){ //continually reads messages from fpm
			System.out.println("in fpm handler read run()");
			while(true){
				try{
					FactoryUpdater update = (FactoryUpdater)input.readObject();
					server.setLanesStatus(update.getBoxUpdater()); // set lanes status and send to lanes manager
					server.sendOrderToGRM(update.getBoxUpdater()); // send order to gantry robot manager				
				}
				catch(Exception ex){
					ex.printStackTrace();
					break;
				}
			}
		}
		
	}


}
