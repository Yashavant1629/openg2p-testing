
package testcase;

import base.DriverCreator;
import listener.TestListeners;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import utilities.ReadXLSData;

import java.util.concurrent.TimeUnit;

@Listeners(TestListeners.class)

public class LoginPageTest extends DriverCreator {

    @Test(priority = 1,dataProviderClass = ReadXLSData.class,dataProvider = "openg2pdata" )
    public static void resetPassword(String email, String scenario) {
        driver.findElement(By.linkText(locators.getProperty("reset_link"))).click();
        driver.findElement(By.xpath(locators.getProperty("reset_email_field"))).sendKeys(email);
        driver.findElement(By.xpath(locators.getProperty("confirm_email_button"))).click();
        if (scenario.equals("TRUE")) {
//            driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
            String successMessage = driver.findElement(By.cssSelector(locators.getProperty("confirmation_message"))).getText();
            Assert.assertEquals(successMessage,"An email has been sent with credentials to reset your password");
        }
        else {
            String errorMessage = driver.findElement(By.cssSelector(locators.getProperty("reset_password_error_message"))).getText();
            Assert.assertEquals(errorMessage, "Incorrect email. Please enter the registered email address.");

        }
    }


    @Test(priority = 2,dataProviderClass = ReadXLSData.class,dataProvider = "openg2pdata")
    public static void loginTest(String username, String password, String scenario) throws InterruptedException {
        //Act
        driver.findElement(By.id(locators.getProperty("username_field"))).sendKeys(username);
        driver.findElement(By.id(locators.getProperty("password_field"))).sendKeys(password);
        driver.findElement(By.xpath(locators.getProperty("login_button"))).click();
        Thread.sleep(2000);
        if(scenario.equals("TRUE")) {
            driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
            WebElement registry = driver.findElement(By.xpath(locators.getProperty("registry_name_field")));
            Assert.assertTrue(registry.isDisplayed(),"BaseLogin Failed");
        }
        else {
            driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
            String errorMessage = driver.findElement(By.xpath(locators.getProperty("login_error_message"))).getText();
            Assert.assertEquals(errorMessage,"Login failed due to Invalid credentials !","Credentials are Invalid");

        }

    }



}
