
public class Employee {
	 int id;
	 String	firstName; 
	 String	lastName;
	 Address address;
	 String phone;
	 String email;
	public Employee(){

	}
	public Employee( String firstName , String lastName, String address, String phone, String email ){
		setFirstName( firstName );
		setLastName( lastName );
		setAddress( new Address(address) );
		setPhone( phone );
		setEmail( email );
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

	public Address getAddress() {
		return address;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
