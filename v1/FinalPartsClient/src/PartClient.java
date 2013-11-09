import java.io.*;
import java.net.*;
import javax.swing.*;
import java.util.*;
import FactoryResources.*;

public class PartClient extends JFrame{
	Socket socket;
	ObjectOutputStream out;
	ObjectInputStream in;
	JButton button;
	
	public static void main(String[] args){
		new PartClient(); 
	}
	public PartClient(){
		
		PartsManager parts = new PartsManager(this);
		setVisible(true);
		setSize(800,400);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		add(parts);
		
		try{
			socket = new Socket("aludra.usc.edu", 8000);
			
			System.out.println("new socket");
			out = new ObjectOutputStream(socket.getOutputStream());
			in = new ObjectInputStream(socket.getInputStream());
			Integer n = 1;
			out.writeObject(n);
			System.out.println("Client stream");			
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
		
		
		while(true){
			
		}		
	}
	
	public void sendPartsList(ArrayList<Part> parts){
		try{
			out.writeObject(parts);
			
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
		
	}
	

}
