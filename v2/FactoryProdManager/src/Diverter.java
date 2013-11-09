//Johnny Rusnak

import java.awt.*;
import java.awt.geom.Rectangle2D;
import FactoryResources.*;

public class Diverter {
	Rectangle2D.Double diverter, position;
	Image Diverter;
	int height, width;
	Boolean tp=true, bp=false;

	public Diverter(int x, int y)
	{
		height = x;
		width = y;
		diverter = new Rectangle2D.Double(height, width, 25, 65);
		position = new Rectangle2D.Double(height, width, 25, 33);
		Diverter = Toolkit.getDefaultToolkit().createImage("images/xmas_roofTop.fw.png");
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
		g2.drawImage(Diverter, height, width, null);
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
