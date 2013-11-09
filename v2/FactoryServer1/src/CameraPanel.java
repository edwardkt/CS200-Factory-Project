import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import FactoryResources.*;

public class CameraPanel extends JPanel implements ActionListener{
	JButton lanes, nests, stand;
	boolean lanesBool, nestBool, standBool;
	JLabel lanesTakePic, nestTakePic, standTakePic;
	
	public CameraPanel(){
		//Initialization of the Jlabels.
		lanesTakePic = new JLabel("Lanes : Not taking picture");
		nestTakePic = new JLabel("Nests : Not taking picture");
		standTakePic = new JLabel("Stands : Not taking picture");
		
		
		//booleans for panels.
		lanesBool = false;
		nestBool = false;
		standBool = false;
	
		//initialize components
		lanes = new JButton("Lanes");
		nests = new JButton("Nests");
		stand = new JButton("Kitting stand");
		
		lanes.addActionListener(this);
		nests.addActionListener(this);
		stand.addActionListener(this);
		
		//initialize layout
		setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.gridx = 0; c.gridy = 0;c.weighty =1; c.weightx = 0.5;
		//add(takePic,c);
		
		//iniialize and add panel with buttons
		c.gridy = 1;
		add(lanes,c);
		c.gridy = 2;
		add(nests,c);
		c.gridy = 3;
		add(stand,c);
		
		//add Jlabels
		c.gridx = 1; c.gridy = 1;
		add(lanesTakePic,c);
		c.gridy = 2;
		add(nestTakePic,c);
		c.gridy = 3;
		add(standTakePic,c);
	}
	
	//Getters
	
	public boolean getLanesBool()
	{	return lanesBool;}
	
	public boolean getNestBool()
	{	return nestBool;}
	
	public boolean GetStandBool()
	{	return standBool;}
	
	//Action performed for JButtons only.
	public void actionPerformed(ActionEvent ae){
		
		if(ae.getSource() == lanes)
		{
			if(lanesBool == false)
			{
				lanesBool = true;
				lanesTakePic.setText("Lanes : Taking pictures");
				revalidate();
			}
			else
			{
				lanesBool = false;
				lanesTakePic.setText("Lanes : Not taking pictures");
				revalidate();
			}
		}
		else if(ae.getSource() == nests)
		{
			if(nestBool == false)
			{
				nestBool = true;
				nestTakePic.setText("Nests : Taking pictures");
				revalidate();
			}
			else
			{
				nestBool = false;
				nestTakePic.setText("Nests : Not taking pictures");
				revalidate();
			}
		}
		else if(ae.getSource() == stand)
		{
			if(standBool == false)
			{
				standBool = true;
				standTakePic.setText("Stands : Taking pictures");
				revalidate();
			}
			else
			{
				standBool = false;
				standTakePic.setText("Stands : Not taking pictures");
				revalidate();
			}
		}
	}

}
