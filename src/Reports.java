

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
import java.awt.event.WindowListener;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
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
	private JButton backBtn;
	private JButton wordBtn;
	ArrayList<Order> searchResult;
	private OrderHelper orderHelper;
	private Employee employee;
	Socket socket;
	DataInputStream fromNetInputStram;
	PrintStream toNetOutputStream;
	
	public Reports(Socket socket,DataInputStream fromNetInputStram,PrintStream toNetOutputStream,Employee loggedInUser){
		this.socket=socket;
		this.fromNetInputStram=fromNetInputStram;
		this.toNetOutputStream=toNetOutputStream;
		employee=loggedInUser;
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
			
				
				
				 String[] columns = {"barcode", "Date","Employee ID","CustomerID","Amount"};
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

				 
				 
				searchPanel=new JPanel();
				searchPanel.setSize(100,100);
				searchLabel=new JLabel("Search");
				searchText=new JTextField(15);
				searchBtn= new JButton("Search");
				backBtn= new JButton("Back");
				
				searchBySeller= new JRadioButton("Seller");
				showAll=new JRadioButton("Show All");
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

				wordBtn=new JButton("Word Repoert");
				userPanel.add(userLabel);
				userPanel.add(spaceLabel);
				addComp(mainPanel,userPanel,0,0,1,1,GridBagConstraints.NORTHEAST,GridBagConstraints.NONE);
				addComp(mainPanel,searchPanel,0,0,1,1,GridBagConstraints.NORTH,GridBagConstraints.NONE);
				addComp(mainPanel,searchTypePanel,0,0,1,1,GridBagConstraints.SOUTH,GridBagConstraints.NONE);
				addComp(mainPanel,wordBtn,0,1,1,1,GridBagConstraints.SOUTH,GridBagConstraints.NONE);
				addComp(mainPanel,tablePanel,0,2,1,2,GridBagConstraints.CENTER,GridBagConstraints.BOTH);
				addComp(mainPanel,backBtn,0,4,1,1,GridBagConstraints.EAST,GridBagConstraints.NONE);
				searchResult = new ArrayList<Order>();
				ListenForKeys lForSearch=new ListenForKeys();
				searchText.addKeyListener(lForSearch);
				userPanel.setBorder(blackBorder);
				ListenForButton lForBtn= new ListenForButton();
				searchBtn.addActionListener(lForBtn);
				backBtn.addActionListener(lForBtn);
				this.add(mainPanel);
				this.setVisible(true);
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
			orderHelper=new OrderHelper();
		
			
			if(e.getSource()==searchBtn) {	
				toNetOutputStream.println("search");
				for(int i=(searchResult.size()-1);i>=0;i--)
				{
					model.removeRow(i);
					searchResult.remove(i);
				}
				try {
							if(showAll.isSelected()) {
								searchResult=orderHelper.getAllOrders();
							}	
							else if(searchByBuyer.isSelected()) {
								
									searchResult=orderHelper.getAllByCustomerEmail( "luke@gmail.com" );
								
							}
							else if(searchBySeller.isSelected()) {
								searchResult=orderHelper.getAllByEmployeeEmail("niv@gmail.com");
							}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				for(int i=0;i<searchResult.size();i++ )
				{
					Object[] row=new Object[5];
					row[0] = searchResult.get(i).getId();
	                row[1] =searchResult.get(i).getDate();
	                row[2] = searchResult.get(i).getEmployeeID();
	                row[3] =searchResult.get(i).getCustomerID();
	                row[4] =searchResult.get(i).getAmount();
	                model.addRow(row);
				}
			}
			if(e.getSource()==wordBtn) {
				try {
					OrderHelper OrderHelper = new OrderHelper();	
					OrderHelper.createWordFromAllOrders(true);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			if(e.getSource()==backBtn) {	
				//toNetOutputStream.println("back");
				dispose();
			}
		}
	}
	
	 private class eventForClose implements WindowListener {

			@Override
			public void windowOpened(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowClosing(WindowEvent e) {
				
			}

			@Override
			public void windowClosed(WindowEvent e) {
			}
			//MainMenu mainMenu=new MainMenu(socket,fromNetInputStram,toNetOutputStream,employee);

			@Override
			public void windowIconified(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowDeiconified(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowActivated(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowDeactivated(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
		 
		 }
	
}
