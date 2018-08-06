package utils;

import data_provider.ConfigFileReader;
import org.apache.log4j.Logger;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.util.concurrent.TimeUnit;


import static utils.DriverProvider.closeWebBrowser;
import static utils.DriverProvider.driver;
import static utils.DriverProvider.getDriver;

public class Annotations {
    private Logger log = Logger.getLogger(Annotations.class);




    @BeforeMethod
    public void setUp() {
        getDriver("chrome");
        driver.manage().window().maximize();
        ConfigFileReader configFileReader = new ConfigFileReader();
        driver.get(configFileReader.getApplicationUrl());
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);


    }

    //TODO:Method method, ITestResult testResult
    //TODO   if (testResult.getStatus() == ITestResult.FAILURE) {
    //TODO   takeScreenshot(testResult);
    @AfterMethod
    public void close() {
        closeWebBrowser();
       log.info("Test has finished");
   }

  /*  private void takeScreenshot(ITestResult testResult) {
        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String pathname = "target/screenshots/" + testResult.getInstanceName() + "-" + testResult.getName() + ".png";
        try {
            FileUtils.moveFile(screenshot, new File(pathname));
        } catch (IOException e) {
            log.error("Screenshot cannot be created", e);
        }
    }*/
}

