package AdityaPargunde.pageObjects.Android;

import java.time.Duration;
import java.util.List;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import AdityaPargunde.utils.AndroidActions;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class ProductCatalogue extends AndroidActions {
	AndroidDriver driver;
	WebDriverWait wait;

	@AndroidFindBy(id = "com.androidsample.generalstore:id/productName")
	private List<WebElement> productCount;

	@AndroidFindBy(id = "com.androidsample.generalstore:id/productAddCart")
	private List<WebElement> addToCart;

	@AndroidFindBy(id = "com.androidsample.generalstore:id/appbar_btn_cart")
	public WebElement cartButton;

	@AndroidFindBy(id = "android:id/alertTitle")
	private WebElement alertTitle;

	public ProductCatalogue(AndroidDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
		wait = new WebDriverWait(driver, Duration.ofSeconds(15));
	}

	public void addItemToCartByIndex(String name) {
		// Use UiScrollable to bring the desired product into view
		driver.findElement(io.appium.java_client.AppiumBy
				.androidUIAutomator("new UiScrollable(new UiSelector().scrollable(true))"
						+ ".scrollIntoView(new UiSelector().text(\"" + name + "\"));"));

		for (int i = 0; i < productCount.size(); i++) {
			String productName = productCount.get(i).getText();
			if (productName.equalsIgnoreCase(name)) {
				addToCart.get(i).click();
				// break after clicking the intended product to avoid multiple clicks
				break;
			}
		}
	}

	public CartPage goToCartPage() throws InterruptedException {
		wait.until(ExpectedConditions.visibilityOf(cartButton));
		cartButton.click();
		// ensure cart page elements are visible instead of Thread.sleep
		Thread.sleep(1000); // very small pause to let transition happen; not the long sleeps used earlier
		return new CartPage(driver);
	}

}
