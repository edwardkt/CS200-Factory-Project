//Ulf Bonde Akerlind and Johnny Rusnak
// last edited 11/12/2012


import java.awt.*;
import java.awt.geom.*;
import java.util.ArrayList;
import FactoryResources.*;
import Graphics.*;
import java.util.*;

public class PartRobot{


	Rectangle2D.Double toptrack,bottomtrack, bar, arm;
	Image Robot;
	int xspeed=0;
	int yspeed = 0;
	ArrayList<Rectangle2D> lineList;
	int x,y,xgoto,ygoto, width, height;
	Boolean occupied;
	int laneindex;
	ArrayList<Nest> nests = new ArrayList<Nest>();
	ArrayList<kitBox> kits = new ArrayList<kitBox>();
	PartGraph temp = new PartGraph();
	int count =0;
	int drop = 0;
	boolean partIsDropped = false;
	boolean kitNotPerfect = false;

	public PartRobot(int newx, int newy, ArrayList<Nest> nests, ArrayList<kitBox> kits) {
		
		this.nests = nests;
		this.kits = kits;
		
		x = newx;
		y = newy;
		xgoto = newx;
		ygoto = newy;
		occupied = false;
		width = 150;
		height = 400;
		Robot = Toolkit.getDefaultToolkit().createImage("images/xmas_partsRobot.fw.png");
		toptrack = new Rectangle2D.Double( x, y, width, 5 );
		bottomtrack = new Rectangle2D.Double( x, y+height-5, width, 5 );
		bar = new Rectangle2D.Double( x+width/2, y, 5, height );
		arm =new Rectangle2D.Double( x+width/2-12, y+height/2-12, 30, 30 );
	}

	public void giveNewPos(int newx, int newy){
		xgoto=newx+20;
		ygoto = newy+10;
	}
	
	public boolean isOccupied(){
		return occupied;
	}
	
	public void setOccupied(boolean status){
		occupied = status;
	}
	
	public void setIndex(int x)
	{	laneindex = x;}

	public void UpdateImage(Graphics2D g2) {
		
		//redrawing the rails
		//g2.setColor(Color.BLACK);
		//g2.fill(toptrack);
		//g2.fill(bottomtrack);

		//moving the bar of the robot
		xspeed = getXDir();
		yspeed = getYDir();
			bar.setFrame(bar.getX()+xspeed,y,5,height);

		// moving the robot part on the bar
		arm.setFrame(bar.getX()-12,arm.getY()+yspeed,30,30);
		
		//if robot has arrived at nest, go to kitting stand
		if( (bar.getX() == 360 && arm.getY() == 150 && laneindex == 1)|| (bar.getX() == 360 && arm.getY() == 182&& laneindex == 2)|| 
				(bar.getX() == 360 && arm.getY() == 250&& laneindex == 3)|| (bar.getX() == 360 && arm.getY() == 282&& laneindex == 4)|| 
				(bar.getX() == 360 && arm.getY() == 350&& laneindex == 5)|| (bar.getX() == 360 && arm.getY() == 382&& laneindex == 6)||
				(bar.getX() == 360 && arm.getY() == 450&& laneindex == 7)|| (bar.getX() == 360 && arm.getY() == 482&& laneindex == 8) )
		{
			xgoto = 230; ygoto = 230;
			if(!nests.get(laneindex-1).isEmpty())
			{
				temp = nests.get(laneindex-1).getPartInNest();
				nests.get(laneindex-1).removeAPart();
				
				Random r = new Random();
				drop = r.nextInt(100) + 1;
				if(drop<40)
				{
					partIsDropped = true;
					kitNotPerfect = true;
				}
			}
		}
		
		//if robot has arrived at kitting stand, go back to original position
		if(bar.getX()==230&&arm.getY()==230)
		{
			xgoto = 250; ygoto = 150;
			if(!nests.get(laneindex-1).isEmpty())
			{
				if(partIsDropped == false)
					kits.get(0).addPartsInKit(count,temp.getFileName());
				else
					kits.get(0).addPartsInKit(count,"");
					
				count++;
				if(count==8)
					count = 0;
				partIsDropped = false;
			}
		}

		//paint arm
		//g2.setColor(Color.GRAY);
		//g2.fill(arm);

		g2.drawImage(Robot, (int)bar.getX()-30,(int)arm.getY()+yspeed, null);
		//paint bar
		//g2.setColor(Color.BLACK);
		//g2.fill(bar);
	}

	
	public boolean hasArrived(int newx, int newy){
		if(arm.getY()-10==newy && bar.getX()-20 == newx)
			return true;
		return false;
	}
	int getXDir(){
		if((xgoto-bar.getX())<0){
			return -1;
		}
		else if(xgoto-bar.getX()>0){
			return 1;
		}
		else
		return 0;
		
	}
	
	int getYDir(){
		if((ygoto-arm.getY())<0){
			return -1;
		}
		else if(ygoto-arm.getY()>0){
			return 1;
		}
		else
		return 0;
		
	}
	
	//method for adding parts in kit
	
	public void addPartsToKit(kitBox kit){
		for(int i=0; i<8; i++){
			kit.addPartsInKit(i,"images/parts/ct_ball_purple.png");
		}
	}
	
	public boolean kitStatus()
	{
		return kitNotPerfect;
	}
	
	
}




