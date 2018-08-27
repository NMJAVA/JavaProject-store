import java.sql.*;
public class DataBaseHelper {
	static {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch(ClassNotFoundException ex) {
			System.err.println("Unable to load MySQL Driver");
		}
	}

	static public void main(String[] args) throws Exception
	{
		String jdbcUrl = "jdbc:mysql://localhost/sample?user=root&password=1!qaz2@wsx";
		Connection con = DriverManager.getConnection(jdbcUrl);
		System.out.println("Connected!");
		con.close();
	}
}
