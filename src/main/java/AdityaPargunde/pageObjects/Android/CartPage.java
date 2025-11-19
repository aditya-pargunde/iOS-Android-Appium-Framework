package AdityaPargunde.pageObjects.Android;

import java.time.Duration;
import java.util.Set;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import AdityaPargunde.utils.AndroidActions;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class CartPage extends AndroidActions {
	AndroidDriver driver;
	WebDriverWait wait;

	@AndroidFindBy(id = "com.androidsample.generalstore:id/toolbar_title")
	private WebElement cartTitle;

	@AndroidFindBy(id = "com.androidsample.generalstore:id/productName")
	public WebElement productInCart;

	@AndroidFindBy(className = "android.widget.CheckBox")
	public WebElement checkbox;

	@AndroidFindBy(id = "com.androidsample.generalstore:id/termsButton")
	public WebElement termsButton;

	@AndroidFindBy(id = "com.androidsample.generalstore:id/alertTitle")
	public WebElement termsTitle;

	@AndroidFindBy(id = "android:id/message")
	public WebElement conditions;
	
	@AndroidFindBy(id="android:id/button1")
	private WebElement closeTermsButton;
	
	@AndroidFindBy(id="com.androidsample.generalstore:id/btnProceed")
	private WebElement goToWebsiteButton;

	public CartPage(AndroidDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}

	public void verifyProductsInCart() {
		WaitForElementToAppear(cartTitle);
		Assert.assertTrue(cartTitle.isDisplayed());
		String checkOutProductName = productInCart.getText();
		Assert.assertEquals(checkOutProductName, "Air Jordan 4 Retro");
	}

	public void selectCheckbox() {
		checkbox.click();
		Assert.assertEquals(checkbox.getAttribute("checked"), "true");
	}

	public void verifyTermsAndConditions() {
		LongPressAction(termsButton);
		Assert.assertEquals(termsTitle.getText(), "Terms Of Conditions");
		Assert.assertTrue(conditions.getText().contains("Lorem Ipsum is simply dummy text"), "Text not found!");
		closeTermsButton.click();
	}
	
	public void goToWebsite() throws InterruptedException {
		goToWebsiteButton.click();
		Thread.sleep(10000);
		
		Set<String> contexts = driver.getContextHandles();
		for(String contextName : contexts)
		{
			System.out.println(contextName);
		}
		driver.context("WEBVIEW_com.androidsample.generalstore");
		driver.findElement(By.id("APjFqb")).sendKeys("Kadambari");
		driver.findElement(By.id("APjFqb")).sendKeys(Keys.ENTER);
		driver.pressKey(new KeyEvent(AndroidKey.BACK));
		driver.context("NATIVE_APP");
	}
}
