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
}
