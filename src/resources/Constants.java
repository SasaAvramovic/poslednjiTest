package resources;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

@SuppressWarnings("unused")
public class Constants {

	// Ovo sa izvlacenjem By xxx = By.xxx je iz videa koji nam je Jelena poslala, ne
	// znam da li je bolje ovako ili ne

	// Home constants
	public static final String URL = "https://www.saucedemo.com/";
	public static By username_input = By.id("user-name");
	public static By password_input = By.id("password");
	public static By login_button = By.id("login-button");

	// Inventory constants
	public static final String URL_INV = "https://www.saucedemo.com/inventory.html/";
	public static By sort_button = By.xpath("//*[@id=\"header_container\"]/div[2]/div[2]/span/select");
	public static By lo_to_high = By.xpath("//*[@id=\"header_container\"]/div[2]/div[2]/span/select/option[3]");
	public static By inventory_items = By.className("inventory_item_price");

}
