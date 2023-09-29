package demoBlaze.pages;

import demoBlaze.elements.login;
import demoBlaze.elements.signup;
import demoBlaze.utilities.utils;
import libs.Driver;
import demoBlaze.constants.Enums;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

public class demoBlaze {
    private static final WebDriver driver = Driver.getInstance(Enums.driverType.CHROME);


    public static void verifyLogin(String username, String password) throws InterruptedException {
        login demo = new login(driver);

        try {
            demo.login(username, password);
            Alert alert = utils.getAlert(driver);
            System.out.println(alert.getText());
            alert.accept();
            return;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        System.out.println("User login");
    }

    public static void verifySignup(String username, String password) {
        signup demo = new signup(driver);

        try {
            demo.signup(username, password);
            Alert alert = utils.getAlert(driver);
            System.out.println(alert.getText());
            alert.accept();
        } catch (Exception ex) {
            ex.printStackTrace();

        }
    }

    public static void main(String[] args) throws Exception {
        try {
            driver.manage().window().maximize();
            driver.get("https://demoblaze.com");

            /* TEST SCRIPTS */
            verifyLogin("", "");
            verifyLogin("", "test");
            verifyLogin("test", "");
            verifyLogin("test", "supertest");
            verifyLogin("supertest", "test");
            verifyLogin("test", "test");

            verifySignup("", "");
            verifySignup("supertest", "");
            verifySignup("", "supertest");
            verifySignup("supertest", "supertest");
            verifySignup("supertest12", "supertest");
            System.out.println("hello");
        } catch (Exception e) {
            System.out.println("SOMETHING WENT WRONG");
            e.printStackTrace();
        } finally {
            Thread.sleep(10000);
            driver.close();
        }


    }
}
