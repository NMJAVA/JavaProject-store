import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.lang.*;


public class MultiServer {
	
	
	
	static String proudctToString(Product x) {
	return (x.getSKU()+"|"+x.getName()+"|"+x.getType()+"|"+x.getSize()+"|"+x.getPrice());
	
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
						Product newProduct;
						boolean isOff=false;
						boolean getBack=false;
						String option="";
						String searchBy;
						Employee employee=null;
						boolean isLogged=false;
						ProductHelper productHelper=new ProductHelper();
						ArrayList<Order> searchResult;

						
						
					
						while(!isLogged) {
							use=inputStream.readLine();
							pass=inputStream.readLine();
							EmployeeHelper EmployeeHelper = new EmployeeHelper();
							employee             =  EmployeeHelper.login( new Email( use ) , pass);
							if( employee != null ) {
								if( employee.isLoggedIn() ){
									isLogged=true;
									outputStream.println("true");
									//firstName, lastName, address, phone , email
									String tempString=employee.getFirstName()+"|"+employee.getLastName()+"|"+employee.getObjectAddress().getCity()+"|"+employee.getObjectAddress().getStreet()+"|"+employee.getObjectAddress().getHouseNumber()+"|"+employee.getPhone()+"|"+employee.getEmail();
									outputStream.println(tempString);
								} else{
									outputStream.println("false");
								}	
							}
							else{
							outputStream.println("false");
							}	
						}
						
						while(!isOff) {
							getBack=false;
							option=inputStream.readLine();
							switch(option) {
							   case "sell":
								   while(!getBack) {
								   option=inputStream.readLine();
							switch(option) {
							   case "add":
									outputStream.println("approved");
									line=inputStream.readLine();
									
						
									
									
									newProduct=productHelper.GetBySKU(line);
									
									
									if(newProduct!=null)
									{
										outputStream.println(proudctToString(newProduct));
									}
									else
									{
										outputStream.println("false");
									}
							      break; 
							   case "sell":
								  
								   outputStream.println("approved");		   
								   line=inputStream.readLine();
								   line=inputStream.readLine();
								   Email email=new Email(line);
								   if(email.isValid(email.toString())) {
								   }
								   System.out.println(email.toString());
								   CustomerHelper customerHelper=new CustomerHelper();
								 
								   Customer customer = customerHelper.login(email, "111111111");
								   System.out.println(customer.getLastName());
					
									if (customer.isLoggedIn()) {
									
										outputStream.println("true");
										line=inputStream.readLine();
										while(!(line.equals("end")))
										{
											
											newProduct=productHelper.GetBySKU(line);
											line=inputStream.readLine();
											int amount=Integer.parseInt(line);
											newProduct.setAmount(amount);
											employee.sell(newProduct, customer.getId());
											line=inputStream.readLine();
											System.out.println(line);
											
										}
									} else {
										System.out.println("false");
										outputStream.println("false");
									}
									
							
								   		case "back":
									   getBack=true;
									   break;
								}
								   }
								      break;
								      
								      
							   case "reports":
									
								   while(!getBack) {
									   option=inputStream.readLine();
									   switch (option) {
										   case "search":
											   OrderHelper orderHelper=new OrderHelper();
											   searchResult = new ArrayList<Order>();
											   searchBy=inputStream.readLine();
											 switch (searchBy) {	
											 case "all":
										
													searchResult=orderHelper.getAllOrders();
												 break;
											 case "buyer":
												 line=inputStream.readLine();
									
													searchResult=orderHelper.getAllByCustomerEmail(line);
													 
												 break;
											 case "seller":
												 line=inputStream.readLine();	
											
													searchResult=orderHelper.getAllByEmployeeEmail(line);
													 
												 break;
											 }
											 for(int i=0;i<searchResult.size();i++ )
												{
												 
												 outputStream.println("next");
												 line=String.valueOf(searchResult.get(i).getId());
												 outputStream.println(line);
												 line=String.valueOf(searchResult.get(i).getProductSKU());
												 outputStream.println(line);
												 line=searchResult.get(i).getDate();
												 outputStream.println(line);
												 line=String.valueOf(searchResult.get(i).getEmployeeID());
												 outputStream.println(line);
												 line=String.valueOf(searchResult.get(i).getCustomerID());
												 outputStream.println(line);
												 line=String.valueOf(searchResult.get(i).getAmount());
												 outputStream.println(line);
									              
												}
											 outputStream.println("end");
											 break;
										   case "back":
											   getBack=true;
												 break;
									   }  
								   }
							      break;
							   case "newCustomer":
								   while(!getBack) {
									   option=inputStream.readLine();
									   switch (option) {
										   case "submit":
								   line=inputStream.readLine();
									{
										Customer newCustomer= new Customer( stringToMember(line) );
											
										if( newCustomer.register("password") != null ){
											System.out.println("Employee Successfully Registered ");
										} else{
											System.out.println("Register Failed: Member Already Registered");
										}
									}
									break;
										
										   case "back":
											   getBack=true;
										   break;
										   		
									   }
									   
								   }
								      break;
							   case "Chat":
								   while(!getBack) {
									   option=inputStream.readLine();
									   switch (option) {
									   
									   case "chat":
										  line=inputStream.readLine();
										  outputStream.println(line);
									   break;

									   case "back":
										   getBack=true;
									   break;
									   }
								   }
								   break;
							   // You can have any number of case statements.
							   default : // Optional
							      // Statements
							}
							
						}
					}catch(IOException e) {System.err.println(e);} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}}
				}).start();
				}
				}		
	
	
}