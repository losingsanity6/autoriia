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
    public void setUp(long pageWaitTimeout, String url) {
        ConfigFileReader configFileReader = new ConfigFileReader();
        System.setProperty("webdriver.chrome.driver", "browsers/chromedriver.exe");
        driver = new ChromeDriver();
       // driver = DriverProvider.getDriver();
        driver.get(url);
        driver.manage().timeouts().implicitlyWait(pageWaitTimeout, TimeUnit.SECONDS);


    }

    @AfterMethod
    public void close(Method method, ITestResult testResult) {
        if (testResult.getStatus()==ITestResult.FAILURE){
            File screenshot  = ( (TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            try {
                FileUtils.moveFile(screenshot, new File("screenshot/file.png"));
            } catch (IOException e) {
                log.error("Screenshot can not ber created",e );
            }
        }
        driver.close();
        log.info("Test has finished");
    }


}
