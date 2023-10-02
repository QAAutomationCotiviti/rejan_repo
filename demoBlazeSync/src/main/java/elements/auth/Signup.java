package elements.auth;

import constants.FinalVariables;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utilities.Utils;

public class Signup {
    private WebDriver driver;
    private WebDriverWait wait;
    private By signupLink = By.id("signin2");
    private By username = By.id("sign-username");
    private By password = By.id("sign-password");

    private By signupModal = By.xpath("//div[@id='signInModal']//div[@class='modal-content']");
    private By signupBtn = By.xpath("//div[@class='modal-footer']//button[@class='btn btn-primary']");
    private By cancelBtn = By.xpath("//div[@class='modal-footer']//button[@class='btn btn-secondary']");


    public Signup(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    public void clickSignup() {
        driver.findElement(signupLink).click();
    }

    public void userSignup(String username, String password) throws Exception {
        WebElement modal = Utils.visibilityOfElementLocated(wait, signupModal);
        JavascriptExecutor exe = (JavascriptExecutor) driver;

        if (modal.isDisplayed()) {
            clearInputs();
            driver.findElement(this.username).sendKeys(username);
            driver.findElement(this.password).sendKeys(password);

            exe.executeScript("arguments[0].click();", driver.findElement(signupBtn));
            Alert alert = Utils.getAlert(wait);

            System.out.println(alert.getText());
            exe.executeScript("arguments[0].click();", driver.findElement(cancelBtn));
            driver.navigate().refresh();
        }
    }

    public void clearInput(By locator) {
        driver.findElement(locator).clear();
    }

    public void clearInputs() {
        clearInput(username);
        clearInput(password);
    }


}
