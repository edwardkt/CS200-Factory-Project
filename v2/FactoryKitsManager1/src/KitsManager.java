import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javax.swing.JDialog;
import javax.swing.*;
import java.util.*;
import java.net.*;
import java.io.*;
import FactoryResources.*;


public class KitsManager extends PartsManager implements ActionListener
{
	
	 KitManagerClient client;
    JLabel choosePart,choosePart2,choosePart3,choosePart4,choosePart5,choosePart6,choosePart7,choosePart8;
	 ArrayList<Kit> kits;
	 ArrayList<Part> parts;
	 int numKits;
	 int counter;
	 int totalParts;
	 //KitsUpdater kitsUpdater;
	
    public KitsManager(KitManagerClient client)
    {
    	this.client = client;
		counter = 0;
		totalParts = 0;
		numKits = 0;
		kits = new ArrayList<Kit>();
		parts = new ArrayList<Part>();
		//kitsUpdater = new KitsUpdater();
		
	
		//relabel
		topLabel.setText("Kits Manager");
		nameLabel.setText("Enter name of kit ");
		numberLabel.setText("Enter number of kit");
		descriptionLabel.setText("Enter description of kit");
		listLabel.setText("List of Kits");
		chooseImage.setVisible(false);
		p5b.setVisible(false);
    }
	 
	 //Method to update the combo box.
	 public void updateComboBox(ArrayList<Part> partList)
	 {
		 
	 	parts = partList;
	 	while(counter < partList.size())
		{
			combo.addItem((partList.get(counter)).getName());
			combo2.addItem((partList.get(counter)).getName());
			combo3.addItem((partList.get(counter)).getName());
			combo4.addItem((partList.get(counter)).getName());
			combo5.addItem((partList.get(counter)).getName());
			combo6.addItem((partList.get(counter)).getName());
			combo7.addItem((partList.get(counter)).getName());
			combo8.addItem((partList.get(counter)).getName());
			counter++;
		}
		combo.setVisible(true);
		combo2.setVisible(true);
		combo3.setVisible(true);
		combo4.setVisible(true);
		combo5.setVisible(true);
		combo6.setVisible(true);
		combo7.setVisible(true);
		combo8.setVisible(true);
 	}
	
	 public void actionPerformed(ActionEvent ae)
    { 
	 	//when submit button is clicked
		if(ae.getSource() == submit)
		{
			//if totalparts selected is less than 4, warn the user
			if(totalParts<4)
			{	JOptionPane.showMessageDialog(this, "You have to choose at least 4 parts");}
	
			else
			{
				int success = 1;	//this integer is used to do the validation.
				
				//if name field is not field, success is set to 0		
				if(nameField.getText() == "")
					success = 0;
					
				if(success == 1)
				{
					Kit newkit;
					s = nameField.getText();
					String des = descripField.getText();
					newkit = new Kit(s,numParts,des);
				
					//Getting the selected items from the combo box
					String a1,a2,a3,a4,a5,a6,a7,a8;
					a1 = combo.getSelectedItem().toString();
					a2 = combo2.getSelectedItem().toString();
					a3 = combo3.getSelectedItem().toString();
					a4 = combo4.getSelectedItem().toString();
					a5 = combo5.getSelectedItem().toString();
					a6 = combo6.getSelectedItem().toString();
					a7 = combo7.getSelectedItem().toString();
					a8 = combo8.getSelectedItem().toString();
					
					//Comparing the values inside combobox and update the configuration int[]
					for(int i=0; i<parts.size(); i++)
					{
						if(a1.equals((parts.get(i).getName())))
						{
							//adding the parts to the kit
							newkit.addPart(parts.get(i));
							newkit.updateConfig0(parts.get(i).getImageName());
							
							if((parts.get(i).getImage()) == 0)
							{	newkit.update1();	}
							else if((parts.get(i).getImage()) == 1)
							{	newkit.update2(); }
							else if((parts.get(i).getImage()) == 2)
							{	newkit.update3(); }
							else if((parts.get(i).getImage()) == 3)
							{	newkit.update4(); }
							else if((parts.get(i).getImage()) == 4)
							{	newkit.update5(); }
							else if((parts.get(i).getImage()) == 5)
							{	newkit.update6(); }
							else if((parts.get(i).getImage()) == 6)
							{	newkit.update7(); }
							else if((parts.get(i).getImage()) == 7)
							{	newkit.update8(); }
						}
						if(a2.equals((parts.get(i).getName())))
						{
							//adding the parts to the kit
							newkit.addPart(parts.get(i));
							newkit.updateConfig1(parts.get(i).getImageName());
							
							if((parts.get(i).getImage()) == 0)
							{	newkit.update1();	}
							else if((parts.get(i).getImage()) == 1)
							{	newkit.update2(); }
							else if((parts.get(i).getImage()) == 2)
							{	newkit.update3(); }
							else if((parts.get(i).getImage()) == 3)
							{	newkit.update4(); }
							else if((parts.get(i).getImage()) == 4)
							{	newkit.update5(); }
							else if((parts.get(i).getImage()) == 5)
							{	newkit.update6(); }
							else if((parts.get(i).getImage()) == 6)
							{	newkit.update7(); }
							else if((parts.get(i).getImage()) == 7)
							{	newkit.update8(); }
						}
						if(a3.equals((parts.get(i).getName())))
						{
							//adding the parts to the kit
							newkit.addPart(parts.get(i));
							newkit.updateConfig2(parts.get(i).getImageName());
							
							if((parts.get(i).getImage()) == 0)
							{	newkit.update1();	}
							else if((parts.get(i).getImage()) == 1)
							{	newkit.update2(); }
							else if((parts.get(i).getImage()) == 2)
							{	newkit.update3(); }
							else if((parts.get(i).getImage()) == 3)
							{	newkit.update4(); }
							else if((parts.get(i).getImage()) == 4)
							{	newkit.update5(); }
							else if((parts.get(i).getImage()) == 5)
							{	newkit.update6(); }
							else if((parts.get(i).getImage()) == 6)
							{	newkit.update7(); }
							else if((parts.get(i).getImage()) == 7)
							{	newkit.update8(); }
						}
						if(a4.equals((parts.get(i).getName())))
						{
							//adding the parts to the kit
							newkit.addPart(parts.get(i));
							newkit.updateConfig3(parts.get(i).getImageName());
							
							if((parts.get(i).getImage()) == 0)
							{	newkit.update1();	}
							else if((parts.get(i).getImage()) == 1)
							{	newkit.update2(); }
							else if((parts.get(i).getImage()) == 2)
							{	newkit.update3(); }
							else if((parts.get(i).getImage()) == 3)
							{	newkit.update4(); }
							else if((parts.get(i).getImage()) == 4)
							{	newkit.update5(); }
							else if((parts.get(i).getImage()) == 5)
							{	newkit.update6(); }
							else if((parts.get(i).getImage()) == 6)
							{	newkit.update7(); }
							else if((parts.get(i).getImage()) == 7)
							{	newkit.update8(); }
						}
						if(a5.equals((parts.get(i).getName())))
						{
							//adding the parts to the kit
							newkit.addPart(parts.get(i));
							newkit.updateConfig4(parts.get(i).getImageName());
							
							if((parts.get(i).getImage()) == 0)
							{	newkit.update1();	}
							else if((parts.get(i).getImage()) == 1)
							{	newkit.update2(); }
							else if((parts.get(i).getImage()) == 2)
							{	newkit.update3(); }
							else if((parts.get(i).getImage()) == 3)
							{	newkit.update4(); }
							else if((parts.get(i).getImage()) == 4)
							{	newkit.update5(); }
							else if((parts.get(i).getImage()) == 5)
							{	newkit.update6(); }
							else if((parts.get(i).getImage()) == 6)
							{	newkit.update7(); }
							else if((parts.get(i).getImage()) == 7)
							{	newkit.update8(); }
						}
						if(a6.equals((parts.get(i).getName())))
						{
							//adding the parts to the kit
							newkit.addPart(parts.get(i));
							newkit.updateConfig5(parts.get(i).getImageName());
							
							if((parts.get(i).getImage()) == 0)
							{	newkit.update1();	}
							else if((parts.get(i).getImage()) == 1)
							{	newkit.update2(); }
							else if((parts.get(i).getImage()) == 2)
							{	newkit.update3(); }
							else if((parts.get(i).getImage()) == 3)
							{	newkit.update4(); }
							else if((parts.get(i).getImage()) == 4)
							{	newkit.update5(); }
							else if((parts.get(i).getImage()) == 5)
							{	newkit.update6(); }
							else if((parts.get(i).getImage()) == 6)
							{	newkit.update7(); }
							else if((parts.get(i).getImage()) == 7)
							{	newkit.update8(); }
						}
						if(a7.equals((parts.get(i).getName())))
						{
							//adding the parts to the kit
							newkit.addPart(parts.get(i));
							newkit.updateConfig6(parts.get(i).getImageName());
							
							if((parts.get(i).getImage()) == 0)
							{	newkit.update1();	}
							else if((parts.get(i).getImage()) == 1)
							{	newkit.update2(); }
							else if((parts.get(i).getImage()) == 2)
							{	newkit.update3(); }
							else if((parts.get(i).getImage()) == 3)
							{	newkit.update4(); }
							else if((parts.get(i).getImage()) == 4)
							{	newkit.update5(); }
							else if((parts.get(i).getImage()) == 5)
							{	newkit.update6(); }
							else if((parts.get(i).getImage()) == 6)
							{	newkit.update7(); }
							else if((parts.get(i).getImage()) == 7)
							{	newkit.update8(); }
						}
						if(a8.equals((parts.get(i).getName())))
						{
							//adding the parts to the kit
							newkit.addPart(parts.get(i));
							newkit.updateConfig7(parts.get(i).getImageName());
							
							if((parts.get(i).getImage()) == 0)
							{	newkit.update1();	}
							else if((parts.get(i).getImage()) == 1)
							{	newkit.update2(); }
							else if((parts.get(i).getImage()) == 2)
							{	newkit.update3(); }
							else if((parts.get(i).getImage()) == 3)
							{	newkit.update4(); }
							else if((parts.get(i).getImage()) == 4)
							{	newkit.update5(); }
							else if((parts.get(i).getImage()) == 5)
							{	newkit.update6(); }
							else if((parts.get(i).getImage()) == 6)
							{	newkit.update7(); }
							else if((parts.get(i).getImage()) == 7)
							{	newkit.update8(); }
						}
					}
				
					newkit.printParts();
					newkit.printImageNames();
					
					kits.add(newkit);	//add new kit to list
					client.sendKits(kits);
					
					printConfig();
					numKits++;
				
					//Resetting Fields
					nameField.setText("");
					numberField.setText("");
					descripField.setText("");
					combo.setSelectedIndex(0);
					combo2.setSelectedIndex(0);
					combo3.setSelectedIndex(0);
					combo4.setSelectedIndex(0);
					combo5.setSelectedIndex(0);
					combo6.setSelectedIndex(0);
					combo7.setSelectedIndex(0);
					combo8.setSelectedIndex(0);
					totalParts = 0;
					printList();			
					add(listPanel);
					revalidate();
				}
			}
		}
	}
	
	//print the kits that have been created
	public void printList()
	{
		for(int i = kits.size()-1; i < kits.size(); i++)
			{	listPanel.add(new JLabel(kits.get(i).getName()));	}
	}
	
	//print the 8 number configuration
	public void printConfig()
	{
		for(int i=kits.size()-1; i<kits.size(); i++)
			kits.get(i).print();
	}
	
	 public void choose()
    {
    	//combo box for part 1
    	combo.addItem("Select a Part");
    	combo.setEditable(true);
    	combo.addActionListener(new ActionListener()
    	{
    		public void actionPerformed(ActionEvent ae)
    		{
    		    contents = combo.getSelectedItem();	// contents is now a part object
				 totalParts++;
    		    s = contents.toString();
    		}
    	});
    	p4.add(choosePart = new JLabel("Choose Part"));
    	p4.add(combo);
    	formPanel.add(p4);
    	
    	//combo box for part 2
    	combo2.addItem("Select a Part");
    	combo2.setEditable(true);
    	combo2.addActionListener(new ActionListener()
    	{
    		public void actionPerformed(ActionEvent ae)
    		{
    			 contents = combo2.getSelectedItem();
				 totalParts++;
    		    s2 = contents.toString();
    		}
    	});
    	p5.add(choosePart2 = new JLabel("Choose Part"));
    	p5.add(combo2);
    	formPanel.add(p5);
    	
    	//combo box for part 3
    	combo3.addItem("Select a Part");
    	combo3.setEditable(true);
    	combo3.addActionListener(new ActionListener()
    	{
    		public void actionPerformed(ActionEvent ae)
    		{
    			 contents = combo3.getSelectedItem();
				 totalParts++;
    		    s3 = contents.toString();
    		}
    	});
    	p6.add(choosePart3 = new JLabel("Choose Part"));
    	p6.add(combo3);
    	formPanel.add(p6);
    	
    	//combo box for part 4
    	combo4.addItem("Select a Part");
    	combo4.setEditable(true);
    	combo4.addActionListener(new ActionListener()
    	{
    		public void actionPerformed(ActionEvent ae)
    		{
    		    contents = combo4.getSelectedItem();
				 totalParts++;
    		    s4 = contents.toString();
    		}
    	});
    	p8.add(choosePart4 = new JLabel("Choose Part"));
    	p8.add(combo4);
    	formPanel.add(p8);
    		
    	//combo box for part 5
    	combo5.addItem("Select a Part");
    	combo5.setEditable(true);
    	combo5.addActionListener(new ActionListener()
    	{
    		public void actionPerformed(ActionEvent ae)
    		{
    		    contents = combo5.getSelectedItem();
				 totalParts++;
    		    s5 = contents.toString();
    		}
    	});
    	p9.add(choosePart5 = new JLabel("Choose Part"));
    	p9.add(combo5);
    	formPanel.add(p9);
    	
    	//combo box for part 6
    	combo6.addItem("Select a Part");
    	combo6.setEditable(true);
    	combo6.addActionListener(new ActionListener()
    	{
    		public void actionPerformed(ActionEvent ae)
    		{
    		    contents = combo6.getSelectedItem();
				 totalParts++;
    		    s6 = contents.toString();
    		}
    	});
    	p10.add(choosePart6 = new JLabel("Choose Part"));
    	p10.add(combo6);
    	formPanel.add(p10);
    	
    	//combo box for part 7
    	combo7.addItem("Select a Part");
    	combo7.setEditable(true);
    	combo7.addActionListener(new ActionListener()
    	{
    		public void actionPerformed(ActionEvent ae)
    		{
    		    contents = combo7.getSelectedItem();
				 totalParts++;
    		    s7 = contents.toString();
    		}
    	});
    	p11.add(choosePart7 = new JLabel("Choose Part"));
    	p11.add(combo7);
    	formPanel.add(p11);
    	
    	//combo box for part 8
    	combo8.addItem("Select a Part");
    	combo8.setEditable(true);
    	combo8.addActionListener(new ActionListener()
    	{
    		public void actionPerformed(ActionEvent ae)
    		{
    		    contents = combo8.getSelectedItem();
				 totalParts++;
    		    s8 = contents.toString();
    		}
    	});
    	p12.add(choosePart8 = new JLabel("Choose Part"));
    	p12.add(combo8);
    	formPanel.add(p12);  	
    }
	 
	 
	 public void setPartsList(ArrayList<Part> parts){
		 this.parts = parts;
		 updateComboBox(parts);
	 }
	 
	 
	 
	 
}
