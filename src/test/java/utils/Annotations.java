package utils;

import data_provider.ConfigFileReader;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import java.util.concurrent.TimeUnit;

public class Annotations {
    private Logger log = Logger.getLogger(Annotations.class);

    //private final static org.slf4j.Logger log = LoggerFactory.getLogger(FiltersTest.class);
    protected static WebDriver driver;

    @BeforeTest
    public void setUp() {

        ConfigFileReader configFileReader = new ConfigFileReader();
        System.setProperty("webdriver.chrome.driver", "browsers/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        //  String baseUrl = "https://auto.ria.com:443";
        //log.info("The test has started");
        log.info("The test started");
        driver.get(configFileReader.getApplicationUrl());
        driver.manage().timeouts().implicitlyWait(configFileReader.getImplicitlyWait(), TimeUnit.SECONDS);


    }

    @AfterTest
    public void close() {
        driver.close();
     log.info("Test has finished");
    }


}
