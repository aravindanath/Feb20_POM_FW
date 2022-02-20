package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage{

    public HomePage(WebDriver driver) {
        super(driver);
    }


    @FindBy(xpath = "//span[text()='My Account']")
    WebElement myAccountMenu;

    @FindBy(xpath = "(//a[text()='Login'])[1]")
    WebElement loginBotton;

    @FindBy(xpath = "(//a[text()='Register'])[1]")
    WebElement registerButton;


    public void navigateToRegisterPage(){
        BasePage.assertTitle(driver,"Your Store");
        myAccountMenu.click();
        registerButton.click();
        BasePage.assertTitle(driver,"Register Account");
    }


    public void navigateToLoginPage(){
        BasePage.assertTitle(driver,"Your Store");
        myAccountMenu.click();
        loginBotton.click();
        BasePage.assertTitle(driver,"Account Login");
    }
}
