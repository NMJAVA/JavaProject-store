import javafx.application.Application;
import javafx.stage.Stage;


import java.sql.SQLException;
import java.util.concurrent.ExecutionException;

public class Main extends Application {

	public static void main(String[] args) throws SQLException {
		//launch(args);
		//DataBaseHelper dbHelper = new DataBaseHelper();
		//LoginForm lg =new LoginForm();
		try{
			//dbHelper.getTableResultSet( "employees" );
			//dbHelper.insert( "employees" , new String[] {"name" , "last_name"} ,  new String[] { "Or2" , "Papo2" } );
			EmployeeHelper employeeHelper = new EmployeeHelper();
			employeeHelper.printAllEmployees();
		} catch(Exception e){
			e.getMessage();
		}
		//dbHelper.destroy();
	}

	@Override
	public void start(Stage primaryStage) {

	}
}
