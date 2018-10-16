import java.lang.*;
public class InventoryItem {
	String product_sku;
	Integer amount;

	public InventoryItem( String product_sku , Integer amount){
		setProductSKU(product_sku);
		setAmount(amount);
	}

	public String getProductSKU() { return product_sku; }
	public void setProductSKU(String product_sku) { this.product_sku = product_sku; }

	public Integer getAmount() { return amount; }
	public void setAmount(Integer amount) { this.amount = amount; }
}
