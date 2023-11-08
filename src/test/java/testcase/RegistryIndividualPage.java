package testcase;

import base.BaseLogin;
import org.apache.poi.ss.formula.functions.T;
import org.openqa.selenium.By;
import org.testng.annotations.Test;
import utilities.ReadXLSData;
import utilities.commons;

import java.io.IOException;

public class RegistryIndividualPage extends BaseLogin {


    @Test(dataProviderClass = ReadXLSData.class,dataProvider = "openg2pdata")
    public static void individualCreation(String familyName, String firstName, String additionalName, String address, String email) throws IOException, InterruptedException {
    login();
        Thread.sleep(2000);
        commons.click(driver, By.xpath(locators.getProperty("registry_individual")));
        commons.click(driver,By.xpath(locators.getProperty("individual_create_button")));
        commons.enter(driver,By.name(locators.getProperty("family_name_field")),familyName);
        commons.enter(driver,By.name(locators.getProperty("first_name_field")),firstName);
        commons.enter(driver,By.name(locators.getProperty("additional_name_field")),additionalName);
        commons.enter(driver,By.xpath(locators.getProperty("individual_address_field")),address);
        commons.enter(driver,By.xpath(locators.getProperty("individual_email_field")),email);
        commons.dropdown(driver,By.name(locators.getProperty("individual_notification_preference")));
//        commons.click(driver,By.xpath(locators.getProperty("selecting_notification_preference")));
        Thread.sleep(2000);

    }


}
