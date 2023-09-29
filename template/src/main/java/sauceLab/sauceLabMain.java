package sauceLab;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utitlties.utility;
import utitlties.variables;

import java.util.List;

public class sauceLabMain {
    static final WebDriver driver = utility.webdriver("chrome");
    static sauceLabElements source = new sauceLabElements(driver);

    public static void main(String[] args) {
        driver.manage().window().maximize();
        driver.get("https://www.saucedemo.com");

        verifyLogin("", "");
        verifyLogin("", "test");
        verifyLogin("test", "");
        verifyLogin("test", "test");
        verifyLogin("standard_user", "test");
        verifyLogin("test", "secret_sauce");
        verifyLogin("standard_user", "secret_sauce");
    }

    public static void verifyLogin(String username, String password) {
        source.userLogin(username, password);
        List<WebElement> elementCount = utility.getElementCount(driver, source.getErrorElem());

        if (!elementCount.isEmpty()) {
            String errMessage = elementCount.get(0).getText();
            if(variables.usernameEmpty.equals(errMessage)) {
                System.out.println(variables.usernameEmpty);
                source.clearInputs();
            }else if(variables.passwordEmpty.equals(errMessage)) {
                System.out.println(variables.passwordEmpty);
                source.clearInputs();
            }else if(variables.invalidCreds.equals(variables.invalidCreds)) {
                System.out.println(variables.invalidCreds);
                source.clearInputs();
            }else {
                System.out.println("User login successful");
            }
        }
    }
}
