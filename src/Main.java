import javafx.application.Application;
import javafx.stage.Stage;


import java.beans.Expression;
import java.sql.SQLException;
import java.util.concurrent.ExecutionException;
/*
* Remove - extends Application |  Niv 13.09.18 [ public class Main  { ]
* */
public class Main  {
	public static void main(String[] args){
		//launch(args);
		//DataBaseHelper dbHelper = new DataBaseHelper();
		//LoginForm lg =new LoginForm();
		try{
			//dbHelper.getTableResultSet( "employees" );
			//dbHelper.insert( "employees" , new String[] {"name" , "last_name"} ,  new String[] { "Or2" , "Papo2" } );

			/* Member - Employee*/
			//////////////////////
			MemberHelper MemberHelper = new EmployeeHelper();
			Member memberToRegister = new Member("Niv", "Noiman", "Rishon Lezzion, Maccabi Zahir, 6", "0524011331" , "niv945@gmail.com" );
			Member newMember = MemberHelper.register( memberToRegister  , "123456789" );
			if( newMember != null ){
				MemberHelper.printAllMembers();
			} else{
				System.out.println("User Already Registerd");
			}
			Member member =  MemberHelper.login( new Email( "niv945@gmail.com" ) , "123456789" );
			if( member.isLoggedIn() ){
				System.out.println( member.getFirstName() + " Is Logged IN !");
			} else{
				System.out.println( "Email Or Password Is Incorrect ");
			}

			/* Product */
			/////////////
			ProductHelper ProductHelper = new ProductHelper();
			Product productToInsert     = new Product( "1" , "Jeans" , "trousers" , "M" , 500, 20 );
			Product newProduct = ProductHelper.insert( productToInsert );
			if( newProduct != null ){
				System.out.println("Product Insert Successfully");
			} else{
				System.out.println("Product Already Inserted");
			}
			ProductHelper.printAllProducts();
		} catch(Exception e){
			System.out.println( e.getMessage() );
		}
		//dbHelper.destroy();
	}

//	@Override
//	public void start(Stage primaryStage) {

//	}
}
