import java.awt.Graphics2D;
import FactoryResources.*;


public class UnitC {
	PartRobot partRobot;
	KitRobot kitRobot;
	KittingStand kittingStand;
	KitConveyorBeltOut kitBeltOut;
	@SuppressWarnings("rawtypes")
	KitConveyorBeltIn kitBeltIn;
	public UnitC() {
		kittingStand = new KittingStand(180,200);
		kitRobot = new KitRobot(100,125);
		partRobot = new PartRobot(250, 125);
		kitBeltIn = new KitConveyorBeltIn(0,200);
		kitBeltOut = new KitConveyorBeltOut(0,400);
	}
	
	
	
	public void updateImage(Graphics2D g2){
	
	}

}
