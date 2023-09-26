package pages;

import driver.SeleniumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class OrderPage {
    WebDriver driver;
    private Wait<WebDriver> wait = new WebDriverWait(SeleniumDriver.getInstance(), Duration.ofSeconds(2));

    public OrderPage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean addToCart() {
        final String labelPath = "//a[@id='item_4_title_link']//div[@class='inventory_item_name']";
        String label = driver.findElement(By.xpath(labelPath)).getText();
        WebElement addToCartBtn = driver.findElement(By.id("add-to-cart-sauce-labs-backpack"));
        WebElement cartBtn = driver.findElement(By.id("shopping_cart_container"));
        addToCartBtn.click();
        cartBtn.click();

        String cartItemLabel = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(labelPath))).getText();
        return cartItemLabel.compareTo(label) == 0;
    }

    public boolean removeProduct() {
        WebElement removeBtn = driver.findElement(By.id("remove-sauce-labs-backpack"));
        removeBtn.click();
        List<WebElement> cartList = driver.findElements(By.className("cart-item"));
        if (cartList.size() < 1) {
            return true;
        }
        return false;
    }

    public boolean checkout() {
        String successLabel = "Thank you for your order!";
        WebElement checkoutBtn = driver.findElement(By.id("checkout"));
        checkoutBtn.click();

        WebElement firstName = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("first-name")));
        WebElement lastName = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("last-name")));
        WebElement postalCode = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("postal-code")));
        firstName.sendKeys("super");
        lastName.sendKeys("test");
        postalCode.sendKeys("42600");

        WebElement continueBtn = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("continue")));
        continueBtn.click();

        WebElement finishBtn = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("finish")));
        finishBtn.click();

        WebElement orderSuccess = wait.until(ExpectedConditions.presenceOfElementLocated(By.className("complete-header")));
        return successLabel.compareTo(orderSuccess.getText()) == 0;
    }
}
