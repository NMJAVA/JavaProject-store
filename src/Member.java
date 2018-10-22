import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.lang.*;

public class Member {
	Integer id;
	boolean isloggedin = false;
	String	firstName;
	String	lastName;
	Address address;
	String phone;
	Email email;

	/**
	 * Constructor - Insert all the args to this Member Instant
	 * @param id [Integer]
	 * @param firstName [String]
	 * @param lastName [String]
	 * @param address [String]
	 * @param phone [String]
	 * @param email [String]
	 */
	public Member( Integer id , String firstName , String lastName, String address, String phone, String email ){
		setId( id );
		setFirstName( firstName );
		setLastName( lastName );
		setAddress( address ); // Casting From String to Address Class
		setPhone( phone );
		setEmail( email );
		this.isloggedin = true;
	}
	public Member( String firstName , String lastName, String address, String phone, String email ){
		setFirstName( firstName );
		setLastName( lastName );
		setAddress( address ); // Casting From String to Address Class
		setPhone( phone );
		setEmail( email );
		this.isloggedin = false;
	}
	public Member( String firstName , String lastName, Address address, String phone, String email ){
		setFirstName( firstName );
		setLastName( lastName );
		setAddress( address ); // Casting From String to Address Class
		setPhone( phone );
		setEmail( email );
		this.isloggedin = false;
	}

	public Member() {
		this.isloggedin = false;
	}
	public boolean isLoggedIn(){
		return this.isloggedin;
	}
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getAddress() {
		return address.getAsString();
	}
	public Address getObjectAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = new Address( address );
	}
	public void setAddress(Address address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() { return email.toString(); }

	public void setEmail(String email) {
		this.email = new Email( email );
	}

	public String getSecuredPassword(){
		try{
			if( id != 0  ){
				DataBaseHelper db  = new DataBaseHelper();
				String SecuredPassword = db.get( "members" , "password" , "id = '" + id + "'" );
				return SecuredPassword;
			}
		}catch ( Exception e ){
			System.out.println(e.getMessage());
		}
		return null;
	}

	public Member register( String password ){
		MemberHelper MemberHelper = new MemberHelper();
		Member newMember          = MemberHelper.register( this , password );
		return newMember;
	}

	public Order buy( String productSKU, Integer memberID ) throws SQLException {
		ProductHelper ProductHelper = new ProductHelper();
		OrderHelper OrderHelper     = new OrderHelper();
		Product product             = ProductHelper.GetBySKU( productSKU );
		java.sql.Date sqlDate       = new java.sql.Date(new java.util.Date().getTime());
		Order order                 = new Order( productSKU , sqlDate.toString(), memberID, this.getId(), product.getAmount() );
		OrderHelper.insert( order );
		// if member buy more X products change status to VIP
		if( product.getAmount() > 3 ){
			changeStatus(1);
		}
		return order;
	}

	public Order buy( String productSKU , Integer memberID , Double discountRate ) throws SQLException {
		ProductHelper ProductHelper = new ProductHelper();
		OrderHelper OrderHelper     = new OrderHelper();
		Product product             = ProductHelper.GetBySKU( productSKU );
		java.sql.Date sqlDate       = new java.sql.Date(new java.util.Date().getTime());
		Order order                 = new Order( productSKU , sqlDate.toString(), memberID, this.getId(), (int)(product.getAmount()*discountRate));
		OrderHelper.insert( order );
		// if member buy more X products change status to VIP
		if( product.getAmount() > 3 ){
			changeStatus(1);
		}
		return order;
	}

	// Check if Employee OR Customer Type
	public String checkType() throws SQLException {
		MemberHelper MemberHelper = new MemberHelper();
		return MemberHelper.checkType( this );
	}
	// Check if Member Status
	public Integer checkStatus() throws SQLException {
		MemberHelper MemberHelper = new MemberHelper();
		return MemberHelper.checkStatus( this );
	}
	// Change Member Status ( 1 - VIP | 0 - Regular )
	public void changeStatus( Integer status ) throws SQLException {
		MemberHelper MemberHelper = new MemberHelper();
		MemberHelper.changeStatus( this , status );
	}

}
