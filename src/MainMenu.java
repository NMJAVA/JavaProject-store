import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.PrintStream;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;



public class MainMenu extends GUIFunctinos{

	private JPanel buttons;
	private JLabel headLineLabel;
	private JButton sellBtn;
	
	private JButton reportsBtn;
	private JButton newCustomerBtn;
	private JButton newEmployeeBtn;
	private JButton exitBtn;
	private JPanel mainPanel;
	private JLabel userLabel;
	private JButton chatBtn;
	Socket socket;
	DataInputStream fromNetInputStram;
	PrintStream toNetOutputStream;
	
	private Employee loggedInUser;
	
	
	public MainMenu(Socket socket,DataInputStream fromNetInputStram,PrintStream toNetOutputStream,Employee user) {
		
		this.socket=socket;
		this.fromNetInputStram=fromNetInputStram;
		this.toNetOutputStream=toNetOutputStream;
		//Setting the frame size
		this.setSize(300,400);
		//Setting the frame default location in the middle of the screen
		this.setLocationRelativeTo(null);
		//Disabling the option to change the size of the frame
		this.setResizable(false);
		//Setting the option to close the window using the X button
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//Setting the title of the frame
		this.setTitle("Main Menu");
	
		mainPanel=new JPanel();
		mainPanel.setLayout(new GridBagLayout());

		userLabel=new JLabel("user");
		buttons=new JPanel();
		buttons.setLayout(new GridLayout(0,1,3,3));
		buttons.setAlignmentX(CENTER_ALIGNMENT);
		buttons.setBounds(0, 0, 2, 2);
		headLineLabel=new JLabel("M&N Fashion");
		sellBtn=new JButton("Sell");		
		reportsBtn=new JButton("Stock");
		reportsBtn=new JButton("Reports");
		newCustomerBtn=new JButton("New Customer");
		newEmployeeBtn=new JButton("New Employee");
		exitBtn=new JButton("Exit");
		chatBtn=new JButton("Chat");
		//mainPanel.add(headLineLabel);
		buttons.add(sellBtn);
		buttons.add(reportsBtn);
		buttons.add(reportsBtn);
		buttons.add(chatBtn);
		buttons.add(chatBtn);
		buttons.add(newCustomerBtn);
		buttons.add(newEmployeeBtn);
		buttons.add(exitBtn);
		loggedInUser=user;
		addComp(mainPanel,userLabel,0,0,1,1,GridBagConstraints.NORTHEAST,GridBagConstraints.NONE);
		addComp(mainPanel,headLineLabel,0,0,1,1,GridBagConstraints.CENTER,GridBagConstraints.NONE);
		addComp(mainPanel,buttons,0,1,1,1,GridBagConstraints.CENTER,GridBagConstraints.BOTH);

		//Connecting the buttons to the listeners
		ListenForButton lForBtn= new ListenForButton();
		sellBtn.addActionListener(lForBtn);
		reportsBtn.addActionListener(lForBtn);
		newCustomerBtn.addActionListener(lForBtn);
		newEmployeeBtn.addActionListener(lForBtn);
		exitBtn.addActionListener(lForBtn);
		chatBtn.addActionListener(lForBtn);
		this.add(mainPanel);
		
		this.setVisible(true);
	}

//Listener  for the buttons open the wanted JFrame or close the app 
private class ListenForButton implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent e) {
		//Open the sale JFrame
		if(e.getSource()==sellBtn) {
			toNetOutputStream.println("sell");
			SalePage sale=new SalePage(socket,fromNetInputStram,toNetOutputStream,loggedInUser);
			dispose();
			}
		//Open the report JFrame
		if(e.getSource()==reportsBtn) {
			toNetOutputStream.println("reports");
			Reports reports=new Reports(socket,fromNetInputStram,toNetOutputStream,loggedInUser);
			dispose();
		}
		//Open the report new customer JFrame
		if(e.getSource()==newCustomerBtn) {
			toNetOutputStream.println("newCustomer");
			CustomerRegisterForm CustomerRegisterForm=new CustomerRegisterForm(socket,fromNetInputStram,toNetOutputStream,loggedInUser);
			dispose();
		}
		//Open the report new employee JFrame
		if(e.getSource()==newEmployeeBtn) {
			toNetOutputStream.println("newEmployee");
			EmployeeRegisterForm Employee=new EmployeeRegisterForm(socket,fromNetInputStram,toNetOutputStream,loggedInUser);
			dispose();
		}
		//Open the report new chat JFrame
		if(e.getSource()==chatBtn) {
			toNetOutputStream.println("Chat");
			ChatGUI chat=new ChatGUI(socket,fromNetInputStram,toNetOutputStream,loggedInUser);
			dispose();
		}
		if(e.getSource()==exitBtn) {
			System.exit(0);
		}

	}
}
}