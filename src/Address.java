//Class the keep all the adress details
public class Address {
	int houseNumber;
	String street;
	String city;
	
	Address(){
		setCity("");
		setStreet("");
		setHouseNumber(0);
	}
	/**
	 * Constructor
	 * @param City [String]
	 * @param Street [String]
	 * @param HouseNumber [int]
	 */
	//constructor for address with each properties
	Address(String City,String Street,int HouseNumber){
		setCity(City);
		setStreet(Street);
		setHouseNumber(HouseNumber);
	}
	/**
	 * Constructor - Split One String to City And Street
	 * @param fullAddress [String]
	 */
	//constructor for address based on another address
	Address( String fullAddress ){

		String[] addressParts = fullAddress.split(",");
		if(addressParts.length > 0 && addressParts[0] != null ){
			setCity( addressParts[0] );
		}
		try{
			if(addressParts.length > 1 && addressParts[1] != null){
				String[] fullStreet = addressParts[1].split( " " );
				setStreet( fullStreet[0] );
				setHouseNumber( 0 );
			}
		}catch ( Exception e){
			return;
		}
	}
//Set for the class properties
	public void setCity(String City) {city=new String(City);};
	public void setStreet(String Street) {street=new String(Street);};
	public void setHouseNumber(int HouseNumber) {houseNumber=HouseNumber;};
	public void setHouseNumber(String HouseNumber) {houseNumber=Integer.valueOf(HouseNumber);};
//get for the class Properties
	public String getCity() {return city;};
	public String getStreet(){return street;};
	public int getHouseNumber() {return houseNumber;};
	public String getAsString(){
		return new String( getCity() + ", " + getStreet() + ", " + getHouseNumber() );
	}
}


