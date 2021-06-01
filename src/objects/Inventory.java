package objects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import resources.Constants;

@SuppressWarnings("unused")
public class Inventory {

	public static void sortLoToHigh(WebDriver driver) {
		driver.findElement(Constants.sort_button).click();
		driver.findElement(Constants.lo_to_high).click();
	}

}
