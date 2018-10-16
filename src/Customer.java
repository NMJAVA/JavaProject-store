public class Customer extends Member {
	public Customer( Member member){
		setFirstName( member.getFirstName() );
		setLastName( member.getLastName() );
		setAddress( member.getObjectAddress() ); // Casting From String to Address Class
		setPhone( member.getPhone() );
		setEmail( member.getEmail() );
		if( member.getId() != null ){
			setId( member.getId() );
			this.isloggedin = true;
		} else{
			this.isloggedin = false;
		}
	}
	@Override
	public Member register( String password ){
		MemberHelper MemberHelper = new CustomerHelper();
		Member newMember          = MemberHelper.register( this  , password );
		return newMember;
	}
	@Override
	public Order buy( String productSKU , Integer employeeID){
		return super.buy( productSKU, employeeID );
	}
}
