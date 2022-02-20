package TestScripts;

import Pages.BasePage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.jsoup.Connection;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.io.File;
import java.util.Hashtable;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class BaseTest {
    protected WebDriver driver;


    String browser = BasePage.getValue("browser");
    @BeforeClass
    public void setup( ){
        String downloadFilepath = System.getProperty("user.dir")+ File.separator+"Downloads";
        Map<String, Object> preferences = new Hashtable<String, Object>();
        preferences.put("profile.default_content_settings.popups", 0);
        preferences.put("download.prompt_for_download", "false");
        preferences.put("download.default_directory", downloadFilepath);
        preferences.put("plugins.plugins_disabled", new String[]{
                "Adobe Flash Player", "Chrome PDF Viewer"});

        if(browser.equalsIgnoreCase("chrome")) {
            ChromeOptions ops =  new ChromeOptions();
            ops.setExperimentalOption("prefs", preferences);
            ops.addArguments("--disable-notifications");
            ops.addArguments("--incognito");
            ops.setAcceptInsecureCerts(true);
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver(ops);
        }else if(browser.equalsIgnoreCase("firefox")){
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
        }
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.get(BasePage.getValue("url"));
    }

  //  @AfterClass
    public void teardown() throws InterruptedException {
        Thread.sleep(5000);
        driver.quit();
    }


}
