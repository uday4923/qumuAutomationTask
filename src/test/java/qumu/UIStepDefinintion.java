package qumu;

import java.util.List;
import java.util.Map;

import com.gargoylesoftware.htmlunit.javascript.host.Element;

import cucumber.api.DataTable;
import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import junit.framework.Assert;
import ui.pages.CartPage;
import ui.pages.CheckoutPage;
import ui.pages.HomePage;
import ui.pages.ProductPage;

public class UIStepDefinintion {
	HomePage homePage;
	ProductPage productPage;
	CartPage cartPage;
	CheckoutPage checkoutPage;
	double amount;
	String tax;
	
	@Given("^I am on the home page$")
	public void iAmOnTheHomePage() {
		homePage = new HomePage();
		homePage.openUrl(LoadProp.getproperty("url"));
	}
	
	@When("^I login in with the following details$")
	public void i_login_in_with_the_following_details(DataTable arg1) throws Throwable {
		List<Map<String, String>> rows = arg1.asMaps(String.class, String.class);
		System.out.println(rows.get(0).get("userName"));
	    homePage.login(rows.get(0).get("userName"), rows.get(0).get("password"));
	}
	
	@Given("^I add the following items to the basket$")
	public void i_add_the_following_items_to_the_basket(DataTable arg1) throws Throwable {
		productPage = new ProductPage();
		List<String> rows = arg1.asList(String.class);
		for(String str: rows) {
			System.out.println("--->"+ str);
			productPage.selectProduct(str);
		}
	}
	
	@Given("^I  should see (\\d+) items added to the shopping cart$")
	public void i_should_see_items_added_to_the_shopping_cart(int arg1) throws Throwable {
	    String count = productPage.getAddedItemCount();
	    Assert.assertEquals(Integer.parseInt(count), arg1);
	}

	@Given("^I click on the shopping cart$")
	public void i_click_on_the_shopping_cart() throws Throwable {
	    productPage.clickCartButton();
	    cartPage = new CartPage();
	}
	
	@Given("^I verify that the QTY count for each item should be (\\d+)$")
	public void i_verify_that_the_QTY_count_for_each_item_should_be(int arg1) throws Throwable {
	    cartPage.verifyEachItemCount(arg1);
	}

	@Given("^I remove the following item:$")
	public void i_remove_the_following_item(DataTable arg1) throws Throwable {
		List<String> rows = arg1.asList(String.class);
	    cartPage.clickRemoveItem(rows.get(0));
	}
	
	@Given("^I click on the CHECKOUT button$")
	public void i_click_on_the_CHECKOUT_button() throws Throwable {
		cartPage.clickCheckoutButton();
		checkoutPage = new CheckoutPage();
	}

	@Given("^I type \"([^\"]*)\" for First Name$")
	public void i_type_for_First_Name(String arg1) throws Throwable {
		checkoutPage.enterFirstName(arg1);
	}

	@Given("^I type \"([^\"]*)\" for Last Name$")
	public void i_type_for_Last_Name(String arg1) throws Throwable {
		checkoutPage.enterLastName(arg1);
	}

	@Given("^I type \"([^\"]*)\" for ZIP/Postal Code$")
	public void i_type_for_ZIP_Postal_Code(String arg1) throws Throwable {
		checkoutPage.enterZipCode(arg1);
	}

	@When("^I click on the CONTINUE button$")
	public void i_click_on_the_CONTINUE_button() throws Throwable {
	   checkoutPage.clickContinueButton();
	}

	@Then("^Item total will be equal to the total of items on the list$")
	public void item_total_will_be_equal_to_the_total_of_items_on_the_list() throws Throwable {
	    amount = checkoutPage.getTotalPriceForAllItem();
	    Assert.assertTrue(checkoutPage.getTotalPayableDisplayedPrice().contains(String.valueOf(amount)));
	}

	@Then("^a Tax rate of (\\d+) % is applied to the total$")
	public void a_Tax_rate_of_is_applied_to_the_total(int arg1) throws Throwable {
		tax = checkoutPage.getTotalTax();
		System.out.println("Displayed Tax: " + tax);
		double calcluatedTax = checkoutPage.getTax(amount, arg1);
		System.out.println("Calculated Tax: " + calcluatedTax);
		Assert.assertTrue(tax.contains(String.valueOf(calcluatedTax)));
	}
}
