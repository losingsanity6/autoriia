package pages;

import io.qameta.allure.Step;
import utils.Utils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ResultPage {
    private final WebDriver driver;
    private final By h1 = By.xpath("//*[@id='headerPage']/h1/span[5]");
    private final By dropdownCar = By.xpath("//*[@id='autoCmplt']/label");
    private final By priceInputFrom = By.name("price.USD.gte");
    private final By priceInputTo = By.name("price.USD.lte");
    private final By noAdsMessage = By.id("emptyResultsNotFoundBlock");


    public ResultPage(WebDriver driver) {
        this.driver = driver;
    }

    public String getTitle() {
        return driver.getTitle();
    }

    @Step
    public String getH1Text() {
        Utils utils = new Utils(driver);
        utils.waitTimeout(h1);
        return driver.findElement(h1).getAttribute("innerHTML");


    }

    @Step
    public String findCarDropdown() {
        return driver.findElement(dropdownCar).getAttribute("data-text");

    }

    @Step
    public String getTextFromPriceInputFrom() {
        Utils utils = new Utils(driver);
        utils.ScrollWindow(priceInputFrom);
        return driver.findElement(priceInputFrom).getAttribute("value");
    }

    @Step
    public String getInputFromPriceFieldTo() {
        Utils utils = new Utils(driver);
        utils.ScrollWindow(priceInputTo);
        return driver.findElement(priceInputTo).getAttribute("value");
    }

    @Step
    public String getTextFromNoResultsMessage() {
        return driver.findElement(noAdsMessage).getText();
    }
}