//Johnny Rusnak

import java.awt.*;
import java.util.*;
import FactoryResources.*;
import Graphics.*;

public class Pallet {
	Image basket;
	Boolean isFull=false, PalletIn = false, PalletOut = false, PalletNeutral = true;
	int height, width, gtx, gty, xLoc,yLoc;
	
	
	
	

	public Pallet(int x, int y)
	{
		xLoc = 50;
		yLoc = 65;
		
		height = x;
		width = y;
		basket = Toolkit.getDefaultToolkit().createImage("images/pallet.jpg");
	}
	
	public void setHeight(int h){
		height = h;
	}
	
	public void setWidth(int w){
		width = w;
	}
	
	public int getHeight(){
		return height;
	}
	public int status()
	{
		if(height==1000)
		{
			return 1;
		}
		else
			return 0;
	}
	public void gotoCoordinate(int gotox, int gotoy)
	{
		gtx = gotox;
		gty = gotoy;
	}
	
	public void PalletIn()
	{
		PalletIn = true;
		PalletOut = false;
		PalletNeutral = false;
	}
	
	public void PalletOut()
	{
		PalletOut = true;
		PalletIn = false;
		PalletNeutral = false;
	}
	
	public void PalletNeutral()
	{
		PalletNeutral = true;
		PalletOut = false;
		PalletIn = false;
	}
	

	public Boolean isFull()
	{
		return isFull;
	}

	public void UpdateImage(Graphics2D g2) {
		g2.drawImage(basket, height, width, xLoc, yLoc, null);
	}
}


