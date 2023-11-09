package testcase;

import base.BaseLogin;
import org.apache.hc.core5.reactor.Command;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.ReadXLSData;

import java.io.IOException;
import java.util.PriorityQueue;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.support.ui.Select;
import utilities.commons;

import static utilities.commons.click;


public class RegistryGroupPageTest extends BaseLogin {
    private static String groupId;
    int optionIndex = 1;


    @Test(priority = 1,dataProviderClass = ReadXLSData.class,dataProvider = "openg2pdata")
    public static void groupCreation(String groupName, String address, String phoneNumber, String email) throws IOException, InterruptedException {
        login();
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        commons.click(driver,By.cssSelector(locators.getProperty("group_create_button")));
        WebElement groupsName = driver.findElement(By.xpath(locators.getProperty("group_creation_name_field")));
        groupId = groupsName.getAttribute(groupName);
        groupsName.sendKeys(groupName);
//        driver.findElement(By.xpath(locators.getProperty("tags_dropdown"))).click();
//        driver.findElement(By.xpath(locators.getProperty("contact_info_section"))).click();
//        driver.findElement(By.xpath(locators.getProperty("group_address_fields"))).sendKeys(address);
//        driver.findElement(By.xpath(locators.getProperty("add_line_group_phone_number"))).click();
//        driver.findElement(By.xpath(locators.getProperty("add_line_group_phone_number"))).click();
//        driver.findElement(By.xpath(locators.getProperty("group_phone_number"))).sendKeys(phoneNumber);
//        driver.findElement(By.xpath(locators.getProperty("save_and_close_button"))).click();
        commons.enter(driver,By.xpath(locators.getProperty("group_email_field")),email);
        commons.click(driver,By.xpath(locators.getProperty("group_save_button")));
    }

    @Test(priority = 2)
    public static void addGroupToProgram() throws IOException, InterruptedException {
        login();
        commons.click(driver,By.xpath(locators.getProperty("group_check_box")));
        commons.click(driver,By.xpath(locators.getProperty("action_button")));
        commons.click(driver,By.xpath(locators.getProperty("add_to_program_group")));
        commons.dropdown(driver,By.xpath(locators.getProperty("group_program_list")));
        Thread.sleep(2000);
        commons.click(driver,By.xpath(locators.getProperty("program_selection")));
        commons.click(driver,By.name(locators.getProperty("assign_registrant_group")));

    }
}
