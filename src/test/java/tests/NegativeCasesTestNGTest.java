package tests;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.*;

import java.io.File;
import java.net.URL;

public class NegativeCasesTestNGTest {
    static AndroidDriver driver;
    static LoginPage loginPage;
    static HomePage homePage;
    static SideMenuPage sideMenuPage;
    static YourCartPage yourCartPage;
    static CheckOutInfoPage checkOutInfoPage;
    static CheckOutOverviewPage checkOutOverviewPage;
    static CheckOutCompletePage checkOutCompletePage;

    @BeforeClass
    public static void setUp() {
        try {
            File appDir = new File("C:\\Users\\hdilu\\MobileAutomation\\mobile-assignment\\apps");
            File app = new File(appDir, "Android.SauceLabs.Mobile.Sample.app.2.7.1.apk");

            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setCapability("platformName", "Android");
            capabilities.setCapability("platformVersion","8.0");
            capabilities.setCapability("deviceName","emulator-5554");
            capabilities.setCapability("appPackage","com.swaglabsmobileapp");
            capabilities.setCapability("appActivity","com.swaglabsmobileapp.MainActivity");
            capabilities.setCapability("appium:automationName","UiAutomator2");
            capabilities.setCapability("app", app.getAbsolutePath());

            driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"),capabilities);
            Thread.sleep(3000);
            //driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

            loginPage = new LoginPage(driver);
            homePage = new HomePage(driver);
            sideMenuPage = new SideMenuPage(driver);
            yourCartPage = new YourCartPage(driver);
            checkOutInfoPage = new CheckOutInfoPage(driver);
            checkOutOverviewPage = new CheckOutOverviewPage(driver);
            checkOutCompletePage = new CheckOutCompletePage(driver);
            ;

        } catch (Exception e) {
            System.out.println("Cause is: "+e.getCause());
            System.out.println("Message is: "+e.getMessage());
            e.printStackTrace();
        }
    }

    @Test(priority = 1)
    public static void testInvalidLogin() throws InterruptedException {
        loginPage.login("standard_user","secrett_sauce");
        Assert.assertEquals(loginPage.verifyInvalidLoginMessage(),"Username and password do not match any user in this service.");
    }

    @Test(priority = 2)
    public static void testEmptyLogin() throws InterruptedException {
        loginPage.login("","");
        Assert.assertEquals(loginPage.verifyEmptyUsernameMessage(),"Username is required");
    }

    @Test(priority = 3)
    public static void testEmptyFirstName() throws InterruptedException {
        loginPage.login("standard_user","secret_sauce");
        Assert.assertTrue(homePage.verifyCartIcon());
        Assert.assertEquals(homePage.verifyProductTitle(),"PRODUCTS");
        homePage.clickAddToCartFirstItem();
        homePage.clickCart();
        yourCartPage.clickCheckOut();
        checkOutInfoPage.setFirstName("");
        checkOutInfoPage.setLastName("Xyz");
        checkOutInfoPage.setPostalCode("10000");
        checkOutInfoPage.clickContinue();
        Assert.assertEquals(checkOutInfoPage.verifyEmptyFirstNameMessage(),"First Name is required");
    }

    @Test(priority = 4)
    public static void testEmptyLastName() throws InterruptedException {
        checkOutInfoPage.setFirstName("Abc");
        checkOutInfoPage.setLastName("");
        checkOutInfoPage.setPostalCode("10000");
        checkOutInfoPage.clickContinue();
        Assert.assertEquals(checkOutInfoPage.verifyEmptyLastNameMessage(),"Last Name is required");
    }

    @AfterClass
    public static void tearDown() {
        driver.quit();
    }
}
