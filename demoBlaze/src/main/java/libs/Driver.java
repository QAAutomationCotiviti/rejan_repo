package libs;

import libs.variables.Commons;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Driver {
    public static WebDriver driver;
    public static WebDriverWait wait;


    public static WebDriverWait getWebdriverWait(WebDriver driver) {
        if (wait == null) {
            wait = new WebDriverWait(driver, Commons.wait);
        }
        return wait;
    }


    public static WebDriverWait getWebdriverWait(WebDriver driver, Duration waitTime) {
        if (wait == null) {
            wait = new WebDriverWait(driver, waitTime);
        }
        return wait;
    }

    public static JavascriptExecutor getJavascriptExecutor(WebDriver driver) {
        return (JavascriptExecutor) driver;
    }

    public static WebDriver getWebdriver(Enums.driverType type) {
        if (driver == null) {
            switch (type) {
                case CHROME:
                    driver = new ChromeDriver();
                    break;

                case EDGE:
                    driver = new EdgeDriver();

                case FIREFOX:
                    driver = new FirefoxDriver();
                    break;
            }
        }
        return driver;
    }
}
