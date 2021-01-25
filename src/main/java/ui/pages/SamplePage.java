package ui.pages;

import org.openqa.selenium.support.PageFactory;

import qumu.BasePage;

public class SamplePage extends BasePage {
    public SamplePage() {
        PageFactory.initElements(driver, this);
    }

/**
 * You can use this class to add page objects and methods.
 */
}
