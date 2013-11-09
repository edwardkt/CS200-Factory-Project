import java.io.*;
import java.util.*;

public class FactoryState implements Serializable{
	ArrayList<Kit> kits;
	
	//states of devices in the factory 
	String s = "Nothing written";
	
	public FactoryState(){
		kits = new ArrayList<Kit>();
	}
	
	//getters
	public ArrayList<Kit> getKits(){
		return kits;
	}
	
	//setters
	public void setKits(ArrayList<Kit> kits){
		this.kits = kits;
	}
	 
	public void printState(){
		System.out.println(s);
	}

	public void setString(String s){
		this.s = s;
	}
}
