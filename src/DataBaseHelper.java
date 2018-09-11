import java.sql.*;
import java.lang.*;

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
	public ResultSet getTableResultSet( String tableName ) throws SQLException{
		String sql;
		stmt = conn.createStatement();
		sql  = "SELECT * FROM " + tableName;
		ResultSet rs = stmt.executeQuery(sql);
		return rs;
	}

	public boolean insert(String tableName , String[] keys , String[] values ) throws SQLException{
		try {
			String sql;
			stmt = conn.createStatement();
			sql = "INSERT INTO " + tableName + " (";
			for (int i = 0; i < keys.length; i++) {
				sql += keys[i];
				if (keys.length != (i + 1))
					sql += ", ";
			}
			sql += " ) VALUES( ";
			for (int i = 0; i < values.length; i++) {
				sql += '"' + values[i] + '"';
				if (values.length != (i + 1))
					sql += ", ";
			}
			sql += ")";
			int rows = stmt.executeUpdate(sql);
			return true;
		} catch(Exception e){
			System.out.println( e.getMessage() );
			return false;
		}
	}

	public void destroy() throws SQLException{
		stmt.close();
		conn.close();
	}
}
