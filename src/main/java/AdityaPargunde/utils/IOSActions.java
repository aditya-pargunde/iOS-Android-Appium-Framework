package AdityaPargunde.utils;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.collect.ImmutableMap;

import io.appium.java_client.ios.IOSDriver;

public class IOSActions {

     IOSDriver driver;
     WebDriverWait wait;

    public IOSActions(IOSDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    public void iOSSwipeAction(WebElement element, String direction) {
        ((JavascriptExecutor) driver).executeScript("mobile: swipeGesture",
                ImmutableMap.of("elementId", ((RemoteWebElement) element).getId(), "direction", direction, "percent", 0.3));
    }

    public void iOSScrollToEndAction() {
        boolean canScrollMore;
        do {
            canScrollMore = (boolean) ((JavascriptExecutor) driver).executeScript("mobile: scrollGesture",
                    ImmutableMap.of("left", 100, "top", 100, "width", 300, "height", 600,
                            "direction", "down", "percent", 0.8));
        } while (canScrollMore);
    }

    public void IOSLongPressAction(WebElement element) {
        Map<String, Object> params = new HashMap<>();
        params.put("element", ((RemoteWebElement) element).getId());
        // duration in milliseconds for the long press (use reasonable value)
        params.put("duration", 2000);
        driver.executeScript("mobile: touchAndHold", params);
    }

}
