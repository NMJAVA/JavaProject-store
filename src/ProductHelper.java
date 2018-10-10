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
		} catch ( Exception e ){}
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
	 * Print cards of all Products that registered in the DataBase.
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
}
