public class EmployeeHelper extends MemberHelper {
	@Override
	public Employee register( Member member , String password) {
		try {
			DataBaseHelper db = new DataBaseHelper();
			Member newMember  = super.register(member, password); // Call Parent Class to register user

			// Register Member as employee
			String[] employee_key = {"member_id"};
			String[] employee_value = {String.valueOf(newMember .getId())};
			db.insert("employees", employee_key, employee_value);
			return new Employee( newMember );
		} catch ( Exception e ){}
		return null;
	}

	@Override
	public Employee login( Email email , String password ){
		Employee Employee = new Employee( super.login( email , password ) );
		return Employee;
	}
}
