package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.BrowserType;

public class DriverProvider {
    public static WebDriver getDriver() {
        String browser = System.getProperty("browser");
        if (browser == null) {
            throw new Error("Missing Java Property, please rerun the application with command -Dbrowser=browser, where allowed values for browser:" + "allowed values are:+BrowserType.CHROME" + "BrowserType.Firefox");
        }
        switch (browser) {
            case BrowserType.CHROME:
                System.setProperty("webdriver.chrome.driver", "browsers/chromedriver.exe");
                return new ChromeDriver();
            case BrowserType.FIREFOX:
                System.setProperty("webdriver.gecko.driver", "browsers/geckodriver.exe");
                return new FirefoxDriver();
            default:
                throw new Error("Property browser is incorrect" + browser + "lease rerun the application" + BrowserType.CHROME + BrowserType.FIREFOX);
        }
    }
}