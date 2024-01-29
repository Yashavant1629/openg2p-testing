package testcase;

import base.BaseLogin;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.ReadXLSData;
import utilities.commons;

import java.io.IOException;

public class EligibilityManagerTest extends BaseLogin {


    @Test(dataProviderClass = ReadXLSData.class,dataProvider = "openg2pdata")
    public static void eligibilityManagerCreation(String eligibilityManagerName, String programName,String idtype) throws IOException, InterruptedException {
        login();
        commons.click(driver, By.xpath(locators.getProperty("menu_option")));
        commons.click(driver, By.xpath(locators.getProperty("programs")));
        commons.click(driver,By.xpath(locators.getProperty("programs_configurations")));
        commons.click(driver,By.xpath(locators.getProperty("default_eligibility_manager")));
        commons.click(driver,By.xpath(locators.getProperty("default_eligibility_manager_create_button")));
        commons.enter(driver,By.xpath(locators.getProperty("eligibility_manager_name")),eligibilityManagerName);
        commons.click(driver,By.xpath(locators.getProperty("programs_dropdown")));
        By dropdownLocator = By.xpath("//ul[@class='ui-menu ui-widget ui-widget-content ui-autocomplete dropdown-menu ui-front']//li");
        commons.dropDownByValue(driver, dropdownLocator, programName);
        commons.click(driver,By.xpath(locators.getProperty("add_filter")));
        commons.click(driver,By.xpath(locators.getProperty("eligibility_domain")));
        By dropdownValues = By.xpath("//ul[@class='o_field_selector_page']//li");
        commons.dropDownByValue(driver,dropdownValues,"Registrant IDs");
        Thread.sleep(2000);
        By registrantIDValues =  By.xpath("//ul[@class='o_field_selector_page']//li");
        commons.dropDownByValue(driver,registrantIDValues,"ID Type");
        Thread.sleep(2000);
        By idTypeValues = By.xpath("//ul[@class='o_field_selector_page']//li");
        commons.dropDownByValue(driver,idTypeValues,"Name");
//        By selectiveOptions = By.xpath("//select[@class='o_domain_leaf_operator_select o_input']//option");
//        commons.dropDownByValue(driver,selectiveOptions,"");
        commons.enter(driver,By.xpath(locators.getProperty("id_name_field")),idtype);
        commons.click(driver,By.xpath(locators.getProperty("eligibility_manager_save_button")));
        commons.click(driver,By.xpath(locators.getProperty("breadcrumb_button")));
        String tableXPath = "//table[@class='o_list_table table table-sm table-hover table-striped o_list_table_ungrouped']";
        Thread.sleep(2000);
        boolean entryFound = commons.isEntryPresentInPaginatedTable(driver, tableXPath, eligibilityManagerName);
        Assert.assertTrue(entryFound, "Expected entry with text '" + eligibilityManagerName + "' not found");


    }


    @Test(dataProviderClass = ReadXLSData.class,dataProvider = "openg2pdata")
    public static void eligibilityManagerMapping(String programName, String eligibilityManagerName) throws IOException, InterruptedException {
        login();
        commons.click(driver, By.xpath(locators.getProperty("menu_option")));
        commons.click(driver, By.xpath(locators.getProperty("programs")));
        String tableXPath = "//table[@class='o_list_table table table-sm table-hover table-striped o_list_table_ungrouped']";
        Thread.sleep(2000);
        WebElement entryFound = commons.getEntryElement(driver, tableXPath, programName);
        assert entryFound != null;
        entryFound.click();
        commons.click(driver,By.xpath(locators.getProperty("program_configuration")));
        commons.click(driver,By.xpath(locators.getProperty("remove_default_eligibility_manager")));
        Thread.sleep(1000);
        commons.click(driver,By.xpath(locators.getProperty("add_a_line_eligibility")));
        commons.click(driver,By.xpath(locators.getProperty("create_window_button")));
        commons.click(driver,By.xpath(locators.getProperty("manager_dropdown")));
        commons.click(driver,By.xpath(locators.getProperty("selecting_id_eligibility_manager")));
        commons.click(driver,By.xpath(locators.getProperty("manager_name_dropdown")));
        String managerNames = "//ul[@class='ui-menu ui-widget ui-widget-content ui-autocomplete dropdown-menu ui-front']//li";
        Thread.sleep(2000);
        commons.dropDownByValue(driver, By.id(managerNames),eligibilityManagerName);
        commons.click(driver,By.linkText(locators.getProperty("window_save_and_close_button")));

    }
}
