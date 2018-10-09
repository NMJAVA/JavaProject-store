public class PurchaserHelper extends MemberHelper {
	public Member register( Member member , String password) {
		try {
			DataBaseHelper db = new DataBaseHelper();
			Member newmember  = super.register(member, password);// Call Parent Class to register user

			// Register Member as purchaser
			String[] purchaser_key = {"member_id"};
			String[] purchaser_value = {String.valueOf(newmember .getId())};
			db.insert("purchasers", purchaser_key, purchaser_value);
			return newmember;
		} catch ( Exception e ){}
		return null;
	}
}
