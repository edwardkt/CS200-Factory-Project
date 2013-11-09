import java.awt.*;
import java.io.*;
import java.net.*;
import javax.swing.*;
import java.util.*;
import FactoryResources.*;

public class FactoryProductionClient extends JFrame{
	FactoryState fs;
	Socket socket;
	ObjectOutputStream out;
	ObjectInputStream in;
	
	//we need for sending
	FactoryUpdater factoryUpdater = new FactoryUpdater();
	FactoryManagerAnimation factoryAnimation;

	
	public static void main(String[] args){
		new FactoryProductionClient();
	} 
	
	
	public FactoryProductionClient(){
		
		//jframe for factoryproductionmanager		
		fs = new FactoryState(); // object received
		factoryAnimation = new FactoryManagerAnimation(this);
		setVisible(true);
		setSize(1200,700);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		validate();
		setTitle("Factory Manager");
		add(factoryAnimation);
		validate();
		
		try{
			socket = new Socket("aludra.usc.edu", 8001);
			
			System.out.println("new socket");
			out = new ObjectOutputStream(socket.getOutputStream());
			in = new ObjectInputStream(socket.getInputStream());
			Integer n = 3;
			out.writeObject(n);
			System.out.println("Client stream");
			ArrayList<Kit> kits = (ArrayList<Kit>)in.readObject(); //gets the existing list of kits
			factoryAnimation.setList(kits);
			factoryAnimation.setComboBoxes();
			Read read = new Read();
			new Thread(read).start();
		}
		catch(Exception ex){
			ex.printStackTrace();
		}		
		while(true){ // this loop writes when necessary
			try{
				
			}
			catch(Exception ex){
				ex.printStackTrace();
			}

		}		
	}
	
	public class Read implements Runnable{
		public void run(){
			
			while(true){ // this looop reads all the time
				try{
					fs = (FactoryState)in.readObject();
					fs.printState();
					ArrayList<Kit> kits = new ArrayList<Kit>();
					kits = fs.getKits();
					factoryAnimation.setList(kits);
					factoryAnimation.setComboBoxes();
				
				}
				catch(Exception ex){
					
				}
			}			
		}
	}
	
	public void sendGantryRobotOrder(Box order){
		try{
			factoryUpdater = new FactoryUpdater();			
			factoryUpdater.setBoxUpdater(0,order.getLane()); //creates BoxUpdater in the FactoryUpdater			
			factoryUpdater.getBoxUpdater().setNumOfParts(order.getNumOfItems()); //set the number of Items in the BoxUpdater
			factoryUpdater.getBoxUpdater().setImage(order.getPart());
			out.writeObject(factoryUpdater);
			System.out.println("grm order sent to server");
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	
	

}
