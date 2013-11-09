//Johnny Rusnak
import java.awt.*;
import java.awt.geom.*;
import java.util.ArrayList;
import java.util.Arrays;
import FactoryResources.*;
import javax.swing.*;
import Graphics.*;


public class UnitB{
	FactoryManagerAnimation fpa;
	
	/////////////////PARTS/////////////////
	
	String upperItem, lowerItem, currentItem = "images/parts/ct_ball_blue.png";
	ArrayList<PartGraph> upperQueue, lowerQueue;
	int vibrate = -2;
	int vibrate1 = -2;
	///////////////FEEDER/////////////////////
	Rectangle2D.Double feeder;
	Image Feeder;
	Boolean occupied = false;
	////////////////LANE/////////////////////
	Rectangle2D.Double lane1, lane2;
	Image Lane1, Lane2;
	int xspeed=0;
	int yspeed = 0;
	ArrayList<Rectangle2D.Double> lineList, lineList2;
	int x, y, width, height; //left corner, width and height of upper lane
	Boolean laneOn=true, laneOn2=true;
	int lanecounter=0, lanecounter2=0;
	/////////////////NEST//////////////////////
	Nest nest0, nest1;
	
	///////////////DIVERTER/////////////////
	Rectangle2D.Double diverter, position;
	Image Diverter;
	Boolean tp=true;
	
	int low = 2;
	int high = 4;
	boolean vibrateStatus = false;
	boolean vibrateStatus1 = false;

	public UnitB(int x, int y, FactoryManagerAnimation fpa) {
		this.fpa = fpa;	
		this.x = x;
		this.y = y;
		
		width = 400;
		height = 40;
		diverter = new Rectangle2D.Double(x+width, y, 25, 85);
		Diverter = Toolkit.getDefaultToolkit().createImage("images/xmas_roofTop.fw.png");
		position = new Rectangle2D.Double(x+width+25, y, 25, 33);
		lane1 = new Rectangle2D.Double( x, y, width, height );
		lane2 = new Rectangle2D.Double( x, y+45, width, height );
		Lane1 = Toolkit.getDefaultToolkit().createImage("images/xmas_lane.fw.png");
		Lane2 = Toolkit.getDefaultToolkit().createImage("images/xmas_lane.fw.png");		
		Feeder = Toolkit.getDefaultToolkit().createImage("images/xmas_chimneyFeeder.fw.png");
		feeder = new Rectangle2D.Double(x+width+25, y, 50, 85);
		
		nest0 = new Nest(x-60, y);
		nest1 = new Nest(x-60, y+45);
		
		//initilize lines
		lineList = new ArrayList<Rectangle2D.Double>();		
		for(int i=1; i<21; i++){
			lineList.add(new Rectangle2D.Double(x+i*20, y, 3, 40));
		}		
		lineList2 = new ArrayList<Rectangle2D.Double>();
		for(int i=1; i<21; i++){
			lineList2.add(new Rectangle2D.Double(x+i*20, y+45, 3, 40));
		}
		
		//initialize upperlane parts
		upperQueue = new ArrayList<PartGraph>();
		//initialize lowerlane parts
		lowerQueue = new ArrayList<PartGraph>();

	}




	public void UpdateImage(Graphics2D g2) {
		
		//g2.setColor(Color.GRAY);
		//g2.fill(diverter);
		g2.drawImage(Diverter, x+width, y, 25, 85, null);
		if(tp==true)
		{
			g2.setColor(Color.GREEN);
			g2.fillOval(x+width+2, y+5, 17, 25);
		}

		else
		{
			g2.setColor(Color.GREEN);
			g2.fillOval(x+width+2, y+55, 17, 25);
		}
		g2.setColor(Color.WHITE);
		g2.drawImage(Feeder, x+width+25, y, 60, 60, null);
		g2.drawImage(Lane1, x, y, width, height+10, null);
		g2.drawImage(Lane2, x, y+35, width, height+10, null);
		//g2.fill(feeder);
		//g2.setColor(Color.GRAY);
		//g2.fill(lane1);
		//g2.fill(lane2);
		
		//draw first lineList 
		g2.setColor(Color.WHITE);		
		for(int i=0; i<20; i++){			
			g2.fill(lineList.get(i));
		}
		//remove lines
		if(laneOn)
		{
			lanecounter++;
			if(lanecounter%20==0)
			{
				lineList.remove(0);
				lineList.add(19, new Rectangle2D.Double( x+400, y, 3, 40 ));
			}
			if(lanecounter<100)
			{
				for(int i=0; i<20; i++)
				{
					double x = lineList.get(i).getX();
					double y = lineList.get(i).getY();
					lineList.get(i).setFrame( x-1, y, lineList.get(i).getWidth(), lineList.get(i).getHeight()); 
				}
			}
			if(lanecounter==100)
			{
				lanecounter=0;
			}
		}
		
		//draw second line list
		g2.setColor(Color.WHITE);
		for(int  i=0; i<20; i++){
			g2.fill(lineList2.get(i));
		}
		//remove lines
		if(laneOn2)
		{
			lanecounter2++;
			if(lanecounter2%20==0)
			{
				lineList2.remove(0);
				lineList2.add(19, new Rectangle2D.Double( x+400, y+45, 3, 40 ));
			}
			if(lanecounter2<100)
			{
				for(int i=0; i<20; i++)
				{
					double x = lineList2.get(i).getX();
					double y = lineList2.get(i).getY();
					lineList2.get(i).setFrame( x-1, y, lineList2.get(i).getWidth(), lineList2.get(i).getHeight()); 
				}
			}
			if(lanecounter2==100)
			{
				lanecounter2=0;
			}
		}
		
		moveParts();
		nest0.drawNest(g2);
		nest1.drawNest(g2);
		
		if(upperQueue.size() > 0){
			for(int i=0; i<upperQueue.size(); i++){	
				if(upperQueue.get(i).getX()<x+width && upperQueue.get(i).getX()>nest0.getEndOfNest()){
					upperQueue.get(i).drawPart(g2);
				}
			}
		}
		
		if(lowerQueue.size() > 0){
			for(int i=0; i<lowerQueue.size(); i++){	
				if(lowerQueue.get(i).getX()<x+width && lowerQueue.get(i).getX()>nest1.getEndOfNest()){
					lowerQueue.get(i).drawPart(g2);
				}
			}
		}
		
	}
	
	public void moveParts(){
		
		if(upperQueue.size()>0){
			for(PartGraph part: upperQueue){
				if(laneOn)
				{
					part.setX(part.getX()-1);
					part.setY(part.getY()+vibrate);
				
				}				
			}
			
			if(upperQueue.get(0).getX() < nest0.getEndOfNest()){
				nest0.addPart(upperQueue.get(0).getFileName());
				upperQueue.remove(0);				
			}			
		}
		
		
		if(lowerQueue.size()>0){
			for(PartGraph part: lowerQueue){
				if(laneOn2)
				{
					part.setX(part.getX() -1);
					part.setY(part.getY()+vibrate1);
				}
				
			}
			
			if(lowerQueue.get(0).getX() < nest1.getEndOfNest()){
				nest1.addPart(lowerQueue.get(0).getFileName());
				lowerQueue.remove(0);				
			}
		}
		//upper lane vibration
		if(!vibrateStatus)
		{
			if(vibrate == -1*low)
				vibrate = low;
			else
				vibrate = -1*low;
		}
		else if(vibrateStatus)
		{
			if(vibrate == -1*high)
				vibrate = high;
			else
				vibrate = -1*high;
		}
		
		
		//lower lane vibration
		if(!vibrateStatus1)
		{
			if(vibrate1 == -1*low)
				vibrate1 = low;
			else
				vibrate1 = -1*low;
		}
		else if(vibrateStatus1)
		{
			if(vibrate1 == -1*high)
				vibrate1 = high;
			else
				vibrate1 = -1*high;
		}
		
	}
	
	public void turnOnUpperLane()
	{	laneOn = true;}
	public void turnOnLowerLane()
	{	laneOn2 = true;}
	
	public void turnOffUpperLane()
	{	laneOn = false;}
	public void turnOffLowerLane()
	{	laneOn2 = false;}
	
	public void fillUpperQueue(int n){
		for(int i=0; i<n; i++){
			upperQueue.add(new PartGraph(x+i*20+width, y+height/2-5, currentItem));
		}
		tp = true;
	}

	public void fillLowerQueue(int n){
		for(int i=0; i<n; i++){
			lowerQueue.add(new PartGraph(x+i*20+width, y+45+height/2-5, currentItem));
		}
		tp = false;
	}
	
	//setters
	public void setCurrentItem(String currentItem){
		this.currentItem = currentItem;
	}

	public void laneOn(Boolean laneStatus)
	{
		laneOn=laneStatus;
	}

	public void setOccupied(Boolean occ)
	{
		occupied = occ;	
	}
	
	public void setVibrationHigh()
	{
		vibrateStatus = true;
	}
	
	public void setVibrationHigh1()
	{	vibrateStatus1 = true;}
	
	public void setVibrationLow()
	{
		vibrateStatus = false;
	}
	public void setVibrationLow1()
	{	vibrateStatus1 = false;}
	
	//getters	
	public Boolean isOccupied()
	{
		return occupied;
	}




}





