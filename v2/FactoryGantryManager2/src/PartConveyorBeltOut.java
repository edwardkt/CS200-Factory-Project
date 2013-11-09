//Johnny Rusnak
import java.awt.*;
import java.awt.geom.*;
import java.util.ArrayList;
import FactoryResources.*;

public class PartConveyorBeltOut{


	Rectangle2D.Double lane;
	Image Lane;
	int xspeed=0;
	int yspeed = 0;
	ArrayList<Rectangle2D.Double> lineList;
	int x, y;
	Boolean laneOn=true;
	int lanecounter=0;
	int width = 260;
	int height = 90;


	public PartConveyorBeltOut(int newx, int newy) {
		x = newx;
		y = newy;

		lane = new Rectangle2D.Double( x, y, width, height );
		Lane = Toolkit.getDefaultToolkit().createImage("images/xmas_lane.fw.png");
		
		lineList = new ArrayList<Rectangle2D.Double>();
		lineList.add(new Rectangle2D.Double(x, y, 5, 90));
		lineList.add(new Rectangle2D.Double(x+20, y, 5, 90));
		lineList.add(new Rectangle2D.Double(x+40, y, 5, 90));
		lineList.add(new Rectangle2D.Double(x+60, y, 5, 90));
		lineList.add(new Rectangle2D.Double(x+80, y, 5, 90));
		lineList.add(new Rectangle2D.Double(x+100, y, 5, 90));
		lineList.add(new Rectangle2D.Double(x+120, y, 5, 90));
		lineList.add(new Rectangle2D.Double(x+140, y, 5, 90));
		lineList.add(new Rectangle2D.Double(x+160, y, 5, 90));
		lineList.add(new Rectangle2D.Double(x+180, y, 5, 90));
		lineList.add(new Rectangle2D.Double(x+200, y, 5, 90));
		lineList.add(new Rectangle2D.Double(x+220, y, 5, 90));
		lineList.add(new Rectangle2D.Double(x+240, y, 5, 90));
		lineList.add(new Rectangle2D.Double(x+257, y, 5, 90));
	}

	public void changeDimensions(int x, int y)
	{
		width = x;
		height = y;
	}


	public void UpdateImage(Graphics2D g2) {
		//drawing the lanes
		g2.drawImage(Lane, x, y, width, height, null);
		g2.setColor(Color.WHITE);
		g2.fill(lineList.get(0));
		g2.fill(lineList.get(1));
		g2.fill(lineList.get(2));
		g2.fill(lineList.get(3));
		g2.fill(lineList.get(4));
		g2.fill(lineList.get(5));
		g2.fill(lineList.get(6));
		g2.fill(lineList.get(7));
		g2.fill(lineList.get(8));
		g2.fill(lineList.get(9));
		g2.fill(lineList.get(10));
		g2.fill(lineList.get(11));
		g2.fill(lineList.get(12));
		g2.fill(lineList.get(13));
		if(laneOn)
		{
			lanecounter++;
			if(lanecounter%20==0)
			{
				
				lineList.add(0, new Rectangle2D.Double( x, y, 5, 90 ));
				lineList.remove(13);
			}
			if(lanecounter<100)
			{
				for(int i=0; i<14; i++)
				{
				double x = lineList.get(i).getX();
				double y = lineList.get(i).getY();
				lineList.get(i).setFrame( x+1, y, lineList.get(i).getWidth(), lineList.get(i).getHeight()); 
				}
			}
			if(lanecounter==100)
			{
				lanecounter=0;
			}
		}
	}
}




