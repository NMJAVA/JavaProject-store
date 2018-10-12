import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.Border;

public class SalePage extends GUIFunctinos{

	private JPanel mainPanel;
	private JPanel buttons;
	private JButton addBtn;
	private JButton removeBtn;
	private JButton discountBtn;
	private JButton sellBtn;
	private JTable itemTable;
	
	
	
	public SalePage() {
		//Setting the frame size
		this.setSize(500,500);
		//Setting the frame default location in the middle of the screen
		this.setLocationRelativeTo(null);
		//Disabling the option to change the size of the frame
		this.setResizable(false);
		//Setting the option to close the window using the X button
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//Setting the title of the frame
		this.setTitle("Sale");
		
		mainPanel=new JPanel();
		mainPanel.setLayout(new GridBagLayout());
		 String[] columns = {"Barrcode", "Name", "Size", "Quantity",
                 "Price"};
		
		itemTable=new JTable(10,5);
			Border blackBorder = BorderFactory.createLineBorder(Color.BLACK);
			itemTable.setBorder(blackBorder);
		addComp(mainPanel,itemTable,0,0,1,1,GridBagConstraints.CENTER,GridBagConstraints.NONE);
	
		buttons =new JPanel();
		buttons.setLayout(new GridLayout(0,1,2,2));

		addBtn=new JButton("Add");
		buttons.add(addBtn);
	
		removeBtn=new JButton("Remove");
		buttons.add(removeBtn);

		discountBtn=new JButton("Discount");
		buttons.add(discountBtn);
		
		sellBtn=new JButton("Sell");
		buttons.add(sellBtn);
		
		addBtn.setAlignmentX(Component.CENTER_ALIGNMENT); 
		removeBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
		sellBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		addComp(mainPanel,buttons,1,0,1,1,GridBagConstraints.CENTER,GridBagConstraints.NONE);
		
		
		this.add(mainPanel);
		this.setVisible(true);
	}
	
	static public void main(String[] args) {
		new SalePage();
	}
	

}
