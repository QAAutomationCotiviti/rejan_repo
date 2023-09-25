package driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class SeleniumDriver {
    public static WebDriver chromeDriver;

    public static WebDriver getInstance() {
        if(SeleniumDriver.chromeDriver == null) {
            SeleniumDriver.chromeDriver = new ChromeDriver();
        }
        return  SeleniumDriver.chromeDriver;
    }
}
