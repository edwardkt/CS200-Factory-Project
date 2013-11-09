package FactoryResources;

import java.io.*;

public class BoxUpdater implements Serializable{
	private int numOfParts;
	private int lane;
	
	public BoxUpdater(){}
	
	public BoxUpdater(int num, int lane){
		this.numOfParts = num;
		this.lane  = lane;
	}
	
	//getter
	public int getNumOfParts(){
		return numOfParts;
	}
	
	public int getLane(){
		return lane;
	}
	
	
	
	
}
