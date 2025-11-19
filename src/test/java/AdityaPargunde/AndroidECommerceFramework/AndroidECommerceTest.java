package AdityaPargunde.AndroidECommerceFramework;

import org.testng.annotations.Test;
import org.testng.annotations.Test;
import static org.testng.Assert.assertEquals;

import java.time.Duration;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import AdityaPargunde.pageObjects.Android.CartPage;
import AdityaPargunde.pageObjects.Android.LoginPage;
import AdityaPargunde.pageObjects.Android.ProductCatalogue;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;

public class AndroidECommerceTest extends AndroidBaseTest {

	@Test
	public void TestCaseFour() throws InterruptedException {
		LoginPage loginPage = new LoginPage(driver);
		loginPage.waitForElementToDisappear();
		loginPage.enterCountry("Argentina");
		loginPage.enterName("Kadambari");
		loginPage.selectGender("female");
		loginPage.clickOnShopButton();
		loginPage.visibilityOfHeaderElements();
		ProductCatalogue productCatalogue = new ProductCatalogue(driver);
		productCatalogue.addItemToCartByIndex("Air Jordan 4 Retro");
		CartPage cartPage = productCatalogue.goToCartPage();
		cartPage.verifyProductsInCart();
		cartPage.verifyTermsAndConditions();
		cartPage.selectCheckbox();
	}
}
