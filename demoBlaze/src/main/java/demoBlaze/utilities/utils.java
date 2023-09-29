package demoBlaze.utilities;

import demoBlaze.constants.Enums;
import libs.Driver;
import org.openqa.selenium.Alert;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;

public class utils {
    public static String stringBuilderById(String id, String attributeName, String value) {
        return String.format("document.getElementById('%s').setAttribute('%s', '%s')", id, attributeName, value);
    }

    public static Alert getAlert(WebDriver driver) throws NoAlertPresentException {
        try {
            Wait alertWait = Driver.getWaitInstance(Driver.getInstance(Enums.driverType.CHROME));
            alertWait.until(ExpectedConditions.alertIsPresent());
            return driver.switchTo().alert();
        } catch (NoAlertPresentException Ex) {
            throw Ex;
        }
    }
}
