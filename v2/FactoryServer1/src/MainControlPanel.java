import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import FactoryResources.*;

public class MainControlPanel extends JPanel implements ActionListener {
    Server server;
    //JPanel buttonsPanel;
    // controlPanel;// will switch panels
    CardLayout cardLayout;//layout for control panel
    
    //panels in control panels
    FactoryStatesPanel statesPanel;
    CameraPanel cameraPanel;
    

    
    public MainControlPanel(Server server)
    {
    	this.server = server;
    	
    	//initialize panels
    	statesPanel = new FactoryStatesPanel();
    	cameraPanel = new CameraPanel();
	
    	//initialize layout and add components to layout
		cardLayout = new CardLayout();
		setLayout(cardLayout);
		cardLayout.addLayoutComponent(statesPanel, "states");
		cardLayout.addLayoutComponent(cameraPanel, "camera");
		
		//add components to controlPanel
		add(statesPanel,"states");	
		add(cameraPanel, "camera");
    }
	
    public void actionPerformed(ActionEvent e)
    {
    	
    }
    
    public void updatePanel(){
    	//gantryControlPanel.updateComboBox();
    }
    
    public void showCameraPanel(){
    	cardLayout.show(this, "camera");
    }
    
    public void showStatesPanel(){
    	cardLayout.show(this, "states");
    }
    
    
    
}
