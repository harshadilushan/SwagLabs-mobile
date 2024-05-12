package pages;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;

public class CheckOutInfoPage {
    AndroidDriver driver;

    By firstNameField = By.xpath("//android.widget.EditText[@content-desc=\"test-First Name\"]");
    By lastNameField = By.xpath("//android.widget.EditText[@content-desc=\"test-Last Name\"]");
    By postalCodeField = By.xpath("//android.widget.EditText[@content-desc=\"test-Zip/Postal Code\"]");
    By continueOrderButton = By.xpath("//android.widget.TextView[@text=\"CONTINUE\"]");
    By emptyFirstNameMessage = By.xpath("//android.widget.TextView[@text=\"First Name is required\"]");
    By emptyLastNameMessage = By.xpath("//android.widget.TextView[@text=\"Last Name is required\"]");

    public CheckOutInfoPage(AndroidDriver driver){
        this.driver=driver;
    }

    public void setFirstName(String firstName){
        driver.findElement(firstNameField).sendKeys(firstName);
    }

    public void setLastName(String lastName){
        driver.findElement(lastNameField).sendKeys(lastName);
    }

    public void setPostalCode(String postalCode){
        driver.findElement(postalCodeField).sendKeys(postalCode);
    }

    public void clickContinue() throws InterruptedException {
        driver.findElement(continueOrderButton).click();
        Thread.sleep(3000);
    }

    public String verifyEmptyFirstNameMessage(){
        return driver.findElement(emptyFirstNameMessage).getText();
    }

    public String verifyEmptyLastNameMessage(){
        return driver.findElement(emptyLastNameMessage).getText();
    }

}
