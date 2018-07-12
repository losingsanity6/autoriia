package utils;

import data_provider.ConfigFileReader;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.BrowserType;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

public class Annotations {
    private Logger log = Logger.getLogger(Annotations.class);

    //private final static org.slf4j.Logger log = LoggerFactory.getLogger(FiltersTest.class);
    protected static WebDriver driver;

    @BeforeMethod
    @Parameters({"pageWaitTimeout", "url"})
    public void setUp() {
        //ConfigFileReader configFileReader = new ConfigFileReader();
        System.setProperty("webdriver.chrome.driver", "src/main/resources/browsers/chromedriver.exe");
        driver = new ChromeDriver();
        // driver = DriverProvider.getDriver();
        driver.get("https://auto.ria.com");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);


    }

    @AfterMethod
    public void close(Method method, ITestResult testResult) {
        if (testResult.getStatus() == ITestResult.FAILURE) {
            takeScreenshot(testResult);
        }
        driver.close();
        log.info("Test has finished");
    }

    private void takeScreenshot(ITestResult testResult) {
        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String pathname = "target/screenshots/" + testResult.getInstanceName() + "-" + testResult.getName() + ".png";
        try {
            FileUtils.moveFile(screenshot, new File(pathname));
        } catch (IOException e) {
            log.error("Screenshot cannot be created", e);
        }
    }
}

