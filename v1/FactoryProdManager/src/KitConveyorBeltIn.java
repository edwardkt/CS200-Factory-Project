//Johnny Rusnak
import java.awt.*;
import java.awt.geom.*;
import java.util.ArrayList;
import FactoryResources.*;

public class KitConveyorBeltIn{


	Rectangle2D.Double lane;
	int xspeed=0;
	int yspeed = 0;
	ArrayList<Rectangle2D.Double> lineList;
	int x, y;
	int width = 60;
	int height = 90;
	int lanecounter=0;
	Boolean laneOn = true;
	Point pickUpPoint;


	public KitConveyorBeltIn(int newx, int newy) {
		x = newx;
		y = newy;
		pickUpPoint = new Point(x+width, y + height/2 );
		lane = new Rectangle2D.Double( x, y, width, height );

		lineList = new ArrayList<Rectangle2D.Double>();
		lineList.add(new Rectangle2D.Double(x, y, 5, 90));
		lineList.add(new Rectangle2D.Double(x+20, y, 5, 90));
		lineList.add(new Rectangle2D.Double(x+40, y, 5, 90));
		lineList.add(new Rectangle2D.Double(x+57, y, 5, 90));
	}

	public void changeDimensions(int x, int y)
	{
		width = x;
		height = y;
	}
	
	public void laneStatus(Boolean status)
	{
		laneOn = status;
	}


	public void UpdateImage(Graphics2D g2) {
		//drawing the lanes
		g2.setColor(Color.GRAY);
		g2.fill(lane);
		g2.setColor(Color.BLACK);
		g2.fill(lineList.get(0));
		g2.fill(lineList.get(1));
		g2.fill(lineList.get(2));
		//g2.fill(lineList.get(3));
		if(laneOn)
		{
			lanecounter++;
			if(lanecounter%20==0)
			{
				lineList.remove(3);
				lineList.add(0, new Rectangle2D.Double( x, y, 5, 90 ));
			}
			if(lanecounter<100)
			{
				for(int i=0; i<4; i++)
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




