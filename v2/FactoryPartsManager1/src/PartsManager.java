import javax.swing.*;
import javax.swing.text.JTextComponent;
import javax.swing.JOptionPane;
import javax.swing.JDialog;
import java.io.*;
import java.net.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import FactoryResources.*;
 

public class PartsManager extends JPanel implements ActionListener{
	 PartClient client;
	
    JPanel formPanel, listPanel;
    JLabel topLabel, nameLabel, numberLabel, descriptionLabel, chooseImage, listLabel;
    ArrayList<JLabel> partList;
    ArrayList<Part> parts;
    JTextField nameField, numberField, descripField;
    JButton button1, button2, button3, button4;
	 JButton button5, button6, button7, button8;
	 
    JButton submit;
    JPanel p1 = new JPanel(new FlowLayout());
    JPanel p2 = new JPanel(new FlowLayout());
    JPanel p3 = new JPanel(new FlowLayout());
    JPanel p4 = new JPanel(new FlowLayout());
    JPanel p5 = new JPanel(new FlowLayout());
	 JPanel p5b = new JPanel(new FlowLayout());
    JPanel p6 = new JPanel();
    JPanel p7 = new JPanel();
    JPanel p8 = new JPanel();
    JPanel p9 = new JPanel();
    JPanel p10 = new JPanel();
    JPanel p11 = new JPanel();
    JPanel p12 = new JPanel();
    int numParts = 0;
	 int picIndex = -1;
	 String picName = "";
	 
    JComboBox combo,combo2,combo3,combo4,combo5,combo6,combo7,combo8;
    ImageIcon icon1,icon2,icon3,icon4,icon5,icon6,icon7,icon8;
    String s,s2,s3,s4,s5,s6,s7,s8;
    int image = 0;	 
    Object contents;
    
	 //Empty constructor
    public PartsManager(){}
	 
	 //Constructor
    public PartsManager(PartClient client)
    {
    	this.client = client;
	 
		//there will be 2 sides to this panel, one for form, one for viewing the list
		setLayout(new GridLayout(1,3));			
		setSize(1000,700);
	
		//array allocation
		parts = new ArrayList<Part>();
	
		//image allocation
		icon1 = new ImageIcon("ct_ball_purple.png");
		icon2 = new ImageIcon("ct_ball_blue.png");
		icon3 = new ImageIcon("ct_ball_green.png");
		icon4 = new ImageIcon("ct_ball_grey.png");
		icon5 = new ImageIcon("ct_star_yellow.png");
		icon6 = new ImageIcon("ct_star_green.png");
		icon7 = new ImageIcon("ct_star_red.png");
		icon8 = new ImageIcon("ct_star_rainbow.png");
		
	
		//combo box allocation
		combo = new JComboBox();
		combo2 = new JComboBox();
		combo3 = new JComboBox();
		combo4 = new JComboBox();
		combo5 = new JComboBox();
		combo6 = new JComboBox();
		combo7 = new JComboBox();
		combo8 = new JComboBox();
		
		//initialize 2 main panels
		//formPanel is for creating a new panel
		formPanel = new JPanel();
		formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));		
		formPanel.setSize(700,700);
	
		//listPanel is for viewing existing list of parts
		listPanel = new JPanel();
		listPanel.setLayout(new BoxLayout(listPanel, BoxLayout.Y_AXIS));
		listPanel.setBackground(Color.pink);
		listPanel.setSize(300,700);		
		
		
		//numberLabel = new JLabel("Enter number of part ");
		//numberField = new JTextField(20);
		//add elements to formPanel
		formPanel.add(topLabel = new JLabel("Parts Manager"));
		p1.add(nameLabel = new JLabel("Enter name of part "));
		p1.add(nameField = new JTextField(20));
		formPanel.add(p1);
		p2.add(numberLabel = new JLabel("Enter serial number "));
		p2.add(numberField = new JTextField(20));
		formPanel.add(p2);
		p3.add(descriptionLabel = new JLabel("Enter description of part "));
		p3.add(descripField = new JTextField(20));
		formPanel.add(p3);
		p4.add(chooseImage = new JLabel("Choose Image:"));
		formPanel.add(p4); 
		choose();
		choose2();
		formPanel.add(submit = new JButton("Submit"));
		p7.setLayout(new BoxLayout(p7,BoxLayout.Y_AXIS));

		//add button clicks
		submit.addActionListener(this);

		//add elements to listPanel
		listPanel.add(listLabel = new JLabel("List of Parts"));
		listPanel.add(p7);
		
		//add to this panel
		add(formPanel);
		add(listPanel);
 	 }
      
    public void actionPerformed(ActionEvent ae)
    { 
	 	//when submit button is clicked
		if(ae.getSource() == submit)
		{
			int success = 1;
			//validation	
			if(nameField.getText().equals(""))
			{
				JOptionPane.showMessageDialog(this, "Invalid name");
				nameField.setText("");
				success = 0;
			}
			if(nameField.getText().length() > 10)
			{
				JOptionPane.showMessageDialog(this, "Name must be 10 characters or less");
				nameField.setText("");
				success = 0;
			}	
			
			String s = numberField.getText();
			
			if(s.equals("") || s.length() > 30)
			{
				JOptionPane.showMessageDialog(this, "Please enter the serial number of the parts");	
				numberField.setText("");
				success = 0;
			}	
			
			try{
				int serial = Integer.parseInt(s);
			}
			catch(Exception ex){
				JOptionPane.showMessageDialog(this, "Invalid number of part");	
				numberField.setText("");
				success = 0;
			}
			
			if(descripField.getText().equals("") || descripField.getText().length() > 100)
			{
				JOptionPane.showMessageDialog(this, "Please enter the description of the parts");
				descripField.setText("");
				success = 0;
			}
			if(picIndex == -1)
			{
				JOptionPane.showMessageDialog(this, "Please select the image of the parts");
				success = 0;
			}
			
			if(success == 1)
			{
				//Create new part and then add it to the parts arraylist.
				Part newpart;
				s = nameField.getText();
				String des = descripField.getText();
				try{
					int serial = Integer.parseInt(numberField.getText());
				}
				
				catch(Exception ex){
					numberLabel.setText("must be a number");
				}
				
				newpart = new Part(image,s,numParts,des);
				newpart.setImageName("images/parts/" + picName);
				
				parts.add(newpart);
				ArrayList<Part> list1 = new ArrayList<Part>();
				for(int i=0; i<parts.size(); i++)
					list1.add(parts.get(i));
								
				client.sendPartsList(list1);
				numParts++;
				nameField.setText("");
				numberField.setText("");
				descripField.setText("");
				picIndex = -1;
				picName = "";
				
				//print the contents of the parts
				printList();
			
				//System.out.println(image.toString());
				add(listPanel);
				revalidate();
			}
		}
		
		//when user selects the image buttons.
		else if(ae.getSource() == button1)
		{	image = 0;picIndex = 0; picName = "ct_ball_purple.png"; }
		else if(ae.getSource() == button2)
		{	image = 1;picIndex = 1; picName = "ct_ball_blue.png";}
		else if(ae.getSource() == button3)
		{	image = 2;picIndex = 2; picName = "ct_ball_green.png";}
		else if(ae.getSource() == button4)
		{	image = 3;picIndex = 3; picName = "ct_ball_grey.png";}
		else if(ae.getSource() == button5)
		{	image = 4;picIndex = 4; picName = "ct_star_yellow.png";}
		else if(ae.getSource() == button6)
		{	image = 5;picIndex = 5; picName = "ct_star_green.png";}
		else if(ae.getSource() == button7)
		{	image = 6;picIndex = 6; picName = "ct_star_red.png";}
		else if(ae.getSource() == button8)
		{	image = 7;picIndex = 7; picName = "ct_star_rainbow.png";}
    }
	 
    public void choose()
    { //image buttons
		p5.add(button1 = new JButton(icon1));
		p5.add(button2 = new JButton(icon2));
		p5.add(button3 = new JButton(icon3));
		p5.add(button4 = new JButton(icon4));
		button1.addActionListener(this);
	 	button2.addActionListener(this);
	 	button3.addActionListener(this);
	 	button4.addActionListener(this);
		formPanel.add(p5);
    }
	
	public void choose2()
	{	//image buttons
		p5b.add(button5 = new JButton(icon5));
		p5b.add(button6 = new JButton(icon6));
		p5b.add(button7 = new JButton(icon7));
		p5b.add(button8 = new JButton(icon8));
		button5.addActionListener(this);
	 	button6.addActionListener(this);
	 	button7.addActionListener(this);
	 	button8.addActionListener(this);
		formPanel.add(p5b);
	}
	public void printList()
	{
		for(int i = parts.size()-1; i < parts.size(); i++)
			listPanel.add(new JLabel(parts.get(i).getName()));
	}
	
	public ArrayList<Part> getList()
	{	return parts;}
	
	public ImageIcon getIcon1()
	{	return icon1;}
}
