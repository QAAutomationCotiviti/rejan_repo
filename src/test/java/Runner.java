import driver.SeleniumDriver;
import dto.LoginDTO;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.DashboardPage;
import pages.LoginPage;
import pages.OrderPage;
import utils.Utils;

import java.time.Duration;
import java.util.List;

import static constants.SortValues.*;

public class Runner {
    private WebDriver driver;
    private LoginPage loginPage;
    private OrderPage orderPage;
    private DashboardPage dashboardPage;
    private final String invalidCreds = "Epic sadface: Username and password do not match any user in this service";
    private final String passwordEmpty = "Epic sadface: Password is required";
    private final String emailEmpty = "Epic sadface: Username is required";
    private final String lockedUser = "Epic sadface: Sorry, this user has been locked out.";
    private Wait<WebDriver> wait = new WebDriverWait(SeleniumDriver.getInstance(), Duration.ofSeconds(2));

    public Runner() {
        driver = SeleniumDriver.getInstance();
        driver.manage().window().maximize();
        driver.get("https://www.saucedemo.com");
        loginPage = new LoginPage(driver);
        dashboardPage = new DashboardPage(driver);
        orderPage = new OrderPage(driver);
    }

    public void login(String email, String password, String message) {
        StringBuilder errMessage = new StringBuilder("");
        LoginDTO response = loginPage.login(email, password);
        if (!response.status) {
            errMessage.append("**Error " + response.element.getText() + " ");
            errMessage.append(message.compareTo(response.element.getText()) == 0 ? "Pass" : "Failed");
            errMessage.append(" **");
        }
        System.out.println(errMessage);
    }

    public void login(String email, String password) {
        LoginDTO response = loginPage.login(email, password);
        if (response.status) {
            System.out.println("Login successful");
        }
    }

    public void sortProducts() {
        List<WebElement> ascList = dashboardPage.sortProducts(NAME_ASC);
        List<WebElement> descList = dashboardPage.sortProducts(NAME_DESC);
        List<WebElement> ascPriceList = dashboardPage.sortProducts(PRICE_LOW);
        List<WebElement> descPriceList = dashboardPage.sortProducts(PRICE_HIGH);

        displayProducts("ASC LIST", ascList);
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
        Runner runner = new Runner();
        try {
            runner.login("", "", runner.emailEmpty);
            runner.login("", Utils.getPassword(), runner.emailEmpty);
            runner.login(Utils.getEmail(), Utils.getInvalidPassword(), runner.invalidCreds);
            runner.login(Utils.getInvalidEmail(), Utils.getPassword(), runner.invalidCreds);
            runner.login(Utils.getInvalidEmail(), Utils.getInvalidPassword(), runner.invalidCreds);

            /* LOGIN LOCKED USER*/
            runner.login(Utils.getLockedUser(), Utils.getPassword(), runner.lockedUser);
            runner.login(Utils.getLockedUser(), Utils.getInvalidPassword(), runner.invalidCreds);

            /* LOGIN & FUNC TESTING*/
            runner.login(Utils.getEmail(), Utils.getPassword());
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
