package AdityaPargunde.pageObjects.IOS;

import java.time.Duration;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import AdityaPargunde.utils.IOSActions;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

public class iOSHomePage extends IOSActions {

     IOSDriver driver;
     WebDriverWait wait;

    public iOSHomePage(IOSDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver, Duration.ofSeconds(5)), this);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    @iOSXCUITFindBy(accessibility = "Alert Views")
    private WebElement alertViewsButton;

    public void selectAlertViews() {
        alertViewsButton.click();
    }

}
