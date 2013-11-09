//Johnny Rusnak
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import FactoryResources.*;

public class KittingStand {
	Image kittingStand;
	Boolean isComplete=false;
	int xpos, ypos;
	Point  p1, p2;
	boolean p1occupied, p2occupied;
	
	
	public KittingStand(int x, int y)
	{
		xpos = x;
		ypos = y;
		p1occupied = false;
		p2occupied = false;
		p1 = new Point(xpos+17,ypos+25);
		p2 = new Point ( xpos+17,ypos+150);
		kittingStand = Toolkit.getDefaultToolkit().createImage("images/kittingStand.jpg");
	}
	
	public Boolean isComplete()
	{
		return isComplete;
	}
	
	public boolean p1isOccupied(){
		return p1occupied;
	}
	
	public boolean p2isOccupied(){
		return p2occupied;
	}
	
	public void setP1(boolean status){
		 p1occupied = status;
	}
	
	public void setP2(boolean status){
		 p2occupied = status;
	}
	
	public void UpdateImage(Graphics2D g2) {
		g2.drawImage(kittingStand, xpos, ypos, null);		
	}
}
