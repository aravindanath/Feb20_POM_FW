package Pages;

import Utilities.JavaUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.Map;

public class RegisterAccountPage extends BasePage{
    public RegisterAccountPage(WebDriver driver) {
        super(driver);
    }

    // Object Repository

    @FindBy(id = "input-firstname")
    WebElement firstNameTextField;

    @FindBy(id = "input-lastname")
    WebElement lastNameTextField;

    @FindBy(id = "input-email")
    WebElement emailIdTextField;

    @FindBy(id = "input-telephone")
    WebElement telephoneNumberTextField;

    @FindBy(id = "input-password")
    WebElement passwordTextField;

    @FindBy(id = "input-confirm")
    WebElement confirmPasswordTextField;

    @FindBy(xpath ="//input[@name='agree']" )
    WebElement agreeCheckbox;

    @FindBy(xpath ="//input[@value='Continue']" )
    WebElement continueButton;


    @FindBy(xpath ="//h1[text()='Your Account Has Been Created!']" )
    WebElement accountCreatedSucessMsg;

    @FindBy(xpath ="//*[contains(text(),'Warning: E-Mail Address is already registered!')]" )
    WebElement accountCreatedFailMsg;




    // Business Logic

    Map<String,String> reg;

    String sheetName = "Register";

    public void enterMandatoryFields(String uniqueId){

        reg = JavaUtils.readExcelData(sheetName,uniqueId);
        firstNameTextField.sendKeys(reg.get("FIRSTNAME"));
        lastNameTextField.sendKeys(reg.get("LASTNAME"));
        emailIdTextField.sendKeys(reg.get("EMAIL"));
        telephoneNumberTextField.sendKeys(reg.get("MOBILE"));
        passwordTextField.sendKeys(reg.get("PASSWORD"));
        confirmPasswordTextField.sendKeys(reg.get("CONFIRMPASSWORD"));
        agreeCheckbox.click();
        BasePage.takeScreenshot(driver);
        continueButton.click();
    }

    public void validateSucessfulReg(){
        assertTitle(accountCreatedSucessMsg,"Your Account Has Been Created!");
    }

    public void validateFailedReg(){
        BasePage.takeScreenshot(driver);
        assertTitle(accountCreatedFailMsg,"Warning: E-Mail Address is already registered!");

    }

    public void enterFirst(){
        firstNameTextField.sendKeys(BasePage.firstName());
    }

    public void enterLastFirst(){
        firstNameTextField.sendKeys(BasePage.lastName());
    }




}
