//Johnny Rusnak
import java.awt.*;
import java.awt.geom.*;
import java.util.ArrayList;
import java.util.Arrays;
import FactoryResources.*;

public class UnitB{
	/////////////////PARTS/////////////////
	String upperItem, lowerItem;
	ArrayList<Image> upperQueue, lowerQueue;
	ArrayList<Point> upperPos, lowerPos;
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
	Image basket;
	Boolean isFull=false;
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

		x = newx;
		y = newy;

		width = 400;
		height = 30;
		diverter = new Rectangle2D.Double(x+width, y, 25, 65);
		position = new Rectangle2D.Double(x+width+25, y, 25, 33);
		lane1 = new Rectangle2D.Double( x, y, width, height );
		lane2 = new Rectangle2D.Double( x, y+35, width, height );
		feeder = new Rectangle2D.Double(x+width+25, y, 50, 65);
		basket = Toolkit.getDefaultToolkit().createImage("images/basket.jpg");
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

	public Boolean BasketisFull()
	{
		return isFull;
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





