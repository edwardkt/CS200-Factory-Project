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

	public FactoryManagerAnimation()
	{
		//this.client = client;
		kits = new ArrayList<Kit>();
		setLayout(new BorderLayout());    
		//add(managerPanel(), BorderLayout.PAGE_START);
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
		amount = new JLabel("Amount");
		kit = new JTextField(10);
		submit = new JButton("submit");
		combo = new JComboBox();
		combo.addItem("Select a Kit");		
		///editted from here by edwardddd
		//////////////////////////////////////////////////////////////////////////////////
		int test = 1234;
		int test2 = 12345;
		String tests = "hello";
		String tests2 = "hello2";
		//mannually adding new kits to the arraylist of kits
		
		//adds kits to combobox
		setComboBoxes();

		//end of editting by edwarddd
		/////////////////////////////////////////////////////////////////////////////////////			
		mainFrame = new JPanel();
		mainFrame.add(combo);
		mainFrame.add(amount);
		mainFrame.add(kit);
		mainFrame.add(submit);
		submit.addActionListener(this);
		revalidate();
		return mainFrame;	
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
		
		//Editted EDWARD STARTING HEREEEE
		//////////////////////////////////////////////////////////////////////////////////////
		
			if(ae.getSource() == submit)
			{
				
				//gets the input from the user and converts it to integer
				
				/*
				
				try{
					kitSelected = kits.get(combo.getSelectedIndex()-1);	
				}
				catch(Exception ex){
					JOptionPane.showMessageDialog(this, "must select a kit");
				}
			
				
				String s = kit.getText();
				try
				{   //gets integer and checks for valid input
					numberofKits = Integer.parseInt(s);
					if(numberofKits < 1 || numberofKits > 100)
						JOptionPane.showMessageDialog(this, "Please enter a number between 1 and 100");
				}
				catch(NumberFormatException the_input_string_isnt_an_integer)
				{
					// ask the user to try again
					JOptionPane.showMessageDialog(this, "Please enter a valid number");
				}
	
				kit.setText("");
	
			
								
				ArrayList<Integer> newOrder = new ArrayList<Integer>();				
				newOrder = kitSelected.getConfigAL();		
								
				for(int i=0; i<8; i++){
					if(newOrder.get(i)>0){
						box = new Box(1260,225,newOrder.get(i)*numberofKits, i);
						gantry.addBox(box);
					}
					
				}
				unitc.addOrder(newOrder,numberofKits);
				
				
				*/
				/*************test***************/
				
				
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