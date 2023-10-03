package pages;

import constants.FinalEnums;
import constants.FinalVariables;
import elements.auth.Signup;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import utilities.Driver;

import java.time.Duration;

public class DemoBlaze {
    static WebDriver driver = Driver.getDriver(FinalEnums.driverType.CHROME);
    static WebDriverWait wait = Driver.getWebdriverWait(driver, FinalVariables.defaultDuration);
    static Signup signup = new Signup(driver, wait);

    public static void verifySignup(String username, String password) {
        try {
            signup.clickSignup();
            signup.userSignup(username,password);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        driver.get("https://www.demoblaze.com");
        driver.manage().window().maximize();

        verifySignup("", "");
        verifySignup("supersonic", "");
        verifySignup("", "supersoinic");
        verifySignup("supersonic", "supersonic");
        verifySignup("supersonic", "supersonic");

    }
}
