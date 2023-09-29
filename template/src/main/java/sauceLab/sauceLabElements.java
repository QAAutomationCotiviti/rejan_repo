package sauceLab;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class sauceLabElements {
   WebDriver driver;
   private By username = By.id("user-name");
   private By password = By.id("password");
   private By loginBtn  = By.id("login-button");
    private By error = By.xpath("//*[@data-test='error']");


   public sauceLabElements(WebDriver driver) {
       this.driver = driver;
   }

   public void userLogin(String username, String password) {
       driver.findElement(this.username).sendKeys(username);
       driver.findElement(this.password).sendKeys(password);
       driver.findElement(this.loginBtn).click();
   }

   public void clearInput() {
       clearUsername();
       clearPassword();
   }

   public void clearUsername() {
       driver.findElement(this.username).clear();
   }

   public void clearPassword() {
       driver.findElement(this.password).clear();
   }

   public void clearInputs() {
       driver.findElement(this.username).sendKeys(Keys.CONTROL, "a", Keys.DELETE);
       driver.findElement(this.password).sendKeys(Keys.CONTROL,"a", Keys.DELETE);
   }

   public By getErrorElem() {
       return error;
   }
}
