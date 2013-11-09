import javax.swing.*;

public class Part extends ImageIcon {
	//private ImageIcon image;
	public int x,y;
	
	public Part(String name, int x, int y){
		super(name);
		this.x = x;
		this.y = y;
	}
	
	public void setLocation(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	
	
	

}
