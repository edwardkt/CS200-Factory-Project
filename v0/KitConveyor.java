import java.awt.*;
import java.awt.geom.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;

public class KitConveyor{
    Rectangle2D.Double backgroundRectangle;
    int width, height;
    int speed=1;
	ArrayList<Rectangle2D> lineList;
	int x,y;
    public KitConveyor(int newx, int newy,int newwidth, int newheight) {
    	x = newx;
    	y = newy;
    	width = newwidth;
    	height =newheight;
		//Make the lines go throuhgh the rectangle at the starting position
		lineList = new ArrayList<Rectangle2D>();
		for(int i =0;i<height/10;i++){
			lineList.add(new Rectangle2D.Double(x,y+i*(10),width,1));
		}

		//Make the background rectangle to "erase" the screen
		backgroundRectangle = new Rectangle2D.Double( x, y, width, height );
    }
    
  /*  public static void main(String[] args ) {
    
		KitConveyor b = new KitConveyor();

		b.setSize( 400, 300 );
		b.setVisible(true);
		b.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
	
		new Timer( 50, b ).start();
    }*/
    
   

public void UpdateImage(Graphics2D g2) {
	  
		//This will be called by the Timer
	for(int i=0;i<lineList.size();i++)
		lineList.get(i).setFrame(x,((lineList.get(i).getY()+speed-y)%height+y), lineList.get(i).getWidth(), lineList.get(i).getHeight());
	g2.setColor(Color.BLACK);
	g2.fill( backgroundRectangle);
	g2.setColor(Color.WHITE);
	for(int i=0;i<lineList.size();i++)
		g2.fill(lineList.get(i));
    
   }
}
	
	
	

