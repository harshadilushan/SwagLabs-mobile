package pages;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;

public class CheckOutOverviewPage {
    AndroidDriver driver;

    By finishOrderButton = By.xpath("//android.widget.TextView[@text=\"FINISH\"]");

    public CheckOutOverviewPage(AndroidDriver driver){
        this.driver=driver;
    }

    public void clickFinish() throws InterruptedException {
        driver.findElement(AppiumBy
                .androidUIAutomator("new UiScrollable(new UiSelector().scrollable(true)).scrollIntoView(new UiSelector().text(\"FINISH\"))"));
        driver.findElement(finishOrderButton).click();
        Thread.sleep(3000);
    }
}
