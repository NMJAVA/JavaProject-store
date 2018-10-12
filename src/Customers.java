
public abstract class Customers {
	int id;
	String firstName;
	String lastname;
	Address address;
	String	phone;
	String email;

	void setId(int id) {
		this.id=id;
	}
	void setFirstName(String firstName) {
		this.firstName=firstName;
	}
	void SetLastName(String lastName) {
		this.lastname=lastName;
	}
	void setAdress(Address adress) {
		this.address=adress;
	}
	void setPhone(String phone) {
		this.phone=phone;
	}
	void setEmail(String email) {
		this.email=email;
	}
	
	public abstract void Buy();
	public abstract void Sell();
}
 
