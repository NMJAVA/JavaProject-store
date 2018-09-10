import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class EmployeeHelper {
	public void Insert(Employee employee) {
		
	};
	public void Get(Employee employee) {
		
	};
	public void Update(Employee employee){
		
	};
	public void ImportWordFile(){
		
	};

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
