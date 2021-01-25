package ui.pages;

import org.openqa.selenium.WebElement;

import qumu.GetPage;
import qumu.LoadProp;

public class HomePage extends GetPage {
	
	public HomePage() {
		super("homepage");
	}

    public void homePage() {
        openUrl(LoadProp.getproperty("url"));
    }
    
    public void login(String username, String password) {
    	enterValue(element("username"), "username", username);
    	enterValue(element("password"), "password", password);
    	clickButton(element("btn_login"), "Login");
    }
}
