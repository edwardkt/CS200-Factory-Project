import java.awt.*;
import java.io.*;
import java.net.*;
import javax.swing.*;
import java.util.*;

public class KitsRobotClient extends JFrame{
	//ArrayList<Integer> gantryRobotOrder = new ArrayList<Integer>();
	
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
		setSize(600,600);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		validate();
		setTitle("Kits Robot Manager");
		add(fpa);
		validate();
		
		try{
			socket = new Socket("localhost", 8000);
			
			System.out.println("new socket");
			out = new ObjectOutputStream(socket.getOutputStream());
			in = new ObjectInputStream(socket.getInputStream());
			Integer n = 7;
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
				/*try{
					gantryRobotOrder = (ArrayList<Integer>)in.readObject();
					for( int i=0; i<gantryRobotOrder.size(); i++){
						System.out.println(gantryRobotOrder.get(i));
					}
					
				}
				catch(Exception ex){
					
				}*/
			}			
		}
	}
	
	
	

}