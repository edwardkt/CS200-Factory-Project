//Johnny Rusnak
import java.awt.*;
import java.awt.geom.*;
import java.util.ArrayList;
import java.util.Arrays;

public class UnitB{
	/////////////////PARTS/////////////////
	String upperItem, lowerItem;
	ArrayList<Image> upperQueue, lowerQueue;
	ArrayList<Point> upperPos, lowerPos;
	ArrayList<Point> nestParts;
	int upperXLoc, lowerXLoc;
	///////////////FEEDER/////////////////////
	Rectangle2D.Double feeder;
	Boolean occupied = false;
	////////////////LANE/////////////////////
	Rectangle2D.Double lane1, lane2;
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
	Boolean tp=true, bp=false;

	public UnitB(int newx, int newy, String firstitem, String seconditem) {
		upperItem= firstitem;
		lowerItem= seconditem;
		upperQueue = new ArrayList<Image>();
		lowerQueue = new ArrayList<Image>();
		upperPos = new ArrayList<Point>();
		lowerPos = new ArrayList<Point>();
		destinations = new ArrayList<Point>();
		
		x = newx;
		y = newy;
		
		//initialize every destination in the nest
		for (int i =0; i < 5; i++) {
			for(int j = 0; j < 2; j++) {
				destinations.add(new Point((x-50)+i*10, (y+5)+j*10));
			}
		}
		
		width = 400;
		height = 30;
		diverter = new Rectangle2D.Double(x+width, y, 25, 65);
		position = new Rectangle2D.Double(x+width+25, y, 25, 33);
		lane1 = new Rectangle2D.Double( x, y, width, height );
		lane2 = new Rectangle2D.Double( x, y+35, width, height );
		feeder = new Rectangle2D.Double(x+width+25, y, 50, 65);
		basket1 = Toolkit.getDefaultToolkit().createImage("images/basket1.png");
		basket2 = Toolkit.getDefaultToolkit().createImage("images/basket2.png");
		lineList = new ArrayList<Rectangle2D.Double>();
		//lineList.add(new Rectangle2D.Double(x, y, 3, 30));
		lineList.add(new Rectangle2D.Double(x+20, y, 3, 30));
		lineList.add(new Rectangle2D.Double(x+40, y, 3, 30));
		lineList.add(new Rectangle2D.Double(x+60, y, 3, 30));
		lineList.add(new Rectangle2D.Double(x+80, y, 3, 30));
		lineList.add(new Rectangle2D.Double(x+100, y, 3, 30));
		lineList.add(new Rectangle2D.Double(x+120, y, 3, 30));
		lineList.add(new Rectangle2D.Double(x+140, y, 3, 30));
		lineList.add(new Rectangle2D.Double(x+160, y, 3, 30));
		lineList.add(new Rectangle2D.Double(x+180, y, 3, 30));
		lineList.add(new Rectangle2D.Double(x+200, y, 3, 30));
		lineList.add(new Rectangle2D.Double(x+220, y, 3, 30));
		lineList.add(new Rectangle2D.Double(x+240, y, 3, 30));
		lineList.add(new Rectangle2D.Double(x+260, y, 3, 30));
		lineList.add(new Rectangle2D.Double(x+280, y, 3, 30));
		lineList.add(new Rectangle2D.Double(x+300, y, 3, 30));
		lineList.add(new Rectangle2D.Double(x+320, y, 3, 30));
		lineList.add(new Rectangle2D.Double(x+340, y, 3, 30));
		lineList.add(new Rectangle2D.Double(x+360, y, 3, 30));
		lineList.add(new Rectangle2D.Double(x+380, y, 3, 30));
		lineList.add(new Rectangle2D.Double(x+400, y, 3, 30));

		lineList2 = new ArrayList<Rectangle2D.Double>();
		//lineList.add(new Rectangle2D.Double(x, y, 3, 30));
		lineList2.add(new Rectangle2D.Double(x+20, y+35, 3, 30));
		lineList2.add(new Rectangle2D.Double(x+40, y+35, 3, 30));
		lineList2.add(new Rectangle2D.Double(x+60, y+35, 3, 30));
		lineList2.add(new Rectangle2D.Double(x+80, y+35, 3, 30));
		lineList2.add(new Rectangle2D.Double(x+100, y+35, 3, 30));
		lineList2.add(new Rectangle2D.Double(x+120, y+35, 3, 30));
		lineList2.add(new Rectangle2D.Double(x+140, y+35, 3, 30));
		lineList2.add(new Rectangle2D.Double(x+160, y+35, 3, 30));
		lineList2.add(new Rectangle2D.Double(x+180, y+35, 3, 30));
		lineList2.add(new Rectangle2D.Double(x+200, y+35, 3, 30));
		lineList2.add(new Rectangle2D.Double(x+220, y+35, 3, 30));
		lineList2.add(new Rectangle2D.Double(x+240, y+35, 3, 30));
		lineList2.add(new Rectangle2D.Double(x+260, y+35, 3, 30));
		lineList2.add(new Rectangle2D.Double(x+280, y+35, 3, 30));
		lineList2.add(new Rectangle2D.Double(x+300, y+35, 3, 30));
		lineList2.add(new Rectangle2D.Double(x+320, y+35, 3, 30));
		lineList2.add(new Rectangle2D.Double(x+340, y+35, 3, 30));
		lineList2.add(new Rectangle2D.Double(x+360, y+35, 3, 30));
		lineList2.add(new Rectangle2D.Double(x+380, y+35, 3, 30));
		lineList2.add(new Rectangle2D.Double(x+400, y+35, 3, 30));
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


	public void UpdateImage(Graphics2D g2) {


		g2.setColor(Color.GRAY);
		g2.fill(diverter);
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
		g2.setColor(Color.WHITE);
		g2.fill(feeder);

		g2.setColor(Color.GRAY);
		g2.fill(lane1);
		g2.fill(lane2);
		g2.setColor(Color.BLACK);
		g2.fill(lineList.get(0));
		g2.fill(lineList.get(1));
		g2.fill(lineList.get(2));
		g2.fill(lineList.get(3));
		g2.fill(lineList.get(4));
		g2.fill(lineList.get(5));
		g2.fill(lineList.get(6));
		g2.fill(lineList.get(7));
		g2.fill(lineList.get(8));
		g2.fill(lineList.get(9));
		g2.fill(lineList.get(10));
		g2.fill(lineList.get(11));
		g2.fill(lineList.get(12));
		g2.fill(lineList.get(13));
		g2.fill(lineList.get(14));
		g2.fill(lineList.get(15));
		g2.fill(lineList.get(16));
		g2.fill(lineList.get(17));
		g2.fill(lineList.get(18));
		g2.fill(lineList.get(19));
		//g2.fill(lineList.get(20));	
		if(laneOn)
		{
			lanecounter++;
			if(lanecounter%20==0)
			{
				lineList.remove(0);
				lineList.add(19, new Rectangle2D.Double( x+400, y, 3, 30 ));
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
		g2.setColor(Color.BLACK);
		g2.fill(lineList2.get(0));
		g2.fill(lineList2.get(1));
		g2.fill(lineList2.get(2));
		g2.fill(lineList2.get(3));
		g2.fill(lineList2.get(4));
		g2.fill(lineList2.get(5));
		g2.fill(lineList2.get(6));
		g2.fill(lineList2.get(7));
		g2.fill(lineList2.get(8));
		g2.fill(lineList2.get(9));
		g2.fill(lineList2.get(10));
		g2.fill(lineList2.get(11));
		g2.fill(lineList2.get(12));
		g2.fill(lineList2.get(13));
		g2.fill(lineList2.get(14));
		g2.fill(lineList2.get(15));
		g2.fill(lineList2.get(16));
		g2.fill(lineList2.get(17));
		g2.fill(lineList2.get(18));
		g2.fill(lineList2.get(19));
		//g2.fill(lineLi2st.get(20));	
		if(laneOn2)
		{
			lanecounter2++;
			if(lanecounter2%20==0)
			{
				lineList2.remove(0);
				lineList2.add(19, new Rectangle2D.Double( x+400, y+35, 3, 30 ));
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
		g2.drawImage(basket1, x-55, y, 60, 32, null);
		g2.drawImage(basket1, x-55, y+32, 60, 32, null);
		
		for(int i = 0; i < upperPos.size();i++) {
			if(!upperPos.isEmpty()) {
				 // if the parts are on the lane
					if(upperPos.get(i).x>=x-50+10*i){
						//System.out.println("here");
						upperPos.get(i).x--;
					}
			}
			if(upperPos.get(i).x<x+20) {
				if (i < destinations.size()) {
					if (upperPos.get(i).x > destinations.get(i).x)
						upperPos.get(i).x--;
					if (upperPos.get(i).y > destinations.get(i).y)
						upperPos.get(i).y--;
					if (upperPos.get(i).y < destinations.get(i).y)
						upperPos.get(i).y++;
				}
			}
			g2.drawImage(upperQueue.get(i),upperPos.get(i).x,upperPos.get(i).y, 10,10,null);
		} // end for upper lane loop
		
		//Lower Lane
		for(int i = 0; i < lowerPos.size();i++) {
			if(!lowerPos.isEmpty()) {
				 // if the parts are on the lane
					if(lowerPos.get(i).x>=x-50+10*i){
						//System.out.println("here");
						lowerPos.get(i).x--;
					}
			}
		
			if (i < destinations.size()) {
				if (lowerPos.get(i).x > destinations.get(i).x)
					lowerPos.get(i).x--;
				if (lowerPos.get(i).y > destinations.get(i).y)
					lowerPos.get(i).y--;
				if (lowerPos.get(i).y < destinations.get(i).y)
					lowerPos.get(i).y++;
			}
			g2.drawImage(lowerQueue.get(i),lowerPos.get(i).x,lowerPos.get(i).y, 10,10,null);
		} // end for lower lane loop
	}

	public void addPart(int newlane){

		if(newlane==0){
			upperQueue.add(Toolkit.getDefaultToolkit().createImage(upperItem));
			upperPos.add(new Point(x+width,y+10));
		}
		if(newlane==1){
			lowerQueue.add(Toolkit.getDefaultToolkit().createImage(lowerItem));
			lowerPos.add(new Point(x+width,y+45));

		}
	}

}





