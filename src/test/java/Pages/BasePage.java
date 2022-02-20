package Pages;

import com.github.javafaker.Faker;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Properties;

public class BasePage {

    protected WebDriver driver;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }


    public static void readTxt(String path) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(path));
            String x = "";
            while ((x = br.readLine()) != null) {
                System.out.println(x);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static String getValue(String key) {

        String path = System.getProperty("user.dir") + File.separator + "config.properties";

        String value = null;
        try {
            Properties prop = new Properties();
            prop.load(new FileInputStream(path));
            value = prop.getProperty(key);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return value;
    }


    public static void setValue(String key, String value, boolean append) {

        String path = System.getProperty("user.dir") + File.separator + "TestData" + File.separator + "config.properties";
        try {
            FileOutputStream fos = new FileOutputStream(new File(path), append);
            Properties prop = new Properties();
            prop.setProperty(key, value);
            prop.store(fos, "");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void waitForElement(long milliseconds) {

        try {
            Thread.sleep(milliseconds);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void mouseHover(WebDriver driver, WebElement element) {
        Actions act = new Actions(driver);
        act.moveToElement(element).build().perform();
    }


    public static void click(WebDriver driver, WebElement element) {
        Actions act = new Actions(driver);
        act.click(element).build().perform();
    }

    public static void rightClick(WebDriver driver, WebElement element) {
        Actions act = new Actions(driver);
        act.contextClick(element).build().perform();
    }

    public static void dragDrop(WebDriver driver, WebElement element1, WebElement element2) {
        Actions act = new Actions(driver);
        act.dragAndDrop(element1, element2).build().perform();
    }

    public static void doubleClickElement(WebDriver driver, WebElement element) {
        Actions act = new Actions(driver);
        act.doubleClick(element).build().perform();
    }

    public static void acceptAlert(WebDriver driver) {
        Alert alert = driver.switchTo().alert();
        System.out.println("Title " + alert.getText());
        alert.accept();

    }


    public static void dismissAlert(WebDriver driver) {
        Alert alert = driver.switchTo().alert();
        System.out.println("Title " + alert.getText());
        alert.dismiss();

    }


    public static void acceptAlert(WebDriver driver, String text) {
        Alert alert = driver.switchTo().alert();
        System.out.println("Title " + alert.getText());
        alert.sendKeys(text);
        alert.accept();

    }

    public static void dismissAlert(WebDriver driver, String text) {
        Alert alert = driver.switchTo().alert();
        System.out.println("Title " + alert.getText());
        alert.sendKeys(text);
        alert.dismiss();

    }


    public static void scroll(WebDriver driver, WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView();", element);
        js.executeScript("arguments[0].setAttribute('style', ' border: 2px solid red;');", element);

    }


    public static String currentDataTime() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter df = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String formateDateTime = now.format(df);
        return formateDateTime.replace("-", "_").replace(":", "_").replace(" ", "_");

    }

    public static void takeScreenshot(WebDriver driver) {
        try {
            TakesScreenshot srcShot = (TakesScreenshot) driver;
            File srcFile = srcShot.getScreenshotAs(OutputType.FILE);
//            Integer ran = (int) (Math.random()*4567);
            File destFile = new File("./screen" + currentDataTime() + ".png");
            FileUtils.copyFile(srcFile, destFile);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    public static void assertTitle(WebElement element, String exceptedText) {
        String actual = element.getText();
        Assert.assertEquals(actual, exceptedText, "Title not match");
    }

    public static void assertIcon(WebElement element) {
        Assert.assertTrue(element.isDisplayed(), "Icon is not displayed");
    }

    public static void assertCheckBoxTrue(WebElement element) {
        Assert.assertTrue(element.isSelected(), "Checkbox is not selected");
    }

    public static void assertCheckBoxFalse(WebElement element) {
        Assert.assertFalse(element.isSelected(), "Checkbox is selected");
    }

    public static void assertTitle(WebDriver driver, String exceptedText) {
        Assert.assertEquals(driver.getTitle(), exceptedText, "User is on wrong page");
    }


    public static String firstName() {

        Faker faker = new Faker(new Locale("en-IND"));
        String fn = faker.name().firstName();
        System.out.println("First Name "+ fn);
        return fn;
    }

    public static String lastName() {

        Faker faker = new Faker(new Locale("en-IND"));
        String ln = faker.name().lastName();
        System.out.println("Last Name "+ ln);
        return ln;
    }

    public static String emailId() {
        Faker faker = new Faker(new Locale("en-IND"));
        String email = faker.name().firstName().replace(".", "").replace(" ", "") + "@test.com";
        System.out.println("Email "+ email);
        return email;
    }

    public static String mobileNumber(){
        Faker faker = new Faker(new Locale("en-IND"));
        String num = faker.number().digits(10);
        System.out.println("Mobile Number "+ num);
        return num;
    }

    public static String password(){
        return "password@123";
    }

}
