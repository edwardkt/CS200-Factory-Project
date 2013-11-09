
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
	private int[] order={0,0,0,0,0,0,0,0};
	int[] currentParts={0,0,0,0,0,0,0,0};
	
	public kitBox(int newx, int newy, ArrayList<Integer> temp ) {
		x=newx;
		y=newy;
		for(int i  = 0; i < 8 ; i++){
		order[i]=temp.get(i);
		}
	
		box = Toolkit.getDefaultToolkit().createImage("images/pallet.jpg");
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
	
	public boolean isFull(){
		int num = 0;
		int finalnum = 0;
		for(int i =0;i<8;i++){
			finalnum= finalnum +order[i];
			num = num + currentParts[i];
		}
		//System.out.println(finalnum + " and "+ num);
		if(num==finalnum)
			return true;
					
		return false;
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
	
	//getters
	public int getNumOfItems(){
		return numOfItems;
	}
	
	
	public int getLane(){
		return lane;
	}
	
	

}
