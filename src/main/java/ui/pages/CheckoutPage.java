package ui.pages;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import qumu.GetPage;

public class CheckoutPage extends GetPage {
	public CheckoutPage() {
		super("checkoutpage");
	}
	
	public void enterFirstName(String firstName) {
		enterValue(element("txt_firstName"), "First Name", firstName);
	}
	
	public void enterLastName(String lastName) {
		enterValue(element("txt_lastName"), "Last Name", lastName);
	}
	
	public void enterZipCode(String zipCode) {
		enterValue(element("txt_zip"), "Zip Code", zipCode);
	}
	
	public void clickContinueButton() {
		clickButton(element("btn_continue"), "Continue");
	}
	
	public double getTotalPriceForAllItem() {
		List<WebElement> elems = elements("items_list");
		double amount = 0.0;
		for(int i =0; i < elems.size(); i++) {
			amount = amount + Double.parseDouble(element(elems.get(i),"item_price").getText().replace("$", ""));
		}
		return amount;
	}
	
	public String getTotalDisplayedPrice() {
		return element("item_total_price").getText();
	}
	
	public String getTotalPayableDisplayedPrice() {
		return element("item_total_payable").getText();
	}
	
	public String getTotalTax() {
		return element("item_total_tax").getText();
	}
	 
	public double getTax(Double amount, int arg1) {
		Double amt = amount * arg1 /100;
		return Math.round(amt * 100.0) / 100.0;
	}
}
