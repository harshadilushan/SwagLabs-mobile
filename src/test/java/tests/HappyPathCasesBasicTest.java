package tests;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.net.URL;

public class HappyPathCasesBasicTest {
    static AndroidDriver driver;

    public static void main(String[] args) {

        try {
            File appDir = new File("C:\\Users\\hdilu\\MobileAutomation\\mobile-test\\apps");
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

            By usernameField = By.xpath("//android.widget.EditText[@content-desc=\"test-Username\"]");
            By passwordField = By.xpath("//android.widget.EditText[@content-desc=\"test-Password\"]");
            By loginButton = By.xpath("//android.widget.TextView[@text=\"LOGIN\"]");
            By cartIcon = By.xpath("//android.view.ViewGroup[@content-desc=\"test-Cart\"]/android.view.ViewGroup/android.widget.ImageView");
            By titleProduct = By.xpath("//android.widget.TextView[@text=\"PRODUCTS\"]");
            By addToCartButtonFirstItem = By.xpath("(//android.widget.TextView[@text=\"ADD TO CART\"])[1]");
            By cartItemsNumber = By.xpath("(//android.widget.TextView[@text=\"1\"])[1]");
            By removeButton = By.xpath("//android.widget.TextView[@text=\"REMOVE\"]");
            By continueShoppingButton = By.xpath("//android.widget.TextView[@text=\"CONTINUE SHOPPING\"]");
            By checkOutButton = By.xpath("//android.widget.TextView[@text=\"CHECKOUT\"]");
            By firstNameField = By.xpath("//android.widget.EditText[@content-desc=\"test-First Name\"]");
            By lastNameField = By.xpath("//android.widget.EditText[@content-desc=\"test-Last Name\"]");
            By postalCodeField = By.xpath("//android.widget.EditText[@content-desc=\"test-Zip/Postal Code\"]");
            By continueOrderButton = By.xpath("//android.widget.TextView[@text=\"CONTINUE\"]");
            By finishOrderButton = By.xpath("//android.widget.TextView[@text=\"FINISH\"]");
            By titleOrderComplete = By.xpath("//android.widget.TextView[@text=\"CHECKOUT: COMPLETE!\"]");
            By OrderCompleteMessage = By.xpath("//android.widget.TextView[@text=\"THANK YOU FOR YOU ORDER\"]");
            By menuIcon = By.xpath("//android.view.ViewGroup[@content-desc=\"test-Menu\"]/android.view.ViewGroup/android.widget.ImageView");
            By logout = By.xpath("//android.widget.TextView[@text=\"LOGOUT\"]");


            // testcase 1: verify login with valid credentials
            driver.findElement(usernameField).sendKeys("standard_user");
            driver.findElement(passwordField).sendKeys("secret_sauce");
            driver.findElement(loginButton).click();
            Thread.sleep(3000);

            // verify login is success
            if(driver.findElement(cartIcon).isDisplayed()){
                System.out.println("Cart icon is visible.");
            }
            if (driver.findElement(titleProduct).getText().equals("PRODUCTS")) {
                System.out.println("The title PRODUCT is displayed.");
                System.out.println("Testcase 1: Login is passed.");
            }
            else
                System.out.println("Testcase 1: Login is failed.");


            // testcase2 : verify remove item from the cart
            // add item to cart
            driver.findElement(addToCartButtonFirstItem).click();

            // verify add item to cart is success
            driver.findElement(cartIcon).click();
            String actualCartItems = driver.findElement(cartItemsNumber).getText();
            String expectedCartItems = "1";
            if (actualCartItems.equals(expectedCartItems)) {
                System.out.println("Successfully added 1 item to cart");
            }
            else
                System.out.println("Failed to add 1 item to cart");

            Thread.sleep(3000);

            // remove item from cart
            driver.findElement(removeButton).click();
            Thread.sleep(3000);
            driver.findElement(continueShoppingButton).click();
            Thread.sleep(3000);

            // verify remove item from cart is success
            String actualButtonTextRemove = driver.findElement(addToCartButtonFirstItem).getText();
            String expectedButtonTextRemove = "ADD TO CART";
            if (actualButtonTextRemove.equals(expectedButtonTextRemove)) {
                System.out.println("Successfully removed item from cart");
                System.out.println("Testcase 2: Remove item from cart is passed.");
            }
            else
                System.out.println("Testcase 2: Remove item from cart is failed.");

            Thread.sleep(3000);


            // testcase 3: verify order an item
            // add item to cart
            driver.findElement(addToCartButtonFirstItem).click();

            // verify add item to cart is success
            driver.findElement(cartIcon).click();
            String actualCartItemsAdd = driver.findElement(cartItemsNumber).getText();
            String expectedCartItemsAdd = "1";
            if (actualCartItemsAdd.equals(expectedCartItemsAdd)) {
                System.out.println("Successfully added 1 item to cart");
            }
            else
                System.out.println("Failed to add 1 item to cart");

            Thread.sleep(3000);

            // click on checkout
            driver.findElement(checkOutButton).click();
            Thread.sleep(3000);

            // enter customer details and continue
            driver.findElement(firstNameField).sendKeys("Abc");
            driver.findElement(lastNameField).sendKeys("Xyz");
            driver.findElement(postalCodeField).sendKeys("00000");
            driver.findElement(continueOrderButton).click();
            Thread.sleep(3000);

            // finish the order
            driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector().scrollable(true)).scrollIntoView(new UiSelector().text(\"FINISH\"))"));
            driver.findElement(finishOrderButton).click();
            Thread.sleep(3000);

            // verify order an item
            if(driver.findElement(titleOrderComplete).getText().equals("CHECKOUT: COMPLETE!")){
                System.out.println("Checkout is success");
            }
            if (driver.findElement(OrderCompleteMessage).getText().equals("THANK YOU FOR YOU ORDER")) {
                System.out.println("Testcase 3: Order 1 item is passed.");
            }
            else
                System.out.println("Testcase 3: Order 1 item is failed.");

            Thread.sleep(3000);

            // testcase 4: verify logout
            driver.findElement(menuIcon).click();
            Thread.sleep(3000);
            driver.findElement(logout).click();
            Thread.sleep(3000);

            // verify logout is success
            if(driver.findElement(loginButton).isDisplayed()){
                System.out.println("Login button is visible");
                System.out.println("Testcase 4: Logout is passed.");
            }
            else
                System.out.println("Testcase 4: Logout is failed.");


            // close the app
            driver.quit();


        } catch (Exception e) {
            System.out.println("Cause is: "+e.getCause());
            System.out.println("Message is: "+e.getMessage());
            e.printStackTrace();
        }
    }

}
