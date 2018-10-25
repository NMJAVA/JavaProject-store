import java.lang.*;
public class InventoryItem {
	String product_sku;
	Integer amount;
//constructor for inventory
	public InventoryItem( String product_sku , Integer amount){
		setProductSKU(product_sku);
		setAmount(amount);
	}
//getting a product by his SKU
	public String getProductSKU() { return product_sku; }
	public void setProductSKU(String product_sku) { this.product_sku = product_sku; }

//getiing the amount of an item in the inventory
	public Integer getAmount() { return amount; }
	public void setAmount(Integer amount) { this.amount = amount; }
}
