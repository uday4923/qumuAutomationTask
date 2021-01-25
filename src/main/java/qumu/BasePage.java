package qumu;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import utils.SeleniumWait;

public class BasePage {
    public static WebDriver driver;
    public String pageName;
	protected SeleniumWait wait;
    
    public BasePage() {
    }
    
    public BasePage(String pageName) {
    	this.pageName = pageName;
		this.wait = new SeleniumWait(driver, Integer.parseInt("20"));
    }
    
    public void enterValue(WebElement webElement, String fieldName, String value) {
    	System.out.println("Entering " + value + " in " + fieldName + " field");
    	webElement.clear();
    	webElement.sendKeys(value);
    	System.out.println("Entered " + value);
    }
    
    public void clickButton(WebElement element, String buttonName) {
    	System.out.println("Trying to click " + buttonName + " button");
    	wait.waitForElementToBeVisible(element);
    	element.click();
    	System.out.println("clicked button");
    }
    
}
