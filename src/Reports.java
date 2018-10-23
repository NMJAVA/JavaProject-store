

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;




public class Reports extends GUIFunctinos{

	private JPanel mainPanel;
	private JTable stockTable;
	private JLabel searchLabel;
	private JTextField searchText;
	private JPanel thePanel;
	private JPanel tablePanel;
	private JPanel searchPanel;
	private JButton searchBtn;
	private DefaultTableModel model;
	private JRadioButton searchBySeller;
	private JRadioButton showAll;
	private JRadioButton searchByBuyer;
	private JPanel searchTypePanel;
	private JLabel userLabel;
	private JPanel userPanel;
	private JLabel spaceLabel;
	ArrayList<Order> searchResult;
	private OrderHelper orderHelper;
	public Reports(){
		//Setting the frame size
				this.setSize(550,650);
				//Setting the frame default location in the middle of the screen
				this.setLocationRelativeTo(null);
				//Disabling the option to change the size of the frame
				this.setResizable(false);
				//Setting the option to close the window using the X button
				this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				//Setting the title of the frame
				this.setTitle("Reports");
			
				
				mainPanel=new JPanel();
				mainPanel.setLayout(new GridBagLayout());
				///////////////////////////
			
				
				
				 String[] columns = {"barcode", "Name", "Size","Price","Quantity","Seller","Buyer"};
				 tablePanel=new JPanel();
					// int numRows = 0;
					 model = new DefaultTableModel()  {
					      public boolean isCellEditable(int rowIndex, int mColIndex) {
					          return false;
					        }
					      };
					 model.setColumnIdentifiers(columns);
					 stockTable = new JTable(model);

					 

					 JScrollPane scroll = new JScrollPane(stockTable);
						Border blackBorder = BorderFactory.createLineBorder(Color.BLACK);
						scroll.setBorder(blackBorder);
						
						tablePanel.add(scroll);
					
//////////////////////////////
				 
				 
				searchPanel=new JPanel();
				searchPanel.setSize(100,100);
				searchLabel=new JLabel("Search");
				searchText=new JTextField(15);
				searchBtn= new JButton("Search");
				
				searchBySeller= new JRadioButton("Seller");
				showAll=new JRadioButton("Barcode");
				searchByBuyer=new JRadioButton("Buyer");
				ButtonGroup searchTypes= new ButtonGroup();
				
				searchTypes.add(showAll);
				searchTypes.add(searchByBuyer);
				searchTypes.add(searchBySeller);
			
				searchTypePanel=new JPanel();
				Border serchTypeBorder=BorderFactory.createTitledBorder("Search By");
				searchTypePanel.setBorder(serchTypeBorder);
				
				searchTypePanel.add(showAll);
				searchTypePanel.add(searchBySeller);
				searchTypePanel.add(searchByBuyer);
				
				showAll.setSelected(true);
				
				userPanel=new JPanel();
				userLabel=new JLabel("user");
				spaceLabel=new JLabel("                 ");
				searchPanel.add(searchLabel);
				searchPanel.add(searchText);
				searchPanel.add(searchBtn);
				
				userPanel.add(userLabel);
				userPanel.add(spaceLabel);
				addComp(mainPanel,userPanel,0,0,1,1,GridBagConstraints.NORTHEAST,GridBagConstraints.NONE);
				addComp(mainPanel,searchPanel,0,0,1,1,GridBagConstraints.NORTH,GridBagConstraints.NONE);
				addComp(mainPanel,searchTypePanel,0,0,1,1,GridBagConstraints.SOUTH,GridBagConstraints.NONE);
				addComp(mainPanel,tablePanel,0,1,1,2,GridBagConstraints.CENTER,GridBagConstraints.BOTH);
				ListenForKeys lForSearch=new ListenForKeys();
				searchText.addKeyListener(lForSearch);
				userPanel.setBorder(blackBorder);
				ListenForButton lForSendBtn= new ListenForButton();
				
				this.add(mainPanel);
				this.setVisible(true);
	}
	
	public static void main(String[] args) {
		new Reports();
	}
	
	private class ListenForKeys implements KeyListener{

		@Override
		public void keyTyped(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void keyPressed(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void keyReleased(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}
	}

	private class ListenForButton implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			
			if(e.getSource()==searchBtn) {	
				try {
							if(showAll.isSelected()) {
								searchResult=orderHelper.getAllOrders();
							}	
							else if(searchByBuyer.isSelected()) {
								
									searchResult=orderHelper.getAllByCustomerEmail( "String email" );
								
							}
							else if(searchBySeller.isSelected()) {
								searchResult=orderHelper.getAllByEmployeeEmail("luke@gmail.com");
							}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				for(int i=0;i<searchResult.size();i++ )
				{
					
				}
			}
		}
	}
	
}
