package pages;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;

public class CheckOutCompletePage {
    AndroidDriver driver;

    By titleOrderComplete = By.xpath("//android.widget.TextView[@text=\"CHECKOUT: COMPLETE!\"]");
    By OrderCompleteMessage = By.xpath("//android.widget.TextView[@text=\"THANK YOU FOR YOU ORDER\"]");

    public CheckOutCompletePage(AndroidDriver driver){
        this.driver=driver;
    }

    public String verifyOrderCompleteTitle(){
        return driver.findElement(titleOrderComplete).getText();
    }

    public String verifyOrderCompleteMessage(){
        return driver.findElement(OrderCompleteMessage).getText();
    }

}
