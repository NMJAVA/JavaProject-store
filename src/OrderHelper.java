import java.sql.ResultSet;

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
	 * Return Order object by given order details ( without ID )
	 * @param order
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
}
