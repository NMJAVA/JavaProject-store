public class CustomerHelper extends MemberHelper {
	@Override
	public Customer register( Member member , String password) {
		try {
			DataBaseHelper db = new DataBaseHelper();
			Member newMember  = super.register(member, password);// Call Parent Class to register user

			// Register Member as customer
			String[] customer_key = {"member_id"};
			String[] customer_value = {String.valueOf(newMember .getId())};
			db.insert("customers", customer_key, customer_value);
			return new Customer( newMember );
		} catch ( Exception e ){}
		return null;
	}

	@Override
	public Customer login( Email email , String password ){
		try {
			Customer Customer = new Customer( super.login( email , password ) );
			if( Customer.checkStatus() == 1 ){
				Customer VIPCustomer = new VIPCustomer( super.login( email , password ) );
				return VIPCustomer;
			}
			return Customer;
		}catch(Exception e) {
			return null;
		}
	}
}
