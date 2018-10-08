public class Member {
	int id;
	boolean isloggedin = false;
	String	firstName;
	String	lastName;
	Address address;
	String phone;
	String email;

	/**
	 * Constructor - Insert all the args to this Member Instant
	 * @param id [int]
	 * @param firstName [String]
	 * @param lastName [String]
	 * @param address [String]
	 * @param phone [String]
	 * @param email [String]
	 */
	public Member( int id , String firstName , String lastName, String address, String phone, String email ){
		setId( id );
		setFirstName( firstName );
		setLastName( lastName );
		setAddress( new Address(address) ); // Casting From String to Address Class
		setPhone( phone );
		setEmail( email );
		this.isloggedin = true;
	}
	public Member( String firstName , String lastName, String address, String phone, String email ){
		setFirstName( firstName );
		setLastName( lastName );
		setAddress( new Address(address) ); // Casting From String to Address Class
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
	public int getId() {
		return id;
	}

	public void setId(int id) {
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

	public void setAddress(Address address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Email getEmail() { return new Email(email); }

	public void setEmail(String email) {
		this.email = email;
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
}
