package pages;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;

public class LoginPage {
    AndroidDriver driver;

    By usernameField = By.xpath("//android.widget.EditText[@content-desc=\"test-Username\"]");
    By passwordField = By.xpath("//android.widget.EditText[@content-desc=\"test-Password\"]");
    By loginButton = By.xpath("//android.widget.TextView[@text=\"LOGIN\"]");
    By invalidLoginMessage = By.xpath("//android.widget.TextView[@text=\"Username and password do not match any user in this service.\"]");
    By emptyUsernameMessage = By.xpath("//android.widget.TextView[@text=\"Username is required\"]");

    public LoginPage(AndroidDriver driver) {
        this.driver = driver;
    }


    public void login(String username,String password) throws InterruptedException {
        driver.findElement(usernameField).sendKeys(username);
        driver.findElement(passwordField).sendKeys(password);
        driver.findElement(loginButton).click();
        Thread.sleep(3000);
    }

    public String verifyInvalidLoginMessage(){
        return driver.findElement(invalidLoginMessage).getText();
    }

    public String verifyEmptyUsernameMessage(){
        return driver.findElement(emptyUsernameMessage).getText();
    }

}
