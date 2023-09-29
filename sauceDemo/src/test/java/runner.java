import driver.seleniumDriver;
import constants.variables;
import constants.error;
import dto.loginDTO;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.dashboardPage;
import pages.loginPage;
import pages.orderPage;

import java.time.Duration;
import java.util.List;

public class runner {
    private WebDriver driver;
    private pages.loginPage loginPage;
    private pages.orderPage orderPage;
    private pages.dashboardPage dashboardPage;

    private Wait<WebDriver> wait = new WebDriverWait(seleniumDriver.getInstance(), Duration.ofSeconds(2));

    public runner() {
        driver = seleniumDriver.getInstance();
        driver.manage().window().maximize();
        driver.get("https://www.saucedemo.com");
        loginPage = new loginPage(driver);
        dashboardPage = new dashboardPage(driver);
        orderPage = new orderPage(driver);
    }

    public void login(String email, String password, String message) {
        StringBuilder errMessage = new StringBuilder("");
        loginDTO response = loginPage.login(email, password);
        if (!response.status) {
            errMessage.append("**Error " + response.element.getText() + " ");
            errMessage.append(message.compareTo(response.element.getText()) == 0 ? "Pass" : "Failed");
            errMessage.append(" **");
        }
        System.out.println(errMessage);
    }

    public void login(String email, String password) {
        loginDTO response = loginPage.login(email, password);
        if (response.status) {
            System.out.println("Login successful");
        }
    }

    public void sortProducts() {
        List<WebElement> ascList = dashboardPage.sortProducts(variables.sortValues.NAME_ASC);
        List<WebElement> descList = dashboardPage.sortProducts(variables.sortValues.NAME_DESC);
        List<WebElement> ascPriceList = dashboardPage.sortProducts(variables.sortValues.PRICE_LOW);
        List<WebElement> descPriceList = dashboardPage.sortProducts(variables.sortValues.PRICE_HIGH);

//        displayProducts("ASC LIST", ascList);
//        displayProducts("DESC LIST", descList);
//        displayProducts("ASC PRICE LIST", ascPriceList);
//        displayProducts("DESC PRICE LIST", descPriceList);
    }

    public void addToCart() {
        boolean isProdAdded = orderPage.addToCart();
//        boolean isProdRemoved = orderPage.removeProduct();
        if(isProdAdded) {
            System.out.println("Add to cart/remove successful");
        }

        List<WebElement> items = driver.findElements(By.className("cart_item"));
        for(WebElement item : items) {
            String title = item.findElement(By.className("inventory_item_name")).getText();
            String quantity = item.findElement(By.className("cart_quantity")).getText();
            String desc = item.findElement(By.className("inventory_item_desc")).getText();
            String price = item.findElement(By.className("inventory_item_price")).getText();

            System.out.println("Title: " + title);
            System.out.println("Quantity: " + quantity);
            System.out.println("Price: " + price);
            System.out.println("Description: " + desc);
        }
//        if(isProdRemoved) {
//            System.out.println("Product removed");
//        }

        boolean isCheckout = orderPage.checkout();
        if(isCheckout) {
            System.out.println("Checkout successful");
        }
    }

    public void displayProducts(String type, List<WebElement> elements) {
        System.out.println();
        StringBuilder sortType = new StringBuilder("");
        sortType.append("*************** ");
        sortType.append(type);
        sortType.append(" ***************");
        System.out.println(sortType.toString());

        for(WebElement elem : elements) {
            String title = elem.findElement(By.className("inventory_item_name")).getText();
            String desc = elem.findElement(By.className("inventory_item_desc")).getText();
            System.out.println("Title: " + title);
            System.out.println("Description: " + desc);
        }
    }

    public static void main(String[] args) throws Exception {
        runner runner = new runner();
        try {
            runner.login("", "", error.emailEmpty );
            runner.login("", variables.password , error.emailEmpty );
            runner.login(variables.email, variables.invalidPassword, error.invalidCreds);
            runner.login(variables.invalidEmail, variables.password, error.invalidCreds);
            runner.login(variables.invalidEmail, variables.invalidPassword, error.invalidCreds);

            /* LOGIN LOCKED USER*/
            runner.login(variables.lockedUser, variables.password, error.lockedUser);
            runner.login(variables.lockedUser, variables.invalidPassword, error.invalidCreds);

            /* LOGIN & FUNC TESTING*/
            runner.login(variables.email, variables.password);
            runner.sortProducts();
            runner.addToCart();

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Something went wrong!");
        } finally {
//            SeleniumDriver.getInstance().close();
        }
    }
}
