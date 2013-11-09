import java.awt.*;
import java.awt.geom.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;
import FactoryResources.*;

@SuppressWarnings("serial")
public class FactoryManagerAnimation extends JPanel implements ActionListener {
	
	ArrayList<ArrayList <Integer>> kitOrdered = new ArrayList<ArrayList <Integer>>();
	ArrayList<Boolean> statesParts = new ArrayList<Boolean>();
	ArrayList<Boolean> statesLanes = new ArrayList<Boolean>();
	ArrayList<Boolean> statesKits = new ArrayList<Boolean>();
	ArrayList<Integer> totalUnits = new ArrayList<Integer>();
	ArrayList<Integer> totalKits = new ArrayList<Integer>();
	ArrayList<Integer> partsKitA = new ArrayList<Integer>();
	ArrayList<Integer> partsKitB = new ArrayList<Integer>();
	Rectangle2D.Double backgroundRectangle;
	UnitB[] lanes = new UnitB[4]; // ARRAY LIST OF UNIT B
	int palletx = 100, pallety = 100;
	int count = 0;
	Pallet pallet;
	String s,s2;
	Box box;
	Image background;
	boolean takingPicture = false;
	int counter = 0;
	
	//elements in kit
	JButton submit, laneOn,laneOff,vibHigh,vibLow,takeAPicture;
	JPanel mainFrame;
	JLabel numberLabel, imageLabel, laneNoLabel, picStatus ;
	JTextField numberTF, imageTF, laneTF;
	JComboBox combo,combo1;
	boolean isLaneOff = false;
	boolean isLaneHigh = false;


	public FactoryManagerAnimation()  // constructor
	{
		setLayout(new BorderLayout());    
		add(managerPanel(), BorderLayout.NORTH);
	//	backgroundRectangle = new Rectangle2D.Double( 0, 0, 1200, 700 );
		background = Toolkit.getDefaultToolkit().createImage("images/xmas_laneClientBackground.fw.png");
		
		pallet = new Pallet(palletx, pallety);
		createLanes();
		revalidate();
		new Timer( 10, this ).start();
	} // end constructor
	
	/////////methods used in constructory//////////////////
	public void createLanes(){
		for(int i=0; i < lanes.length; i++){
			lanes[i] = new UnitB(250, 120 + i*120,this);
		}
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public JPanel managerPanel(){ // This function creates and returns a JPanel
		picStatus = new JLabel("");
		takeAPicture = new JButton("Take a Picture");
		submit = new JButton("Submit");
		laneOn = new JButton("Lane On");
		laneOff = new JButton("Lane Off");
		vibHigh = new JButton("High Vibration");
		vibLow = new JButton("Normal Vibration");
		combo = new JComboBox();
		combo1 = new JComboBox();
		combo.addItem("Select Lanes");
		combo.addItem("Lane 1");
		combo.addItem("Lane 2");
		combo.addItem("Lane 3");
		combo.addItem("Lane 4");
		combo.addItem("Lane 5");
		combo.addItem("Lane 6");
		combo.addItem("Lane 7");
		combo.addItem("Lane 8");
		combo.setEditable(true);
		/*
		combo1.addItem("Select Lanes");
		combo1.addItem("Lane 1");
		combo1.addItem("Lane 2");
		combo1.addItem("Lane 3");
		combo1.addItem("Lane 4");
		combo1.addItem("Lane 5");
		combo1.addItem("Lane 6");
		combo1.addItem("Lane 7");
		combo1.addItem("Lane 8");
		combo1.setEditable(true);
		*/
		
		numberLabel  = new JLabel("Enter numbers of parts:");
		numberTF = new JTextField(10);
		imageLabel = new JLabel("Name of image file");
		imageTF = new JTextField(10);
		laneNoLabel = new JLabel("Lane number");
		laneTF = new JTextField(10);

		mainFrame = new JPanel();
		mainFrame.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridx = 0;	
		gbc.weightx = 0.5;
		
		mainFrame.add(numberLabel,gbc);
		gbc.gridx = 1;
		mainFrame.add(numberTF,gbc);
		//mainFrame.add(imageLabel,gbc);
		//mainFrame.add(imageTF,gbc);
		gbc.gridx = 2;
		mainFrame.add(laneNoLabel,gbc);
		gbc.gridx = 3;
		//mainFrame.add(combo,gbc);
		mainFrame.add(laneTF,gbc);
		gbc.gridx = 4;
		mainFrame.add(submit,gbc);
		gbc.gridx = 5;
		mainFrame.add(picStatus,gbc);
		submit.addActionListener(this);
		
		gbc.gridy = 1;
		
		gbc.gridx =0;
		mainFrame.add(combo,gbc);
		gbc.gridx = 1;
		mainFrame.add(laneOn,gbc);
		gbc.gridx = 2;
		mainFrame.add(laneOff,gbc);
		laneOn.addActionListener(this);
		laneOff.addActionListener(this);
		
		//gbc.gridx = 4;
		//mainFrame.add(combo1,gbc);
		gbc.gridx = 3;
		mainFrame.add(vibHigh,gbc);
		gbc.gridx = 4;
		mainFrame.add(vibLow,gbc);
		gbc.gridx = 5;
		mainFrame.add(takeAPicture,gbc);
		takeAPicture.addActionListener(this);
		vibHigh.addActionListener(this);
		vibLow.addActionListener(this);
		
		
		
		return mainFrame;	
	}
	//////////////////////
	
	public void setPartsInLanes(int lane, int number, String fileName){
		
		//String imageName = "images/parts/ct_ball_red.png";		
		int sublane = lane%2;
		
		lanes[lane/2].setCurrentItem(fileName);
		if(sublane == 0)
			lanes[lane/2].fillUpperQueue(number);
		else
			lanes[lane/2].fillLowerQueue(number);
		
	}
	
	
	
	

	public void paint(Graphics g)
	{
		super.paint(g);
		Graphics2D g2 = (Graphics2D)g;
		g.drawImage(background, 0, 50, null);
		for(UnitB lane: lanes)
			lane.UpdateImage(g2);	
		if(takingPicture)
		{
			g.setColor(Color.YELLOW);
			g.fillOval(155, 60, 610, 610);
			
			if(isLaneOff || isLaneHigh)
				picStatus.setText("BAD PICTURE");
			else
				picStatus.setText("GOOD PICTURE");
			revalidate();
			
			count++;
			if(count==10)
			{
				count = 0;
				takingPicture = false;
			}
		}
	}

	public void actionPerformed(ActionEvent ae) 
	{
		// TODO Auto-generated method stub
		repaint();

		if(ae.getSource() == submit)
		{
			int number,lane,sublane;
			String imageName;
			try{
				
				imageName = "images/parts/ct_ball_red.png";
				/*
				String a = combo.getSelectedItem().toString();
				if(a.equals("Lane 1"))
					lanes[0].setVibrationHigh();
				else if(a.equals("Lane 2"))
					lanes[0].setVibrationHigh1();
				else if(a.equals("Lane 3"))
					lanes[1].setVibrationHigh();
				else if(a.equals("Lane 4"))
					lanes[1].setVibrationHigh1();
				else if(a.equals("Lane 5"))
					lanes[2].setVibrationHigh();
				else if(a.equals("Lane 6"))
					lanes[2].setVibrationHigh1();
				else if(a.equals("Lane 7"))
					lanes[3].setVibrationHigh();
				else if(a.equals("Lane 8"))
					lanes[3].setVibrationHigh1();*/
				
			number = Integer.parseInt(numberTF.getText());	
				
				
				lane = Integer.parseInt(laneTF.getText()) - 1;
				sublane = lane%2;
				lanes[lane/2].setCurrentItem(imageName);
				if(sublane == 0)
					lanes[lane/2].fillUpperQueue(number);
				else
					lanes[lane/2].fillLowerQueue(number);					
			}
			catch(Exception ex){
				ex.printStackTrace();
			}
		}
		else if(ae.getSource() == takeAPicture)
		{
			takingPicture = true;
		}
		
		else if(ae.getSource() == vibHigh)
		{
			isLaneHigh = true;
			String a = combo.getSelectedItem().toString();
			if(a.equals("Lane 1"))
				lanes[0].setVibrationHigh();
			else if(a.equals("Lane 2"))
				lanes[0].setVibrationHigh1();
			else if(a.equals("Lane 3"))
				lanes[1].setVibrationHigh();
			else if(a.equals("Lane 4"))
				lanes[1].setVibrationHigh1();
			else if(a.equals("Lane 5"))
				lanes[2].setVibrationHigh();
			else if(a.equals("Lane 6"))
				lanes[2].setVibrationHigh1();
			else if(a.equals("Lane 7"))
				lanes[3].setVibrationHigh();
			else if(a.equals("Lane 8"))
				lanes[3].setVibrationHigh1();
			//for(int i= 0 ;i <4; i++)
				//lanes[i].setVibrationHigh();
				
			//lanes[0].setVibrationHigh();
			//lanes[0].setVibrationHigh1();
		}
		else if(ae.getSource() == vibLow)
		{
			isLaneHigh = false;
			String a = combo.getSelectedItem().toString();
			if(a.equals("Lane 1"))
				lanes[0].setVibrationLow();
			else if(a.equals("Lane 2"))
				lanes[0].setVibrationLow1();
			else if(a.equals("Lane 3"))
				lanes[1].setVibrationLow();
			else if(a.equals("Lane 4"))
				lanes[1].setVibrationLow1();
			else if(a.equals("Lane 5"))
				lanes[2].setVibrationLow();
			else if(a.equals("Lane 6"))
				lanes[2].setVibrationLow1();
			else if(a.equals("Lane 7"))
				lanes[3].setVibrationLow();
			else if(a.equals("Lane 8"))
				lanes[3].setVibrationLow1();
			//for(int i= 0 ;i <4; i++)
				//lanes[i].setVibrationLow();
			
			//lanes[0].setVibrationLow();
			//lanes[0].setVibrationLow1();
		}
		
		else if(ae.getSource() == laneOn)
		{
			isLaneOff = false;
			String a = combo.getSelectedItem().toString();
			if(a.equals("Lane 1"))
				lanes[0].turnOnUpperLane();
			else if(a.equals("Lane 2"))
				lanes[0].turnOnLowerLane();
			else if(a.equals("Lane 3"))
				lanes[1].turnOnUpperLane();
			else if(a.equals("Lane 4"))
				lanes[1].turnOnLowerLane();
			else if(a.equals("Lane 5"))
				lanes[2].turnOnUpperLane();
			else if(a.equals("Lane 6"))
				lanes[2].turnOnLowerLane();
			else if(a.equals("Lane 7"))
				lanes[3].turnOnUpperLane();
			else if(a.equals("Lane 8"))
				lanes[3].turnOnLowerLane();
		}
		else if(ae.getSource() == laneOff)
		{
			isLaneOff = true;
			String a = combo.getSelectedItem().toString();
			if(a.equals("Lane 1"))
				lanes[0].turnOffUpperLane();
			else if(a.equals("Lane 2"))
				lanes[0].turnOffLowerLane();
			else if(a.equals("Lane 3"))
				lanes[1].turnOffUpperLane();
			else if(a.equals("Lane 4"))
				lanes[1].turnOffLowerLane();
			else if(a.equals("Lane 5"))
				lanes[2].turnOffUpperLane();
			else if(a.equals("Lane 6"))
				lanes[2].turnOffLowerLane();
			else if(a.equals("Lane 7"))
				lanes[3].turnOffUpperLane();
			else if(a.equals("Lane 8"))
				lanes[3].turnOffLowerLane();
		}
		
	}
}
