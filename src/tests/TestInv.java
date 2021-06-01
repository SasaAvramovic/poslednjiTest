package tests;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import objects.Home;
import objects.Inventory;
import resources.Constants;

@SuppressWarnings("unused")
public class TestInv {

	WebDriver driver = null;

	@BeforeClass
	public void createDriver() {
		System.setProperty("webdriver.chrome.driver", "C:\\ChromeDriver\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	@Test
	public void probniTest() {
		driver.navigate().to("https://www.saucedemo.com/");
		Home.inputUsername(driver, "standard_user");
		Home.inputPassword(driver, "secret_sauce");
		Home.clickLogin(driver);

		Inventory.sortLoToHigh(driver);

		List<WebElement> item_list = driver.findElements(Constants.inventory_items);

		SoftAssert sa = new SoftAssert();

		for (int i = 0; i < item_list.size() - 1; i++) {

			// izvlacim tekst iz objekta, skidam $ i menjam ga u double da bih mogao da
			// poredim
			sa.assertTrue(Double.parseDouble(item_list.get(i).getText().substring(1)) <= Double
					.parseDouble(item_list.get(i + 1).getText().substring(1)));
		}

		sa.assertAll();
	}

	@AfterClass
	public void closeAll() {
		driver.quit();
	}

}
