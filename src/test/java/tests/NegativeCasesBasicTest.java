package tests;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.net.URL;

public class NegativeCasesBasicTest {
    static AndroidDriver driver;

    public static void main(String[] args) {

        try {
            File appDir = new File("C:\\Users\\hdilu\\MobileAutomation\\mobile-test\\apps");
            File app = new File(appDir, "Android.SauceLabs.Mobile.Sample.app.2.7.1.apk");

            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setCapability("platformName", "Android");
            capabilities.setCapability("platformVersion", "8.0");
            capabilities.setCapability("deviceName", "emulator-5554");
            capabilities.setCapability("appPackage", "com.swaglabsmobileapp");
            capabilities.setCapability("appActivity", "com.swaglabsmobileapp.MainActivity");
            capabilities.setCapability("appium:automationName", "UiAutomator2");
            capabilities.setCapability("app", app.getAbsolutePath());

            driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
            Thread.sleep(3000);

            By usernameField = By.xpath("//android.widget.EditText[@content-desc=\"test-Username\"]");
            By passwordField = By.xpath("//android.widget.EditText[@content-desc=\"test-Password\"]");
            By loginButton = By.xpath("//android.widget.TextView[@text=\"LOGIN\"]");
            By invalidLoginMessage = By.xpath("//android.widget.TextView[@text=\"Username and password do not match any user in this service.\"]");
            By emptyUsernameMessage = By.xpath("//android.widget.TextView[@text=\"Username is required\"]");
            By cartIcon = By.xpath("//android.view.ViewGroup[@content-desc=\"test-Cart\"]/android.view.ViewGroup/android.widget.ImageView");
            By titleProduct = By.xpath("//android.widget.TextView[@text=\"PRODUCTS\"]");
            By addToCartButtonFirstItem = By.xpath("(//android.widget.TextView[@text=\"ADD TO CART\"])[1]");
            By cartItemsNumber = By.xpath("(//android.widget.TextView[@text=\"1\"])[1]");
            By checkOutButton = By.xpath("//android.widget.TextView[@text=\"CHECKOUT\"]");
            By firstNameField = By.xpath("//android.widget.EditText[@content-desc=\"test-First Name\"]");
            By lastNameField = By.xpath("//android.widget.EditText[@content-desc=\"test-Last Name\"]");
            By postalCodeField = By.xpath("//android.widget.EditText[@content-desc=\"test-Zip/Postal Code\"]");
            By continueOrderButton = By.xpath("//android.widget.TextView[@text=\"CONTINUE\"]");
            By emptyFirstNameMessage = By.xpath("//android.widget.TextView[@text=\"First Name is required\"]");
            By emptyLastNameMessage = By.xpath("//android.widget.TextView[@text=\"Last Name is required\"]");


            // testcase 1: verify login with invalid credentials
            driver.findElement(usernameField).sendKeys("standardd_user");
            driver.findElement(passwordField).sendKeys("secrett_sauce");
            driver.findElement(loginButton).click();
            Thread.sleep(3000);

            // verify login is failed
            String actualInvalidLoginFailedMessage = driver.findElement(invalidLoginMessage).getText();
            String expectedInvalidLoginFailedMessage = "Username and password do not match any user in this service.";
            if (actualInvalidLoginFailedMessage.equals(expectedInvalidLoginFailedMessage)) {
                System.out.println("Login is failed");
                System.out.println("Testcase 1: Login is failed for invalid credentials. Testcase is passed.");
            }
            else
                System.out.println("Testcase 1: Testcase is failed");


            // testcase 2: verify login with empty credentials
            driver.findElement(usernameField).sendKeys("");
            driver.findElement(passwordField).sendKeys("");
            driver.findElement(loginButton).click();
            Thread.sleep(3000);

            // verify login is failed
            String actualEmptyLoginFailedMessage = driver.findElement(emptyUsernameMessage).getText();
            String expectedEmptyLoginFailedMessage = "Username is required";
            if (actualEmptyLoginFailedMessage.equals(expectedEmptyLoginFailedMessage)) {
                System.out.println("Login is failed");
                System.out.println("Testcase 2: Login is failed for empty credentials. Testcase is passed.");
            }
            else
                System.out.println("Testcase 2: Testcase is failed.");


            // login to the app
            driver.findElement(usernameField).sendKeys("standard_user");
            driver.findElement(passwordField).sendKeys("secret_sauce");
            driver.findElement(loginButton).click();
            Thread.sleep(3000);

            // verify login is success
            if(driver.findElement(cartIcon).isDisplayed()){
                System.out.println("Cart icon is visible");
            }
            if (driver.findElement(titleProduct).getText().equals("PRODUCTS")) {
                System.out.println("The title PRODUCT is displayed");
            }
            else
                System.out.println("Login is failed");

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

            // testcase 3: submit customer details without firstname
            driver.findElement(firstNameField).sendKeys("");
            driver.findElement(lastNameField).sendKeys("Xyz");
            driver.findElement(postalCodeField).sendKeys("00000");
            driver.findElement(continueOrderButton).click();
            Thread.sleep(3000);

            // verify firstname is required
            String actualFirstNameRequiredMessage = driver.findElement(emptyFirstNameMessage).getText();
            String expectedFirstNameRequiredMessage = "First Name is required";
            if (actualFirstNameRequiredMessage.equals(expectedFirstNameRequiredMessage)) {
                System.out.println("First Name is required");
                System.out.println("Testcase 3: First Name is required. Testcase is passed.");
            }
            else
                System.out.println("Testcase 3: Testcase is failed.");


            // testcase 4: submit customer details without lastname
            driver.findElement(firstNameField).sendKeys("Abc");
            driver.findElement(lastNameField).sendKeys("");
            driver.findElement(postalCodeField).sendKeys("00000");
            driver.findElement(continueOrderButton).click();
            Thread.sleep(3000);

            // verify lastname is required
            String actualLastNameRequiredMessage = driver.findElement(emptyLastNameMessage).getText();
            String expectedLastNameRequiredMessage = "Last Name is required";
            if (actualLastNameRequiredMessage.equals(expectedLastNameRequiredMessage)) {
                System.out.println("Last Name is required");
                System.out.println("Testcase 4: Last Name is required. Testcase is passed.");
            }
            else
                System.out.println("Testcase 4: Testcase is failed.");


            // close the app
            driver.quit();


        } catch (Exception e) {
            System.out.println("Cause is: " + e.getCause());
            System.out.println("Message is: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
