package demoBlaze.elements;

import demoBlaze.utilities.utils;
import libs.Driver;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class login {
    private WebDriver driver;
    private WebDriverWait wait;
    private JavascriptExecutor js;

    private final By login = By.id("login2");
    private final By username = By.id("loginusername");
    private final By password = By.id("loginpassword");
    private final By loginBtn = By.xpath("//div[@class='modal-footer']//button[@class='btn btn-primary']");
    private final By closeBtn = By.xpath("//div[@class='modal-footer']//button[@class='btn btn-secondary']");

    public login(WebDriver driver) {
        this.driver = driver;
        this.js = (JavascriptExecutor) driver;
        this.wait = Driver.getWaitInstance(driver);
    }

    public void closeBtn() {
        js.executeScript("arguments[0].click();", driver.findElement(closeBtn));

    }

    public void login(String username, String password) {
        driver.findElement(login).click();
        clearInputs();
        enterText(this.username, username);
        enterText(this.password, password );
        js.executeScript("arguments[0].click();", driver.findElement(loginBtn));
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
