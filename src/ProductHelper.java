import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProductHelper {
	/**
	 * Insert New Product to our database by given Product Object
	 * @param product [Product]
	 * @return Product Object On Success | null to failed
	 */
	public Product insert( Product product ) {
		try{
			if( ! isExist( product.getSKU() ) ){
				DataBaseHelper db  = new DataBaseHelper();
				String[] keys   = { "sku" , "name" , "type" , "size" , "price" };
				String[] values = {
						product.getSKU(),
						product.getName(),
						product.getType(),
						product.getSize(),
						product.getPrice().toString()
				};
				if( db.insert( "products" ,keys , values ) ){
					Product newProduct = this.GetBySKU( product.getSKU() );
					// Update Inventory with the new product
					db.insert( "inventory" , new String[] {"product_sku" , "amount"} , new String[] {product.getSKU() , product.getAmount().toString() } );
					return newProduct;
				}
			} else{
				return null;
			}
		} catch ( Exception e ){
			System.out.println( e.getMessage() );
		}
		return null;
	};

	/**
	 * Check if product exist by SKU(int)
	 * @param sku
	 * @return true on exist | false on failed
	 */
	public boolean isExist( String sku ) {
		try{
			DataBaseHelper db  = new DataBaseHelper();
			ResultSet rs       = db.getResult( "SELECT ID FROM products WHERE sku =" + sku );
			if( rs.next() ) {
				return true;
			}
			rs.close();

		}catch ( Exception e ){}
		return false;
	};

	/**
	 * Return Product object by given exist sku ( sku is unique )
	 * @param sku
	 * @return Product
	 */
	public Product GetBySKU( String sku ) {
		DataBaseHelper db  = new DataBaseHelper();
		try{
			ResultSet rs   = db.getResult( "SELECT * FROM products WHERE sku ='" + sku + "'" );
			if( rs.next() ){
				Product tmpProduct =  new Product(
						rs.getInt("ID"),
						rs.getString("sku"),
						rs.getString("name"),
						rs.getString("type"),
						rs.getString("size"),
						rs.getInt("price")
				);
				ResultSet rs2   = db.getResult( "SELECT * FROM inventory WHERE product_sku ='" + sku + "'" );
				if( rs2.next() ){
					tmpProduct.setAmount(rs2.getInt("amount"));
				}
				return tmpProduct;
			}
		}catch ( Exception e ){
			System.out.println( e.getMessage());
		}
		return null;
	};

	/**
	 * Update Product Fields in the DataBase
	 * @param product [Product]
	 * @throws SQLException
	 */
	public void update(Product product) throws SQLException {
		DataBaseHelper db  = new DataBaseHelper();
		int product_id = product.getId();
		db.update( "members" , "name" , product.getName() , product_id );
		db.update( "members" , "sku" , product.getSKU() , product_id );
		db.update( "members" , "type" , product.getType() , product_id );
		db.update( "members" , "size" , product.getSize() , product_id );
		db.update( "members" , "price" , product.getPrice() , product_id );
		db.update( "inventory" , "amount" , (float)product.getAmount() , "product_sku" , product.getSKU() );
	};

	/**
	 * Get ArrayList of all the Products in the database
	 * @return ArrayList
	 * @throws SQLException
	 */
	public ArrayList<Product> getAllProducts() throws SQLException {
		ArrayList<Product> allProducts= new ArrayList<Product>();
		try{
			DataBaseHelper db  = new DataBaseHelper();
			ResultSet rs       = db.getTableResultSet( "products" );
			while( rs.next() ) {
				Product tmpProduct = new Product(
						rs.getInt("ID"),
						rs.getString("sku"),
						rs.getString("name"),
						rs.getString("type"),
						rs.getString("size"),
						rs.getInt("price")
				);
				ResultSet rs2   = db.getResult( "SELECT * FROM inventory WHERE product_sku ='" + tmpProduct.getSKU() + "'" );
				if( rs2.next() ){
					tmpProduct.setAmount(rs2.getInt("amount"));
				}
				allProducts.add(tmpProduct);
			}
			rs.close();
		}catch ( Exception e ){
			System.out.println( e.getMessage() );
		}
		return allProducts;
	}
	/**
	 * Get ArrayList of all the Products in the database with the provided Type
	 * @param typeToSearch [String]
	 * @return ArrayList
	 * @throws SQLException
	 */
	public ArrayList<Product> getAllByType( String typeToSearch ) throws SQLException {
		ArrayList<Product> allProducts= new ArrayList<Product>();
		try{
			DataBaseHelper db  = new DataBaseHelper();
			ResultSet rs       = db.getResult( "SELECT * FROM products WHERE type LIKE '%" + typeToSearch + "%'" );
			while( rs.next() ) {
				Product tmpProduct = new Product(
						rs.getInt("ID"),
						rs.getString("sku"),
						rs.getString("name"),
						rs.getString("type"),
						rs.getString("size"),
						rs.getInt("price")
				);
				ResultSet rs2   = db.getResult( "SELECT * FROM inventory WHERE product_sku ='" + tmpProduct.getSKU() + "'" );
				if( rs2.next() ){
					tmpProduct.setAmount(rs2.getInt("amount"));
				}
				allProducts.add(tmpProduct);
			}
			rs.close();
		}catch ( Exception e ){
			System.out.println( e.getMessage() );
		}
		return allProducts;
	}
	/**
	 * Get ArrayList of all the Products in the database with the provided Size
	 * @param sizeToSearch [String]
	 * @return ArrayList
	 * @throws SQLException
	 */
	public ArrayList<Product> getAllBySize( String sizeToSearch ) throws SQLException {
		ArrayList<Product> allProducts= new ArrayList<Product>();
		try{
			DataBaseHelper db  = new DataBaseHelper();
			ResultSet rs       = db.getResult( "SELECT * FROM products WHERE type LIKE '%" + sizeToSearch + "%'" );
			while( rs.next() ) {
				Product tmpProduct = new Product(
						rs.getInt("ID"),
						rs.getString("sku"),
						rs.getString("name"),
						rs.getString("type"),
						rs.getString("size"),
						rs.getInt("price")
				);
				ResultSet rs2   = db.getResult( "SELECT * FROM inventory WHERE product_sku ='" + tmpProduct.getSKU() + "'" );
				if( rs2.next() ){
					tmpProduct.setAmount(rs2.getInt("amount"));
				}
				allProducts.add(tmpProduct);
			}
			rs.close();
		}catch ( Exception e ){
			System.out.println( e.getMessage() );
		}
		return allProducts;
	}
	/**
	 * Print all Products that registered in the DataBase.
	 * @throws SQLException
	 */
	public void printAllProducts()throws SQLException {
		ArrayList<Product> allProducts = new ArrayList<Product>();
		allProducts = this.getAllProducts();
		for( int i=0;i<allProducts.size(); i++){
			Product tmpProduct = allProducts.get(i);
			System.out.println( "Name: " + tmpProduct.getName() + " (" + tmpProduct.getId() + ")");
			System.out.println( "SKU: " + tmpProduct.getSKU());
			System.out.println( "Type: " + tmpProduct.getType() );
			System.out.println( "Size: " + tmpProduct.getSize() );
			System.out.println( "Price: " + tmpProduct.getPrice() );
			System.out.println( "Amount: " + tmpProduct.getAmount() );
			System.out.println( "-------");
		}
	}
	/**
	 * Print all Products that registered in the DataBase with the provided type
	 * @param type [String]
	 * @throws SQLException
	 */
	public void printAllProductsByType( String type )throws SQLException {
		ArrayList<Product> allProducts = new ArrayList<Product>();
		allProducts = this.getAllByType( type );
		for( int i=0;i<allProducts.size(); i++){
			Product tmpProduct = allProducts.get(i);
			System.out.println( "Name: " + tmpProduct.getName() + " (" + tmpProduct.getId() + ")");
			System.out.println( "SKU: " + tmpProduct.getSKU());
			System.out.println( "Type: " + tmpProduct.getType() );
			System.out.println( "Size: " + tmpProduct.getSize() );
			System.out.println( "Price: " + tmpProduct.getPrice() );
			System.out.println( "Amount: " + tmpProduct.getAmount() );
			System.out.println( "-------");
		}
	}
	public void insertDemoProductsToDataBase(){
		ArrayList<Product> products  = new ArrayList<Product>();

		products.add( new Product( "100" , "Jeans unisex" , "trousers" , "M" , 100, 20 ) );
		products.add( new Product( "101" , "Jeans male" , "trousers" , "L" , 50, 20 ) );
		products.add( new Product( "102" , "Jeans female" , "trousers" , "S" , 40, 20 ) );
		products.add( new Product( "200" , "Pants unisex" , "trousers" , "M" , 100, 20 ) );
		products.add( new Product( "201" , "Pants male" , "trousers" , "L" , 50, 20 ) );
		products.add( new Product( "202" , "Pants female" , "trousers" , "S" , 40, 20 ) );
		products.add( new Product( "300" , "Bermuda unisex" , "trousers" , "M" , 100, 20 ) );
		products.add( new Product( "301" , "Bermuda male" , "trousers" , "L" , 50, 20 ) );
		products.add( new Product( "302" , "Bermuda female" , "trousers" , "S" , 40, 20 ) );
		products.add( new Product( "400" , "Undershirt unisex" , "shirts" , "M" , 500, 20 ) );
		products.add( new Product( "401" , "Undershirt male" , "shirts" , "L" , 500, 20 ) );
		products.add( new Product( "402" , "Undershirt female" , "shirts" , "S" , 500, 20 ) );
		products.add( new Product( "500" , "T-shirt unisex" , "shirts" , "M" , 500, 20 ) );
		products.add( new Product( "501" , "T-shirt male" , "shirts" , "L" , 500, 20 ) );
		products.add( new Product( "502" , "T-shirt female" , "shirts" , "S" , 500, 20 ) );
		products.add( new Product( "600" , "Shoe unisex" , "footwear" , "M" , 500, 20 ) );
		products.add( new Product( "601" , "Shoe male" , "footwear" , "L" , 500, 20 ) );
		products.add( new Product( "602" , "Shoe female" , "footwear" , "S" , 500, 20 ) );
		products.add( new Product( "700" , "Flip flops unisex" , "footwear" , "M" , 500, 20 ) );
		products.add( new Product( "701" , "Flip flops male" , "footwear" , "L" , 500, 20 ) );
		products.add( new Product( "702" , "Flip flops female" , "footwear" , "S" , 500, 20 ) );
		products.add( new Product( "800" , "Underpants unisex" , "lingerie" , "M" , 500, 20 ) );
		products.add( new Product( "801" , "Underpants male" , "lingerie" , "L" , 500, 20 ) );
		products.add( new Product( "802" , "Underpants female" , "lingerie" , "S" , 500, 20 ) );
		products.add( new Product( "900" , "Socks unisex" , "lingerie" , "M" , 500, 20 ) );
		products.add( new Product( "901" , "Socks male" , "lingerie" , "L" , 500, 20 ) );
		products.add( new Product( "902" , "Socks female" , "lingerie" , "S" , 500, 20 ) );


		for( int i=0;i<products.size(); i++){
			Product tmpProduct = products.get(i);
			Product newProduct = insert( tmpProduct );
		}
	}
}
