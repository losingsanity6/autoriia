package utils;

import data_provider.ConfigFileReader;
import io.qameta.allure.Attachment;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.util.concurrent.TimeUnit;


import static utils.DriverProvider.closeWebBrowser;
import static utils.DriverProvider.driver;
import static utils.DriverProvider.getDriver;

public class Annotations{
    private Logger log = Logger.getLogger(Annotations.class);




    @BeforeMethod
    public void setUp() {
        getDriver("chrome");
        driver.manage().window().maximize();
        ConfigFileReader configFileReader = new ConfigFileReader();
        driver.get(configFileReader.getApplicationUrl());
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);


    }


    @AfterMethod
    public void close(ITestResult result) {
        if(ITestResult.FAILURE == result.getStatus()){
            saveScreenshot(driver);
        }
        closeWebBrowser();

       log.info("Test has finished");
   }



        @Attachment(value = "Page screenshot", type = "image/png")
        private byte[] saveScreenshot(WebDriver driver) {
            return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
        }
        }



