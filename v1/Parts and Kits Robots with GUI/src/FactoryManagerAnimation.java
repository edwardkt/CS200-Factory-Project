import java.awt.*;
import java.awt.geom.*;

import javax.swing.*;

import java.awt.event.*;
import java.io.IOException;
import java.util.ArrayList;
import FactoryResources.*;

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
	
	JLabel chooseKit, amount;
	
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
	JButton submitGUI,submitGUI1, placeBox;
	////
	
	//BASKETS
	Image basket1, basket2, basket3, basket4, basket5, basket6,basket7,basket8;
	Boolean OneisFull=false, TwoisFull=false, ThreeisFull=false, FourisFull=false;
	Boolean FiveisFull=false, SixisFull=false, SevenisFull=false, EightisFull=false;
	ArrayList<Point> destinations;

	public FactoryManagerAnimation()
	{
		//////////////////////////BASKET
		basket1 = Toolkit.getDefaultToolkit().createImage("images/basket1.png");
		basket2 = Toolkit.getDefaultToolkit().createImage("images/basket2.png");
		
		basket3 = Toolkit.getDefaultToolkit().createImage("images/basket1.png");
		basket4 = Toolkit.getDefaultToolkit().createImage("images/basket2.png");
		
		basket5 = Toolkit.getDefaultToolkit().createImage("images/basket1.png");
		basket6 = Toolkit.getDefaultToolkit().createImage("images/basket2.png");
		
		basket7 = Toolkit.getDefaultToolkit().createImage("images/basket1.png");
		basket8 = Toolkit.getDefaultToolkit().createImage("images/basket2.png");
		//////////////////////////////////
		
		//this.client = client;
		kits = new ArrayList<Kit>();
		setLayout(new BorderLayout());    
		add(managerPanel(), BorderLayout.PAGE_START);
		backgroundRectangle = new Rectangle2D.Double( 0, 0, 1200, 700 );
		background = Toolkit.getDefaultToolkit().createImage("images/snowbg.jpg");
		createLanes();
		unitc= new UnitC(lane1,lane2,lane3,lane4);
		pallet = new Pallet(palletx, pallety);
		
		
		//createLanes();
		gantry = new UnitA(lane1,lane2,lane3,lane4);

		//setKitParts();//defines kitA and kitB...test scenario
		revalidate();
		new Timer( 5, this ).start();
	}//end constructor

	public void createLanes(){
		lane1 = new UnitB(400,150,"images/parts/ct_ball_blue.png","images/parts/ct_ball_blue.png");
		lane2 = new UnitB(400,250,"images/parts/ct_ball_blue.png","images/parts/ct_ball_blue.png");
		lane3 = new UnitB(400,350,"images/parts/ct_ball_blue.png","images/parts/ct_ball_blue.png");
		lane4 = new UnitB(400,450,"images/parts/ct_ball_blue.png","images/parts/ct_ball_blue.png");	
	}


	@SuppressWarnings({ "unchecked", "rawtypes" })
	public JPanel managerPanel(){ // creates a JPanel for the GUI and returns that panel
		//GUI STUFFFFFFFFFFFFFFFFF
		
		prg = new JPanel();
		comboGUI = new JComboBox();
		comboGUI.addItem("Select a Part");
    	comboGUI.setEditable(true);
		
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
		submitGUI1 = new JButton ("Bring Finished Kit to the Conveyor Belt");
		placeBox = new JButton("Place Kit Box");
		
		submitGUI.addActionListener(this);
		submitGUI1.addActionListener(this);
		placeBox.addActionListener(this);
		
		//panels created to add onto jframe
		prg.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.weightx = 0.5;
		
		//Parts Robot GUI
		prg.add(new JLabel("Parts Robot"),gbc);
		gbc.gridx = 1;
		prg.add(comboGUI,gbc);
		gbc.gridx = 2;
		prg.add(new JLabel(" From "),gbc);
		gbc.gridx = 3;
		prg.add(comboGUI1,gbc);
		gbc.gridx = 4;
		gbc.weightx = 0.5;
		prg.add(submitGUI,gbc);
		
		//Kits Robot GUI
		gbc.gridx = 0;
		gbc.gridy = 3;
		prg.add(new JLabel("Kits Robot"),gbc);
		gbc.gridx = 1;
		prg.add(placeBox,gbc);
		gbc.gridx = 2;
		prg.add(submitGUI1,gbc);

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
		g2.drawImage(basket1, 345, 150, 60, 32, null);
		g2.drawImage(basket2, 345, 182, 60, 32, null);
		
		g2.drawImage(basket3, 345, 250, 60, 32, null);
		g2.drawImage(basket4, 345, 282, 60, 32, null);
		
		g2.drawImage(basket5, 345, 350, 60, 32, null);
		g2.drawImage(basket6, 345, 382, 60, 32, null);
		
		g2.drawImage(basket7, 345, 450, 60, 32, null);
		g2.drawImage(basket8, 345, 482, 60, 32, null);
		unitc.updateImage(g2);
		
		/*lane1.UpdateImage(g2);
		lane2.UpdateImage(g2);
		lane3.UpdateImage(g2);
		lane4.UpdateImage(g2);
		gantry.updateImage(g2);*/
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
				
				
				ArrayList<Box> boxes = gantry.getBoxes();
				ArrayList<BoxUpdater> boxList = new ArrayList<BoxUpdater>();
				for(int i=0; i<boxes.size(); i++){
					int num = boxes.get(i).getNumOfItems();
					int lane = boxes.get(i).getLane();
					new BoxUpdater();
					
					boxList.add(new BoxUpdater(num, lane));
				}
				//client.sendGantryRobotOrder(boxList);				
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
				unitc.updateKitRobot(210,240);
			}
			
			else if(ae.getSource() == placeBox)
			{
				unitc.placeBox();
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