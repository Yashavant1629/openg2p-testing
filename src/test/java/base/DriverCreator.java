package base;

import io.github.bonigarcia.wdm.WebDriverManager;
import listener.TestListeners;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.*;
import utilities.TestLogger;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Properties;

public class DriverCreator {
    public static WebDriver driver;
    public static Properties properties = new Properties();
    public static Properties locators = new Properties();
    public static FileReader fileReader1;
    public static FileReader fileReader2;

  @BeforeMethod
    public void setUp() throws IOException {

        if (driver == null) {
            fileReader1 = new FileReader(System.getProperty("user.dir")+"\\testconfig\\configfile\\config.properties");
            fileReader2 = new FileReader(System.getProperty("user.dir")+"\\src\\main\\resources\\configfiles\\locators.properties");
            properties.load(fileReader1);
            locators.load(fileReader2);
        }
        if (properties.getProperty("browser").equalsIgnoreCase("chrome")) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
            driver.get(properties.getProperty("testurl"));
            driver.manage().window().maximize();
        }
        else if (properties.getProperty("browser").equalsIgnoreCase("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
            driver.get(properties.getProperty("testurl"));
            driver.manage().window().maximize();
        }
        else if (properties.getProperty("browser").equalsIgnoreCase("edge")) {
            WebDriverManager.edgedriver().setup();
            driver = new EdgeDriver();
            driver.get(properties.getProperty("testurl"));
            driver.manage().window().maximize();
        }
    }

    @BeforeMethod
    public void beforeMethod(Method m){
        TestLogger.info("STARTING TEST: " + m.getName());
        TestLogger.info("THREAD ID: " + Thread.currentThread().getId());
    }


    @AfterMethod
    public void tearDown() {

        driver.close();
    }
}
