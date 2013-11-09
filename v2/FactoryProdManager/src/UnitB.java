//Johnny Rusnak
import java.awt.*;
import java.awt.geom.*;
import java.util.ArrayList;
import java.util.Arrays;
import FactoryResources.*;
import Graphics.Nest;
import Graphics.PartGraph;

public class UnitB{
	/////////////////PARTS/////////////////
	int vibrate = -2;
	String lowerItem , upperItem;
	ArrayList<PartGraph> upperQueue, lowerQueue;
	ArrayList<Point> nestParts;

	int upperXLoc, lowerXLoc, lowerNestCount, upperNestCount;
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
	int x, y, width, height;
	Boolean laneOn=true, laneOn2=true;
	int lanecounter=0, lanecounter2=0;
	/////////////////BASKET//////////////////////
	Image basket1, basket2;
	Boolean OneisFull=false, TwoisFull=false;
	ArrayList<Point> destinations;
	///////////////DIVERTER/////////////////
	Rectangle2D.Double diverter, position;
	Image Diverter;
	Boolean tp=true, bp=false;
	
	Nest nest0, nest1;

	public UnitB(int newx, int newy) {
	
		upperItem = new String("");
		lowerItem = new String("");;

		upperQueue = new ArrayList<PartGraph>();
		lowerQueue = new ArrayList<PartGraph>();
		x = newx;
		y = newy;
		nest0 = new Nest(x-60, y);
		nest1 = new Nest(x-60, y+45);


		width = 400;
		height = 30;
		diverter = new Rectangle2D.Double(x+width, y, 25, 65);
		Diverter = Toolkit.getDefaultToolkit().createImage("images/xmas_roofTop.fw.png");
		position = new Rectangle2D.Double(x+width+25, y, 25, 33);
		Lane1 = Toolkit.getDefaultToolkit().createImage("images/xmas_lane.fw.png");
		Lane2 = Toolkit.getDefaultToolkit().createImage("images/xmas_lane.fw.png");		
		
		lane1 = new Rectangle2D.Double( x, y, width, height );
		lane2 = new Rectangle2D.Double( x, y+45, width, height );
		feeder = new Rectangle2D.Double(x+width+25, y, 50, 65);
		basket1 = Toolkit.getDefaultToolkit().createImage("images/xmas_nest.fw.png");
		basket2 = Toolkit.getDefaultToolkit().createImage("images/xmas_nest.fw.png");
		Feeder = Toolkit.getDefaultToolkit().createImage("images/xmas_chimneyFeeder.fw.png");
		
		lineList = new ArrayList<Rectangle2D.Double>();
		lineList2 = new ArrayList<Rectangle2D.Double>();

		for(int i =0;i<20;i++){
			lineList.add(new Rectangle2D.Double(x+20+20*i, y, 3, 33));
			lineList2.add(new Rectangle2D.Double(x+20+20*i, y+45, 3, 33));
		}





	}

	public String getPart(int temp){
		if(temp==0)
			return upperItem;

		else
			return lowerItem;
	}

	public void laneOn(Boolean laneStatus)
	{
		laneOn=laneStatus;
	}

	public void setOccupied(Boolean occ)
	{
		occupied = occ;	
	}

	public Boolean isOccupied()
	{
		return occupied;
	}

	public Boolean BasketOneisFull()
	{
		return OneisFull;
	}

	public Boolean BasketTwoisFull()
	{
		return TwoisFull;
	}

	public void setTopPosition()
	{
		tp=true;
		bp=false;
	}

	public void setBottomPosition(Boolean bP)
	{
		bp=true;
		tp=false;
	}
	
	public void movelines(Graphics2D g2){
		//g2.setColor(Color.WHITE);
		//g2.fill(diverter);
		g2.drawImage(Diverter,x+width, y,25,85, null);
		if(tp==true)
		{
			g2.setColor(Color.GREEN);
			g2.fillOval(x+width+2, y, 17, 17);
		}

		if(bp==true)
		{
			g2.setColor(Color.GREEN);
			g2.fillRect(x+width+2, y, 17, 17);
		}
		g2.drawImage(Feeder, x+width+25, y, 60, 60, null);
		g2.drawImage(Lane1, x, y, width, height+7, null);
		g2.drawImage(Lane2, x, y+45, width, height+7, null);
		
		g2.setColor(Color.WHITE);
		for(int i = 0;i<lineList.size();i++){
			g2.fill(lineList.get(i));
		}
		
		if(laneOn)
		{
			lanecounter++;
			if(lanecounter%20==0)
			{
				lineList.remove(0);
				lineList.add(19, new Rectangle2D.Double( x+400, y, 3, 33 ));
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
		g2.setColor(Color.WHITE);
		for(int i = 0;i<lineList2.size();i++){
			g2.fill(lineList2.get(i));
		}

		if(laneOn2)
		{
			lanecounter2++;
			if(lanecounter2%20==0)
			{
				lineList2.remove(0);
				lineList2.add(19, new Rectangle2D.Double( x+400, y+45, 3, 33 ));
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
		//g2.drawImage(basket1, x-55, y, 60, 32, null);
		//g2.drawImage(basket1, x-55, y+32, 60, 32, null);
	}


	public void UpdateImage(Graphics2D g2) {

		
	movelines(g2);

	

		//////////////////////PARTS MOVEMENT//////////////////////////////
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
				part.setX(part.getX()-1);
				part.setY(part.getY()+vibrate);				
			}

			if(upperQueue.get(0).getX() < nest0.getEndOfNest()){
				nest0.addPart(upperQueue.get(0).getFileName());
				upperQueue.remove(0);				
			}			
		}


		if(lowerQueue.size()>0){
			for(PartGraph part: lowerQueue){
				part.setX(part.getX() -1);
				part.setY(part.getY()+vibrate);	
			}

			if(lowerQueue.get(0).getX() < nest1.getEndOfNest()){
				nest1.addPart(lowerQueue.get(0).getFileName());
				lowerQueue.remove(0);				
			}
		}

		if(vibrate == -2)
			vibrate = 2;
		else
			vibrate = -2;

	}
	


	public void addPart(int newlane, String newpart){
		
		if(newlane==0){
			//System.out.println("before adding part to unit b  "+upperItem);
			upperItem = new String(newpart);
			//System.out.println("adding part to unit b  "+upperItem);
			upperQueue.add(new PartGraph(x+width,y+10,upperItem));
			tp=true;



		}

		if(newlane==1){
			//System.out.println("before adding part to unit b  "+lowerItem);
			lowerItem = new String(newpart);
			//System.out.println("adding part to unit b  "+lowerItem);
			lowerQueue.add(new PartGraph(x+width,y+45,lowerItem));
			bp=true;
			tp=false;

		}
	}

	public String checkUpper(){
		if(!nest0.isEmpty())
		return upperItem;
		
		else return "";
	}

	public String checkLower(){
		if(!nest1.isEmpty())
		return lowerItem;
		
		else return "";
	}


	// used by UnitC
	public void removePart(int newlane){
		if(newlane==0){
			//System.out.println("B  " +upperItem);
			nest0.removeAPart();
			if(upperQueue.isEmpty()&& nest0.isEmpty()){
				//System.out.println("Just got changed  "+upperItem);
				upperItem = new String("");
				
				
			}

		}

		else if (newlane==1){
			//System.out.println("B    " +lowerItem);
			nest1.removeAPart();
			if(lowerQueue.isEmpty()&& nest1.isEmpty()){
			//	System.out.println("Just got changed  "+lowerItem);
				lowerItem = new String("");
				
			}
		}
	}

}



