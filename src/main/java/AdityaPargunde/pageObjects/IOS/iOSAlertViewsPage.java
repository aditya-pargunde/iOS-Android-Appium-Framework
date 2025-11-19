package AdityaPargunde.pageObjects.IOS;

import java.time.Duration;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import AdityaPargunde.utils.IOSActions;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

public class iOSAlertViewsPage extends IOSActions {
     IOSDriver driver;
     WebDriverWait wait;

    public iOSAlertViewsPage(IOSDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver, Duration.ofSeconds(5)), this);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    @iOSXCUITFindBy(iOSNsPredicate = "name == 'Alert Views'")
    private WebElement alertViewsButton;

    // "Text Entry" option in Alerts
    @iOSXCUITFindBy(iOSNsPredicate = "name == 'Text Entry'")
    private WebElement textEntryButton;


    // The alert's text field (text entry alert uses a text field)
    @iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeTextField")
    private WebElement textField;

    @iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeButton[`name == 'Cancel'`]")
    private WebElement alertCancelButton;

    @iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeButton[`name == 'OK'`]")
    private WebElement alertOkButton;
    
    @iOSXCUITFindBy(accessibility = "Confirm / Cancel")
    private WebElement confirmCancelAlertButton;

    // Use label instead of value for alert text
    @iOSXCUITFindBy(iOSNsPredicate = "label BEGINSWITH[c] 'A message'")
    private WebElement confirmAlertMessage;

    @iOSXCUITFindBy(iOSNsPredicate = "name == 'Confirm'")
    private WebElement confirmButton;

    @iOSXCUITFindBy(accessibility = "BackButton")
    private WebElement backButton;

    // Alerts views validation
    public void selectAlertView() {
        alertViewsButton.click();
    }

    public void selectTextEntry() {
        wait.until(ExpectedConditions.elementToBeClickable(textEntryButton)).click();
    }

    public void enterTextAndCancel(String text) {
        wait.until(ExpectedConditions.visibilityOf(textField)).sendKeys(text);
        wait.until(ExpectedConditions.elementToBeClickable(alertCancelButton)).click();
    }

    public void enterTextAndOK(String text) {
    	selectTextEntry();
        wait.until(ExpectedConditions.visibilityOf(textField)).sendKeys(text);
        wait.until(ExpectedConditions.elementToBeClickable(alertOkButton)).click();
    }

    public void verifyToastMessage() {
        // wait then assert
    	confirmCancelAlertButton.click();
        String actual = wait.until(ExpectedConditions.visibilityOf(confirmAlertMessage)).getText();
        Assert.assertEquals(actual, "A message should be a short, complete sentence.");
        confirmButton.click();
        backButton.click();
    }
    
    public void goToHomePage() {
    	backButton.click();
    }
}
