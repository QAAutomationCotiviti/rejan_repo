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
    private By signupBtn = By.xpath("//div[@id='signInModal']//div[@class='modal-content']//button[@class='btn btn-primary']");
    private By cancelBtn = By.xpath("//div[@id='signInModal']//div[@class='modal-content']//button[@class='btn btn-secondary']");


    public Signup(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    public void clickSignup() {
        driver.findElement(signupLink).click();
    }

    public void userSignup(String username, String password) throws Exception {
        JavascriptExecutor exe = (JavascriptExecutor) driver;
        WebElement modal = Utils.visibilityOfElementLocated(wait, signupModal);

        if (modal.isDisplayed()) {

            clearInputs();
            driver.findElement(this.username).sendKeys(username);
            driver.findElement(this.password).sendKeys(password);
            WebElement signupBtn = modal.findElement(this.signupBtn);

            signupBtn.click();
            Alert alert = Utils.getAlert(wait);

            if(FinalVariables.signupSuccess.equals(alert.getText())) {
                System.out.println(alert.getText());
                alert.accept();
            }
            System.out.println(alert.getText());
            alert.accept();
            WebElement cancelBtn = modal.findElement(this.cancelBtn);
            cancelBtn.click();
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
