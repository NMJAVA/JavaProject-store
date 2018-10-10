public class Product {
	int id;
	String  name;
	String  sku;
	String  type;
	String  size;
	Integer price;
	Integer amount;

	/**
	 * Constructor - Insert all the args to this Product Instant
	 * @param id [int]
	 * @param sku [String]
	 * @param name [String]
	 * @param type [String]
	 * @param size [String]
	 * @param price [Float]
	 */
	public Product( int id , String sku , String name, String type, String size, Integer price ){
		setId( id );
		setSKU( sku );
		setName( name );
		setType( type );
		setSize( size );
		setPrice( price );
	}
	public Product( String sku , String name, String type, String size, Integer price ,Integer amount ){
		setSKU( sku );
		setName( name );
		setType( type );
		setSize( size );
		setPrice( price );
		setAmount( amount );
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSKU() {
		return sku;
	}

	public void setSKU(String sku) {
		this.sku = sku;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public Integer getPrice() { return price; }

	public void setPrice(Integer price) {
		this.price = price;
	}

	public Integer getAmount() { return amount; }

	public void setAmount(Integer amount) {
		this.amount = amount;
	}
}
