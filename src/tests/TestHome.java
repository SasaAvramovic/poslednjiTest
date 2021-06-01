package tests;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import objects.Home;
import resources.Constants;

@SuppressWarnings("unused")
public class TestHome {

	WebDriver driver = null;

	@BeforeClass
	public void createDriver() {
		System.setProperty("webdriver.chrome.driver", "C:\\ChromeDriver\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	@Test(priority = 1) // provera jednog nasumicnog nevalidnog unosa sa ocekivanim rezultatom
	public void testLoginFail() {
		driver.navigate().to("https://www.saucedemo.com/");
		Home.inputUsername(driver, "neki_user");
		Home.inputPassword(driver, "neka_sifra");
		Home.clickLogin(driver);

		Assert.assertNotEquals(driver.getCurrentUrl(), Constants.URL_INV);
	}

	@Test(priority = 2) // provera nevalidnih i validnih unosa sa ocekivanim rezultatom
	public void testLoginData() {

		File f = new File("data.xlsx");

		try {
			InputStream is = new FileInputStream(f);
			OutputStream os = new FileOutputStream("rezultat.xlsx"); // ovo nije zahtevano al sto da ne...
			XSSFWorkbook wb = new XSSFWorkbook(is);
			Sheet sheet = wb.getSheetAt(0);

			SoftAssert sa = new SoftAssert();

			// za performance glitch user ima malo duzu pauzu kod logina, ali nisam uvodio
			// drugi wait jer radi
			for (int i = 0; i < sheet.getLastRowNum() + 1; i++) {
				Row row = sheet.getRow(i);
				Cell c0 = row.getCell(0);
				Cell c1 = row.getCell(1);
				Cell c2 = row.getCell(2);
				Cell c4 = row.createCell(4);

				driver.navigate().to("https://www.saucedemo.com/"); // nazad na homepage

				Home.inputUsername(driver, c0.toString());
				Home.inputPassword(driver, c1.toString());
				Home.clickLogin(driver);

				sa.assertEquals(driver.getCurrentUrl(), c2.toString());

				// ovo sigurno moze elegantnije sa try/catch al nisam ga naterao da radi kad sam
				// vezbao
				if (driver.getCurrentUrl().equals(c2.toString())) {
					c4.setCellValue("OK");
				} else
					c4.setCellValue("KO");
			}
			wb.write(os);
			wb.close();
			sa.assertAll();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@AfterClass
	public void closeAll() {
		driver.quit();
	}

}
