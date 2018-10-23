import java.sql.SQLException;

public class VIPCustomer extends Customer {
	public VIPCustomer(Member member) {
		super(member);
	}

	@Override
	public Order buy( String productSKU , Integer employeeID ) throws SQLException {
		double discountRate = 0.9;
		return super.buy( productSKU, employeeID , discountRate );
	}
	@Override
	public Order buy( Product product , Integer employeeID ) throws SQLException {
		double discountRate = 0.9;
		return super.buy( product.getSKU(), employeeID , discountRate );
	}
}
