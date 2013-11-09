//Johnny Ulf
import java.awt.Graphics2D;
import java.util.ArrayList;
import FactoryResources.*;


public class UnitA {
	FactoryProductionClient client;
	ArrayList<UnitB> lanes;
	GantryRobot gan;
	int feeder; // keeps track of 
	ArrayList<Box> boxes;
	PartConveyorBeltIn partBeltIn;
	PartConveyorBeltOut partBeltOut;
	Boolean boxVisible;
	int xgoto , ygoto, dropoffcounter;

	public UnitA(UnitB newlane1,UnitB newlane2,UnitB newlane3,UnitB newlane4, FactoryProductionClient client) {
		this.client = client;
		boxes = new ArrayList<Box>();
		lanes = new ArrayList<UnitB>();
		lanes.add(newlane1);
		lanes.add(newlane2);
		lanes.add(newlane3);
		lanes.add(newlane4);
		
		
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



		xgoto = 1000;
		ygoto = 225;
		boxVisible=true;
		////////who changed this
		boxes.get(boxes.size()-1).giveNewPos(xgoto, ygoto);
	}

	public void updateImage(Graphics2D g2){		
		partBeltIn.UpdateImage(g2);
		partBeltOut.UpdateImage(g2);
		gan.UpdateImage(g2);
		if(!boxes.isEmpty())
			boxes.get(0).updateImage(g2);

		

		// determine which lane to send the box too
		if(!boxes.isEmpty()){
	


		
			if(boxes.get(0).hasArrived(1000,225)){
				gan.giveNewPos(1000, 225);
			}

			if(gan.hasArrived(1000,225)){
					if(boxes.get(0).getLane()==-1){
					outerloop:
					for(int i =0;i<4;i++){//keeps track of the feeder
						for(int j =0;j<2;j++){//keeps track of the lane
							if(boxes.get(0).part.equals(lanes.get(i).getPart(j))||lanes.get(i).getPart(j).equals("")){
							//	System.out.println("deciding which lane"+lanes.get(i).getPart(j));
								feeder = i; boxes.get(0).setLane(2*i+j);
								client.sendGantryRobotOrder(boxes.get(0));
								break outerloop;
							}
						}
					}
				}
				
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
					outerloop:
					for(int i =0;i<4;i++){
						for(int j =0;j<2;j++){
							if(boxes.get(0).lane == i*2+j){
								lanes.get(i).addPart(j,boxes.get(0).part);
								//System.out.println("A   "+boxes.get(0).part);
								break outerloop;
							}
						}
					}
				}
			}


			if(boxes.get(0).hasArrived(1000,425)){
				boxes.get(0).giveNewPos(1300, 425);
			}

			if(boxes.get(0).hasArrived(1300,425)){
				boxes.remove(0);			
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
