package FactoryResources;
import java.io.*;

public class LanesUpdater implements Serializable{
	BoxUpdater partsInNest = new BoxUpdater();
	BoxUpdater partsInLane = new BoxUpdater();
	
	public LanesUpdater(){		
	}
	
	public LanesUpdater(BoxUpdater nest, BoxUpdater lane){
		partsInNest = nest;
		partsInLane = lane;
	}
	
	//setters
	public void setPartsInNest(BoxUpdater nest){
		partsInNest = nest;
	}
	public void setPartsInLane(BoxUpdater lane){
		partsInLane = lane;
	}
	
	//getters
	public BoxUpdater getPartsInNest(){
		return partsInNest;
	}
	public BoxUpdater getPartsInLane(){
		return partsInLane;
	}
}
