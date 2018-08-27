import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

public class Login extends JFrame{
	
	public Login(){
		setTitle("Login");
		setResizable(false);
		setLocation(500,500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel mainPanel= new JPanel();
		SpringLayout theLayout = new SpringLayout();
		mainPanel.setLayout(theLayout);
		
		JLabel userName = new JLabel("User Name");
		mainPanel.add(userName);
		JTextField userNameText=new JTextField(15);
		mainPanel.add(userNameText);
		
		JLabel Password = new JLabel("Password");
		mainPanel.add(Password);
		JPasswordField  PasswordText=new JPasswordField (15);
		mainPanel.add(PasswordText);
		
		JButton logIng = new JButton("Login");
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
		
		add(mainPanel);
		pack();
		setVisible(true);
		
	}

	public static void main(String[] args) {
		Login lg =new Login();
		
	}
}


