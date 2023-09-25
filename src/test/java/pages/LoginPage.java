package pages;

import driver.SeleniumDriver;
import dto.LoginDTO;
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

public class LoginPage {
    private WebDriver driver;
    private WebElement email;
    private WebElement password;
    private WebElement loginButton;
    private List<WebElement> errorList;
    private final String brandText = "Swag Labs";
    private Wait<WebDriver> wait = new WebDriverWait(SeleniumDriver.getInstance(), Duration.ofSeconds(2));

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        email = driver.findElement(By.id("user-name"));
        password = driver.findElement(By.id("password"));
        loginButton = driver.findElement(By.id("login-button"));
    }

    public LoginDTO login(String email, String password) throws NoSuchElementException {
        LoginDTO resp = new LoginDTO(); // Initialize the LoginDTO object;
        this.email.clear();
        this.email.sendKeys(email);
        this.password.clear();
        this.password.sendKeys(password);
        loginButton.click();
        try{
            WebElement brand =  wait.until(ExpectedConditions.presenceOfElementLocated(By.className("app_logo")));
            if(brand.getText().equals(brandText)) {
                resp.element = brand;
                resp.status = true;
            }

        }catch(TimeoutException e) {
            WebElement error = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@data-test='error']")));
            resp.element = error;
            resp.status = false;
        }
        finally {
            return resp;
        }
    }


}
