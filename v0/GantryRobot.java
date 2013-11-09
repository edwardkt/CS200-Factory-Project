//Ulf Bonde Akerlind
// last edited 11/12/2012


import java.awt.*;
import java.awt.geom.*;
import java.util.ArrayList;

public class GantryRobot{


	Rectangle2D.Double toptrack,bottomtrack, bar, arm;
	int xspeed=0;
	int yspeed = 0;
	ArrayList<Rectangle2D> lineList;
	int x,y,xgoto,ygoto, width, height;


	public GantryRobot(int newx, int newy) {
		x = newx;
		y = newy;

		width = 100;
		height = 295;
		toptrack = new Rectangle2D.Double( x, y, width, 5 );
		bottomtrack = new Rectangle2D.Double( x, y+height-5, width, 5 );
		bar = new Rectangle2D.Double( x+width/2, y, 5, height );
		arm =new Rectangle2D.Double( x+width/2-12, y+height/2-12, 30, 30 );

	}

	public void giveNewPos(int newx, int newy){
		if(newx>=0 && newx<=width)
			xgoto = newx;
		else
			System.out.println("problem in gantryrobot giveNewPos xinput");

		if(xgoto<bar.getX())
			xspeed = -1;
		else if(xgoto>bar.getX())
			xspeed = 1;
		else
			xspeed=0;
		/////////////////////////////////////////////////////////
		if(newy>=0 && newy<=height)
			ygoto = newy;
		else
			System.out.println("problem in gantryrobot giveNewPos yinput");

		if(ygoto<arm.getY())
			yspeed = -1;
		else if(ygoto>arm.getY())
			yspeed = 1;
		else
			yspeed=0;
	}
	public void UpdateImage(Graphics2D g2) {
		//redrawing the rails
		g2.setColor(Color.GRAY);
		g2.fill(toptrack);
		g2.fill(bottomtrack);

		//moving the bar of the robot
		if(bar.getX()!=xgoto)
			bar.setFrame(bar.getX()+xspeed,y,5,height);

		// moving the robot part on the bar
		arm.setFrame(bar.getX()-12,arm.getY(),30,30);
		if(arm.getY()!=ygoto+12){
			arm.setFrame(arm.getX(),arm.getY()+yspeed,30,30);}

		//paint arm
		g2.setColor(Color.GRAY);
		g2.fill(arm);

		//paint bar
		g2.setColor(Color.BLACK);
		g2.fill(bar);


	}
}




