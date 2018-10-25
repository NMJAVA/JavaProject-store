import java.sql.SQLException;
//Sub class for member, save the Employee detais
public class Employee extends Member {
	public Employee( Member member){
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
		MemberHelper MemberHelper = new EmployeeHelper();
		Member newMember          = MemberHelper.register( this  , password );
		return newMember;
	}
//sell functino using the database, for the spesfic employee
	public Order sell( Product product , Integer customer_id ) throws SQLException {
		MemberHelper MemberHelper = new MemberHelper();
		Member customer           = new Customer( MemberHelper.GetByID( customer_id ) );
		System.out.println(product.getAmount());
		
		return customer.buy( product , this.getId() );
	}

	@Override
	public String checkType() throws SQLException {
		return new String("employee");
	}
}
