
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class LoginForm extends GUIFunctinos{

	private JPanel mainPanel;
	private JLabel emailLabel;
	private JLabel passwordLabel;
	private JTextField emailText;
	private JTextField passwordText;
	private JButton loginBtn;
	private Employee user;
	
	Socket socket;
	DataInputStream fromNetInputStram;
	PrintStream toNetOutputStream;
	
	private String passwordAttempt="";
	private String emailAttempt="";
	
	//The LoginForm constructor, create the GUI side of the login form
	public LoginForm(Socket socket,DataInputStream fromNetInputStram,PrintStream toNetOutputStream) {
		//Set connection to server objects variable 
		
		this.socket=socket;
		this.fromNetInputStram=fromNetInputStram;
		this.toNetOutputStream=toNetOutputStream;
		
		//Setting the frame size
		this.setSize(350,170);
		//Setting the frame default location in the middle of the screen
		this.setLocationRelativeTo(null);
		//Disabling the option to change the size of the frame
		this.setResizable(false);
		//Setting the option to close the window using the X button
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//Setting the title of the frame
		this.setTitle("Login");
	
		
		mainPanel = new JPanel();
		mainPanel.setLayout(new GridBagLayout());
		
		emailLabel =new JLabel("Email");	
		addComp(mainPanel,emailLabel,0,0,1,1,GridBagConstraints.SOUTHWEST,GridBagConstraints.NONE);
		
		passwordLabel= new JLabel("Password");
		addComp(mainPanel,passwordLabel,0,1,1,1,GridBagConstraints.NORTHWEST,GridBagConstraints.NONE);
		
		emailText= new JTextField(15);
		addComp(mainPanel,emailText,0,0,1,1,GridBagConstraints.SOUTHEAST,GridBagConstraints.NONE);
		
		passwordText= new JTextField(15);
		addComp(mainPanel,passwordText,0,1,1,1,GridBagConstraints.NORTHEAST,GridBagConstraints.NONE);
		
		ListenForKeys lForText=new ListenForKeys();
		emailText.addKeyListener(lForText);
		passwordText.addKeyListener(lForText);
		
		loginBtn=new JButton("Login");
		addComp(mainPanel,loginBtn,0,2,1,1,GridBagConstraints.CENTER,GridBagConstraints.NONE);
		this.add(mainPanel);
		
		ListenForButton lForSendBtn= new ListenForButton();
		loginBtn.addActionListener(lForSendBtn);
		
		//Setting the frame to be visible
		this.setVisible(true);
		
	}
	
	private class ListenForKeys implements KeyListener{

		@Override
		public void keyPressed(KeyEvent e) {
			
		}

		@Override
		public void keyReleased(KeyEvent arg0) {
			emailAttempt=emailText.getText();
			passwordAttempt=passwordText.getText();
					
		}

		@Override
		public void keyTyped(KeyEvent arg0) {
			// TODO Auto-generated method stub
			
		}
	}

	
	private class ListenForButton implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			
			if(e.getSource()==loginBtn) {
		
					toNetOutputStream.println(emailAttempt);
					toNetOutputStream.println(passwordAttempt);
					
					try {
						
						if(fromNetInputStram.readLine().equals("true")) {
							user=new Employee(stringToMember(fromNetInputStram.readLine()));
							MainMenu mainMenu=new MainMenu(socket,fromNetInputStram,toNetOutputStream,user);
							//setVisible(false);
							dispose();
						}
						else {
							JOptionPane.showMessageDialog(null, "Wrong password or email");
						}
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				
				}
			
		
				//send message from string
		
	}
	

static Member stringToMember(String customerDetails){
		
		String firstName;
		String lastName;
		Address address=new Address();
		String phone;
		String email;
		
		
		 firstName=customerDetails.substring(0, customerDetails.indexOf('|'));	
		 customerDetails=customerDetails.substring(customerDetails.indexOf('|')+1, customerDetails.length());
		 
		 lastName=customerDetails.substring(0, customerDetails.indexOf('|'));
		 customerDetails=customerDetails.substring(customerDetails.indexOf('|')+1, customerDetails.length());
		 
		 address.setCity(customerDetails.substring(0, customerDetails.indexOf('|')));
		 customerDetails=customerDetails.substring(customerDetails.indexOf('|')+1, customerDetails.length());
		 
		 address.setStreet(customerDetails.substring(0, customerDetails.indexOf('|')));
		 customerDetails=customerDetails.substring(customerDetails.indexOf('|')+1, customerDetails.length());
		 
		 address.setHouseNumber(customerDetails.substring(0, customerDetails.indexOf('|')));
		 customerDetails=customerDetails.substring(customerDetails.indexOf('|')+1, customerDetails.length());
		 
		 
		 phone=customerDetails.substring(0, customerDetails.indexOf('|'));;
		 customerDetails=customerDetails.substring(customerDetails.indexOf('|')+1, customerDetails.length());
		 
		 email=customerDetails;
		

		Member memberToRegister2   = new Member(firstName, lastName, address, phone , email );
		return memberToRegister2;
	}
	
	
	}
	
	

	

