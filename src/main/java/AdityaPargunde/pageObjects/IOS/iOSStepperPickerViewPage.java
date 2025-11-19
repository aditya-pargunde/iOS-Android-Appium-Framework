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

public class iOSStepperPickerViewPage extends IOSActions {

     IOSDriver driver;
     WebDriverWait wait;

    public iOSStepperPickerViewPage(IOSDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver, Duration.ofSeconds(5)), this);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    @iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeStaticText[`name == 'Steppers'`]")
    private WebElement steppersButton;

    @iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeButton[`name == 'Increment'`][3]")
    private WebElement plusButton;

    @iOSXCUITFindBy(accessibility = "BackButton")
    private WebElement backButton;

    @iOSXCUITFindBy(accessibility = "Web View")
    private WebElement webViewButton;

    @iOSXCUITFindBy(accessibility = "Picker View")
    private WebElement pickerViewButton;

    @iOSXCUITFindBy(accessibility = "Red color component value")
    private WebElement firstDatePicker;

    @iOSXCUITFindBy(accessibility = "Green color component value")
    private WebElement secondDatePicker;

    @iOSXCUITFindBy(accessibility = "Blue color component value")
    private WebElement thirdDatePicker;

    // Long Press validation
    public void iOSLongPressValidation() {
//    	while (!driver.getPageSource().contains("Steppers")) {
//    	    try {
//    	        backButton.click();
//    	    } catch (Exception e) {
//    	        break; // If no back button, stop
//    	    }
//    	}
        wait.until(ExpectedConditions.elementToBeClickable(steppersButton)).click();
        IOSLongPressAction(plusButton);
        backButton.click();
    }

    // Scroll action validation (uses central utility)
    public void iOSScrollActionValidation() {
        iOSScrollToEndAction();
        wait.until(ExpectedConditions.elementToBeClickable(webViewButton)).click();
        backButton.click();
    }

    // Picker / dropdown validation
    public void DropdownValidation() {
        wait.until(ExpectedConditions.elementToBeClickable(pickerViewButton)).click();

        wait.until(ExpectedConditions.visibilityOf(firstDatePicker)).sendKeys("80");
        Assert.assertEquals(firstDatePicker.getText(), "80");

        wait.until(ExpectedConditions.visibilityOf(secondDatePicker)).sendKeys("255");
        Assert.assertEquals(secondDatePicker.getText(), "255");

        wait.until(ExpectedConditions.visibilityOf(thirdDatePicker)).sendKeys("180");
        Assert.assertEquals(thirdDatePicker.getText(), "180");

        backButton.click();
    }
}
