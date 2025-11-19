package AdityaPargunde.AndroidECommerceFramework;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

public class AndroidBaseTest {

    public AndroidDriver driver;
    public AppiumDriverLocalService service;
    public WebDriverWait wait;

    @BeforeClass
    public void appiumConfiguration() throws MalformedURLException {
    	// Start Appium server
        service = new AppiumServiceBuilder()
                .withAppiumJS(new File("/usr/local/lib/node_modules/appium/build/lib/main.js"))
                .withIPAddress("127.0.0.1")
                .usingPort(4723)
                .build();
        service.start();

        // Set device and app path
        UiAutomator2Options options = new UiAutomator2Options();
        options.setDeviceName("Pixel 3a");
        options.setApp("/Users/adityapargunde/eclipse-workspace/AppiumProject/src/test/java/resources/General-Store.apk");
        options.setCapability("chromedriver_autodownload", true);
        options.setCapability("newCommandTimeout", 300);

        // Initialize Android driver
        driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), options);

        // Initialize wait after driver
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    // NOTE: gesture & wait helpers moved into AndroidActions (single source)

    @AfterMethod
	@AfterClass
    public void tearDown() {
        if (driver != null) driver.quit();
        if (service != null) service.stop();
        System.out.println("Driver and Appium service stopped successfully.");
    }

}

 
