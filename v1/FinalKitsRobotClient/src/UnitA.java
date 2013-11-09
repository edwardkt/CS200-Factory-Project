//Johnny Ulf
import java.awt.Graphics2D;
import java.util.ArrayList;
import FactoryResources.*;


public class UnitA {
	//FactoryProductionClient client;
	UnitB lane1,lane2,lane3,lane4;
	GantryRobot gan;
	int feeder; // keeps track of 
	ArrayList<Box> boxes;
	PartConveyorBeltIn partBeltIn;
	PartConveyorBeltOut partBeltOut;
	Boolean boxVisible;
	int xgoto , ygoto, dropoffcounter;

	public UnitA(UnitB newlane1,UnitB newlane2,UnitB newlane3,UnitB newlane4) {
		//this.client = client;
		boxes = new ArrayList<Box>();
		lane1=newlane1;
		lane2=newlane2;
		lane3=newlane3;
		lane4=newlane4;
		boxVisible = false;
		partBeltIn = new PartConveyorBeltIn(1000,200);
		partBeltOut = new PartConveyorBeltOut(1000,400);
		gan = new GantryRobot(820,125, this); //gantry robot
		gan.giveNewPos(900, 155); // give position to gr
	}

	public boolean restState(){
		return false;
	}
	
	public void addBox(Box newbox){ // add box to array and initialize
		boxes.add(newbox);
		
		/*
		ArrayList<BoxUpdater> boxList = new ArrayList<BoxUpdater>();
		for(int i=0; i<boxes.size(); i++){
			int num = boxes.get(i).getNumOfItems();
			int lane = boxes.get(i).getLane();
			new BoxUpdater();
			
			boxList.add(new BoxUpdater(num, lane));
		}
		client.sendGantryRobotOrder(boxList);
		*/
		
		xgoto = 1000;
		ygoto = 225;
		boxVisible=true;
		////////who changed this
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
			
			
			if(boxes.get(0).hasArrived(1000,225)){
				gan.giveNewPos(1000, 225);
			}

			if(gan.hasArrived(1000,225)){
				xgoto = 825;
				ygoto = 157 + 100*feeder;
				gan.giveNewPos(xgoto,ygoto);
				boxes.get(0).giveNewPos(xgoto,ygoto);

			}

			if(gan.hasArrived(825,157 + 100*feeder)){

				if(boxes.get(0).isEmpty()){
					gan.giveNewPos(1000, 425);
					boxes.get(0).giveNewPos(1000, 425);
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
			

			if(boxes.get(0).hasArrived(1000,425)){
				boxes.get(0).giveNewPos(1300, 425);
			}

			if(boxes.get(0).hasArrived(1300,425)){
				boxes.remove(0);			
				
				ArrayList<BoxUpdater> boxList = new ArrayList<BoxUpdater>();
				for(int i=0; i<boxes.size(); i++){
					int num = boxes.get(i).getNumOfItems();
					int lane = boxes.get(i).getLane();
					new BoxUpdater();
					
					boxList.add(new BoxUpdater(num, lane));
				}
				//client.sendGantryRobotOrder(boxList);
			
			}


		}

	}

	public boolean isStandBy(){
		if(boxes.isEmpty())
			return true;

		return false;
	}
	
	//getter
	public ArrayList<Box> getBoxes(){
		return boxes;
	}


}
