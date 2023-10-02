import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class testDemo {
    WebDriver driver;
    final String pageTitle = "Swag Labs";

    @BeforeClass
    public void initializeDriver() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        driver.get("https://www.saucedemo.com");
    }


    @Test
    public void getPageTitle() {
        assert driver.getTitle().equals(pageTitle);

    }
}
