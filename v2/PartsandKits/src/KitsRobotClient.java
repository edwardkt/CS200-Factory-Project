import java.awt.*;
import java.io.*;
import java.net.*;
import javax.swing.*;

import FactoryResources.BoxUpdater;
import FactoryResources.LanesUpdater;

import java.util.*;
import java.awt.event.*;

public class KitsRobotClient extends JFrame{
	private ArrayList<BoxUpdater> originalNests = new ArrayList<BoxUpdater>(); //These BoxUpdaters will hold the status for each nest
	
	FactoryState fs;
	Socket socket;
	ObjectOutputStream out;
	ObjectInputStream in;
	FactoryManagerAnimation fpa;
	
	public static void main(String[] args){
		new KitsRobotClient();
	}
	
	
	public KitsRobotClient(){		
	
		//jframe for factoryproductionmanager		
		fs = new FactoryState(); // object received
		fpa = new FactoryManagerAnimation();
		
		setVisible(true);
		setSize(700,700);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		validate();
		setTitle("Kits Robot Manager");
		add(fpa);
		
		validate();
		
		try{
			socket = new Socket("aludra.usc.edu", 8001);
			
			System.out.println("new socket");
			out = new ObjectOutputStream(socket.getOutputStream());
			in = new ObjectInputStream(socket.getInputStream());
			Integer n = 6;
			out.writeObject(n);
			System.out.println("Client stream");	
			
			originalNests = (ArrayList<BoxUpdater>)in.readObject(); // reads the initial status of the nests
			
			
			for(BoxUpdater update: originalNests){
				fpa.addPartsToNest(update.getLane(), update.getNumOfParts(), update.getFileName());
				//System.out.println("lane no " + update.getLane() + " number of items " + update.getNumOfParts() + " image file " + update.getFileName());
			}
			
			
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
					fpa.addPartsToNest(newUpdater.getPartsInNest().getLane(), newUpdater.getPartsInNest().getNumOfParts(), newUpdater.getPartsInNest().getFileName());
					
				}
				catch(Exception ex){
					ex.printStackTrace();
				}
			}			
		}
	}
	

}