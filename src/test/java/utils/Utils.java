package utils;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;

import static utils.DriverProvider.driver;

public class Utils {

private final Logger log = Logger.getLogger(Utils.class);

    public void ScrollWindow(By element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView();", driver.findElement(element));
        log.info("Scroll to element");
    }

    public void waitTimeout(By element) {
        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(element)));
        log.info("Wait for element to be visible");
    }
    public void switchBetweenWindows(int window){
        ArrayList<String> windowHandles = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(windowHandles.get(window));
    }
    public String getUrl(){
        return  driver.getCurrentUrl();
    }
    public String getTitle(){
        return driver.getTitle();
    }

}
