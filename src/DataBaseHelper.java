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

	/**
	 * Constructor - Create connection to the DataBase
	 */
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

	/**
	 * Return ResultSet by given sql statement [ String ]
	 * @param sql [String]
	 * @return ResultSet of the executeQuery
	 * @throws SQLException
	 */
	public ResultSet getResult( String sql ) throws SQLException{
		stmt = conn.createStatement();;
		ResultSet rs = stmt.executeQuery(sql);
		return rs;
	}
	/**
	 * Return ResultSet of all the rows in the specific given tableName  [ String ]
	 * @param tableName [String]
	 * @return ResultSet of the executeQuery
	 * @throws SQLException
	 */
	public ResultSet getTableResultSet( String tableName ) throws SQLException{
		return getResult( "SELECT * FROM " + tableName );
	}

	/**
	 * Insert to the DataBase New Values By given tableName array of keys and array of values
	 * Length of both StringArrays need to be the same ( Or we get sql Error )
	 * @param tableName [String]
	 * @param keys [Array of strings]
	 * @param values [Array of strings]
	 * @return true on success | false on failed
	 * @throws SQLException
	 */
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

	/**
	 * Update Column in specific row by the ID of the row
	 * @param tableName [String]
	 * @param key [String]
	 * @param value [String]
	 * @param ID [INT]
	 * @return true on update success | false on failed
	 * @throws SQLException
	 */
	public boolean update(String tableName , String key , String value , int ID ) throws SQLException{
		String query = "UPDATE ? SET ? = ? WHERE ID = ?";
		try{
			PreparedStatement preparedStmt = conn.prepareStatement(query);

			preparedStmt.setString   (1, tableName );
			preparedStmt.setString   (2, key);
			preparedStmt.setString   (3, value );
			preparedStmt.setInt	 	 (4, ID );

			preparedStmt.executeUpdate();
		} catch (Exception e){
			return false;
		}
		return true;
	}

	/**
	 * Kind of destructor ( need to be called )
	 * At the END of our program ( this close the connections and statements we used )
	 * @throws SQLException
	 */
	public void destroy() throws SQLException{
		stmt.close();
		conn.close();
	}
}
