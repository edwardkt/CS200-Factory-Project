
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.geom.Rectangle2D;
import java.io.*;
import java.util.ArrayList;
import java.util.TreeMap;
import FactoryResources.*;
import Graphics.PartGraph;


public class kitBox implements Serializable {
	int x,y, xgoto, ygoto, xspeed, yspeed;
	int width = 50, height = 50;
	Image  box,kaboom;
	int numOfItems, counter, lane;
	private int[] order={0,0,0,0,0,0,0,0};
	int[] currentParts={0,0,0,0,0,0,0,0};
	boolean boxGone = false;
	
	TreeMap<Integer, PartGraph> partsInKit = new TreeMap<Integer, PartGraph>();  
	
	
	
	public kitBox(int newx, int newy, ArrayList<Integer> temp ) {
		x=newx;
		y=newy;
		for(int i  = 0; i < 8 ; i++){
		order[i]=temp.get(i);
		}
	
		kaboom = Toolkit.getDefaultToolkit().createImage("images/kaboom.png");
		box = Toolkit.getDefaultToolkit().createImage("images/xmas_kitSleighBag.fw.png");
		counter = 0;
		
		/*
		for(int i=0; i<8; i++){
			addPartsInKit(i,"images/parts/ct_ball_purple.png");
		}*/
		
		
	}

	public void giveNewPos(int newx, int newy){		
		xgoto=newx;
		ygoto = newy;
	}
	
	
	public void moveParts(){
		for(int i=0; i<8; i++){
			if(partsInKit.containsKey(i)){
				partsInKit.get(i).setX(partsInKit.get(i).getX() + xspeed);
				partsInKit.get(i).setY(partsInKit.get(i).getY() + yspeed);
			}
		}
		
	}
	

	public void updateImage(Graphics2D g2){
		
		xspeed = getXDir();
		yspeed = getYDir();
		x=x+xspeed;
		y=y+yspeed;
		g2.drawImage(box,x,y, 50,50,null);
		moveParts();
	
		for(int i=0; i<8; i++){
			if(partsInKit.containsKey(i)){
				partsInKit.get(i).drawPart(g2);
			}
		}
		
		if(x == 200 && y >= 600 && y <675)
			g2.drawImage(kaboom,150,535,200,200,null);
	}
	public int getX()
	{	return x;}
	public int getY()
	{	return y;}
	
	public boolean isBoxGone()
	{	return boxGone;}
	public void setBoxGone(boolean x)
	{	boxGone = x;}
	
	public boolean isFull(){
		int num = 0;
		int finalnum = 0;
		for(int i =0;i<8;i++){
			finalnum= finalnum +order[i];
			num = num + currentParts[i];
		}
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
	
	
	public void addPartsInKit(Integer partNo,String fileName){
		PartGraph value = new PartGraph(x+partNo%2*width/2+5, y + partNo/2*height/4, fileName);		
		partsInKit.put(partNo, value);
	}
	
	
	
	
	
	//getters
	public int getNumOfItems(){
		return numOfItems;
	}
	
	
	public int getLane(){
		return lane;
	}
	
	
	
	
	

}
