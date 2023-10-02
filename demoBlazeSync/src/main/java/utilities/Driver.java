package utilities;

import constants.FinalEnums;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Driver {
    static WebDriver driver;
    static WebDriverWait wait;

    public static WebDriver getDriver(FinalEnums.driverType type) {
        if (driver == null) {
            switch (type) {
                case CHROME:
                    driver = new ChromeDriver();
                    break;

                case EDGE:
                    driver = new EdgeDriver();
                    break;

                case FIREFOX:
                    driver = new FirefoxDriver();
                    break;
            }
        }
        return driver;
    }


    public static WebDriverWait getWebdriverWait(WebDriver driver, Duration duration) {
        if (wait == null) {
            wait = new WebDriverWait(driver, duration);
        }
        return wait;
    }

}
