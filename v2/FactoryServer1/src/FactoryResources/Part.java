package FactoryResources;

import javax.swing.ImageIcon;
import java.io.*;

public class Part implements Serializable
{
    //String imageFile = "";
    String name = "";
    int howMany = 0;	//how many parts of this kind will be created
    String description = "";
    int image;
	 String imageName = "";
    
    public Part(){}
	 
	 public Part(int img, String n, int number, String detail)
    {
    	name = n;
    	howMany = number;
    	description = detail;
    	image = img;
    }

	public String getName()
	{	return name;}

    public String getDes()
    {	return description;}
	 
	 public int getHowMany()
	 {	return howMany;}
    
    public int getImage()
    {	return image;}
	 
	 public void setImageName(String s)
	 {	imageName = s;}
	 
	 public String getImageName()
	 {	return imageName;}
}
