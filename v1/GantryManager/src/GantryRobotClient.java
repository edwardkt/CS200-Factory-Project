import java.awt.*;
import java.io.*;
import java.net.*;
import javax.swing.*;
import java.util.*;
import FactoryResources.*;

public class GantryRobotClient extends JFrame{

	BoxUpdater gantryRobotOrder = new BoxUpdater();
	
	FactoryState fs;
	Socket socket;
	ObjectOutputStream out;
	ObjectInputStream in;
	FactoryManagerAnimation fpa;

	
	public static void main(String[] args){
		new GantryRobotClient();
	}
	
	
	public GantryRobotClient(){
		//jframe for factoryproductionmanager		
		fs = new FactoryState(); // object received
		fpa = new FactoryManagerAnimation(this);
		setVisible(true);
		setSize(600,700);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		validate();
		setTitle("Gantry Robot Manager");
		add(fpa);
		validate();
		
		try{
			socket = new Socket("aludra.usc.edu", 8001);
			
			System.out.println("new socket");
			out = new ObjectOutputStream(socket.getOutputStream());
			in = new ObjectInputStream(socket.getInputStream());
			Integer n = 4;
			out.writeObject(n);
			System.out.println("Client stream");	
			Read read = new Read();
			new Thread(read).start();			
		}
		catch(Exception ex){
			ex.printStackTrace();
		}	
		while(true){

		}	
	}
	
	public class Read implements Runnable{
		public void run(){
			
			while(true){
				try{
					
					gantryRobotOrder = (BoxUpdater)in.readObject();
					System.out.println("grm received order");						
					fpa.gantry.addBox(new Box(660, 225,gantryRobotOrder.getNumOfParts(), gantryRobotOrder.getLane()));	
					System.out.println("lane no " + gantryRobotOrder.getLane());
				
				}
				catch(Exception ex){
					
				}
			}			
		}
	}
	
	
	

}
