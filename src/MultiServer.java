import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
import java.lang.*;


public class MultiServer {
	
	
	
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
	
	public static void main(String[] args) throws IOException{
		final ServerSocket server= new ServerSocket(7000);
		System.out.println(new Date()+"-->Server waits for clients...");
		Employee loggedIn;
		while(true)	{
			final Socket socket=server.accept();
			
			new Thread(new Runnable() {
				@Override
				public void run() {
					String clientAddress="";
					try{
						clientAddress=socket.getInetAddress()+":"+socket.getPort();
						System.out.println(new Date()+"-->Client connected from "+ clientAddress);
						DataInputStream inputStream=new DataInputStream(socket.getInputStream());
						PrintStream outputStream=new PrintStream(socket.getOutputStream());
						outputStream.println("Welcom to server!");
						String pass;
						String use;
						String line="";
						String password="";
						int option=0;
						while(!line.equals("goodbye")) {
							
							
						/*	line=inputStream.readLine();
							password=inputStream.readLine();
							System.out.println(password);
							{
								Employee newEmployee= new Employee( stringToMember(line) );
									
								if( newEmployee.register("password") != null ){
									System.out.println("Employee Successfully Registered ");
								} else{
									System.out.println("Register Failed: Member Already Registered");
								}
							}*/
							
							use=inputStream.readLine();
							pass=inputStream.readLine();
							EmployeeHelper EmployeeHelper = new EmployeeHelper();
							Employee employee             =  EmployeeHelper.login( new Email( use ) , pass);
							if( employee.isLoggedIn() ){
								//System.out.println( employee.getFirstName() + "[" + employee.getId() + "] Is Logged IN AS Employee !");
								outputStream.println("true");
							} else{
								outputStream.println("false");
							}
							
							switch(option) {
							   case 1:
							      // Statements
							      break; 
							   case 2:
								// Statements
							      break;
							   case 3:
									// Statements
								      break;
							   case 4:
									// Statements
								      break;
							   // You can have any number of case statements.
							   default : // Optional
							      // Statements
							}
							
						}
					}catch(IOException e) {System.err.println(e);}}
				}).start();
				}
				}		
	
	
}