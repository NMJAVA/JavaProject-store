import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class EmployeeHelper {
	/**
	 * Register New Employee to our database ( insert ) by given Employee Object and password
	 * @param employee [Employee]
	 * @param password [String]
	 * @return Employee Object On Success | null to failed
	 */
	public Employee register( Employee employee , String password) {
		try{
			DataBaseHelper db  = new DataBaseHelper();
			String[] keys   = { "name" , "last_name" , "address" , "phone" , "email" , "password" };
			String[] values = {
						employee.getFirstName(),
						employee.getLastName(),
						employee.getAddress(),
						employee.getPhone(),
						employee.getEmail().getEmailString(),
						PasswordUtils.generateSecurePassword( password , PasswordUtils.getSalt() )
					};
			if( db.insert( "employee" ,keys , values ) ){
				Employee newEmployee = this.GetByEmail( employee.getEmail() );
				return newEmployee;
			}
		} catch ( Exception e ){}
		return null;
	};

	/**
	 * Check if employee exist by ID(int)
	 * @param ID
	 * @return true on exist | false on failed
	 */
	public boolean isExist( int ID ) {
		boolean exist = false;
		try{
			DataBaseHelper db  = new DataBaseHelper();
			ResultSet rs       = db.getResult( "SELECT ID FROM employees WHERE ID =" + ID );
			if( rs.next() ) {
				exist = true;
			}
			rs.close();

		}catch ( Exception e ){}
		return exist;
	};
	/**
	 * Check if employee exist by email(Email)
	 * @param email
	 * @return true on exist | false on failed
	 */
	public boolean isExist( Email email ) {
		boolean exist = false;
		try{
			DataBaseHelper db  = new DataBaseHelper();
			ResultSet rs       = db.getResult( "SELECT ID FROM employees WHERE email =" + email );
			if( rs.next() ) {
				exist = true;
			}
			rs.close();

		}catch ( Exception e ){}
		return exist;
	};

	/**
	 * Return Employee object by given exist email address ( email is unique )
	 * @param email
	 * @return Employee
	 */
	public Employee GetByEmail( Email email ) {
		DataBaseHelper db  = new DataBaseHelper();
		try{
			ResultSet rs   = db.getResult( "SELECT * FROM employees WHERE email =" + email );
			if( rs.next() ){
				return new Employee(
							rs.getInt("id"),
							rs.getString("name"),
							rs.getString("last_name"),
							rs.getString("address"),
							rs.getString("phone"),
							rs.getString("email")
						);
			}
		}catch ( Exception e ){}
			return null;
	};
	/**
	 * Return Employee object by given exist ID ( ID is unique )
	 * @param ID
	 * @return Employee
	 */
	public Employee GetByID( int ID ) {
		DataBaseHelper db  = new DataBaseHelper();
		try{
			ResultSet rs   = db.getResult( "SELECT * FROM employees WHERE ID =" + ID );
			if( rs.next() ){
				return new Employee(
						rs.getInt("id"),
						rs.getString("name"),
						rs.getString("last_name"),
						rs.getString("address"),
						rs.getString("phone"),
						rs.getString("email")
				);
			}
		}catch ( Exception e ){}
		return null;
	};

	/**
	 * Update Employee Fields in the DataBase
	 * @param employee [Employee]
	 * @throws SQLException
	 */
	public void update(Employee employee) throws SQLException {
		DataBaseHelper db  = new DataBaseHelper();
		db.update( "employee" , "name" , employee.getFirstName() , employee.getId() );
		db.update( "employee" , "last_name" , employee.getLastName() , employee.getId() );
		db.update( "employee" , "address" , employee.getAddress() , employee.getId() );
		db.update( "employee" , "phone" , employee.getPhone() , employee.getId() );
	};
	public void ImportWordFile(){
		
	};

	/**
	 * Get ArrayList of all the Employees in the database
	 * @return ArrayList
	 * @throws SQLException
	 */
	public ArrayList<Employee> getAllEmployees() throws SQLException {
		ArrayList<Employee> allEmployees = new ArrayList<Employee>();
		try{
			DataBaseHelper db  = new DataBaseHelper();
			ResultSet rs       = db.getTableResultSet( "employees" );
			while( rs.next() ) {
				allEmployees.add( new Employee( rs.getInt("id") , rs.getString("name") , rs.getString("last_name") , rs.getString("address"), rs.getString("phone"), rs.getString("email") ) );
			}
			rs.close();
		}catch ( Exception e ){}
		return allEmployees;
	}

	/**
	 * Print cards of all Employees that registered in the DataBase.
	 * @throws SQLException
	 */
	public void printAllEmployees()throws SQLException {
		ArrayList<Employee> allEmployees = new ArrayList<Employee>();
		allEmployees = this.getAllEmployees();
		for( int i=0;i<allEmployees.size(); i++){
			Employee tmpEmployee = allEmployees.get(i);
			System.out.println( "Name: " + tmpEmployee.getFirstName() + " " + tmpEmployee.getLastName() + " (" + tmpEmployee.getId() + ")");
			System.out.println( "Email: " + tmpEmployee.getEmail() );
			System.out.println( "Phone: " + tmpEmployee.getPhone() );
			System.out.println( "Address: " + tmpEmployee.getAddress() );
			System.out.println( "-------");
		}
	}
}
