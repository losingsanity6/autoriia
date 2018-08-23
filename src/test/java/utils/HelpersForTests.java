package utils;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

import static configs.DriverProvider.driver;

public class HelpersForTests {

    private final Logger log = Logger.getLogger(HelpersForTests.class);

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

    public void switchBetweenWindows(int window) {
        ArrayList<String> windowHandles = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(windowHandles.get(window));
    }

    public String getUrl() {
        return driver.getCurrentUrl();
    }

    public String getTitle() {
        return driver.getTitle();
    }
    public String getLinkText(By element){
        return  driver.findElement(element).getText();
    }

    public void methodToChooseCheckboxes(By element, String textToCompare) {
        List<WebElement> Checkbox = driver.findElements(element);
        log.info("Getting list of checkboxes" + element);
        for (int i = 0; i < Checkbox.size(); i++) {
            WebElement El = Checkbox.get(i);
            String textFromElement = El.getAttribute("innerHTML");
            System.out.println(textFromElement);
            if (textFromElement.equals(textToCompare)) {
                El.click();
                log.info("Click on checkbox was perfomed");
                break;
            }
        }

    }

}
