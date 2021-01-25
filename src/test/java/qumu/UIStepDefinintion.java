package qumu;

import java.util.List;
import java.util.Map;

import cucumber.api.DataTable;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import junit.framework.Assert;

public class UIStepDefinintion {
	HomePage homePage;
	ProductPage productPage;
	
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
	}
	
	@Given("^I verify that the QTY count for each item should be (\\d+)$")
	public void i_verify_that_the_QTY_count_for_each_item_should_be(int arg1) throws Throwable {
	    
	}

	@Given("^I remove the following item:$")
	public void i_remove_the_following_item(DataTable arg1) throws Throwable {
	    
	}
}
