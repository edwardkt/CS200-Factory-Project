package Graphics;
import java.awt.*;
import java.util.*;

public class Nest {
	
	Image image;
	int x, y, width = 60, height = 40;
	int endOfNest;
	Stack<PartGraph> parts = new Stack<PartGraph>();
	
	
	public Nest(int x, int y){
		image = Toolkit.getDefaultToolkit().createImage("images/basket1.png");
		this.x = x;
		this.y = y;
		endOfNest = x + 10;
	}
	
	public void setPartImage(String fileName){
		//this.fileName = fileName;
	}
	
	public void setEndOfNest(){		
		endOfNest = x + (1 + parts.size()/4)*10;		
		
	}
	
	public int getEndOfNest(){
		return endOfNest;
	}
	
	public void drawNest(Graphics g2){
		g2.drawImage(image, x, y, width, height, null);
		for(PartGraph part: parts)
			part.drawPart(g2);
	}
	
	public void addPart(String fileName){
		int partX = x + (parts.size()/4)*10;
		int partY= y + (parts.size()%4)*10;
		parts.add(new PartGraph(partX,partY,fileName));
		setEndOfNest();
	}
	
	public void removeAPart(){
		parts.pop();
		setEndOfNest();
	}

	public boolean isEmpty() {
		if(parts.isEmpty())
		return true;
		
		return false;
	}

}
