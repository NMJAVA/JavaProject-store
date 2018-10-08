import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class EmployeeHelper extends MemberHelper {
	public Member register( Member member , String password) {
		try {
			DataBaseHelper db = new DataBaseHelper();
			Member newmember = super.register(member, password);
			String[] employee_key = {"member_id"};
			String[] employee_value = {String.valueOf(newmember .getId())};
			db.insert("employees", employee_key, employee_value);
			return newmember;
		} catch ( Exception e ){}
		return null;
	}
}
