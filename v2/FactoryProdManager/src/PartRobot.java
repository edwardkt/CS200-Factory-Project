//Ulf Bonde Akerlind and Johnny Rusnak
// last edited 11/12/2012


import java.awt.*;
import java.awt.geom.*;
import java.util.ArrayList;
import FactoryResources.*;
import Graphics.PartGraph;

public class PartRobot{


	Rectangle2D.Double toptrack,bottomtrack, bar, arm;
	Image Robot;
	int xspeed=0;
	int yspeed = 0;
	ArrayList<Rectangle2D> lineList;
	int x,y,xgoto,ygoto, width, height;
	Boolean occupied;
	PartGraph part;


	public PartRobot(int newx, int newy) {

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
		g2.drawImage(Robot,  (int)bar.getX()-30, (int)arm.getY()+yspeed, null); 
		//paint arm
		//g2.setColor(Color.GRAY);
		//g2.fill(arm);

		//paint bar
		//g2.setColor(Color.BLACK);
		//g2.fill(bar);
		if(part!=null){
			part.setX((int)bar.getX());
			part.setY((int)arm.getY());
			part.drawPart(g2);
		}
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

	public void setPart(String n){
		part = new PartGraph((int)bar.getX(),(int)arm.getY(),n);

	}
	
	public void deletePart(){
		part = null;
	}
}




