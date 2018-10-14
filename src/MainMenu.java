import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MainMenu extends JFrame{

	JPanel buttons;
	JLabel headLineLabel;
	JButton sellBtn;
	JButton stockBtn;
	JButton reportsBtn;
	JButton newCustomerBtn;
	JButton exitBtn;
	JPanel mainPanel;
	JLabel userLabel;
	
	
	
	public MainMenu() {
		
		//Setting the frame size
		this.setSize(300,400);
		//Setting the frame default location in the middle of the screen
		this.setLocationRelativeTo(null);
		//Disabling the option to change the size of the frame
		this.setResizable(false);
		//Setting the option to close the window using the X button
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//Setting the title of the frame
		this.setTitle("mainMenu");
	
		mainPanel=new JPanel();
		mainPanel.setLayout(new GridBagLayout());

		userLabel=new JLabel("user");
		buttons=new JPanel();
		buttons.setLayout(new GridLayout(0,1,3,3));
		buttons.setAlignmentX(CENTER_ALIGNMENT);
		buttons.setBounds(0, 0, 2, 2);
		headLineLabel=new JLabel("M&N Fashion");
		sellBtn=new JButton("Sell");		
		stockBtn=new JButton("Stock");
		reportsBtn=new JButton("Reports");
		newCustomerBtn=new JButton("New Customer");
		exitBtn=new JButton("Exit");
		
		//mainPanel.add(headLineLabel);
		buttons.add(sellBtn);
		buttons.add(stockBtn);
		buttons.add(reportsBtn);
		buttons.add(newCustomerBtn);
		buttons.add(exitBtn);
		
		addComp(mainPanel,userLabel,0,0,1,1,GridBagConstraints.NORTHEAST,GridBagConstraints.NONE);
		addComp(mainPanel,headLineLabel,0,0,1,1,GridBagConstraints.CENTER,GridBagConstraints.NONE);
		addComp(mainPanel,buttons,0,1,1,1,GridBagConstraints.CENTER,GridBagConstraints.BOTH);


		this.add(mainPanel);
		
		this.setVisible(true);
	}


	public static void main(String[] args) {
		new MainMenu();
	}

private void addComp(JPanel thePanel, JComponent comp, int xPos, int yPos, int compWidth, int compHeight, int place, int stretch){
        
       GridBagConstraints gridConstraints = new GridBagConstraints();       
       gridConstraints.gridx = xPos;
       gridConstraints.gridy = yPos;
       gridConstraints.gridwidth = compWidth;
       gridConstraints.gridheight = compHeight;
       gridConstraints.weightx = 100;
       gridConstraints.weighty = 100;
       gridConstraints.insets = new Insets(5,5,5,5);
       gridConstraints.anchor = place;
       gridConstraints.fill = stretch;
       thePanel.add(comp, gridConstraints);

   }

}