package FactoryResources;

import java.io.*;
import java.util.*;

public class FactoryUpdater implements Serializable{

	BoxUpdater boxUpdater = new BoxUpdater();
	
	public FactoryUpdater(){}
	
	//setters
	public void setBoxUpdater(int num, int lane){
		boxUpdater = new BoxUpdater(num, lane);		
	}
	
	//getters
	public BoxUpdater getBoxUpdater(){
		return boxUpdater;
	}
	
}
