import javax.swing.*;
import java.awt.*;
import FactoryResources.*;


public class FactoryStatesPanel extends JPanel{
	JLabel topLabel;
	
	public FactoryStatesPanel(){
		setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints(); 
		c.gridx = 0; c.gridy = 0;
		topLabel = new JLabel("Factory States");
		add(topLabel, c);		
	}
	
}
