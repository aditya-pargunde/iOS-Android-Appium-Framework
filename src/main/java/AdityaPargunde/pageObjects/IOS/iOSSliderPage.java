package AdityaPargunde.pageObjects.IOS;

import java.time.Duration;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import AdityaPargunde.utils.IOSActions;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

public class iOSSliderPage extends IOSActions {
     IOSDriver driver;
     WebDriverWait wait;

    public iOSSliderPage(IOSDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver, Duration.ofSeconds(5)), this);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    @iOSXCUITFindBy(accessibility = "Sliders")
    private WebElement sliderButton;

    @iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeCell[1]/**/XCUIElementTypeSlider")
    private WebElement defaultSlider;

    @iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeCell[2]/**/XCUIElementTypeSlider")
    private WebElement tintedSlider;

    @iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeCell[3]/**/XCUIElementTypeSlider")
    private WebElement customSlider;

    @iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeCell[4]/**/XCUIElementTypeSlider")
    private WebElement minMaxSlider;

    @iOSXCUITFindBy(accessibility = "BackButton")
    private WebElement backButton;

    public void iOSSliderValidation() {
        sliderButton.click();
    }

    /**
     * Sliders accept values in range 0.0 - 1.0 (not "0%" or "0.8%").
     * Use clear values without percent signs.
     */
    public void iOSDefaultSliderValidation() {
        defaultSlider.sendKeys("0");
        defaultSlider.sendKeys("0.8");
    }

    public void iOSTintedSliderValidation() {
        tintedSlider.sendKeys("0");
        tintedSlider.sendKeys("0.8");
    }

    public void iOSCustomSliderValidation() {
        customSlider.sendKeys("0");
        customSlider.sendKeys("0.75");
    }

    public void iOSMinMaxSliderValidation() {
        minMaxSlider.sendKeys("0");
        minMaxSlider.sendKeys("0.75");
        backButton.click();
    }

}
