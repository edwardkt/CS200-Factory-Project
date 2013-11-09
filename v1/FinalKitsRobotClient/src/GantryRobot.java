//Ulf Bonde Akerlind and Johnny Rusnak
// last edited 11/12/2012


import java.awt.*;
import java.awt.geom.*;
import java.util.ArrayList;
import FactoryResources.*;

public class GantryRobot{


	Rectangle2D.Double toptrack,bottomtrack, bar, arm;
	
	int xspeed=0;
	int yspeed = 0;
	UnitA unita;
	ArrayList<Rectangle2D> lineList;
	int x,y,xgoto,ygoto, width, height;
	boolean isOccupied;

	public GantryRobot(int newx, int newy, UnitA newunita) {
		x = newx;
		y = newy;
		unita = newunita;

		width = 250;
		height = 400;
		toptrack = new Rectangle2D.Double( x, y, width, 5 );
		bottomtrack = new Rectangle2D.Double( x, y+height-5, width, 5 );
		bar = new Rectangle2D.Double( x+width/2, y, 5, height );
		arm =new Rectangle2D.Double( x+width/2-12, y+height/2-12, 30, 30 );
		

	}

	public void giveNewPos(int newx, int newy){
		xgoto=newx+20;
		ygoto = newy+10;
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

	
	
	
	public void UpdateImage(Graphics2D g2) {
		
		
	
		//redrawing the rails
		
	
		g2.setColor(Color.GREEN);
		g2.fill(toptrack);
		g2.fill(bottomtrack);

		//moving the bar of the robot
		
		xspeed = getXDir();
		yspeed = getYDir();
			bar.setFrame(bar.getX()+xspeed,y,5,height);

		// moving the robot part on the bar
		arm.setFrame(bar.getX()-12,arm.getY()+yspeed,30,30);

		//paint arm
		g2.setColor(Color.GRAY);
		g2.fill(arm);

		//paint bar
		g2.setColor(Color.BLACK);
		g2.fill(bar);
	}
	


	public boolean hasArrived(int newx, int newy){
		if(arm.getY()-10==newy && bar.getX()-20 == newx)
			return true;
		return false;
	}
	

	



	

}




