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
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

public class Annotations {
    private Logger log = Logger.getLogger(Annotations.class);

    //private final static org.slf4j.Logger log = LoggerFactory.getLogger(FiltersTest.class);
    protected static WebDriver driver;

    @BeforeMethod
    public void setUp() {
        ConfigFileReader configFileReader = new ConfigFileReader();
        driver = DriverProvider.getDriver();
        driver.get(configFileReader.getApplicationUrl());
        driver.manage().timeouts().implicitlyWait(configFileReader.getImplicitlyWait(), TimeUnit.SECONDS);


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
