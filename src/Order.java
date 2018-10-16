import java.lang.*;
public class Order {
	Integer id;
	String product_sku;
	String date;
	Integer employee_id;
	Integer customer_id;
	Integer amount;

	public Order( String product_sku, String date, Integer employee_id, Integer customer_id, Integer amount){
		this.setProductSKU( product_sku );
		this.setDate( date );
		this.setEmployeeID( employee_id );
		this.setCustomerID( customer_id );
		this.setAmount( amount );
	}
	public Order( Integer id, String product_sku, String date, Integer employee_id, Integer customer_id, Integer amount){
		this.setId( id );
		this.setProductSKU( product_sku );
		this.setDate( date );
		this.setEmployeeID( employee_id );
		this.setCustomerID( customer_id );
		this.setAmount( amount );
	}

	public Integer getId() { return id; }

	public void setId(Integer id) { this.id = id; }

	public String getProductSKU() { return product_sku; }

	public void setProductSKU(String product_sku) { this.product_sku = product_sku; }

	public String getDate() { return date; }

	public void setDate(String date) { this.date = date; }

	public Integer getEmployeeID() { return employee_id; }

	public void setEmployeeID(Integer employee_id) { this.employee_id = employee_id; }

	public Integer getCustomerID() { return customer_id; }

	public void setCustomerID(Integer customer_id) { this.customer_id = customer_id; }

	public Integer getAmount() { return amount; }

	public void setAmount(Integer amount) { this.amount = amount; }
}