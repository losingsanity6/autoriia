package pages;

import utils.Utils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ResultPageObject {
    private WebDriver driver;
    private By h1 = By.xpath("//*[@id=\"headerPage\"]/h1/span[5]");
    private By dropdownCar = By.xpath("//*[@id=\"autoCmplt\"]/label");
    private By priceInputFrom = By.name("price.USD.gte");
    private By priceInputTo = By.name("price.USD.lte");
    private By noAdsMessage = By.id("emptyResultsNotFoundBlock");


    public ResultPageObject(WebDriver driver) {
        this.driver = driver;
    }

    public String getTitle() {
        return driver.getTitle();
    }

    public String getH1Text() {
        return driver.findElement(h1).getAttribute("innerHTML");


    }

    public String findCarDropdown() {
        return driver.findElement(dropdownCar).getAttribute("data-text");

    }


    public String getTextFromPriceInputFrom() {
        Utils utils = new Utils(driver);
        utils.ScrollWindow(priceInputFrom);
        return driver.findElement(priceInputFrom).getAttribute("value");
    }

    public String getInputFromPriceFieldTo() {
        Utils utils = new Utils(driver);
        utils.ScrollWindow(priceInputTo);
        return driver.findElement(priceInputTo).getAttribute("value");
    }

    public String getTextFromNoResultsMessage() {
        return driver.findElement(noAdsMessage).getText();
    }
}