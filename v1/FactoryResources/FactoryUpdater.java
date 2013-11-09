package FactoryResources;

import java.io.*;
import java.util.*;

public class FactoryUpdater implements Serializable{
	ArrayList<BoxUpdater> boxes = new ArrayList<BoxUpdater>();
	BoxUpdater boxUpdater = new BoxUpdater();
	
	public FactoryUpdater(){}
	
	public void setBoxUpdater(int num, int lane){
		boxUpdater = new BoxUpdater(num, lane);
		
	}
	
	
	//setters
	public void setBoxes(ArrayList<BoxUpdater> boxes){
		this.boxes = boxes;
	}
	
	//getters
	public ArrayList<BoxUpdater> getBoxes(){
		return boxes;
	}
	
}
