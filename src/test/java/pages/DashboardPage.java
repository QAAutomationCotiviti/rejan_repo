package pages;

import constants.SortValues;
import driver.SeleniumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;


public class DashboardPage {
    WebDriver driver;
    private Wait<WebDriver> wait = new WebDriverWait(SeleniumDriver.getInstance(), Duration.ofSeconds(2));


    public DashboardPage(WebDriver driver) {
        this.driver = driver;
    }

    public void sortProducts(SortValues sortBy) {
        /*
        WebElement selectElem = wait.until(ExpectedConditions.presenceOfElementLocated(By.className("product_sort_container")));
        selectElem.click();
        WebElement option = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//select[@class='product_sort_container']//option[@value='za']")));
        option.click();
        */

        WebElement selectElement = driver.findElement(By.className("product_sort_container"));
        Select select = new Select(selectElement);

        switch (sortBy) {
            case NAME_ASC:
                select.selectByIndex(0);
                break;

            case NAME_DESC:
                select.selectByIndex(1);
                break;

            case PRICE_LOW:
                select.selectByIndex(2);
                break;

            case PRICE_HIGH:
                select.selectByIndex(3);
                break;
        }
    }

    public void addToCart() {
        final String labelPath = "//a[@id='item_4_title_link']//div[@class='inventory_item_name']";
        String label = driver.findElement(By.xpath(labelPath)).getText();
        WebElement addToCartBtn = driver.findElement(By.id("add-to-cart-sauce-labs-backpack"));
        WebElement cartBtn = driver.findElement(By.id("shopping_cart_container"));
        addToCartBtn.click();
        cartBtn.click();

        String cartItemLabel = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(labelPath))).getText();
        if(cartItemLabel.compareTo(label) == 0) {
            System.out.println("Cart Item added successfully!");
        }
    }
}
