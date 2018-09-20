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
			EmployeeHelper employeeHelper = new EmployeeHelper();
			Employee employeeToRegister = new Employee("Niv", "Noiman", "Rishon Lezzion, Maccabi Zahir, 6", "0524011331" , "niv945@gmail.com" );
			employeeHelper.register( employeeToRegister  , "123456789" );
			employeeHelper.printAllEmployees();
			Employee employee =  employeeHelper.login( new Email( "niv945@gmail.com" ) , "1223456789" );
			if( employee.isLoggedIn() ){
				System.out.println( employee.getFirstName() + " Is Logged IN !");
			} else{
				System.out.println( "Email Or Password Is Incorrect ");
			}
		} catch(Exception e){
			System.out.println( e.getMessage() );
		}
		//dbHelper.destroy();
	}

//	@Override
//	public void start(Stage primaryStage) {

//	}
}
