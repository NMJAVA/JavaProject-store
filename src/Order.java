import java.io.IOException;
import java.lang.*;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

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

	/**
	 * Create Word File from this order.
	 * @param openFile [boolean] - if true open file after create
	 * @throws SQLException
	 */
	public void createWord( boolean openFile ) throws SQLException, IOException {
		GeneralHelper gHelper         = new GeneralHelper();
		ArrayList<String> wordContent = new ArrayList<>();

		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy_MM_dd");
		LocalDate localDate   = LocalDate.now();
		String dateString     = dtf.format(localDate);

		MemberHelper mh   = new MemberHelper();
		Employee employee = new Employee( mh.GetByID( getEmployeeID() ) );
		Customer customer = new Customer( mh.GetByID( getCustomerID() ) );

		wordContent.add("Product Sku: " + getProductSKU() );
		wordContent.add("Sale Date: " + getDate() );
		wordContent.add("Employee: " + employee.getFirstName() );
		wordContent.add("Customer: " + customer.getFirstName() );
		wordContent.add("Amount: " + getAmount() );

		gHelper.createWordFile("Order #" + getId() , wordContent, "order" + dateString + ".docx");

		if (openFile){
			gHelper.openFile("order" + dateString + ".docx");
		}
	}
}