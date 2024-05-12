package pages;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;

public class SideMenuPage {
    AndroidDriver driver;

    By logout = By.xpath("//android.widget.TextView[@text=\"LOGOUT\"]");

    public SideMenuPage(AndroidDriver driver) {
        this.driver = driver;
    }

    public void clickLogout() throws InterruptedException {
        driver.findElement(logout).click();
        Thread.sleep(3000);
    }

}
