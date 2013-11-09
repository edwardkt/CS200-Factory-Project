import java.awt.*;
import java.awt.geom.*;

import javax.swing.*;

import java.awt.event.*;
import java.util.ArrayList;
import FactoryResources.*;

@SuppressWarnings("serial")
public class FactoryManagerAnimation extends JPanel implements ActionListener {
	GantryRobotClient client;
	
	ArrayList<ArrayList <Integer>> kitOrdered = new ArrayList<ArrayList <Integer>>();
	ArrayList<Boolean> statesParts = new ArrayList<Boolean>();
	ArrayList<Boolean> statesLanes = new ArrayList<Boolean>();
	ArrayList<Boolean> statesKits = new ArrayList<Boolean>();
	ArrayList<Integer> totalUnits = new ArrayList<Integer>();
	ArrayList<Integer> totalKits = new ArrayList<Integer>();
	ArrayList<Integer> partsKitA = new ArrayList<Integer>();
	ArrayList<Integer> partsKitB = new ArrayList<Integer>();
	Rectangle2D.Double backgroundRectangle;
	Rectangle2D.Double feeder1,feeder2,feeder3,feeder4;
	JPanel panel,panel2,panel3,mainFrame;
	UnitB lane1, lane2, lane3, lane4;
	int palletx = 100, pallety = 100;
	UnitC unitc;
	
	JLabel chooseKit, amount;
	ArrayList<String> kits;
	
	int numberofKits;
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

	public FactoryManagerAnimation(GantryRobotClient client)
	{
		this.client = client;
		feeder1 = new Rectangle2D.Double(230,150,50,65);
		feeder2 = new Rectangle2D.Double(230,250,50,65);
		feeder3 = new Rectangle2D.Double(230,350,50,65);
		feeder4 = new Rectangle2D.Double(230,450,50,65);
		
		kits = new ArrayList<String>();
		setLayout(new BorderLayout());    
		//add(managerPanel(), BorderLayout.PAGE_START);
		backgroundRectangle = new Rectangle2D.Double( 0, 0, 1200, 700 );
		background = Toolkit.getDefaultToolkit().createImage("images/snowbg.jpg");
		unitc= new UnitC();
		pallet = new Pallet(palletx, pallety);
		
			
		createLanes();
		gantry = new UnitA(lane1,lane2,lane3,lane4,client);
		//gantry.addBox(new Box(660,225,20, 3));
		//setKitParts();//defines kitA and kitB...test scenario
		revalidate();
		new Timer( 5, this ).start();
	}

	
	public void createLanes(){
		lane1 = new UnitB(400,150,"images/parts/ct_ball_blue.png","images/parts/ct_ball_blue.png");
		lane2 = new UnitB(400,250,"images/parts/ct_ball_blue.png","images/parts/ct_ball_blue.png");
		lane3 = new UnitB(400,350,"images/parts/ct_ball_blue.png","images/parts/ct_ball_blue.png");
		lane4 = new UnitB(400,450,"images/parts/ct_ball_blue.png","images/parts/ct_ball_blue.png");

	
	}


	@SuppressWarnings({ "unchecked", "rawtypes" })
	public JPanel managerPanel(){
		amount = new JLabel("Amount");
		kit = new JTextField(10);
		submit = new JButton("submit");
		combo = new JComboBox();
		combo.addItem("Select a Kit");
		combo.addItem("Kit A");
		combo.addItem("Kit B");
		combo.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ae)
			{
				System.out.println("ComboBox: Selected index = " + combo.getSelectedIndex() + " Selected Item = " + combo.getSelectedItem());
				contents = combo.getSelectedItem();
				s2 = contents.toString();
			}
		});
		mainFrame = new JPanel();
		mainFrame.add(combo);
		mainFrame.add(amount);
		mainFrame.add(kit);
		mainFrame.add(submit);
		submit.addActionListener(this);
		revalidate();
		return mainFrame;	
	}

	public void paint(Graphics g)
	{
		super.paint(g);
		Graphics2D g2 = (Graphics2D)g;
		g.drawImage(background, 0, 50, null);
		unitc.updateImage(g2);
		g2.fill(feeder1);
		g2.fill(feeder2);
		g2.fill(feeder3);
		g2.fill(feeder4);
		lane1.UpdateImage(g2);
		lane2.UpdateImage(g2);
		lane3.UpdateImage(g2);
		lane4.UpdateImage(g2);
		gantry.updateImage(g2);	
	}

	public void actionPerformed(ActionEvent ae) 
	{
		// TODO Auto-generated method stub
		repaint();
	
	}
}
