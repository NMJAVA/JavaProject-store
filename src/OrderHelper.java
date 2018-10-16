import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class OrderHelper {
	/**
	 * Create New Order to our database
	 * @param order [Order]
	 * @return Product Object On Success | null to failed
	 */
	public Order insert( Order order ) {
		try{
			DataBaseHelper db  = new DataBaseHelper();
			String[] keys   = { "product_sku" , "date" , "employee_id" , "customer_id", "amount" };
			String[] values = {
					order.getProductSKU(),
					order.getDate(),
					order.getEmployeeID().toString(),
					order.getCustomerID().toString(),
					order.getAmount().toString()
			};
			if( db.insert( "orders" ,keys , values ) ){
				Integer orderID = this.GetIdByOrder( order );
				// Decrease product amount in the Inventory
				this.decrease( order.getProductSKU(), order.getAmount() );
				order.setId( orderID );
				return order;
			}
		} catch ( Exception e ){
			System.out.println( e.getMessage() );
		}
		return null;
	};

	public boolean decrease(String productSKU , Integer amount ) {
		try {
			DataBaseHelper db = new DataBaseHelper();
			boolean returnInt = true;
			Integer oldAmount = db.get( "inventory" , "amount" , "product_sku = " + productSKU , returnInt );
			Integer newAmount = amount - oldAmount;
			if( newAmount < 0 ){
				newAmount = 0;
			}
			return db.update( "UPDATE inventory SET amount = " + newAmount + " WHERE product_sku = " + productSKU  );
		}catch (Exception e){
			System.out.println( e.getMessage() );
		}
		return false;
	}

	/**
	 * Return Order ID by given order details ( without ID )
	 * @param order [Order]
	 * @return Order ID
	 */
	public Integer GetIdByOrder( Order order ){
		DataBaseHelper db  = new DataBaseHelper();
		try{
			String query = "SELECT * FROM orders WHERE product_sku ='" + order.getProductSKU() + "' AND date ='" + order.getDate() + "' AND employee_id ='" + order.getEmployeeID() + "' AND customer_id ='" + order.getCustomerID() + "' AND amount ='" + order.getAmount() + "'";
			ResultSet rs   = db.getResult( query );
			if( rs.next() ){
				return rs.getInt("ID");
			}
		}catch ( Exception e ){
			System.out.println( e.getMessage());
		}
		return null;
	};

	/**
	 * Get ArrayList of all the Orders in the database
	 * @return ArrayList
	 * @throws SQLException
	 */
	public ArrayList<Order> getAllOrders() throws SQLException {
		ArrayList<Order> allOrders= new ArrayList<Order>();
		try{
			DataBaseHelper db  = new DataBaseHelper();
			ResultSet rs       = db.getTableResultSet( "orders" );
			while( rs.next() ) {
				Order tmpOrder = new Order(
						rs.getInt("ID"),
						rs.getString("product_sku"),
						rs.getString("date"),
						rs.getInt("employee_id"),
						rs.getInt("customer_id"),
						rs.getInt("amount")
				);
				allOrders.add(tmpOrder);
			}
			rs.close();
		}catch ( Exception e ){
			System.out.println( e.getMessage() );
		}
		return allOrders;
	}
	/**
	 * Get ArrayList of all the Orders By provided Employee Email from our database
	 * @param email [String]
	 * @return ArrayList
	 * @throws SQLException
	 */
	public ArrayList<Order> getAllByEmployeeEmail( String email ) throws SQLException {
		ArrayList<Order> allOrders= new ArrayList<Order>();
		try{
			DataBaseHelper db         = new DataBaseHelper();
			MemberHelper MemberHelper = new MemberHelper();
			Integer memberID          = MemberHelper.GetIDByEmail( email );
			ResultSet rs       = db.getResult( "SELECT * FROM orders WHERE employee_id ='" + memberID + "'" );
			while( rs.next() ) {
				Order tmpOrder = new Order(
						rs.getInt("ID"),
						rs.getString("product_sku"),
						rs.getString("date"),
						rs.getInt("employee_id"),
						rs.getInt("customer_id"),
						rs.getInt("amount")
				);
				allOrders.add(tmpOrder);
			}
			rs.close();
		}catch ( Exception e ){
			System.out.println( e.getMessage() );
		}
		return allOrders;
	}
	/**
	 * Get ArrayList of all the Orders By provided Customer Email from our database
	 * @param email [String]
	 * @return ArrayList
	 * @throws SQLException
	 */
	public ArrayList<Order> getAllByCustomerEmail( String email ) throws SQLException {
		ArrayList<Order> allOrders= new ArrayList<Order>();
		try{
			DataBaseHelper db         = new DataBaseHelper();
			MemberHelper MemberHelper = new MemberHelper();
			Integer memberID          = MemberHelper.GetIDByEmail( email );
			ResultSet rs       = db.getResult( "SELECT * FROM orders WHERE customer_id ='" + memberID + "'" );
			while( rs.next() ) {
				Order tmpOrder = new Order(
						rs.getInt("ID"),
						rs.getString("product_sku"),
						rs.getString("date"),
						rs.getInt("employee_id"),
						rs.getInt("customer_id"),
						rs.getInt("amount")
				);
				allOrders.add(tmpOrder);
			}
			rs.close();
		}catch ( Exception e ){
			System.out.println( e.getMessage() );
		}
		return allOrders;
	}
	/**
	 * Get ArrayList of our Inventory from the database
	 * @return ArrayList
	 * @throws SQLException
	 */
	public ArrayList<InventoryItem> getInventory() throws SQLException {
		ArrayList<InventoryItem> Inventory= new ArrayList<InventoryItem>();
		try{
			DataBaseHelper db  = new DataBaseHelper();
			ResultSet rs       = db.getTableResultSet( "inventory" );
			while( rs.next() ) {
				InventoryItem tmpItem = new InventoryItem(
						rs.getString("product_sku"),
						rs.getInt("amount")
				);
				Inventory.add(tmpItem);
			}
			rs.close();
		}catch ( Exception e ){
			System.out.println( e.getMessage() );
		}
		return Inventory;
	}
	/**
	 * Print Inventory From the DataBase.
	 * @throws SQLException
	 */
	public void printInventory()throws SQLException {
		ArrayList<InventoryItem> Inventory = new ArrayList<InventoryItem>();
		Inventory = this.getInventory();
		for( int i=0;i<Inventory.size(); i++){
			InventoryItem tmpOrder = Inventory.get(i);
			System.out.println( "Product SKU: " + tmpOrder.getProductSKU());
			System.out.println( "Amount: " + tmpOrder.getAmount() );
			System.out.println( "-------");
		}
	}
	/**
	 * Print all Orders that registered in the DataBase.
	 * @throws SQLException
	 */
	public void printAllOrders()throws SQLException {
		ArrayList<Order> allOrders = new ArrayList<Order>();
		allOrders = this.getAllOrders();
		for( int i=0;i<allOrders.size(); i++){
			Order tmpOrder = allOrders.get(i);
			System.out.println( "ID: " + tmpOrder.getId() + "");
			System.out.println( "Product SKU: " + tmpOrder.getProductSKU());
			System.out.println( "Date: " + tmpOrder.getDate() );
			System.out.println( "Employee ID: " + tmpOrder.getEmployeeID() );
			System.out.println( "Customer ID: " + tmpOrder.getCustomerID() );
			System.out.println( "Amount: " + tmpOrder.getAmount() );
			System.out.println( "-------");
		}
	}
}
