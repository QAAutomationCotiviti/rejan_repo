package pages;

import constants.variables;
import driver.seleniumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;


public class dashboardPage {
    WebDriver driver;
    private By select = By.className("product_sort_container");

    private By prodContainer = By.className("inventory_item_description");
    private Wait<WebDriver> wait = new WebDriverWait(seleniumDriver.getInstance(), Duration.ofSeconds(2));


    public dashboardPage(WebDriver driver) {
        this.driver = driver;
    }

    public List<WebElement> sortProducts(variables.sortValues sortBy) {
        /*
        WebElement selectElem = wait.until(ExpectedConditions.presenceOfElementLocated(By.className("product_sort_container")));
        selectElem.click();
        WebElement option = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//select[@class='product_sort_container']//option[@value='za']")));
        option.click();
        */
        List<WebElement> prodList = null;
        WebElement selectElement = driver.findElement(select);
        Select select = new Select(selectElement);

        switch (sortBy) {
            case NAME_ASC:
                select.selectByIndex(0);
                prodList = driver.findElements(prodContainer);
                break;

            case NAME_DESC:
                select.selectByIndex(1);
                prodList = driver.findElements(prodContainer);
                break;

            case PRICE_LOW:
                select.selectByIndex(2);
                prodList = driver.findElements(prodContainer);
                break;

            case PRICE_HIGH:
                select.selectByIndex(3);
                prodList = driver.findElements(prodContainer);
                break;
        }

        return prodList;
    }


}
