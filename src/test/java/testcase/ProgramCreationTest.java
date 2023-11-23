package testcase;

import base.BaseLogin;
import org.openqa.selenium.By;
import org.testng.annotations.Test;
import utilities.ReadXLSData;
import utilities.commons;

import java.io.IOException;

public class ProgramCreationTest extends BaseLogin {


    @Test(dataProviderClass = ReadXLSData.class,dataProvider = "openg2pdata")
    public static void programCreation(String programName) throws IOException, InterruptedException {
        login();
        Thread.sleep(2000);
        commons.click(driver, By.xpath(locators.getProperty("menu_option")));
        commons.click(driver,By.xpath(locators.getProperty("programs")));
        commons.click(driver,By.xpath(locators.getProperty("create_program_button")));
        commons.enter(driver,By.xpath(locators.getProperty("program_name")),programName);
        commons.click(driver,By.xpath(locators.getProperty("target_type")));
//        commons.click(driver,By.xpath(locators.getProperty("add_filter")));
//        commons.click(driver,By.xpath(locators.getProperty("filter_dropdown")));
        commons.click(driver,By.linkText(locators.getProperty("configure_the_cycle_manager")));
        Thread.sleep(2000);
    }

}
