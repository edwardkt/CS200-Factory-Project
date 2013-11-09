//Johnny Rusnak
import java.awt.*;
import java.awt.geom.*;
import java.util.ArrayList;

public class PartLane{


	Rectangle2D.Double lane;
	int xspeed=0;
	int yspeed = 0;
	ArrayList<Rectangle2D.Double> lineList;
	int x, y, width, height;
	Boolean laneOn=true;
	int lanecounter=0;

	public PartLane(int newx, int newy) {
		x = newx;
		y = newy;

		width = 400;
		height = 30;
		lane = new Rectangle2D.Double( x, y, width, height );
		
		lineList = new ArrayList<Rectangle2D.Double>();
		//lineList.add(new Rectangle2D.Double(x, y, 3, 30));
		lineList.add(new Rectangle2D.Double(x+20, y, 3, 30));
		lineList.add(new Rectangle2D.Double(x+40, y, 3, 30));
		lineList.add(new Rectangle2D.Double(x+60, y, 3, 30));
		lineList.add(new Rectangle2D.Double(x+80, y, 3, 30));
		lineList.add(new Rectangle2D.Double(x+100, y, 3, 30));
		lineList.add(new Rectangle2D.Double(x+120, y, 3, 30));
		lineList.add(new Rectangle2D.Double(x+140, y, 3, 30));
		lineList.add(new Rectangle2D.Double(x+160, y, 3, 30));
		lineList.add(new Rectangle2D.Double(x+180, y, 3, 30));
		lineList.add(new Rectangle2D.Double(x+200, y, 3, 30));
		lineList.add(new Rectangle2D.Double(x+220, y, 3, 30));
		lineList.add(new Rectangle2D.Double(x+240, y, 3, 30));
		lineList.add(new Rectangle2D.Double(x+260, y, 3, 30));
		lineList.add(new Rectangle2D.Double(x+280, y, 3, 30));
		lineList.add(new Rectangle2D.Double(x+300, y, 3, 30));
		lineList.add(new Rectangle2D.Double(x+320, y, 3, 30));
		lineList.add(new Rectangle2D.Double(x+340, y, 3, 30));
		lineList.add(new Rectangle2D.Double(x+360, y, 3, 30));
		lineList.add(new Rectangle2D.Double(x+380, y, 3, 30));
		lineList.add(new Rectangle2D.Double(x+400, y, 3, 30));
	}
	
	public void laneOn(Boolean laneStatus)
	{
		laneOn=laneStatus;
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
		g2.fill(lineList.get(11));
		g2.fill(lineList.get(12));
		g2.fill(lineList.get(13));
		g2.fill(lineList.get(14));
		g2.fill(lineList.get(15));
		g2.fill(lineList.get(16));
		g2.fill(lineList.get(17));
		g2.fill(lineList.get(18));
		g2.fill(lineList.get(19));
		//g2.fill(lineList.get(20));	
			if(laneOn)
			{
				lanecounter++;
				if(lanecounter%20==0)
				{
					lineList.remove(0);
					lineList.add(19, new Rectangle2D.Double( x+400, y, 3, 30 ));
				}
				if(lanecounter<100)
				{
					for(int i=0; i<20; i++)
					{
					double x = lineList.get(i).getX();
					double y = lineList.get(i).getY();
					lineList.get(i).setFrame( x-1, y, lineList.get(i).getWidth(), lineList.get(i).getHeight()); 
					}
				}
				if(lanecounter==100)
				{
					lanecounter=0;
				}
			}
		}
	}






