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
		reportsBtn=new JButton("Stock");
		reportsBtn=new JButton("Reports");
		newCustomerBtn=new JButton("New Customer");
		exitBtn=new JButton("Exit");
		chatBtn=new JButton("Chat");
		//mainPanel.add(headLineLabel);
		buttons.add(sellBtn);
		buttons.add(reportsBtn);
		buttons.add(reportsBtn);
		buttons.add(chatBtn);
		buttons.add(newCustomerBtn);
		buttons.add(chatBtn);
		buttons.add(exitBtn);
	
		addComp(mainPanel,userLabel,0,0,1,1,GridBagConstraints.NORTHEAST,GridBagConstraints.NONE);
		addComp(mainPanel,headLineLabel,0,0,1,1,GridBagConstraints.CENTER,GridBagConstraints.NONE);
		addComp(mainPanel,buttons,0,1,1,1,GridBagConstraints.CENTER,GridBagConstraints.BOTH);

		ListenForButton lForSendBtn= new ListenForButton();
		sellBtn.addActionListener(lForSendBtn);
		reportsBtn.addActionListener(lForSendBtn);
		reportsBtn.addActionListener(lForSendBtn);
		newCustomerBtn.addActionListener(lForSendBtn);
		exitBtn.addActionListener(lForSendBtn);
		chatBtn.addActionListener(lForSendBtn);
		this.add(mainPanel);
		
		this.setVisible(true);
	}


private class ListenForButton implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource()==sellBtn) {
			toNetOutputStream.println("sell");
			SalePage sale=new SalePage(socket,fromNetInputStram,toNetOutputStream,loggedInUser);
			setVisible(false);
			}
		if(e.getSource()==reportsBtn) {
			toNetOutputStream.println("reports");
			Reports reports=new Reports();
			setVisible(false);
		}
	
		if(e.getSource()==newCustomerBtn) {
			toNetOutputStream.println("newCustomer");
			CustomerRegisterForm CustomerRegisterForm=new CustomerRegisterForm(socket,fromNetInputStram,toNetOutputStream);
			setVisible(false);
		}
		if(e.getSource()==chatBtn) {
			toNetOutputStream.println("Chat");
			ChatGUI chat=new ChatGUI(socket,fromNetInputStram,toNetOutputStream,loggedInUser);
			setVisible(false);
		}
		if(e.getSource()==exitBtn) {
			System.exit(0);
		}

	}
}
}