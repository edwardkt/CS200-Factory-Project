import java.io.*;
import java.net.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import FactoryResources.*;

public class Server extends JFrame implements Serializable, ActionListener {
	private ObjectOutputStream outputToClient;
	private ObjectInputStream inputFromClient;
	private PrintWriter pw;
	private BufferedReader br;
	private ServerSocket serverSocket;
	
	//array of clients	
	private TreeMap<Integer, HandleAClient> clients = new TreeMap<Integer, HandleAClient>();
	
		
	//GUI	
	JMenu options;
	JMenuItem states, takePicture;
	MainControlPanel mainControlPanel;
	
	//lists of parts and kits
	private ArrayList<Part> partsList = new ArrayList<Part>();
	private ArrayList<Kit> kitsList = new ArrayList<Kit>();	
	
	FactoryState factoryState; //this is the object that writes commands
	
	public static void main(String[] args){
		new Server();
	}
	
	public Server(){
		
		
		//initialize factory state
		factoryState = new FactoryState();		
		
		//start the server
		try{
			serverSocket = new ServerSocket(8001);
			System.out.println("Server started");
		} 
		catch(Exception ex){ 
			ex.printStackTrace();
			System.exit(0);
		}
		

		//GUI stuff
		setLayout(new BorderLayout());
		//menu stuff
		options = new JMenu("options");
		states = new JMenuItem("factory states"); //menu item
		states.addActionListener(this); // add action listener
		takePicture = new JMenuItem("take a picture"); // menu item
		takePicture.addActionListener(this); // add action listener
		//add items to munu
		JMenuBar menubar = new JMenuBar();
		options.add(states);
		options.add(takePicture);
		menubar.add(options);
		//add menu to panel
		add(menubar,BorderLayout.NORTH);
		//initilize panel
		mainControlPanel = new MainControlPanel(this);
		this.add(mainControlPanel, BorderLayout.CENTER);		
		//initialize Jframe
		setSize(500,300);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		
		
		
		//wait for clients
		try{			
			for(int i=0; i<8; i++){				
				Socket socket = serverSocket.accept();
				System.out.println("Got client");
				//create an input stream and an output stream from the socket
				outputToClient = new ObjectOutputStream(socket.getOutputStream());
				inputFromClient = new ObjectInputStream(socket.getInputStream());				
				
				try{
				
					Integer clientNo = (Integer)inputFromClient.readObject();
					System.out.println((Integer)clientNo);
					
					
					switch(clientNo){
					
					case 1: PartsManagerHandler partsManHandler = new PartsManagerHandler(socket, this);
							clients.put(clientNo,partsManHandler);
							new Thread(partsManHandler).start();		
							break;
							
					case 2: KitsManagerHandler kitsManHandler = new KitsManagerHandler(socket, this);
							clients.put(clientNo,kitsManHandler);
							new Thread(kitsManHandler).start();		
							break;						
							
					case 3: FactoryProductionHandler factoryProdHandler = new FactoryProductionHandler(socket, this);
							clients.put(clientNo,factoryProdHandler);
							new Thread(factoryProdHandler).start();		
							break;		
							
							
					case 4: GantryRobotManHandler gantryRobotHandler = new GantryRobotManHandler(socket, this);
							clients.put(clientNo,gantryRobotHandler);
							new Thread(gantryRobotHandler).start();		
							break;
					
					case 5: LaneHandler lanehandler = new LaneHandler(socket,this);
							clients.put(clientNo, lanehandler);
							new Thread(lanehandler).start();
							break;
							
					case 6: PartsRobotHandler partsrobothandler = new PartsRobotHandler(socket,this);
							clients.put(clientNo, partsrobothandler);
							new Thread(partsrobothandler).start();
							break;
							
							
					}
					
				}
				catch(Exception ex){
					ex.printStackTrace();
				}						
			}	
		}

		catch(IOException ex){
			ex.printStackTrace();
		}
		
	}
	
	//methods to send objects
	public void sendFactoryState(){
		if(clients.containsKey(3)){
			clients.get(3).sendFactoryState();
			System.out.println("sent factory state");
		}		
	}
	
	public void sendPartsList(){ //called by parts manager to set parts
		try{
			outputToClient.writeObject(partsList);
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	public void sendKitsList(){ // called by kits manager handler to send kits to factory production manager
		try{
			ArrayList<Kit> newKits = new ArrayList<Kit>();
			for(int i=0; i<kitsList.size(); i++){
				newKits.add(kitsList.get(i));
			}
			outputToClient.writeObject(newKits);
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
		
	}
	public void sendOrderToGRM(BoxUpdater newOrder){ //called from fpm handler
		if(clients.containsKey(4)){
			try{
				clients.get(4).sendGROrder(newOrder);
			}
			catch(Exception ex){
				ex.printStackTrace();				
			}
		}
	}
	/*
	public void sendOrderToGRM(){ //call method from handler
		if(clients.containsKey(4)){
			try{				
				clients.get(4).sendGROrder(boxes);
			}
			catch(Exception ex){
				ex.printStackTrace();
			}
		}
	}
	*/
	
	
	
	//setters
	public void setPartsList(ArrayList<Part> parts){
		try{
			partsList = parts;
			clients.get(2).getOutput().writeObject(parts);
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	public void setKitsList(ArrayList<Kit> kits){
		kitsList = kits;
	}
	

	

	
	//getters
	public ObjectOutputStream getOutput(){
		return outputToClient;
	}
	
	public ObjectInputStream getIput(){
		return inputFromClient;
	}
	
	
	public ArrayList<Part> getPartsList(){
		return partsList;
	}
	
	public ArrayList<Kit> getKitsList(){
		return kitsList;
	}	
		
	
	public void actionPerformed(ActionEvent ae){//for menu options: states and takePicture
		if(ae.getSource() == states){  //call method from main control panel
			mainControlPanel.showStatesPanel();
		}
		
		if(ae.getSource() == takePicture){ //call method from main control panel
			mainControlPanel.showCameraPanel();
		}
		
	}

}
