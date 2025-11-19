package AdityaPargunde.IOSFramework;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import AdityaPargunde.pageObjects.IOS.iOSAlertViewsPage;
import AdityaPargunde.pageObjects.IOS.iOSSliderPage;
import AdityaPargunde.pageObjects.IOS.iOSStepperPickerViewPage;
import io.appium.java_client.AppiumBy;

public class iOSBasicsTest extends iOSBaseTest {

	@Test(priority = 1)
	public void iOSAlertViewsTest() throws InterruptedException {
		// Alerts views validation
		homePage.selectAlertViews();

		iOSAlertViewsPage alertsPage = new iOSAlertViewsPage(driver);
		alertsPage.selectTextEntry();
		alertsPage.enterTextAndCancel("Kadambari");
		alertsPage.enterTextAndOK("Kadambari");
		alertsPage.verifyToastMessage();
	}

	@Test(priority = 2)
	public void iOSSliderTest() {
		// slider validation
		iOSSliderPage slider = new iOSSliderPage(driver);
		slider.iOSSliderValidation();
		slider.iOSDefaultSliderValidation();
		slider.iOSTintedSliderValidation();
		slider.iOSCustomSliderValidation();
		slider.iOSMinMaxSliderValidation();
	}

	@Test(priority = 3)
	public void iOSDropdownPickerTest() {
		iOSStepperPickerViewPage dropdown = new iOSStepperPickerViewPage(driver);
		dropdown.iOSLongPressValidation();
		//dropdown.iOSScrollActionValidation();
		dropdown.DropdownValidation();
	}

	@Test
	public void IOSSwipeTest() throws InterruptedException {
		Map<String, String> params = new HashMap<String, String>();
		params.put("bundleId", "com.apple.mobileslideshow");
		params.put("direction", "right");
		Thread.sleep(2000);
		driver.executeScript("mobile:swipe", params);
		driver.executeScript("mobile:launchApp", params);
		// driver.findElement(AppiumBy.iOSNsPredicateString("name ==
		// 'LibraryTab'")).click();
		List<WebElement> allPhotos = driver.findElements(AppiumBy.iOSClassChain("**/XCUIElementTypeImage"));
		driver.findElement(By.xpath("//XCUIElementTypeImage[1]")).click();
		for (int i = 0; i < allPhotos.size(); i++) {
			Map<String, Object> params1 = new HashMap<String, Object>();
			params1.put("direction", "left");
			driver.executeScript("mobile:swipe", params1);
		}
	}

}
