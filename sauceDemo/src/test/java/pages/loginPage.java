package pages;

import constants.variables;
import driver.seleniumDriver;
import dto.loginDTO;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;

public class loginPage {
    private WebDriver driver;
    public static final By email = By.id("user-name");
    public static final By password = By.id("password");
    public static final By loginBtn = By.id("login-button");
    private  final By brand = By.className("app_logo");
    public static final By error = By.xpath("//*[@data-test='error']");

    private List<WebElement> errorList;
    private Wait<WebDriver> wait = new WebDriverWait(seleniumDriver.getInstance(), Duration.ofSeconds(2));

    public loginPage(WebDriver driver) {
        this.driver = driver;
    }

    public loginDTO login(String email, String password) throws NoSuchElementException {
        loginDTO resp = new loginDTO(); // Initialize the LoginDTO object;
        driver.findElement(this.email).clear();
        driver.findElement(this.email).sendKeys(email);
        driver.findElement(this.password).clear();
        driver.findElement(this.password).sendKeys(password);
        driver.findElement(this.loginBtn).click();
        try{
            WebElement brand =  wait.until(ExpectedConditions.presenceOfElementLocated(this.brand));
            if(brand.getText().equals(variables.brandText)) {
                resp.element = brand;
                resp.status = true;
            }
        }catch(TimeoutException e) {
            WebElement error = wait.until(ExpectedConditions.presenceOfElementLocated(this.error));
            resp.element = error;
            resp.status = false;
        }
        finally {
            return resp;
        }
    }


}
