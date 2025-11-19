package AdityaPargunde.utils;

import java.time.Duration;
import java.util.Arrays;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Pause;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.google.common.collect.ImmutableMap;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;

public class AndroidActions {

	AndroidDriver driver;
	WebDriverWait wait;

	public AndroidActions(AndroidDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
	}

	public void LongPressAction(WebElement element) {
		((JavascriptExecutor) driver).executeScript("mobile: longClickGesture",
				ImmutableMap.of("elementId", ((RemoteWebElement) element).getId(), "duration", 1500));
	}

	public void SwipeAction(WebElement element, String direction) {
		((JavascriptExecutor) driver).executeScript("mobile: swipeGesture", ImmutableMap.of("elementId",
				((RemoteWebElement) element).getId(), "direction", direction, "percent", 0.3));
	}

	public void ScrollToEndAction() {
		boolean canScrollMore;
		do {
			canScrollMore = (boolean) ((JavascriptExecutor) driver).executeScript("mobile: scrollGesture",
					ImmutableMap.of("left", 100, "top", 100, "height", 200, "width", 200, "direction", "down",
							"percent", 3.0));
		} while (canScrollMore);
	}

	public void TapToCloseToast() {
		try {
			Thread.sleep(1000); // allow toast to appear briefly
		} catch (InterruptedException e) {
			// swallow - just a small sleep for toast timing
		}

		Dimension size = driver.manage().window().getSize();
		int x = size.width / 2;
		int y = (int) (size.height * 0.9);

		PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
		Sequence tap = new Sequence(finger, 1);
		tap.addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), x, y));
		tap.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
		tap.addAction(new Pause(finger, Duration.ofMillis(200)));
		tap.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

		driver.perform(Arrays.asList(tap));
	}

	public void WaitForElementToAppear(WebElement element) {
		wait.until(ExpectedConditions.visibilityOf(element));
	}

	public java.util.List<WebElement> ScrollToSpecificElementAndReturnList(String textToScroll) {
		// Return list to allow callers to inspect; internally we use UiScrollable to bring it into view.
		driver.findElement(AppiumBy.androidUIAutomator(
				"new UiScrollable(new UiSelector().scrollable(true))"
						+ ".scrollIntoView(new UiSelector().text(\"" + textToScroll + "\"));"));
		return driver.findElements(AppiumBy.xpath("//*[contains(@text, '" + textToScroll + "')]"));
	}

	public WebElement ScrollToSpecificElement(String countryName) {
		return driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector().scrollable(true))"
				+ ".scrollIntoView(new UiSelector().text(\"" + countryName + "\"));"));
	}
}
