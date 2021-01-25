package ui.pages;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import qumu.GetPage;

public class CartPage extends GetPage{

	public CartPage() {
		super("cartpage");
	}

	public void clickCheckoutButton() {
		clickButton(element("btn_checkout"), "Checkout");
	}
	
	public void clickRemoveItem(String itemName) {
		clickButton(element("btn_remove_item", itemName), "Remove");
	}
	
	public void verifyEachItemCount(int count) {
		List<WebElement> elems = elements("items_list");
		for(int i =0; i < elems.size(); i++) {
			Assert.assertEquals(element(elems.get(i),"item_count").getText(), String.valueOf(count));
		}
	}

}
