
public class Employee extends Member {
	public Employee( Member member){
		setFirstName( member.getFirstName() );
		setLastName( member.getLastName() );
		setAddress( member.getAddress() ); // Casting From String to Address Class
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
		MemberHelper MemberHelper = new EmployeeHelper();
		Member newMember          = MemberHelper.register( this  , password );
		return newMember;
	}

	public Order sell( Product product , Integer customer_id ){
		MemberHelper MemberHelper = new MemberHelper();
		Member customer           = new Customer( MemberHelper.GetByID( customer_id ) );
		return customer.buy( product.getSKU() , this.getId() );
	}
}
