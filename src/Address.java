
public class Address {
	int houseNumber;
	String street;
	String city;
	
	Address(String City,String Street,int HouseNumber)
	{
		setCity(City);
		setStreet(Street);
		setHouseNumber(HouseNumber);
	}
	
	public void setCity(String City) {city=new String(City);};
	public void setStreet(String Street) {street=new String(Street);};
	public void setHouseNumber(int HouseNumber) {houseNumber=HouseNumber;};
	
	public String getCity() {return city;};
	public String getStreet(){return street;};
	public int getHouseNumber() {return houseNumber;};
}


