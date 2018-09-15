
public class Address {
	int houseNumber;
	String street;
	String city;

	/**
	 * Constructor
	 * @param City [String]
	 * @param Street [String]
	 * @param HouseNumber [int]
	 */
	Address(String City,String Street,int HouseNumber)
	{
		setCity(City);
		setStreet(Street);
		setHouseNumber(HouseNumber);
	}
	/**
	 * Constructor - Split One String to City And Street
	 * @param fullAddress [String]
	 */
	Address( String fullAddress ){

		String[] addressParts = fullAddress.split(",");

		if(addressParts.length > 0 && addressParts[0] != null ){
			setCity( addressParts[0] );
		}
		if(addressParts.length > 1 && addressParts[1] != null){
			String[] fullStreet = addressParts[1].split( " " );
			setStreet( fullStreet[0] );
			setHouseNumber( 0 );
		}
	}

	public void setCity(String City) {city=new String(City);};
	public void setStreet(String Street) {street=new String(Street);};
	public void setHouseNumber(int HouseNumber) {houseNumber=HouseNumber;};

	public String getCity() {return city;};
	public String getStreet(){return street;};
	public int getHouseNumber() {return houseNumber;};
	public Address getAddress(){ return  this; }
	public String getAsString(){
		return new String( getCity() + ", " + getStreet() + ", " + getHouseNumber() );
	}
}


