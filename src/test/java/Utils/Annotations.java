package Utils;

import Tests.FiltersTest;
import dataProvider.ConfigFileReader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import java.util.concurrent.TimeUnit;

public class Annotations {
   private final static org.slf4j.Logger log = LoggerFactory.getLogger(FiltersTest.class);
    protected static WebDriver driver;
    @BeforeTest
    public void setUp() {

        ConfigFileReader configFileReader = new ConfigFileReader();
        System.setProperty("webdriver.chrome.driver", "browsers/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        //  String baseUrl = "https://auto.ria.com:443";
        log.info("The test has started");
        driver.get(configFileReader.getApplicationUrl());
        driver.manage().timeouts().implicitlyWait(configFileReader.getImplicitlyWait(), TimeUnit.SECONDS);


    }

    @AfterTest
    public void close() {
        driver.close();
        log.info("Test finished");
    }


}
