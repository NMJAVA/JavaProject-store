import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

public class LoginForm extends JFrame{
	
	private JPanel mainPanel= new JPanel();
	private SpringLayout theLayout = new SpringLayout();
	private JLabel userName = new JLabel("User Name");
	private JTextField userNameText=new JTextField(15);
	private JLabel Password = new JLabel("Password");
	private JPasswordField  PasswordText=new JPasswordField (15);
	private JButton logIng = new JButton("Login");
	private JFrame frame=new JFrame();
	
	public LoginForm(){
		setTitle("Login");
		setResizable(false);
		setLocation(500,500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		mainPanel.setLayout(theLayout);			
		mainPanel.add(userName);		
		mainPanel.add(userNameText);
		mainPanel.add(Password);
		mainPanel.add(PasswordText);
		mainPanel.add(logIng);

		
		theLayout.putConstraint(SpringLayout.WEST, userName, 50, SpringLayout.WEST, mainPanel);
		theLayout.putConstraint(SpringLayout.NORTH, userName, 20, SpringLayout.NORTH, mainPanel);

		theLayout.putConstraint(SpringLayout.WEST, userNameText, 5, SpringLayout.EAST, userName);
		theLayout.putConstraint(SpringLayout.NORTH, userNameText, 20, SpringLayout.NORTH, mainPanel);
				
		
		theLayout.putConstraint(SpringLayout.WEST, Password, 50, SpringLayout.WEST, mainPanel);
		theLayout.putConstraint(SpringLayout.NORTH, Password, 12, SpringLayout.SOUTH, userName);

		theLayout.putConstraint(SpringLayout.WEST, PasswordText, 10, SpringLayout.EAST, Password);
		theLayout.putConstraint(SpringLayout.NORTH, PasswordText, 8, SpringLayout.SOUTH, userNameText);
	
		theLayout.putConstraint(SpringLayout.WEST, logIng, 50, SpringLayout.WEST, mainPanel);
		theLayout.putConstraint(SpringLayout.NORTH, logIng, 5, SpringLayout.SOUTH, PasswordText);
			
		theLayout.putConstraint(SpringLayout.EAST, mainPanel, 5, SpringLayout.EAST, userNameText);
		theLayout.putConstraint(SpringLayout.EAST, mainPanel, 5, SpringLayout.EAST, PasswordText);
		theLayout.putConstraint(SpringLayout.SOUTH, mainPanel, 10, SpringLayout.SOUTH, logIng);
		
		logIng.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0){
				String uName=userNameText.getText();
				String pass=PasswordText.getText();
				
				if((uName.equals("moshe"))&& (pass.equals("moshe")))
				{
					JOptionPane.showMessageDialog(frame, "good");
				}
				else
				{
					JOptionPane.showMessageDialog(frame, "Username and password are incorrect");
				}
				
			}
			});
		add(mainPanel);
		pack();
		setVisible(true);
		
	}
}


