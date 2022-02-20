package TestScripts;


import Pages.BasePage;
import Pages.HomePage;
import Pages.RegisterAccountPage;
import org.testng.annotations.Test;

/**
 * @author  Arvind
 *
 * TC_RF_002: Validate Registering faild scenario
 */

public class TC_RF_002 extends BaseTest {


    @Test
    public void TC_RF_002(){


        HomePage hp = new HomePage(driver);
        hp.navigateToRegisterPage();
        RegisterAccountPage rap = new RegisterAccountPage(driver);
        rap.enterMandatoryFields("TC_RF_002");
        rap.validateFailedReg();

    }



}
