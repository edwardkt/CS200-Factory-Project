
import java.io.*;
import java.net.*;
import java.util.ArrayList;
import javax.swing.*;
import FactoryResources.*;

public class KitManagerClient extends JFrame{
	KitsManager kitsManager;
	
	ArrayList<Part> parts = new ArrayList<Part>();
	ArrayList<Kit> kits = new ArrayList<Kit>();

	Socket socket;
	ObjectOutputStream out;
	ObjectInputStream in;
	
	
	public static void main(String[] args){
		new KitManagerClient();
	}
	
	public KitManagerClient(){	
		
		kitsManager = new KitsManager(this);
		setVisible(true);
		setSize(800,600);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		add(kitsManager);
		
		try{
			socket = new Socket("aludra.usc.edu", 8001);
			//initializing
			System.out.println("new socket");
			out = new ObjectOutputStream(socket.getOutputStream());
			in = new ObjectInputStream(socket.getInputStream());
			Integer n = 2;
			out.writeObject(n);
			System.out.println("Client stream");			
			Read read = new Read();
			new Thread(read).start();
			
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
		
	}
	
	
	public class Read implements Runnable{		
		public void run(){
			System.out.println("in run method of Read");
			while(true){
				try{
					ArrayList<Part> parts = (ArrayList<Part>)in.readObject();
					kitsManager.setPartsList(parts);
					if(parts.size() == 0){
						System.out.println("Nothing in list");
					}
					else
						
					System.out.println("received parts");		
				}
				catch(Exception ex){
					ex.printStackTrace();
				}				
			}
		}
	}
	
	
	
	public void sendKits(ArrayList<Kit> kits){
		try{
			
			ArrayList<Kit> newKits = new ArrayList<Kit>();
			for(int i=0; i<kits.size(); i++){
				newKits.add(kits.get(i));
			}
			out.writeObject(newKits);
			System.out.println("sent kit");
		}
		catch(Exception ex){
			
		}
		
	}
	
	
	
}


