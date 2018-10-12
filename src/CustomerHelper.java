public class CustomerHelper extends MemberHelper {
	public Member register( Member member , String password) {
		try {
			DataBaseHelper db = new DataBaseHelper();
			Member newmember  = super.register(member, password);// Call Parent Class to register user

			// Register Member as customer
			String[] customer_key = {"member_id"};
			String[] customer_value = {String.valueOf(newmember .getId())};
			db.insert("customers", customer_key, customer_value);
			return newmember;
		} catch ( Exception e ){}
		return null;
	}
}
