package FactoryResources;

import java.io.*;

public class BoxUpdater implements Serializable{
	private int numOfParts = 0;
	private int lane = -1;
	private String fileName = "No image";
	
	public BoxUpdater(){}
	
	public BoxUpdater(int num, int lane){
		this.numOfParts = num;
		this.lane  = lane;
	}
	
	//setters
	public void setNumOfParts(int number){
		this.numOfParts = number;
	}
	public void setLane(int lane){
		this.lane = lane;
	}
	public void setImage(String fileName){
		this.fileName = fileName;
	}
	
	
	//getters
	public int getNumOfParts(){
		return numOfParts;
	}	
	public int getLane(){
		return lane;
	}	
	public String getFileName(){
		return fileName;
	}
	
	
	
	
}
