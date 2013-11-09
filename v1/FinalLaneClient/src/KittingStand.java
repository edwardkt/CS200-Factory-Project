//Johnny Rusnak
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;

public class KittingStand {
	Image kittingStand;
	Boolean isComplete=false;
	int height, width;
	
	public KittingStand(int x, int y)
	{
		height = x;
		width = y;
		kittingStand = Toolkit.getDefaultToolkit().createImage("images/kittingStand.jpg");
	}
	
	public Boolean isComplete()
	{
		return isComplete;
	}
	
	public void UpdateImage(Graphics2D g2) {
		g2.drawImage(kittingStand, height, width, null);
		
	}
}
