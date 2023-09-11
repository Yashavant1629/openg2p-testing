package testcase;

import base.DriverCreator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.ReadXLSData;

import java.util.concurrent.TimeUnit;

public class LoginPageTest extends DriverCreator {

    @Test(priority = 1,dataProviderClass = ReadXLSData.class,dataProvider = "openg2pdata")
    public static void resetPassword(String email) {
        //Act
        driver.findElement(By.linkText(locators.getProperty("reset_link"))).click();
        driver.findElement(By.xpath(locators.getProperty("reset_email_field"))).sendKeys(email);
        driver.findElement(By.xpath(locators.getProperty("confirm_email_button"))).click();
        WebElement paragraph1;
        paragraph1 = driver.findElement(By.xpath(locators.getProperty("confirmation_message")));
        String paragraphText1 = paragraph1.getText();
        //Assert
        Assert.assertEquals("An email has been sent with credentials to reset your password", paragraphText1);


    }


    @Test(priority = 2,dataProviderClass = ReadXLSData.class,dataProvider = "openg2pdata")
    public static void loginTest(String username, String password) {
        //Act
        driver.findElement(By.id(locators.getProperty("username_field"))).sendKeys(username);
        driver.findElement(By.id(locators.getProperty("password_field"))).sendKeys(password);
        driver.findElement(By.xpath(locators.getProperty("login_button"))).click();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        WebElement registry;
        registry = driver.findElement(By.xpath(locators.getProperty("text_registry")));
        String registryText = registry.getText();
        System.out.println(registryText);
        //Assert
        Assert.assertEquals("Groups",registryText);
    }



}
