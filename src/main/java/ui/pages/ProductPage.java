package ui.pages;

import qumu.GetPage;

public class ProductPage extends GetPage{
	public ProductPage() {
		super("productpage");
	}
	
    public void selectProduct(String productName) {
    	clickButton(element("item", productName), "Item " + productName);
    }
    
    public void clickCartButton() {
    	clickButton(element("cart"), "Cart");
    }
    
    public String getAddedItemCount() {
    	System.out.println(element("span_item_count").getText());
    	return element("span_item_count").getText();
    }
}
