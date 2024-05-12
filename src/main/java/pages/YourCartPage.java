package pages;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;

public class YourCartPage {
    AndroidDriver driver;

    By checkOutButton = By.xpath("//android.widget.TextView[@text=\"CHECKOUT\"]");
    By removeButton = By.xpath("//android.widget.TextView[@text=\"REMOVE\"]");
    By continueShoppingButton = By.xpath("//android.widget.TextView[@text=\"CONTINUE SHOPPING\"]");

    public YourCartPage(AndroidDriver driver){
        this.driver=driver;
    }

    public void clickRemove() throws InterruptedException {
        driver.findElement(removeButton).click();
    }

    public void clickContinueShopping() throws InterruptedException {
        driver.findElement(continueShoppingButton).click();
        Thread.sleep(3000);
    }

    public void clickCheckOut() throws InterruptedException {
        driver.findElement(checkOutButton).click();
        Thread.sleep(3000);
    }


}
