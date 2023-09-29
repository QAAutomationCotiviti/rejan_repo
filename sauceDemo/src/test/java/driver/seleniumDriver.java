package driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class seleniumDriver {
    public static WebDriver chromeDriver;

    public static WebDriver getInstance() {
        if(seleniumDriver.chromeDriver == null) {
            seleniumDriver.chromeDriver = new ChromeDriver();
        }
        return  seleniumDriver.chromeDriver;
    }
}
