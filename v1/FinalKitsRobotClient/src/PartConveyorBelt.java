//Johnny Rusnak
import java.awt.*;
import java.awt.geom.*;
import java.util.ArrayList;
import FactoryResources.*;

public class PartConveyorBelt{


	Rectangle2D.Double lane;
	int xspeed=0;
	int yspeed = 0;
	ArrayList<Rectangle2D.Double> lineList;
	int x, y;
	int width = 200;
	int height = 90;


	public PartConveyorBelt(int newx, int newy) {
		x = newx;
		y = newy;

		lane = new Rectangle2D.Double( x, y, width, height );

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
		lineList.add(new Rectangle2D.Double(x+196, y, 5, 90));
	}

	public void changeDimensions(int x, int y)
	{
		width = x;
		height = y;
	}


	public void UpdateImage(Graphics2D g2) {
		//drawing the lanes
		g2.setColor(Color.GRAY);
		g2.fill(lane);
		g2.setColor(Color.BLACK);
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

	}
}




