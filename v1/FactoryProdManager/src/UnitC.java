import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;
import FactoryResources.*;


public class UnitC {
	PartRobot partRobot;
	KitRobot kitRobot;
	KittingStand kittingStand;
	KitConveyorBeltOut kitBeltOut;
	KitConveyorBeltIn kitBeltIn;
	//designChange: int to string
	ArrayList<String> nest;
	ArrayList<String>  currentOrder;
	///
	ArrayList<UnitB> lanes;
	ArrayList<Point> lanePickUpPoint;
	//designChange: Integer to String
	ArrayList<ArrayList<String>> kitOrders;
	ArrayList<kitBox> kits;
	Point currentPoint;
	int part,lane;
	boolean occupied;

	public UnitC(UnitB nlane1, UnitB nlane2, UnitB nlane3, UnitB nlane4) {
		occupied = false;
		lanes = new ArrayList<UnitB>();
		lanePickUpPoint = new ArrayList<Point>();
		kitOrders = new ArrayList<ArrayList<String>>();
		lanes.add(nlane1);
		lanes.add(nlane2);
		lanes.add(nlane3);
		lanes.add(nlane4);
		nest = new ArrayList<String>();
		currentOrder = new ArrayList<String>();
		for(int i = 0 ; i<8;i++){
			nest.add("");
			currentOrder.add("");
		}


		kits = new ArrayList<kitBox>();

		for(int i=0;i<4;i++){
			lanePickUpPoint.add( new Point(lanes.get(i).x-55,lanes.get(i).y-10));
			lanePickUpPoint.add( new Point(lanes.get(i).x-55,lanes.get(i).y+25));
		}
		kittingStand = new KittingStand(180,200);
		currentPoint = new Point(kittingStand.p1.x,kittingStand.p1.y);
		kitRobot = new KitRobot(50,125);
		partRobot = new PartRobot(225, 125);
		kitBeltIn = new KitConveyorBeltIn(0,200);
		kitBeltOut = new KitConveyorBeltOut(0,400);

	}



	public void updateImage(Graphics2D g2){

		partCheck();

		buildOrder();



		kitBeltIn.UpdateImage(g2); 
		kitBeltOut.UpdateImage(g2);
		kittingStand.UpdateImage(g2);




		if(!kits.isEmpty()){
			kits.get(0).updateImage(g2);

			// if a new empty kit has arrived at the input converyor belt and the kit robot isnt busy
			if(!kitRobot.isOccupied() && kits.get(0).hasArrived(kitBeltIn.pickUpPoint.x, kitBeltIn.pickUpPoint.y)){
				//System.out.println(1);
				kitRobot.giveNewPos(kitBeltIn.pickUpPoint.x, kitBeltIn.pickUpPoint.y);
				kitRobot.setOccupied(true);

			}


			// if the robot has arrived at the pick up point, pick up the box
			if(kitRobot.hasArrived(kitBeltIn.pickUpPoint.x, kitBeltIn.pickUpPoint.y)
					&& kits.get(0).hasArrived(kitBeltIn.pickUpPoint.x, kitBeltIn.pickUpPoint.y)){
				//System.out.println(2);
				if(!kittingStand.p1isOccupied()){
					currentPoint.x = kittingStand.p1.x;
					currentPoint.y = kittingStand.p1.y;
					kitRobot.giveNewPos(currentPoint.x,currentPoint.y);
					kits.get(0).giveNewPos(currentPoint.x,currentPoint.y);
					kittingStand.setP1(true);
				}
				else if(!kittingStand.p2isOccupied()){
					currentPoint.x = kittingStand.p2.x;
					currentPoint.y = kittingStand.p2.y;
					kitRobot.giveNewPos(currentPoint.x,currentPoint.y);
					kits.get(0).giveNewPos(currentPoint.x,currentPoint.y);
					kittingStand.setP2(true);
				}

			}





			////		// if the kit is not full, and the robot is at the kit, do nothing, move the robot else where
			if(!kits.get(0).isFull()  && kitRobot.hasArrived(currentPoint.x,currentPoint.y)){
				//System.out.println(3);
				kitRobot.setOccupied(false);
				kitRobot.giveNewPos(kitBeltIn.pickUpPoint.x, kitBeltIn.pickUpPoint.y);
			}
			// if the kit IS full, and kit is at the stand, but the robot is not, move the robot to the stand
			//System.out.println(kits.get(0).isFull());
			if(kits.get(0).isFull() && !kitRobot.isOccupied() && !kitRobot.hasArrived(currentPoint.x,currentPoint.y)
					&& kits.get(0).hasArrived(currentPoint.x,currentPoint.y)){
				//System.out.println(4);
				kitRobot.giveNewPos(currentPoint.x,currentPoint.y);
				kitRobot.setOccupied(true);

			}




			// if the kit IS full, and the robot is at the kit, move to the out conveyor belt
			if(kits.get(0).isFull()  && kitRobot.hasArrived(currentPoint.x,currentPoint.y)){
				//System.out.println(5);
				kitRobot.giveNewPos(kitBeltOut.dropOffPoint.x, kitBeltOut.dropOffPoint.y);
				kits.get(0).giveNewPos(kitBeltOut.dropOffPoint.x, kitBeltOut.dropOffPoint.y);
				kitRobot.setOccupied(false);
			}
			// if the kit is at the converyor belt out, take the kit out of the screen
			if(kits.get(0).hasArrived(kitBeltOut.dropOffPoint.x, kitBeltOut.dropOffPoint.y)){
				//System.out.println(6);
				kits.get(0).giveNewPos(0, kitBeltOut.dropOffPoint.y);
			}

			if(kits.get(0).hasArrived(0, kitBeltOut.dropOffPoint.y)){
				//System.out.println(7);
				kits.remove(0);	
				currentPoint.x=0;
				currentPoint.y=0;
				if(currentPoint.x==kittingStand.p1.x){
					kittingStand.setP1(false);
				}
				else{
					kittingStand.setP2(false);

				}
				occupied = false;
				System.out.println("top to bottom");
				// change the dropoffpoint for the kitting stand somehow
				if(!kits.isEmpty()){
					if(!occupied){
						for(int i = 0;i<8;i++){
							System.out.println(kitOrders.size());
							currentOrder.set(i, kitOrders.get(0).get(i));
							//System.out.println(kitOrders.get(0));
						}
						kitOrders.remove(0);
						occupied = true;
					}
				}
			}
		}

		partRobot.UpdateImage(g2);
		kitRobot.UpdateImage(g2);
	}

	public void addOrder(ArrayList<String> temp, int numberofKits){

		for(int i = numberofKits;i>0;i--){
			kitOrders.add(new ArrayList<String>(temp));
			kits.add(new kitBox(0,225,temp));
			kits.get(kits.size()-1).giveNewPos(kitBeltIn.pickUpPoint.x, kitBeltIn.pickUpPoint.y);
			//System.out.println(kitOrders.get(0));

		}
		

		if(!occupied){
			for(int i = 0;i<8;i++){
				currentOrder.set(i, kitOrders.get(0).get(i));
			}
			kitOrders.remove(0);
			
			occupied = true;
		}

	}


	public void buildOrder(){



		///////////////////////////////////////////////

		//////////when the robot is empty

		if(!partRobot.isOccupied() && currentPoint.y !=0){
			outerloop:
				for(int i = 0;i<4;i++){// i keeps track of what unitB to go to
					for(int j=0;j<2;j++){// j keeps track of which lane in the ith unitb to pick up from
						for(int h = 0; h<8;h++){// h keeps track of the h:th spot in the ordered list of parts for the kit
							//System.out.println("nest "+nest);
							//System.out.println("currentOrder "+currentOrder);
							if(nest.get(2*i+j).equals(currentOrder.get(h)) &&  !currentOrder.get(h).equals("")){
								partRobot.giveNewPos(lanePickUpPoint.get(2*i+j).x,lanePickUpPoint.get(2*i+j).y);
								if(partRobot.hasArrived(lanePickUpPoint.get(2*i+j).x,lanePickUpPoint.get(2*i+j).y)){
									partRobot.giveNewPos(currentPoint.x,currentPoint.y);
									partRobot.setOccupied(true);
									lanes.get(i).removePart(j);
									currentOrder.set(h,"");
									part = h;

									break outerloop;	
								}
								else
									break outerloop;
							}


						}

					}
				}
		}
		///////////when the robot is moving parts
		else if(partRobot.isOccupied()){
			if(partRobot.hasArrived(currentPoint.x,currentPoint.y)){
				if(!kits.isEmpty()){
					kits.get(0).addPart(part);
				}
				partRobot.setOccupied(false);
			}
		}



	}//end buildorder



	public void partCheck(){

		nest.set(0,lanes.get(0).checkUpper());
		nest.set(1,lanes.get(0).checkLower());
		nest.set(2,lanes.get(1).checkUpper());
		nest.set(3,lanes.get(1).checkLower());
		nest.set(4,lanes.get(2).checkUpper());
		nest.set(5,lanes.get(2).checkLower());
		nest.set(6,lanes.get(3).checkUpper());
		nest.set(7,lanes.get(3).checkLower());
		//System.out.println(nest);


	}

}
