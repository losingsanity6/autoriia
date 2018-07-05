package pages;

import io.qameta.allure.Step;
import org.apache.log4j.Logger;
import utils.Utils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ExtendedSearchPage {
    private WebDriver driver;

    private final By checkboxSedan = By.xpath("//*[@id=\"bodies\"]/ul/li[1]/label");
    private final By checkboxEngland = By.xpath("//*[@id=\"originContainer\"]/div[2]/ul/li[1]/label");
    private final By showButton = By.xpath("//*[@id=\"results\"]/div/a[1]");

    private final Logger log = Logger.getLogger(ExtendedSearchPage.class);

    public ExtendedSearchPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step
    public void clickShowButton() {
        driver.findElement(showButton).click();
    }

    @Step
    public void clickCheckBoxes() {
        Utils utils = new Utils(driver);
        utils.ScrollWindow(checkboxSedan);
        driver.findElement(checkboxSedan).click();
        log.info("Click on Sedan checkbox was perfomed");
        driver.findElement(checkboxEngland).click();
        log.info("Click on England checkbox was perfomed");
    }
}