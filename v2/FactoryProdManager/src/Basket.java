

//Johnny Rusnak

import java.awt.*;
import FactoryResources.*;

public class Basket {
	Image basket;
	Boolean isFull=false;
	int height, width;

	public Basket(int x, int y)
	{
		height = x;
		width = y;
		basket = Toolkit.getDefaultToolkit().createImage("images/basket.jpg");
	}

	public Boolean isFull()
	{
		return isFull;
	}

	public void UpdateImage(Graphics2D g2) {
		g2.drawImage(basket, height, width, 60, 65, null);

	}

}
