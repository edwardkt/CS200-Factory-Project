import java.awt.*;
import java.awt.geom.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.TreeMap;
import FactoryResources.*;
import Graphics.*;

@SuppressWarnings("serial")
public class FactoryManagerAnimation extends JPanel implements ActionListener {
	//FactoryProductionClient client;
	
	ArrayList<ArrayList <Integer>> kitOrdered = new ArrayList<ArrayList <Integer>>();
	ArrayList<Boolean> statesParts = new ArrayList<Boolean>();
	ArrayList<Boolean> statesLanes = new ArrayList<Boolean>();
	ArrayList<Boolean> statesKits = new ArrayList<Boolean>();
	ArrayList<Integer> totalUnits = new ArrayList<Integer>();
	ArrayList<Integer> totalKits = new ArrayList<Integer>();
	ArrayList<Integer> partsKitA = new ArrayList<Integer>();
	ArrayList<Integer> partsKitB = new ArrayList<Integer>();
	Rectangle2D.Double backgroundRectangle;
	JPanel panel,panel2,panel3,mainFrame;
	UnitB lane1, lane2, lane3, lane4;
	int palletx = 100, pallety = 100;
	UnitC unitc;
	
	JLabel chooseKit, amount, kitStatus;
	
	ArrayList<Kit> kits; // list of kits to choose from
	//production state
	int numberofKits; //number of kits to be produced
	Kit kitSelected; //kit to be produced	
	
	Image background;
	JComboBox combo;
	Object contents;
	JTextField kit;
	JButton submit;
	int count = 0;
	Pallet pallet;
	UnitA gantry;
	String s,s2;
	Box box;
	boolean inProduction = false; // so we know the factory is in production
	
	//GUI
	JPanel prg;
	JComboBox comboGUI,comboGUI1;
	JButton submitGUI,submitGUI1, placeBox, bringToConveyor, takePicture;
	////
	
	//BASKETS
	Image basket1, basket2, basket3, basket4, basket5, basket6,basket7,basket8;
	Boolean OneisFull=false, TwoisFull=false, ThreeisFull=false, FourisFull=false;
	Boolean FiveisFull=false, SixisFull=false, SevenisFull=false, EightisFull=false;
	ArrayList<Point> destinations;
	
	ArrayList<Nest> nests = new ArrayList<Nest>();
	
	TreeMap<Integer, PartGraph> partsInKit = new TreeMap<Integer, PartGraph>(); 
	
	

	public FactoryManagerAnimation()
	{
		
		//initialize nests
				for(int i=0; i<8; i++){
					int newY = 139 + i*47;
					if(i%2 == 0)
						newY = newY + 4;
					
					nests.add(new Nest(345, newY));
					
				}
				String fileName = "images/parts/ct_ball_purple.png";
				/*
				addPartsToNest(0,40,fileName);
				addPartsToNest(1,20,fileName);
				addPartsToNest(2,20,fileName);
				addPartsToNest(3,10,fileName);
				addPartsToNest(4,40,fileName);
				addPartsToNest(5,50,fileName);
				addPartsToNest(6,48,fileName);
				addPartsToNest(7,2,fileName);*/
		
		
		//////////////////////////////////
		
		//this.client = client;
		kits = new ArrayList<Kit>();
		setLayout(new BorderLayout());    
		add(managerPanel(), BorderLayout.PAGE_START);
		backgroundRectangle = new Rectangle2D.Double( 0, 0, 1200, 700 );
		background = Toolkit.getDefaultToolkit().createImage("images/xmas_kitClientBackground.fw.png");
		createLanes();
		unitc= new UnitC(lane1,lane2,lane3,lane4,nests);
		pallet = new Pallet(palletx, pallety);
		
		
		//createLanes();
		gantry = new UnitA(lane1,lane2,lane3,lane4);
		
		revalidate();
		new Timer( 5, this ).start();
	}//end constructor

	public void createLanes(){
		lane1 = new UnitB(400,150,"images/parts/ct_ball_blue.png","images/parts/ct_ball_blue.png");
		lane2 = new UnitB(400,250,"images/parts/ct_ball_blue.png","images/parts/ct_ball_blue.png");
		lane3 = new UnitB(400,350,"images/parts/ct_ball_blue.png","images/parts/ct_ball_blue.png");
		lane4 = new UnitB(400,450,"images/parts/ct_ball_blue.png","images/parts/ct_ball_blue.png");	
	}
	
	
	public void addPartsToNests(ArrayList<BoxUpdater> nestStatus){  //called from client when setting original parts

	}


	@SuppressWarnings({ "unchecked", "rawtypes" })
	public JPanel managerPanel(){ // creates a JPanel for the GUI and returns that panel
		//GUI STUFFFFFFFFFFFFFFFFF
		kitStatus = new JLabel("");
		prg = new JPanel();
		//comboGUI = new JComboBox();
		//comboGUI.addItem("Select a Part");
    	//comboGUI.setEditable(true);
		
		comboGUI1 = new JComboBox();
		comboGUI1.addItem("Select a Nest");
		comboGUI1.addItem("Nest1, Lane 1");
		comboGUI1.addItem("Nest1, Lane 2");
		comboGUI1.addItem("Nest2, Lane 1");
		comboGUI1.addItem("Nest2, Lane 2");
		comboGUI1.addItem("Nest3, Lane 1");
		comboGUI1.addItem("Nest3, Lane 2");
		comboGUI1.addItem("Nest4, Lane 1");
		comboGUI1.addItem("Nest4, Lane 2");
    	comboGUI1.setEditable(true);
		
		submitGUI = new JButton ("Go");
		submitGUI1 = new JButton ("Done");
		placeBox = new JButton("Place Kit Box");
		bringToConveyor = new JButton("Bring to Conveyor");
		takePicture = new JButton("Take picture");
		
		submitGUI.addActionListener(this);
		submitGUI1.addActionListener(this);
		placeBox.addActionListener(this);
		bringToConveyor.addActionListener(this);
		takePicture.addActionListener(this);
		
		//panels created to add onto jframe
		prg.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.weightx = 0.5;
		
		//Parts Robot GUI
		prg.add(new JLabel("Parts Robot"),gbc);
		gbc.gridx = 1;
		prg.add(comboGUI1,gbc);
		gbc.gridx = 2;
		gbc.weightx = 0.5;
		prg.add(submitGUI,gbc);
		gbc.gridx = 3;
		prg.add(kitStatus,gbc);
		
		//Kits Robot GUI
		gbc.gridx = 0;
		gbc.gridy = 3;
		prg.add(new JLabel("Kits Robot"),gbc);
		gbc.gridx = 1;
		prg.add(placeBox,gbc);
		gbc.gridx = 2;
		prg.add(submitGUI1,gbc);
		gbc.gridx = 3;
		prg.add(takePicture,gbc);
		gbc.gridx = 4;
		prg.add(bringToConveyor,gbc);

		////////////END OF GUI STUFF
		revalidate();
		return prg;	
	} // end managerPanel()
	
	
	public void setComboBoxes(){
		combo.removeAllItems();
		combo.addItem("select kit");
		for(int j = 0; j < kits.size(); j++)
		{
			combo.addItem(kits.get(j).getName());
			System.out.println(kits.get(j).getName());
		}
	}

	public void paint(Graphics g)
	{
		super.paint(g);
		Graphics2D g2 = (Graphics2D)g;
		
		g.drawImage(background, 0, 50, null);
		
		for(Nest nest: nests){				
				nest.drawNest(g2);	
		}
		unitc.updateImage(g2);
	
	}

	public void actionPerformed(ActionEvent ae) 
	{
		repaint();
		
			if(ae.getSource() == submit)
			{				
				for(int i=0; i<4; i++){
					box = new Box(1260,225,4, i);
					gantry.addBox(box);
				}
								
			}
			
			else if(ae.getSource() == submitGUI)
			{
				
				
				if((comboGUI1.getSelectedItem().toString()).equals("Nest1, Lane 1"))
				{
					System.out.println("NEST1LANE1");
					unitc.setIndex(1);
					unitc.updatePartRobot(340,140);
					
					}
				else if((comboGUI1.getSelectedItem().toString()).equals("Nest1, Lane 2"))
				{
					System.out.println("NEST1LANE2");
					unitc.setIndex(2);
					unitc.updatePartRobot(340,172);
					
				}
				else if((comboGUI1.getSelectedItem().toString()).equals("Nest2, Lane 1"))
				{
					System.out.println("NEST2LANE1");
					unitc.setIndex(3);
					unitc.updatePartRobot(340,240);
			
				}
				else if((comboGUI1.getSelectedItem().toString()).equals("Nest2, Lane 2"))
				{
					System.out.println("NEST2LANE2");
					unitc.setIndex(4);
					unitc.updatePartRobot(340,272);
				
				}
				else if((comboGUI1.getSelectedItem().toString()).equals("Nest3, Lane 1"))
				{
					System.out.println("NEST3LANE1");
					unitc.setIndex(5);
					unitc.updatePartRobot(340,340);
				
				}
				else if((comboGUI1.getSelectedItem().toString()).equals("Nest3, Lane 2"))
				{
					System.out.println("NEST3LANE2");
					unitc.setIndex(6);
					unitc.updatePartRobot(340,372);
				
				}
				else if((comboGUI1.getSelectedItem().toString()).equals("Nest4, Lane 1"))
				{
					System.out.println("NEST4LANE1");
					unitc.setIndex(7);
					unitc.updatePartRobot(340,440);
	
				}
				else if((comboGUI1.getSelectedItem().toString()).equals("Nest4, Lane 2"))
				{
					System.out.println("NEST4LANE2");
					unitc.setIndex(8);
					unitc.updatePartRobot(340,472);
	
				}
			}
			else if(ae.getSource() == submitGUI1)
			{
				unitc.inspectKit();
			}
			
			else if(ae.getSource() == placeBox)
			{
				unitc.placeBox();
			}
			else if(ae.getSource() == bringToConveyor)
			{
				unitc.bringToConveyor();
			}
			else if(ae.getSource() == takePicture)
			{
				unitc.takePicture();
				if(unitc.kitStat()==true)
					kitStatus.setText("BAD PART");
				else
					kitStatus.setText("GOOD PART");
				revalidate();
			}

	}
	
	//method for adding parts to a nest
	public void addPartsToNest(int nestNo, int numberOfParts, String fileName){		
		for(int j=0; j<numberOfParts; j++){
			nests.get(nestNo).addPart(fileName);
		}
	}
	
	
	//getters
	public ArrayList<Kit> getList(){
		return kits;
	}
	
	//setters
	public void setList(ArrayList<Kit> list){
		kits = list;		
	}
	
	
	
}
//END OF EDITTING BY EDWARDDDD
///////////////////////////////////////////////////////////////////////////////////////////