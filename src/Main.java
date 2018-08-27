import javafx.application.Application;
import javafx.stage.Stage;

import java.sql.SQLException;
import java.util.concurrent.ExecutionException;

public class Main extends Application {

	public static void main(String[] args) throws SQLException {
		//launch(args);
		DataBaseHelper conn = new DataBaseHelper();
		//LoginForm lg =new LoginForm();
		try{
			conn.ViewTable();
			conn.destroy();
		} catch(Exception e){
			e.getMessage();
		}
	}

	@Override
	public void start(Stage primaryStage) {

	}
}
