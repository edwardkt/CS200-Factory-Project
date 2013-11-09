
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.geom.Rectangle2D;
import java.io.*;
import java.util.ArrayList;
import FactoryResources.*;


public class kitBox implements Serializable {
	int x,y, xgoto, ygoto, xspeed, yspeed;
	Image  box;
	int numOfItems, counter, lane;
	ArrayList<String> order, currentParts;
	
	
	public kitBox(int newx, int newy, ArrayList<String> temp ) {
		x=newx;
		y=newy;
		order = new ArrayList<String>();
		currentParts = new ArrayList<String>();
		for(int i  = 0; i < 8 ; i++){
		order.add(temp.get(i));
		currentParts.add("");
		}
	
		box = Toolkit.getDefaultToolkit().createImage("images/xmas_kitSleighBag.fw.png");
		counter = 0;
	}

	public void giveNewPos(int newx, int newy){		
		xgoto=newx;
		ygoto = newy;
	}

	public void updateImage(Graphics2D g2){
		
		xspeed = getXDir();
		yspeed = getYDir();
		x=x+xspeed;
		y=y+yspeed;
		g2.drawImage(box,x,y, 50,50,null);
		
	}
	
// by comparing the currentParts list, and the order list, it can check if they are the same
	// therefore the box is full
	public boolean isFull(){
		
		for(int i =0;i<8;i++){
			if(!currentParts.get(i).equals(order.get(i)))
				return false;
		}	
		return true;
	}
	
	public boolean Empty(){
		counter++;
		if(counter%20==0){
			numOfItems--;
			counter = 0;
			return true;
		}
		return false;
	}
	
	int getXDir(){
		if((xgoto-x)<0){
			return -1;
		}
		else if(xgoto-x>0){
			return 1;
		}
		else
		return 0;
		
	}
	
	int getYDir(){
		if((ygoto-y)<0){
			return -1;
		}
		else if(ygoto-y>0){
			return 1;
		}
		else
		return 0;
		
	}
	
	public boolean hasArrived(int newx, int newy){
		if(x==newx && y==newy)
			return true;
		else
			return false;
		
	}
	


	public void addPart(int part) {
		currentParts.set(part,order.get(part));
		//System.out.println("order "+order);
		//System.out.println("currentParts "+currentParts);
		
	}
	
	

}
