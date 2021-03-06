import java.awt.*;
import java.awt.geom.*;

import javax.swing.*;

import java.awt.event.*;
import java.io.IOException;
import java.util.ArrayList;
import FactoryResources.*;

@SuppressWarnings("serial")
public class FactoryManagerAnimation extends JPanel implements ActionListener {
	FactoryProductionClient client;

	ArrayList<ArrayList <Integer>> kitOrdered = new ArrayList<ArrayList <Integer>>();
	ArrayList<Boolean> statesParts = new ArrayList<Boolean>();
	ArrayList<Boolean> statesLanes = new ArrayList<Boolean>();
	ArrayList<Boolean> statesKits = new ArrayList<Boolean>();
	ArrayList<Integer> totalUnits = new ArrayList<Integer>();
	ArrayList<Integer> totalKits = new ArrayList<Integer>();
	ArrayList<String> partsKitA = new ArrayList<String>();
	ArrayList<String> partsKitB = new ArrayList<String>();
	Rectangle2D.Double backgroundRectangle;
	JPanel panel,panel2,panel3,mainFrame;
	UnitB lane1, lane2, lane3, lane4;
	int palletx = 100, pallety = 100;
	UnitC unitc;
	int counter=0;
	JLabel chooseKit, amount;

	ArrayList<Kit> kits; // list of kits to choose from
	//production state
	int numberofKits; //number of kits to be produced
	Kit kitSelected; //kit to be produced	

	Image background;
	Image background2;
	Image background3;
	Image background4;
	Image background5;
	Image background6;

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

	public FactoryManagerAnimation(FactoryProductionClient client)
	{
		this.client = client;
		kits = new ArrayList<Kit>();
		setLayout(new BorderLayout());    
		add(managerPanel(), BorderLayout.PAGE_START);
		backgroundRectangle = new Rectangle2D.Double( 0, 0, 1200, 700 );
		background = Toolkit.getDefaultToolkit().createImage("images/xmas_background.fw.png");
		background2 = Toolkit.getDefaultToolkit().createImage("images/xmas_background2.fw.png");
		background3 = Toolkit.getDefaultToolkit().createImage("images/xmas_background3.fw.png");
		background4 = Toolkit.getDefaultToolkit().createImage("images/xmas_background4.fw.png");
		background5 = Toolkit.getDefaultToolkit().createImage("images/xmas_background5.fw.png");
		background6 = Toolkit.getDefaultToolkit().createImage("images/xmas_background6.fw.png");
		createLanes();
		unitc= new UnitC(lane1,lane2,lane3,lane4);
		pallet = new Pallet(palletx, pallety);


		gantry = new UnitA(lane1,lane2,lane3,lane4,client);

		//setKitParts();//defines kitA and kitB...test scenario
		revalidate();
		new Timer( 5, this ).start();
	}//end constructor

	public void createLanes(){
		lane1 = new UnitB(400,150);
		lane2 = new UnitB(400,250);
		lane3 = new UnitB(400,350);
		lane4 = new UnitB(400,450);	
	}


	@SuppressWarnings({ "unchecked", "rawtypes" })
	public JPanel managerPanel(){ // creates a JPanel for the GUI and returns that panel
		amount = new JLabel("Amount");
		kit = new JTextField(10);
		submit = new JButton("submit");
		combo = new JComboBox();
		//combo.addItem("Select a Kit");		
		///editted from here by edwardddd
		//////////////////////////////////////////////////////////////////////////////////
	
		Kit hardcodeKit = new Kit("HardCodeKit",8,"no");
		hardcodeKit.updateConfig0("images/parts/ct_ball_red.png");
		hardcodeKit.updateConfig1("images/parts/ct_ball_blue.png");
		hardcodeKit.updateConfig2("images/parts/ct_ball_green.png");
		hardcodeKit.updateConfig3("images/parts/ct_ball_red.png");
		hardcodeKit.updateConfig4("images/parts/ct_ball_blue.png");
		hardcodeKit.updateConfig5("images/parts/ct_ball_purple.png");
		hardcodeKit.updateConfig6("images/parts/ct_ball_blue.png");
		hardcodeKit.updateConfig7("images/parts/ct_ball_blue.png");
		//adds kits to combobox
			kits.add(hardcodeKit);
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
		if(counter<50)
			g.drawImage(background, 0, 50, null);
			else if(counter<100)
				g.drawImage(background2, 0, 50, null);
			else if(counter<150)
				g.drawImage(background3, 0, 50, null);
			else if(counter<200)
				g.drawImage(background4, 0, 50, null);
			else if(counter<250)
				g.drawImage(background5, 0, 50, null);
			else if(counter<300)
				g.drawImage(background6, 0, 50, null);
			else if(counter==300){
				counter=0;}
		
		lane1.UpdateImage(g2);
		lane2.UpdateImage(g2);
		lane3.UpdateImage(g2);
		lane4.UpdateImage(g2);
		gantry.updateImage(g2);
		unitc.updateImage(g2);
		counter++;
	}

	public void actionPerformed(ActionEvent ae) 
	{
		repaint();

		//Editted EDWARD STARTING HEREEEE
		//////////////////////////////////////////////////////////////////////////////////////

		if(ae.getSource() == submit)
		{

			//gets the input from the user and converts it to integer



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
					JOptionPane.showMessageDialog(this, "Please enter a number 1 through  100");
			}
			catch(NumberFormatException the_input_string_isnt_an_integer)
			{
				// ask the user to try again
				JOptionPane.showMessageDialog(this, "Please enter a valid number");
			}

			kit.setText("");



			ArrayList<String> newOrder = new ArrayList<String>();
			String[] temp = kitSelected.getImageConfig();
			for(int i = 0; i <8;i++){
				newOrder.add(temp[i]);
			}	
			unitc.addOrder(newOrder,numberofKits);
			
			for(int i=0; i<8; i++){
				int counter = 0;
				if(!newOrder.get(i).equals("")){
						String currentItem = new String(newOrder.get(i));
						
					for(int j = i;j<9;j++){
						
						
						if(j<8){
							if(currentItem.equals(newOrder.get(j))){
								newOrder.set(j,new String(""));
								counter++;
							}
						}
						else{
							box = new Box(1260,225,counter*numberofKits,0, currentItem);
							counter = 0;
							gantry.addBox(box);
						}

					}
				}

			}
			





		

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