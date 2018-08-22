package utils;

import data_provider.ConfigFileReader;
import data_provider.DriverType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

public class DriverProvider {
    public static WebDriver driver;
    public static ConfigFileReader configFileReader = new ConfigFileReader();
    private static DriverType driverType = configFileReader.getBrowser();

    public DriverProvider() {
    }

    public static WebDriver getDriver() { {
            switch (driverType) {
                case FIREFOX :
                    System.setProperty("webdriver.gecko.driver", configFileReader.getGeckoDriverPath());
                    driver = new FirefoxDriver();
                    break;
                case CHROME :
                    System.setProperty("webdriver.chrome.driver",configFileReader.getChromePath()) ;
                    driver = new ChromeDriver();
                    break;

            }

            }

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(configFileReader.getImplicitlyWait(), TimeUnit.SECONDS);
        return driver;
    }

    public static void closeWebBrowser() {
        if (null != driver) {
            driver.quit();
        }
        driver = null;
    }

}
