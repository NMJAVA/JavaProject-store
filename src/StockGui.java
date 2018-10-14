import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.Border;

public class StockGui extends JFrame{

	private JPanel mainPanel;
	private JTable stockTable;
	private JLabel searchLabel;
	private JTextField searchText;
	private JPanel searchPanel;
	private JButton searchBtn;
	private JRadioButton searchByName;
	private JRadioButton searchByBarcode;
	private JRadioButton searchBySize;
	private JPanel searchTypePanel;
	
	
	public StockGui(){
		//Setting the frame size
				this.setSize(1000,1000);
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
				
				stockTable=new JTable(50,5);
				Border blackBorder = BorderFactory.createLineBorder(Color.BLACK);
				
				searchPanel=new JPanel();
				searchPanel.setSize(100,100);
				searchLabel=new JLabel("Search");
				searchText=new JTextField(15);
				searchBtn= new JButton("Search");
				
				searchByName= new JRadioButton("Name");
				searchByBarcode=new JRadioButton("Barcode");
				searchBySize=new JRadioButton("Size");
				ButtonGroup searchTypes= new ButtonGroup();
				
				searchTypes.add(searchByBarcode);
				searchTypes.add(searchByName);
				searchTypes.add(searchBySize);
				
				searchTypePanel=new JPanel();
				Border serchTypeBorder=BorderFactory.createTitledBorder("Search By");
				searchTypePanel.setBorder(serchTypeBorder);
				
				searchTypePanel.add(searchByBarcode);
				searchTypePanel.add(searchByName);
				searchTypePanel.add(searchBySize);
				
				searchByBarcode.setSelected(true);
				
				
				searchPanel.add(searchLabel);
				searchPanel.add(searchText);
				searchPanel.add(searchBtn);
				
				addComp(mainPanel,searchPanel,0,0,1,1,GridBagConstraints.NORTH,GridBagConstraints.NONE);
				addComp(mainPanel,searchTypePanel,0,0,1,1,GridBagConstraints.SOUTH,GridBagConstraints.NONE);
				addComp(mainPanel,stockTable,0,1,3,1,GridBagConstraints.CENTER,GridBagConstraints.BOTH);
				stockTable.setBorder(blackBorder);
				this.add(mainPanel);
				this.setVisible(true);
	}
	
	public static void main(String[] args) {
		new StockGui();
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
