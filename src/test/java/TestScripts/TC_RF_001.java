package TestScripts;


import Pages.BasePage;
import Pages.HomePage;
import Pages.RegisterAccountPage;
import org.testng.annotations.Test;

/**
 * @author  Arvind
 *
 * TC_RF_001: Validate Registering an Account by providing only the Mandatory fields
 */

public class TC_RF_001 extends BaseTest {


    @Test
    public void TC_RF_001(){

   //     String fn = BasePage.firstName(),ln= BasePage.lastName(),emil = BasePage.emailId(),mob=BasePage.mobileNumber(),pw= BasePage.password();

        HomePage hp = new HomePage(driver);
        hp.navigateToRegisterPage();
        RegisterAccountPage rap = new RegisterAccountPage(driver);
        rap.enterMandatoryFields("TC_RF_001");
        rap.validateSucessfulReg();
    }



}
