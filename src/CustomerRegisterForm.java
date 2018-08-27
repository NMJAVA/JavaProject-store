
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

public class CustomerRegisterForm extends JFrame{
	
	public CustomerRegisterForm()
	{
		setTitle("New Customer");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel mainPanel= new JPanel();
		SpringLayout theLayout = new SpringLayout();
		mainPanel.setLayout(theLayout);
		
		JLabel firstName = new JLabel("First Name");
		mainPanel.add(firstName);
		JTextField firstNameText= new JTextField(15);
		mainPanel.add(firstNameText);
		
		JLabel lastName = new JLabel("Last Name");
		mainPanel.add(lastName);
		JTextField lastNameText= new JTextField(15);
		mainPanel.add(lastNameText);
		
		JLabel phoneNumber = new JLabel("Phone Number");
		mainPanel.add(phoneNumber);
		JTextField phoneNumberText= new JTextField(15);
		mainPanel.add(phoneNumberText);
		
		JLabel adress= new JLabel("Adress");
		mainPanel.add(adress);
		
		JLabel city= new JLabel("City");
		mainPanel.add(city);
		JTextField cityText= new JTextField(15);
		mainPanel.add(cityText);
		JLabel street= new JLabel("Street");
		mainPanel.add(street);
		JTextField streetText= new JTextField(15);
		mainPanel.add(streetText);
		JLabel houseNumber= new JLabel("Number");
		mainPanel.add(houseNumber);
		JTextField houseNumberText= new JTextField(5);
		mainPanel.add(houseNumberText);
		
		theLayout.putConstraint(SpringLayout.WEST, firstName, 50, SpringLayout.WEST, mainPanel);
		theLayout.putConstraint(SpringLayout.NORTH, firstName, 5, SpringLayout.NORTH, mainPanel);
		theLayout.putConstraint(SpringLayout.WEST, firstNameText, 5, SpringLayout.EAST, firstName);
		theLayout.putConstraint(SpringLayout.NORTH, firstNameText, 5, SpringLayout.NORTH, mainPanel);
		
		theLayout.putConstraint(SpringLayout.WEST, lastName, 50, SpringLayout.WEST, mainPanel);
		theLayout.putConstraint(SpringLayout.NORTH, lastName, 5, SpringLayout.SOUTH, firstName);
		theLayout.putConstraint(SpringLayout.WEST, lastNameText, 5, SpringLayout.EAST, lastName);
		theLayout.putConstraint(SpringLayout.NORTH, lastNameText, 5, SpringLayout.SOUTH, firstName);
		
		theLayout.putConstraint(SpringLayout.WEST, phoneNumber, 50, SpringLayout.WEST, mainPanel);
		theLayout.putConstraint(SpringLayout.NORTH, phoneNumber, 5, SpringLayout.SOUTH, lastName);
		theLayout.putConstraint(SpringLayout.WEST, phoneNumberText, 5, SpringLayout.EAST, phoneNumber);
		theLayout.putConstraint(SpringLayout.NORTH, phoneNumberText, 5, SpringLayout.SOUTH, lastName);
		
		theLayout.putConstraint(SpringLayout.WEST, adress, 50, SpringLayout.WEST, mainPanel);
		theLayout.putConstraint(SpringLayout.NORTH, adress, 15, SpringLayout.SOUTH, phoneNumber);
		
		theLayout.putConstraint(SpringLayout.WEST, city, 50, SpringLayout.WEST, mainPanel);
		theLayout.putConstraint(SpringLayout.NORTH, city, 15, SpringLayout.NORTH, adress);
		theLayout.putConstraint(SpringLayout.WEST, cityText, 5, SpringLayout.EAST, city);
		theLayout.putConstraint(SpringLayout.NORTH, cityText, 15, SpringLayout.NORTH, adress);
		
		theLayout.putConstraint(SpringLayout.WEST, street, 20, SpringLayout.EAST, cityText);
		theLayout.putConstraint(SpringLayout.NORTH, street, 5, SpringLayout.SOUTH, adress);
		theLayout.putConstraint(SpringLayout.WEST, streetText, 5, SpringLayout.EAST, street);
		theLayout.putConstraint(SpringLayout.NORTH, streetText, 15, SpringLayout.NORTH, adress);
		
		theLayout.putConstraint(SpringLayout.WEST, houseNumber, 20, SpringLayout.EAST, streetText);
		theLayout.putConstraint(SpringLayout.NORTH, houseNumber, 5, SpringLayout.SOUTH, adress);
		theLayout.putConstraint(SpringLayout.WEST, houseNumberText, 5, SpringLayout.EAST, houseNumber);
		theLayout.putConstraint(SpringLayout.NORTH, houseNumberText, 15, SpringLayout.NORTH, adress);
		
		theLayout.putConstraint(SpringLayout.EAST, mainPanel, 5, SpringLayout.EAST, houseNumberText);
		theLayout.putConstraint(SpringLayout.SOUTH, mainPanel, 10, SpringLayout.SOUTH, houseNumberText);
		
		add(mainPanel);
		pack();
		setVisible(true);
	}
		static public void main(String[] args){
			CustomerRegisterForm page=new CustomerRegisterForm();
		}
	
}
