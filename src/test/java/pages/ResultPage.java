package pages;

import io.qameta.allure.Step;
import utils.Utils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ResultPage {
    private final WebDriver driver;
    private final By h1 = By.xpath("//*[@id='headerPage']/h1/span[@data-name]");
    private final By dropdownCar = By.xpath("//*[@id='autoCmplt']/label");
    private final By priceInputFrom = By.name("price.USD.gte");
    private final By priceInputTo = By.name("price.USD.lte");
    private final By noAdsMessage = By.id("emptyResultsNotFoundBlock");
    private final By newCarsMessage = By.xpath("//*[@id='searchResult']/div[@class='warning middle']/h2");


    public ResultPage(WebDriver driver) {
        this.driver = driver;
    }

    public String getTitle() {
        return driver.getTitle();
    }


    public String getH1Text() {
        Utils utils = new Utils(driver);
        utils.waitTimeout(h1);
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
    public String getTextFromNewCarsMessge(){ return driver.findElement(newCarsMessage).getText();}
}
