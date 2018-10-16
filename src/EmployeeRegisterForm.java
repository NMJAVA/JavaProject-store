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
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;


public class EmployeeRegisterForm extends GUIFunctinos{
	
	private JPanel mainPanel;
	private JLabel firstNameLabel;
	private JTextField firstNameText;
	private JLabel lastNamelabel;
	private JTextField lastNameText;
	
	private JLabel idLabel;
	private JTextField idText;
	
	private JLabel phoneLabel;
	private JTextField phoneText;
	private JLabel emailLabel;
	private JTextField emailText;
	private JLabel adress;
	private JLabel cityLabel;
	private JTextField cityText;
	private JLabel streetLabel;
	private JTextField streetText;
	private JLabel houseNumberLabel;
	private JTextField houseNumberText;
	private JButton submitBtn;
	
	private JLabel passwordLabel;
	private JTextField  passwordText;

	

	private String firstName;
	private String lastname;
	private String houseNumber;
	private String street;
	private String city;
	
	private String	phone;
	private String email;
	private String customer;
	private String password;

	
	Socket socket;
	DataInputStream fromNetInputStram;
	PrintStream toNetOutputStream;
	
	public EmployeeRegisterForm(Socket socket,DataInputStream fromNetInputStram,PrintStream toNetOutputStream) {
			
			this.socket=socket;
			this.fromNetInputStram=fromNetInputStram;
			this.toNetOutputStream=toNetOutputStream;
			//Setting the frame size
			this.setSize(400,400);
			//Setting the frame default location in the middle of the screen
			this.setLocationRelativeTo(null);
			//Disabling the option to change the size of the frame
			this.setResizable(false);
			//Setting the option to close the window using the X button
			this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			//Setting the title of the frame
			this.setTitle("New Employee");
		
			
			mainPanel = new JPanel();
			mainPanel.setLayout(new GridBagLayout());
			firstNameLabel =new JLabel("First Name");	
			addComp(mainPanel,firstNameLabel,1,0,1,1,GridBagConstraints.CENTER,GridBagConstraints.NONE);
			firstNameText= new JTextField(15);
			addComp(mainPanel,firstNameText,2,0,1,1,GridBagConstraints.WEST,GridBagConstraints.NONE);
			
			lastNamelabel= new JLabel("Last Name");
			addComp(mainPanel,lastNamelabel,1,1,1,1,GridBagConstraints.CENTER,GridBagConstraints.NONE);
			lastNameText= new JTextField(15);
			addComp(mainPanel,lastNameText,2,1,1,1,GridBagConstraints.WEST,GridBagConstraints.NONE);
			
			passwordLabel= new JLabel("Password");
			addComp(mainPanel,passwordLabel,1,2,1,1,GridBagConstraints.CENTER,GridBagConstraints.NONE);
			passwordText= new JTextField(15);
			addComp(mainPanel,passwordText,2,2,1,1,GridBagConstraints.WEST,GridBagConstraints.NONE);
			

			
			
			phoneLabel= new JLabel("Phone");
			addComp(mainPanel,phoneLabel,1,4,1,1,GridBagConstraints.CENTER,GridBagConstraints.NONE);
			phoneText= new JTextField(15);
			addComp(mainPanel,phoneText,2,4,1,1,GridBagConstraints.WEST,GridBagConstraints.NONE);
			
			emailLabel= new JLabel("Email");
			addComp(mainPanel,emailLabel,1,5,1,1,GridBagConstraints.CENTER,GridBagConstraints.NONE);
			emailText= new JTextField(15);
			addComp(mainPanel,emailText,2,5,1,1,GridBagConstraints.WEST,GridBagConstraints.NONE);
			
			adress= new JLabel("Adress");
			addComp(mainPanel,adress,1,6,1,1,GridBagConstraints.SOUTH,GridBagConstraints.NONE);
			
			cityLabel= new JLabel("City");
			addComp(mainPanel,cityLabel,1,7,1,1,GridBagConstraints.CENTER,GridBagConstraints.NONE);
			cityText= new JTextField(15);
			addComp(mainPanel,cityText,2,7,1,1,GridBagConstraints.WEST,GridBagConstraints.NONE);
			
			streetLabel= new JLabel("Street");
			addComp(mainPanel,streetLabel,1,8,1,1,GridBagConstraints.CENTER,GridBagConstraints.NONE);
			 streetText= new JTextField(15);
			addComp(mainPanel,streetText,2,8,1,1,GridBagConstraints.WEST,GridBagConstraints.NONE);
			
			houseNumberLabel= new JLabel("House Number");
			addComp(mainPanel,houseNumberLabel,1,9,1,1,GridBagConstraints.CENTER,GridBagConstraints.NONE);
			houseNumberText= new JTextField(2);
			addComp(mainPanel,houseNumberText,2,9,1,1,GridBagConstraints.WEST,GridBagConstraints.NONE);
			
			submitBtn=new JButton("Submit");
			addComp(mainPanel,submitBtn,2,10,1,1,GridBagConstraints.CENTER,GridBagConstraints.NONE);
			this.add(mainPanel);
			
			ListenForButton lForSendBtn= new ListenForButton();
			submitBtn.addActionListener(lForSendBtn);
			
			ListenForKeys lForText=new ListenForKeys();
			firstNameText.addKeyListener(lForText);
			lastNameText.addKeyListener(lForText);
			
			phoneText.addKeyListener(lForText);
			emailText.addKeyListener(lForText);
			cityText.addKeyListener(lForText);
			streetText.addKeyListener(lForText);
			houseNumberText.addKeyListener(lForText);
			
			
			
			//Setting the frame to be visible
			this.setVisible(true);
	}
			
		
	

	private class ListenForKeys implements KeyListener{

		@Override
		public void keyPressed(KeyEvent e) {
			
		}

		@Override
		public void keyReleased(KeyEvent arg0) {
			 
			
			 firstName=firstNameText.getText();
			 lastname=lastNameText.getText();
			
			 city=cityText.getText();
			 street=streetText.getText();
			 houseNumber=houseNumberText.getText();
			 phone=phoneText.getText();
			 email=emailText.getText();
			 password=passwordText.getText();
				
		}

		@Override
		public void keyTyped(KeyEvent arg0) {
			// TODO Auto-generated method stub
			
		}
	}

	
	private class ListenForButton implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource()==submitBtn) {
				if(!lttersOnly(firstName)) {
					JOptionPane.showMessageDialog(null, "First Name should contain only lettes!");

				}
				else if(!lttersOnly(lastname)) {
					JOptionPane.showMessageDialog(null, "Last Name should contain only lettes!");

				}
				else if(!lttersOnly(city)) {
					JOptionPane.showMessageDialog(null, "City should contain only lettes!");

				}
				else if(!lttersOnly(street)) {
					JOptionPane.showMessageDialog(null, "Street should contain only lettes!");

				}
				else if(!digitOnly(houseNumber)) {
					JOptionPane.showMessageDialog(null, "House Number should contain only numbers!");

				}
				else if(!digitOnly(phone)) {
					JOptionPane.showMessageDialog(null, "Phone should contain only numbers!");

				}
	
				else {
				customer=(firstName+"|"+lastname+"|"+city+"|"+street+"|"+houseNumber+"|"+phone+"|"+email);
				toNetOutputStream.println(customer);
				toNetOutputStream.println(password);
				}
			}
			
		}
	}
}
