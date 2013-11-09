//Johnny Ulf
import java.awt.Graphics2D;
import java.util.ArrayList;
import FactoryResources.*;


public class UnitA {
	GantryRobotClient client;
	protected boolean inAction = false;
	
	UnitB lane1,lane2,lane3,lane4;
	GantryRobot gan;
	int feeder; // keeps track of 
	ArrayList<Box> boxes;

	PartConveyorBeltIn partBeltIn;
	PartConveyorBeltOut partBeltOut;
	Boolean boxVisible;
	int xgoto , ygoto, dropoffcounter;

	public UnitA(UnitB newlane1,UnitB newlane2,UnitB newlane3,UnitB newlane4,GantryRobotClient client) {
		this.client = client;
		boxes = new ArrayList<Box>();
		lane1=newlane1;
		lane2=newlane2;
		lane3=newlane3;
		lane4=newlane4;
		boxVisible = false;
		partBeltIn = new PartConveyorBeltIn(400,200);
		partBeltOut = new PartConveyorBeltOut(400,400);

		gan = new GantryRobot(220,125, this);


		gan.giveNewPos(300, 155);
	}

	public boolean restState(){

		return false;
	}
	public void addBox(Box newbox){
		boxes.add(newbox);
		xgoto = 400;
		ygoto = 225;
		boxVisible=true;
		boxes.get(boxes.size()-1).giveNewPos(xgoto, ygoto);
	

	}
	public void updateImage(Graphics2D g2){
		
		partBeltIn.UpdateImage(g2);
		partBeltOut.UpdateImage(g2);
		if(!boxes.isEmpty())
			boxes.get(0).updateImage(g2);
		gan.UpdateImage(g2);

		
		if(!boxes.isEmpty()){
			if(boxes.get(0).lane==0 ||boxes.get(0).lane==1)
			feeder=0;
			else if(boxes.get(0).lane==2 ||boxes.get(0).lane==3)
			feeder=1;
			else if(boxes.get(0).lane==4 ||boxes.get(0).lane==5)
			feeder=2;
			else if(boxes.get(0).lane==6 ||boxes.get(0).lane==7)
			feeder=3;
			
			
			if(boxes.get(0).hasArrived(400,225)){
				gan.giveNewPos(400, 225);
			}

			if(gan.hasArrived(400,225)){
				xgoto = 225;
				ygoto = 157 + 100*feeder;
				gan.giveNewPos(xgoto,ygoto);
				boxes.get(0).giveNewPos(xgoto,ygoto);

			}

			if(gan.hasArrived(225,157 + 100*feeder)){

				if(boxes.get(0).isEmpty()){
					gan.giveNewPos(400, 425);
					boxes.get(0).giveNewPos(400, 425);
				}
				if(boxes.get(0).Empty()){
					if(boxes.get(0).lane == 0)
						lane1.addPart(0);
					else if(boxes.get(0).lane==1)
						lane1.addPart(1);
					else if(boxes.get(0).lane==2)
						lane2.addPart(0);
					else if(boxes.get(0).lane==3)
						lane2.addPart(1);
					else if(boxes.get(0).lane==4)
						lane3.addPart(0);
					else if(boxes.get(0).lane==5)
						lane3.addPart(1);		
					else if(boxes.get(0).lane==6)
						lane4.addPart(0);
					else if(boxes.get(0).lane==7)
						lane4.addPart(1);
				}
				}
			

			if(boxes.get(0).hasArrived(400,425)){
				boxes.get(0).giveNewPos(900, 425);
			}

			if(boxes.get(0).hasArrived(900,425)){
				boxes.remove(0);
				
				if(boxes.isEmpty()){
					inAction = false;
				}
			
			}


		}

	}

	public boolean isStandBy(){
		if(boxes.isEmpty())
			return true;

		return false;
	}


}
