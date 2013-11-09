import java.awt.*;
import java.io.*;
import java.net.*;
import javax.swing.*;
import java.util.*;

import FactoryResources.*;

public class LaneClient extends JFrame{
	
	
	private ArrayList<BoxUpdater> originalNests = new ArrayList<BoxUpdater>(); //These BoxUpdaters will hold the status for each nest
	private FactoryState fs;
	private Socket socket;
	private ObjectOutputStream out;
	private ObjectInputStream in;
	private FactoryManagerAnimation fpa;

	
	public static void main(String[] args){
		new LaneClient();
	}
	
	
	public LaneClient(){
		
		//elements in this class

		fs = new FactoryState(); // object received
		fpa = new FactoryManagerAnimation();
		
		//jframe for factoryproductionmanager

		setVisible(true);
		setSize(900,700);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		validate();
		setTitle("Lane Manager");
		add(fpa);
		validate();
		
		try{
			socket = new Socket("aludra.usc.edu", 8001);
			
			System.out.println("new socket");
			out = new ObjectOutputStream(socket.getOutputStream());
			in = new ObjectInputStream(socket.getInputStream());
			Integer n = 5;
			out.writeObject(n);
			System.out.println("Client stream");
			originalNests = (ArrayList<BoxUpdater>)in.readObject(); // reads the initial status of the nests
			for(BoxUpdater nest: originalNests)
				System.out.println(nest.getLane() + ", " + nest.getNumOfParts());
			
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
					System.out.println("in run method of read class");
					LanesUpdater newUpdater = new LanesUpdater();
					newUpdater = (LanesUpdater)in.readObject();
					System.out.println("lane " + newUpdater.getPartsInLane().getLane() + " received " + newUpdater.getPartsInLane().getNumOfParts() + " parts");
					
					fpa.setPartsInLanes(newUpdater.getPartsInLane().getLane(), newUpdater.getPartsInLane().getNumOfParts(), newUpdater.getPartsInLane().getFileName());
					
					
				}
				catch(Exception ex){
					
				}
			}			
		}
	}
	
	
	

}