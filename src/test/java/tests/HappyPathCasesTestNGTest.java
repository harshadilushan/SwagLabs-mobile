package tests;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.*;

import java.io.File;
import java.net.URL;

public class HappyPathCasesTestNGTest {
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
    public static void testLogin() throws InterruptedException {
        loginPage.login("standard_user","secret_sauce");
        Assert.assertTrue(homePage.verifyCartIcon());
        Assert.assertEquals(homePage.verifyProductTitle(),"PRODUCTS");
    }

    @Test(priority = 2)
    public static void testRemoveItemFromCart() throws InterruptedException {
        homePage.clickAddToCartFirstItem();
        homePage.clickCart();
        yourCartPage.clickRemove();
        yourCartPage.clickContinueShopping();
    }

    @Test(priority = 3)
    public static void testOrderOneItem() throws InterruptedException {
        homePage.clickAddToCartFirstItem();
        homePage.clickCart();
        yourCartPage.clickCheckOut();
        checkOutInfoPage.setFirstName("Abc");
        checkOutInfoPage.setLastName("Xyz");
        checkOutInfoPage.setPostalCode("10000");
        checkOutInfoPage.clickContinue();
        checkOutOverviewPage.clickFinish();
        Assert.assertEquals(checkOutCompletePage.verifyOrderCompleteTitle(),"CHECKOUT: COMPLETE!");
        Assert.assertEquals(checkOutCompletePage.verifyOrderCompleteMessage(),"THANK YOU FOR YOU ORDER");
    }

    @Test(priority = 4)
    public static void testLogout() throws InterruptedException {
        homePage.clickSideMenuIcon();
        sideMenuPage.clickLogout();
    }

    @AfterClass
    public static void tearDown() {
        driver.quit();
    }

}
