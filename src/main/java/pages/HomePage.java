package pages;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;

public class HomePage {
    AndroidDriver driver;

    By cartIcon = By.xpath("//android.view.ViewGroup[@content-desc=\"test-Cart\"]/android.view.ViewGroup/android.widget.ImageView");
    By titleProduct = By.xpath("//android.widget.TextView[@text=\"PRODUCTS\"]");
    By menuIcon = By.xpath("//android.view.ViewGroup[@content-desc=\"test-Menu\"]/android.view.ViewGroup/android.widget.ImageView");
    By addToCartButtonFirstItem = By.xpath("(//android.widget.TextView[@text=\"ADD TO CART\"])[1]");

    public HomePage(AndroidDriver driver) {
        this.driver = driver;
    }

    public boolean verifyCartIcon(){
        return driver.findElement(cartIcon).isDisplayed();
    }

    public String verifyProductTitle(){
        return driver.findElement(titleProduct).getText();
    }

    public void clickSideMenuIcon() throws InterruptedException {
        driver.findElement(menuIcon).click();
        Thread.sleep(3000);
    }

    public void clickAddToCartFirstItem() throws InterruptedException {
        driver.findElement(addToCartButtonFirstItem).click();
        Thread.sleep(3000);
    }

    public void clickCart() throws InterruptedException {
        driver.findElement(cartIcon).click();
        Thread.sleep(3000);
    }


}
