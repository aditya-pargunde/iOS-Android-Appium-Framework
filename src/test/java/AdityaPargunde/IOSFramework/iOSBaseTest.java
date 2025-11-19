package AdityaPargunde.IOSFramework;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;

import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.options.XCUITestOptions;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

import AdityaPargunde.pageObjects.IOS.iOSHomePage;

public class iOSBaseTest {

    public IOSDriver driver;
    public AppiumDriverLocalService service;
    public WebDriverWait wait;
    public iOSHomePage homePage;

    @BeforeClass
    public void appiumConfiguration() throws MalformedURLException {
        // Start Appium server
        service = new AppiumServiceBuilder()
                .withAppiumJS(new File("/usr/local/lib/node_modules/appium/build/lib/main.js"))
                .withIPAddress("127.0.0.1").usingPort(4723).build();
        service.start();

        XCUITestOptions options = new XCUITestOptions();
        options.setDeviceName("iPhone 17");
        options.setApp("/Users/adityapargunde/Downloads/UIKitCatalog.app");
        options.setPlatformVersion("26.1"); // set to your device/simulator version
        options.setAutomationName("XCUITest");
        options.setWdaLaunchTimeout(Duration.ofSeconds(60));

        driver = new IOSDriver(new URL("http://127.0.0.1:4723"), options);

        // Initialize wait and page objects AFTER driver creation
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        homePage = new iOSHomePage(driver);
    }

 
	@AfterClass
    public void tearDown() {
        if (driver != null)
            driver.quit();
        if (service != null)
            service.stop();
        System.out.println("Driver and Appium service stopped successfully.");
    }
}