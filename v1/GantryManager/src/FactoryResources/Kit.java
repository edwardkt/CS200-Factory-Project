package FactoryResources;

import java.io.*;
import java.util.*;

public class Kit implements Serializable
{
    String name = "";
    int serial = 0;
    String description = "";
    int amount = 0;
	 int[] config = {0,0,0,0,0,0,0,0};
	 ArrayList<Part> partsList = new ArrayList<Part>();
	 
	 public Kit(){}
    
    public Kit(String n, int number, String detail)
    {
    	name = n;
    	serial = number;
    	description = detail;
    }

	//get the name of the kit
	public String getName()
	{	return name;}
   
	//get the description of the kit
	public String getDes()
   {	return description;}
	
	//get the serial number of the kit
	public int getSerial()
	{	return serial;}
   
   //setAmount and getAmount is setting amount of that type of kit to be made
   public void setAmount(int number)
   {	amount = number;}
	public int getAmount()
	{	return amount;}
	
	//get the int array of the 8 number configuration 
	public int[] getConfig()
	{	return config;}
	
	public ArrayList<Part> getPartsList()
	{	return partsList;}
	
	public void addPart(Part a)
	{	partsList.add(a);}
	
	public void printParts()
	{
		for(int i=0; i<partsList.size(); i++)
			System.out.println(partsList.get(i).getName());
	}
	
	//get the int arraylist of the 8 number configuration
	public ArrayList<Integer> getConfigAL()
	{
		ArrayList<Integer> al = new ArrayList<Integer>();
		for(int i=0; i<8; i++)
			al.add(config[i]);
		return al;	
	}
	
	//methods to update the 8 number configuration when submit button is pressed on the kitmanager.
	public void update1()
	{	config[0]++;}	
	public void update2()
	{	config[1]++;}	
	public void update3()
	{	config[2]++;}
	public void update4()
	{	config[3]++;}
	public void update5()
	{	config[4]++;}
	public void update6()
	{	config[5]++;}
	public void update7()
	{	config[6]++;}
	public void update8()
	{	config[7]++;}
	
	//print the 8 numbers configuration
	public void print()
	{	
		System.out.print(config[0]);
		System.out.print(config[1]);
		System.out.print(config[2]);
		System.out.print(config[3]);
		System.out.print(config[4]);
		System.out.print(config[5]);
		System.out.print(config[6]);
		System.out.print(config[7]);
		System.out.println();
	}
}
