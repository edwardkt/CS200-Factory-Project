package Graphics;
import java.awt.*;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;

import javax.swing.*;

public class PartGraph{
	
	Image image;
	String fileName = "";
	int x =0,y =0;
	int width = 10, height = 10;
	int lane = 0;
	
	
	
	public PartGraph(){
		image = Toolkit.getDefaultToolkit().createImage(fileName);
		fileName = "No image";
		
	}
	
	public PartGraph(int x, int y, String fileName){
		this.x = x;
		this.y = y;
		image = Toolkit.getDefaultToolkit().createImage(fileName);
		this.fileName = fileName;
	}
	
	public void drawPart(Graphics g2){
		g2.drawImage(image, x, y, width, height, null);
	}
	
	public void drawPart(Graphics g2, int x, int y){
		this.x = x;
		this.y = y;
		g2.drawImage(image, x, y, width, height, null);
	}
	
	//setters
	public void setX(int x){
		this.x = x;
	}
	public void setY(int y){
		this.y = y;
	}

	//getters
	public int getX(){
		return x;
	}
	public int getY(){
		return y;
	}
	public Image getImage(){
		return image;
	}
	public String getFileName(){
		return fileName;
	}



	
}
