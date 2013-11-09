import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Ellipse2D;
import java.util.*;



public class PartsRobot extends JFrame implements ActionListener{
	
	private javax.swing.Timer timer;//timer for animation
	
	private ImageIcon flash;
	
	//states
	boolean getFromNest1;
	boolean getFromNest2;	
	boolean getParts;
	boolean moveToKit;
	boolean partsInKit;
	boolean takePicture;
	
	int noOfPartsOnRob;
	int noOfPartsInKit;
	int flashCount;//timer for the camera
	
	//THIS VARIABLE IS TO MAKE AN ALTERNATING MOVEMENT FOR THE ROBOT. (GO TO THE 1ST NEST, THEN 2ND, THEN 1ST, THEN 2ND, ..)
	int alternate;
	
	
	private ArrayList<Rectangle> nests,nests2;//ArrayList of nests
	//private ArrayList<ImageIcon> parts1,parts2;//ArrayList of parts
	
	private ArrayList<Part>partsOne,partsTwo;//ArrayList of parts  ******************* new code
	private ArrayList<Part> partsOnRob;//ArrayList of parts that are drawn on the robot
	private ArrayList<Part> partsOnKit;//ArrayList of parts drawn on the kit
	
	//objects represented on in the animation
	private Rectangle background;
	private Rectangle kitStand;
	private Rectangle pallete;
	private Ellipse2D.Double partsRobot;

	
	//locations
	private int nestsX,nestsY;
	private int standX, standY;
	private int palleteX, palleteY;
	private int partsRobX, partsRobY;
	private int partX, partY;
	
	
	PartsRobot(){
		
		//initialize elements
		timer = new javax.swing.Timer(25,this);
		//initialize image
		flash = new ImageIcon("flash_dark.png");
		flashCount = 0;
		
		
		getFromNest1 = true;
		getFromNest2 = false;
		getParts = false;
		moveToKit = false;
		partsInKit = false;
		
		alternate = 1;	// INITIALIZE THE VALUE TO 1
		noOfPartsOnRob = 0;
		noOfPartsInKit = 0;
		
		//locations
		standX = 300;
		standY = 200;
		palleteX = 310;
		palleteY = 210;
		nestsX = 700;
		nestsY = 200;
		partsRobX = 450;
		partsRobY = 200;
		partX = nestsX+5;
		partY = nestsY+5;
		
		
		
		//robot
		partsRobot = new Ellipse2D.Double( partsRobX, partsRobY, 70, 70 );
		//rectangles
		nests = new ArrayList<Rectangle>();
		nests2 = new ArrayList<Rectangle>();	// I CREATE NEW ARRAYLIST ONLY FOR NEST2
		
		
		//new code*****************************8
		partsOne = new ArrayList<Part>();
		partsTwo = new ArrayList<Part>();	// AND ALSO NEW ARRAYLIST FOR THE PARTS ON NEST2
		partsOnRob = new ArrayList<Part>();	
		partsOnKit = new ArrayList<Part>();
		
		background = new Rectangle(0,0,1200,700);
		kitStand = new Rectangle(standX,standY,120,260);
		pallete = new Rectangle(palleteX,palleteY,100,70);
		
		//FIRST NEST
		for( int i=0; i<1; i++){
			nests.add(new Rectangle(nestsX , nestsY, 120, 120));		
			
			for( int k=0; k<4; k++){
				for(int j=0; j<4; j++){
					partsOne.add(new Part("ct_candy_small.png",partX +j*30, partY + k*30));						
				}
			}			
		}
		//SECOND NEST
		for( int i=0; i<1; i++){
			nests.add(new Rectangle(nestsX , nestsY + 130, 120, 120));		
			
			for( int k=0; k<4; k++){
				for(int j=0; j<4; j++){
					partsTwo.add(new Part("ct_candy_small.png", partX +j*30, partY + k*30 + 130));  //******************new code***************
				}
			}			
		}
		//initialize parts on the robot
		for(int i=0; i<4; i++){
			partsOnRob.add(new Part("ct_candy_small.png", partsRobX + 10, partsRobY +10));
		}
		//initialize parts on the kit
		for(int i=0; i<4; i++){
			if(i<2)
				partsOnKit.add(new Part("ct_candy_small.png", palleteX + 10 + i*30, palleteY +10));
			else
				partsOnKit.add(new Part("ct_candy_small.png", palleteX + 10 + (i-2)*30, palleteY +40));
		}
		timer.start();

		//set JFrame properties		
		setVisible(true);
		setSize(1200,700);
		
	}
	
	
	
	
	
	
	public static void main(String[] args){
		new PartsRobot();
		
	}
	
	
	
	public void actionPerformed(ActionEvent ae)
	{	
		if(getFromNest1 == true)
			moveRobotToNest();
		else if(getFromNest2 == true)
			moveRobotToNest2();
		
		if(getParts == true)
			getTheParts();
		else if(moveToKit == true)
			moveToStand();
		else if(partsInKit == true){
			movePartsToKit();
			
		}
		repaint();
	}
	
	public void paint(Graphics g){
		Graphics2D g2 = (Graphics2D)g;
		g2.setColor(Color.green);//set color of background
		g2.fill(background);//paint background
		g2.setColor(Color.DARK_GRAY);//set color of background
		g2.fill(kitStand);
		g2.setColor(Color.gray);
		g2.fill(pallete);
		g2.setColor(Color.DARK_GRAY);//set color of nests
		for(int i=0; i<nests.size(); i++){			
			g2.fill(nests.get(i));		
		}
		for(int i=0; i<nests2.size(); i++){			
			g2.fill(nests2.get(i));		
		}
		g2.setColor(Color.blue);
		g2.fill(partsRobot);
		g2.setColor(Color.black);
		
		// IF ALTERNATE == 1, THE 2ND NEST WILL BE FULL AND THE ROBOT WONT TOUCH THE 2ND NEST
		if(alternate%2 == 1)
		{
			//DRAWING THE 1ST NEST
			for(int i=(0+noOfPartsOnRob); i<partsOne.size(); i++){		////new code******	
				//g2.fill(parts1.get(i));
				partsOne.get(i).paintIcon(this, g2, partsOne.get(i).x, partsOne.get(i).y);
				
				
			}
			//DRAWING THE 2ND NEST
			for(int i=0; i<partsTwo.size(); i++){			
				//g2.fill(parts2.get(i));
				partsTwo.get(i).paintIcon(this, g2, partsTwo.get(i).x, partsTwo.get(i).y);
			}
		}
		// IF ALTERNATE == 2, THE 1ST NEST WILL BE FULL AND THE ROBOT WILL GO TO THE 2ND NEST
		else if(alternate%2 == 0)
		{
			//DRAWING THE 1ST NEST
			for(int i=0; i<partsOne.size(); i++){			
				partsOne.get(i).paintIcon(this, g2, partsOne.get(i).x, partsOne.get(i).y);		
			}
			//DRAWING THE 2ND NEST
			for(int i=(0+noOfPartsOnRob); i<partsTwo.size(); i++){			
				partsTwo.get(i).paintIcon(this, g2, partsTwo.get(i).x, partsTwo.get(i).y);		
			}
		}
		//draw parts on robot
		for(int i=0; i<noOfPartsOnRob; i++){
			//g2.fill(partsOnRob.get(i));
			partsOnRob.get(i).paintIcon(this, g2, partsOnRob.get(i).x, partsOnRob.get(i).y);
		}
		//draw parts in kit
		for(int i=0; i<noOfPartsInKit; i++){
			//g2.fill(partsOnKit.get(i));
			partsOnKit.get(i).paintIcon(this, g2, partsOnKit.get(i).x, partsOnKit.get(i).y);
		}
		
		if(takePicture){
			flashCount++;			
			if(flashCount == 3)
				flash.paintIcon(this, g2, standX-85, standY-50);			
			if(flashCount == 9){
				takePicture = false;
				flashCount = 0;
			}			
		}		
	}
	
	
	//moves robot to the kit stand
	public void moveToStand(){
		if(partsRobX > standX+125){
			partsRobX -= 3;
			partsRobot.setFrame(partsRobX, partsRobY, 70, 70);
			
			for(int i=0; i<noOfPartsOnRob; i++){
				if(i<2)
					//partsOnRob.get(i).setFrame(partsRobX + 10 + i*30, partsRobY +10, 20,20);
					partsOnRob.get(i).setLocation(partsRobX + 10 + i*30, partsRobY +10);
				else
					partsOnRob.get(i).setLocation(partsRobX + 10 + (i-2)*30, partsRobY +40);
			}
		}
		else if(partsRobY > standY){
			partsRobY -= 3;
			partsRobot.setFrame(partsRobX,partsRobY,70,70);
			
			for(int i=0; i<noOfPartsOnRob; i++){
				if(i<2)
					partsOnRob.get(i).setLocation(partsRobX + 10 + i*30, partsRobY +10);
				else
					partsOnRob.get(i).setLocation(partsRobX + 10 + (i-2)*30, partsRobY +40);
			}
		}
		else if(partsRobX <= standX+150){
			moveToKit = false;
			partsInKit = true;
		}
	}
	
	//MOVE TO THE FIRST NEST
	public void moveRobotToNest(){
		noOfPartsInKit = 0;
		if(partsRobX < nestsX-80){
			getParts = false;
			partsRobX += 3;
			partsRobot.setFrame(partsRobX, partsRobY, 70, 70);
		}
		else if(partsRobX >= nestsX-80)
		{
			noOfPartsInKit = 0;
			getParts = true;
			getFromNest1 = false;
		}
	}
	
	//MOVE TO THE SECOND NEST
	public void moveRobotToNest2(){
		noOfPartsInKit = 0;
		if(partsRobX < nestsX-80){
			getParts = false;
			partsRobX += 3;
			partsRobot.setFrame(partsRobX, partsRobY, 70, 70);
		}
		else if(partsRobY < nestsY+130){
			partsRobY += 3;
			partsRobot.setFrame(partsRobX, partsRobY, 70, 70);
		}
		else
		{
			noOfPartsInKit = 0;
			getParts = true;
			getFromNest2 = false;
		}
	}
	
	
	public void getTheParts(){
		noOfPartsOnRob = 4;				
		for(int i=0; i<noOfPartsOnRob; i++){
			if(i<2)
				partsOnRob.get(i).setLocation(partsRobX + 10 + i*30, partsRobY +10);
			else
				partsOnRob.get(i).setLocation(partsRobX + 10 + (i-2)*30, partsRobY +40);
		}
		moveToKit = true;
		getParts = false;
	}
	
	public void movePartsToKit(){
		noOfPartsInKit = noOfPartsOnRob;
		noOfPartsOnRob = 0;
		moveToKit = false;
		partsInKit = false;
		
		//CHANGE THE GETFROMNEST2 INTO TRUE -> SO THAT NEXT TIME IT WILL GO TO THE 2ND NEST
		if(alternate%2 == 1)
		{
			getFromNest1 = false;
			getFromNest2 = true;
			alternate ++;
		}
		//CHANGE THE GETFROMNEST1 INTO TRUE -> NEXT TIME IT WILL GO TO THE 1ST NEST
		else if(alternate%2 == 0)
		{
			getFromNest1 = true;
			getFromNest2 = false;
			alternate ++;
		}
		
		takePicture = true;
		
	}
	
	
	

}
