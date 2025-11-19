package AdityaPargunde.pageObjects.Android;

import java.time.Duration;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import AdityaPargunde.utils.AndroidActions;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class LoginPage extends AndroidActions{

	AndroidDriver driver;
	WebDriverWait wait;

	public LoginPage(AndroidDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
		wait = new WebDriverWait(driver, Duration.ofSeconds(15));
	}

	@AndroidFindBy(id="com.androidsample.generalstore:id/splashscreen")
	private WebElement welcomeScreen;

	@AndroidFindBy(id = "com.androidsample.generalstore:id/spinnerCountry")
	private WebElement country;

	@AndroidFindBy(id = "com.androidsample.generalstore:id/nameField")
	private WebElement nameField;

	@AndroidFindBy(id = "com.androidsample.generalstore:id/radioFemale")
	private WebElement female;

	@AndroidFindBy(id = "com.androidsample.generalstore:id/radioMale")
	private WebElement male;

	@AndroidFindBy(id = "com.androidsample.generalstore:id/btnLetsShop")
	private WebElement shopButton;

	@AndroidFindBy(id = "com.androidsample.generalstore:id/appbar_btn_back")
	private WebElement appBackButton;

	@AndroidFindBy(id = "com.androidsample.generalstore:id/toolbar_title")
	private WebElement toolBarTitle;

	@AndroidFindBy(id = "com.androidsample.generalstore:id/appbar_btn_cart")
	private WebElement headerCartButton;

	public void waitForElementToDisappear() {
		wait.until(ExpectedConditions.invisibilityOf(welcomeScreen));
	}

	public void enterCountry(String countryName) {
		country.click();
	    WebElement countryElement = ScrollToSpecificElement(countryName);
	    countryElement.click();
	}

	public void enterName(String name) {
		nameField.clear();
		nameField.sendKeys(name);
		// avoid brittle assertion here — caller can assert later if needed
	}

	public void selectGender(String gender) {
		if (gender != null && gender.toLowerCase().contains("female"))
			female.click();
		else
			male.click();
	}
	
	public ProductCatalogue clickOnShopButton() {
		shopButton.click();
		return new ProductCatalogue(driver);
	}

	public void visibilityOfHeaderElements() {
		wait.until(ExpectedConditions.visibilityOf(appBackButton));
		Assert.assertTrue(appBackButton.isDisplayed());
		wait.until(ExpectedConditions.visibilityOf(toolBarTitle));
		Assert.assertTrue(toolBarTitle.isDisplayed());
		wait.until(ExpectedConditions.visibilityOf(headerCartButton));
		Assert.assertTrue(headerCartButton.isDisplayed());
		System.out.println("Navigation successful — product page loaded ✅");
	}
}
