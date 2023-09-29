package demoBlaze.elements;

import libs.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class signup {
    private WebDriver driver;
    final WebDriverWait wait;
    private JavascriptExecutor js;


    private By signup = By.id("signin2");
    private final By username = By.id("sign-username");
    private final By password = By.id("sign-password");
    private final By modalLabel = By.id("signInModalLabel");
    private final By signupBtn = By.xpath("//div[@class='modal-footer']//button[text()='Sign up']");

    public signup(WebDriver driver ) {
        this.driver = driver;
        this.js = (JavascriptExecutor) driver;
        this.wait = Driver.getWaitInstance(driver, Duration.ofSeconds(5));
    }


    public void enterText(By locator, String text) {
        WebElement element = driver.findElement(locator);
        js.executeScript("arguments[0].value='" + text + "';", element);
    }

    public void clearInput(By locator) {
        WebElement element = driver.findElement(locator);
        js.executeScript("arguments[0].value='';", element);
    }

    private void clearInputs() {
        clearInput(username);
        clearInput(password);
    }


}
