import java.io.*;
import java.util.*;

public class Kit implements Serializable
{
    String name = "";
    int serial = 0;
    String description = "";
    int amount = 0;
	 int[] config = {0,0,0,0,0,0,0,0};
	 
	 public Kit(){}
    
    public Kit(String n, int number, String detail)
    {
    	name = n;
    	serial = number;
    	description = detail;
    }

	public String getName()
	{	return name;}
   
	public String getDes()
   {	return description;}
	
	public int getSerial()
	{	return serial;}
   
   //setAmount and getAmount is setting amount of that type of kit to be made
   public void setAmount(int number)
   {	amount = number;}
	
	public int getAmount()
	{	return amount;}
	
	public int[] getConfig()
	{	return config;}
	
	public ArrayList<Integer> getConfigAL()
	{
		ArrayList<Integer> al = new ArrayList<Integer>();
		for(int i=0; i<8; i++)
			al.add(config[i]);
		return al;	
	}
	
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
