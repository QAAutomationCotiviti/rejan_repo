package utitlties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.List;
import java.util.Locale;

import static java.lang.System.exit;

public class utility {
    public static WebDriver driver;

    public static WebDriver webdriver(String browserName) {
        switch(browserName.toLowerCase()) {
            case "chrome": driver = new ChromeDriver();
            break;

            case "firefox": driver = new FirefoxDriver();
            break;

            case "edge": driver = new EdgeDriver();
            break;

            default:exit(1);
            break;
        }
        return driver;
    }

    public static List<WebElement> getElementCount(WebDriver driver, By element) {
        return driver.findElements(element);
    }
}
