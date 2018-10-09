public class EmployeeHelper extends MemberHelper {
	public Member register( Member member , String password) {
		try {
			DataBaseHelper db = new DataBaseHelper();
			Member newmember  = super.register(member, password); // Call Parent Class to register user

			// Register Member as employee
			String[] employee_key = {"member_id"};
			String[] employee_value = {String.valueOf(newmember .getId())};
			db.insert("employees", employee_key, employee_value);
			return newmember;
		} catch ( Exception e ){}
		return null;
	}
}
