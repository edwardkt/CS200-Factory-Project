//Johnny Rusnak

import java.awt.*;
import java.awt.geom.Rectangle2D;
import FactoryResources.*;

public class Feeder {
	Rectangle2D.Double feeder;
	//Image;
	int height, width;
	Boolean occupied = false;

	public Feeder(int x, int y)
	{
		height = x;
		width = y;
		feeder = new Rectangle2D.Double(height, width, 50, 65);
	}
	
	public void setOccupied(Boolean occ)
	{
		occupied = occ;	
	}
	
	public Boolean isOccupied()
	{
		return occupied;
	}

	public void UpdateImage(Graphics2D g2) 
	{
		g2.setColor(Color.WHITE);
		g2.fill(feeder);
	}
}


