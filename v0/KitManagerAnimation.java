import java.awt.*;
import java.awt.geom.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;

public class KitManagerAnimation extends JFrame implements ActionListener{
	Ellipse2D.Double myRobot;
	Rectangle2D.Double backgroundRectangle;
	Rectangle2D.Double kittingStand;
	Rectangle2D.Double exitingLane;
	boolean kitfull=true, lanestatus=true, palletset=false, robotstatus=true;
	int counter=0;
	int lanecounter = 0;
	int partcounter = 0;
	int counterrobot = 0;
	Image bg, ks, kr;
	int krx = 80;
	int kry = 80;
	int px = 315;
	int py = 320;
	int px2 = 315;
	int py2 = 390;
	int px3 = 315;
	int py3 = 460;
	JButton KitFull, LaneStatus;
	ArrayList<Image> pallet = new ArrayList<Image>();
	ArrayList<Rectangle2D.Double> lanes = new ArrayList<Rectangle2D.Double>();

	public KitManagerAnimation() {
		//Make the ellipse at the starting position
		myRobot = new Ellipse2D.Double( 80, 80, 50, 50 );
		kittingStand = new Rectangle2D.Double( 300,300, 80,240 );
		exitingLane = new Rectangle2D.Double( 170, 600, 80, 100 );
		lanes.add(new Rectangle2D.Double( 170, 600, 80, 5 ));
		lanes.add(new Rectangle2D.Double( 170, 625, 80, 5 ));
		lanes.add(new Rectangle2D.Double( 170, 650, 80, 5 ));
		lanes.add(new Rectangle2D.Double( 170, 675, 80, 5 ));
		pallet.add(Toolkit.getDefaultToolkit().createImage("images/pallet.jpg"));
		pallet.add(Toolkit.getDefaultToolkit().createImage("images/pallet.jpg"));
		pallet.add(Toolkit.getDefaultToolkit().createImage("images/pallet.jpg"));
		bg = Toolkit.getDefaultToolkit().createImage("images/background.jpg");
		ks = Toolkit.getDefaultToolkit().createImage("images/kittingStand.jpg");
		kr = Toolkit.getDefaultToolkit().createImage("images/robot.jpg");
		//Make the background rectangle to "erase" the screen
		backgroundRectangle = new Rectangle2D.Double( 0, 0, 1200, 700 );

		//KitFull = new JButton("Kit Full");
		//LaneStatus = new JButton("Lane On/Off");

		//setLayout(new BorderLayout());
		//add(buttonPanel(), BorderLayout.LINE_END);
		//validate();
		//KitFull.addActionListener(this);
		//LaneStatus.addActionListener(this);
	}

	public static void main(String[] args ) {

		KitManagerAnimation kma = new KitManagerAnimation();

		kma.setSize( 1200, 700 );
		kma.setVisible(true);
		kma.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );

		new Timer( 50, kma ).start();
	}

	public JPanel buttonPanel(){
		JPanel button = new JPanel();
		button.setLayout(new FlowLayout());
		button.add(KitFull);
		button.add(LaneStatus);
		validate();
		return button;
	}
	public void actionPerformed( ActionEvent ae ) {
		if(ae.getSource()==KitFull)
		{
			if(kitfull)
				kitfull=false;
			else
				kitfull=true;
		}

		if(ae.getSource()==LaneStatus)
		{
			if(lanestatus==false)
				lanestatus = true;
			else
				lanestatus = false;
		}

		while(!lanestatus && palletset)
		{
			px = 170;
			py = 640;
		}

		if(lanestatus)
		{
			lanecounter++;
			if(lanecounter%25==0)
			{
				lanes.remove(3);
				lanes.add(0, new Rectangle2D.Double( 170, 600, 80, 5 ));
			}
			if(lanecounter<100)
			{
				double x = lanes.get(0).getX();
				double y = lanes.get(0).getY();
				lanes.get(0).setFrame( x, y+1, lanes.get(0).getWidth(), lanes.get(0).getHeight());  //Move 1 x-pixel and 1 y-pixel every 50 milliseconds
				double x1 = lanes.get(1).getX();
				double y1 = lanes.get(1).getY();
				lanes.get(1).setFrame( x1, y1+1, lanes.get(1).getWidth(), lanes.get(1).getHeight());  //Move 1 x-pixel and 1 y-pixel every 50 milliseconds
				double x2 = lanes.get(2).getX();
				double y2 = lanes.get(2).getY();
				lanes.get(2).setFrame( x2, y2+1, lanes.get(2).getWidth(), lanes.get(2).getHeight());  //Move 1 x-pixel and 1 y-pixel every 50 milliseconds
				double x3 = lanes.get(3).getX();
				double y3 = lanes.get(3).getY();
				lanes.get(3).setFrame( x3, y3+1, lanes.get(3).getWidth(), lanes.get(3).getHeight());  //Move 1 x-pixel and 1 y-pixel every 50 milliseconds
			}
			if(lanecounter==100)
			{
				lanecounter=0;
			}
		}

		if(robotstatus)
		{
			if(counterrobot<50){
				//myRobot.setFrame( x+3, y, myRobot.getWidth(), myRobot.getHeight());  //Move 1 x-pixel and 1 y-pixel every 50 milliseconds
				krx = krx + 3;
			}
			else if(counterrobot<130){
				//myRobot.setFrame( x, y+3, myRobot.getWidth(), myRobot.getHeight());  //Move 1 x-pixel and 1 y-pixel every 50 milliseconds
				kry = kry + 3;
			}
			else if (counterrobot<155){
				//myRobot.setFrame( x+1, y, myRobot.getWidth(), myRobot.getHeight());  //Move 1 x-pixel and 1 y-pixel every 50 milliseconds
				krx = krx + 1;
				//kitfull=false;
			}
			else if(counterrobot<200){
				//myRobot.setFrame( x-3, y, myRobot.getWidth(), myRobot.getHeight());  //Move 1 x-pixel and 1 y-pixel every 50 milliseconds
				krx = krx - 3;
				//px = px - 3;
			}
			else if(counterrobot<275){
				//myRobot.setFrame( x, y+3, myRobot.getWidth(), myRobot.getHeight());  //Move 1 x-pixel and 1 y-pixel every 50 milliseconds
				kry = kry + 3;
				//py = py + 3;
			}
			else if(counterrobot<430){
				//myRobot.setFrame( x, y-3, myRobot.getWidth(), myRobot.getHeight());  //Move 1 x-pixel and 1 y-pixel every 50 milliseconds
				kry = kry - 3;
				//py = py+3;
//				palletset=true;
//				if(counter<345)
//				{
//					py2 = py2 - 1;
//					py3 = py3 - 1;
//				}
			}
			else if(counterrobot<440){
				//myRobot.setFrame( x, y-3, myRobot.getWidth(), myRobot.getHeight());  //Move 1 x-pixel and 1 y-pixel every 50 milliseconds
				krx = krx - 3;
				//palletset=false;
			}
			else if(counterrobot==441)
			{
				//myRobot.setFrame(80,80,50,50);	
				kry = 80;
				krx = 80;
				//px = 315;
				//py = 320;
				//px2 = 315;
				//py2 = 390;
//				px3 = 315;
//				py3 = 460;

//				pallet.add(Toolkit.getDefaultToolkit().createImage("images/pallet.jpg"));
//				pallet.remove(3);
				counterrobot = 0;
			}
			counterrobot ++;
		}

		if(kitfull)	
		{
			//This will be called by the Timer
			//counter++;
			//pallet.get(0).setFrame()
			if(counter<50){
				//myRobot.setFrame( x+3, y, myRobot.getWidth(), myRobot.getHeight());  //Move 1 x-pixel and 1 y-pixel every 50 milliseconds
				//krx = krx + 3;
			}
			else if(counter<130){
				//myRobot.setFrame( x, y+3, myRobot.getWidth(), myRobot.getHeight());  //Move 1 x-pixel and 1 y-pixel every 50 milliseconds
				//kry = kry + 3;
			}
			else if (counter<155){
				//myRobot.setFrame( x+1, y, myRobot.getWidth(), myRobot.getHeight());  //Move 1 x-pixel and 1 y-pixel every 50 milliseconds
				//krx = krx + 1;
				//kitfull=false;
			}
			else if(counter<200){
				//myRobot.setFrame( x-3, y, myRobot.getWidth(), myRobot.getHeight());  //Move 1 x-pixel and 1 y-pixel every 50 milliseconds
				//krx = krx - 3;
				px = px - 3;
			}
			else if(counter<275){
				//myRobot.setFrame( x, y+3, myRobot.getWidth(), myRobot.getHeight());  //Move 1 x-pixel and 1 y-pixel every 50 milliseconds
				//kry = kry + 3;
				py = py + 3;
			}
			else if(counter<430){
				//myRobot.setFrame( x, y-3, myRobot.getWidth(), myRobot.getHeight());  //Move 1 x-pixel and 1 y-pixel every 50 milliseconds
				//kry = kry - 3;
				py = py+3;
				palletset=true;
				if(counter<345)
				{
					py2 = py2 - 1;
					py3 = py3 - 1;
				}
			}
			else if(counter<440){
				//myRobot.setFrame( x, y-3, myRobot.getWidth(), myRobot.getHeight());  //Move 1 x-pixel and 1 y-pixel every 50 milliseconds
				//krx = krx - 3;
				palletset=false;
			}
			else if(counter==441)
			{
				//myRobot.setFrame(80,80,50,50);	
//				kry = 80;
//				krx = 80;
				px = 315;
				py = 320;
				px2 = 315;
				py2 = 390;
				px3 = 315;
				py3 = 460;

				pallet.add(Toolkit.getDefaultToolkit().createImage("images/pallet.jpg"));
				pallet.remove(3);
				counter = 0;
			}
			counter ++;
		}

		repaint();
	}

	public void paint(Graphics g) {
		Graphics2D g2 = (Graphics2D)g;

		g.drawImage(bg, 0, 0, null);
		g.drawImage(ks, 300, 300, null);
		g.drawImage(kr, krx, kry, null);
		g2.setColor(Color.DARK_GRAY);
		g2.fill(exitingLane);
		g2.setColor(Color.BLACK);
		g2.fill(lanes.get(0));
		g2.fill(lanes.get(1));
		g2.fill(lanes.get(2));
		g2.fill(lanes.get(3));



		g.drawImage(pallet.get(0), px, py,  null);
		g.drawImage(pallet.get(1), px2, py2,  null);
		g.drawImage(pallet.get(2), px3, py3,  null);
		//g2.fill(myRobot);


	}
}  
