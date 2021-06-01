package objects;

import org.openqa.selenium.WebDriver;

import resources.Constants;

public class Home {

	public static void inputUsername(WebDriver driver, String username) {
		driver.findElement(Constants.username_input).sendKeys(username);
	}

	public static void inputPassword(WebDriver driver, String password) {
		driver.findElement(Constants.password_input).sendKeys(password);
	}

	public static void clickLogin(WebDriver driver) {
		driver.findElement(Constants.login_button).click();
	}

}
