//Johnny Rusnak

import java.awt.*;
import java.awt.geom.Rectangle2D;
import FactoryResources.*;

public class Diverter {
	Rectangle2D.Double diverter, position;
	int height, width;
	Boolean tp=true, bp=false;

	public Diverter(int x, int y)
	{
		height = x;
		width = y;
		diverter = new Rectangle2D.Double(height, width, 25, 65);
		position = new Rectangle2D.Double(height, width, 25, 33);
	}

	public void setTopPosition()
	{
		tp=true;
		bp=false;
	}

	public void setBottomPosition(Boolean bP)
	{
		bp=true;
		tp=false;
	}

	public void UpdateImage(Graphics2D g2) 
	{
		g2.setColor(Color.GRAY);
		g2.fill(diverter);
		if(tp==true)
		{
			g2.setColor(Color.GREEN);
			g2.fillOval(height, width, 17, 17);
		}

		if(bp==true)
		{
			g2.setColor(Color.GREEN);
			g2.fillRect(height, width, 17, 17);
		}
	}
}
