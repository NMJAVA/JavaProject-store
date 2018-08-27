import java.sql.*;
public class DataBaseHelper {
	// JDBC driver name and database URL
	static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
	static final String DB_URL 		= "jdbc:mysql://localhost:3306/JavaStore?useSSL=false&useLegacyDatetimeCode=false&serverTimezone=UTC";

	//  Database credentials
	static final String USER = "root";
	static final String PASS = "1!qaz2@wsx";

	Connection conn = null;
	Statement  stmt = null;

	public DataBaseHelper() {

		try {
			//Register JDBC driver
			Class.forName("com.mysql.cj.jdbc.Driver");

			//Open a connection
			System.out.println("Connecting to database...");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
		} catch (SQLException se) {
			//Handle errors for JDBC
			se.printStackTrace();
		} catch (Exception e) {
			//Handle errors for Class.forName
			e.printStackTrace();
		} finally {
			//finally block used to close resources
			try {

			} catch (Exception e) {
				e.printStackTrace();
			}//end finally try
		}//end try
	}
	public void ViewTable() throws SQLException{
		stmt = conn.createStatement();
		String sql;
		sql = "SELECT * FROM employees";
		ResultSet rs = stmt.executeQuery(sql);
		while( rs.next() ) {
			Employee worker = new Employee( rs.getString("name") , rs.getString("last_name") , rs.getString("address"), rs.getString("phone"), rs.getString("email") );
		}
		rs.close();
	}

	public void destroy() throws SQLException{
		stmt.close();
		conn.close();
	}
}
