import java.awt.GridBagLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.*;
import java.awt.*;
import javax.swing.*;

public class PartsRobotGUI extends JPanel implements ActionListener
{
   JComboBox combo,combo1;
	JButton submit,submit1;
	
	public PartsRobotGUI()
	{
		combo = new JComboBox();
		combo.addItem("Select a Part");
    	combo.setEditable(true);
		
		combo1 = new JComboBox();
		combo1.addItem("Select a Nest");
		combo1.addItem("Nest1, Lane 1");
		combo1.addItem("Nest1, Lane 2");
		combo1.addItem("Nest2, Lane 1");
		combo1.addItem("Nest2, Lane 2");
		combo1.addItem("Nest3, Lane 1");
		combo1.addItem("Nest3, Lane 2");
		combo1.addItem("Nest4, Lane 1");
		combo1.addItem("Nest4, Lane 2");
    	combo1.setEditable(true);
		
		submit = new JButton ("Go");
		submit1 = new JButton ("Bring Finished Kit to the Conveyor Belt");
		
		submit.addActionListener(this);
		submit1.addActionListener(this);
		
		//panels created to add onto jframe
		setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.weightx = 0.5;
		
		//Parts Robot GUI
		add(new JLabel("Parts Robot"),gbc);
		gbc.gridx = 1;
		add(combo,gbc);
		gbc.gridx = 2;
		add(new JLabel(" From "),gbc);
		gbc.gridx = 3;
		add(combo1,gbc);
		gbc.gridx = 4;
		gbc.weightx = 0.5;
		add(submit,gbc);
		
		//Kits Robot GUI
		gbc.gridx = 0;
		gbc.gridy = 3;
		add(new JLabel("Kits Robot"),gbc);
		gbc.gridx = 1;
		add(submit1,gbc);
		
	}
	
	public void actionPerformed(ActionEvent ae)
	{
		if(ae.getSource() == submit)
		{
			
		}
	}
	
	public void updatePartComboBox(Part a)
	{
		combo.addItem(a.getName());
		revalidate();
	}
}