package pages;

import org.openqa.selenium.WebElement;
import utils.DriverProvider;
import utils.Utils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.List;

public class ResultPage extends DriverProvider {
    private final WebDriver driver;
    private final By h1ForExtendedSearch = By.xpath("//*[@id='headerPage']/h1/span[@data-name]");
    private final By dropdownCar = By.xpath("//*[@id='autoCmplt']/label");
    private final By priceInputFrom = By.name("price.USD.gte");
    private final By priceInputTo = By.name("price.USD.lte");
    private final By noAdsMessage = By.id("emptyResultsNotFoundBlock");
    private final By newCarsMessage = By.xpath("//*[@id='searchResult']/div[@class='warning middle']/h2");
    private final By carCard = By.className("content");
    private final By searchresults = By.xpath("//*[@id='searchResults']/section[@class='ticket-item new__ticket t paid']/div[@class='content-bar']/div[@class='content']/div/div/a/span");
    private final By header = By.xpath("//*[@id='headerPage']/h1");

    public ResultPage(WebDriver driver) {
        this.driver = driver;
    }

    public String getTitle() {
        return driver.getTitle();
    }


    public String getH1Text() {
        Utils utils = new Utils();
        utils.waitTimeout(h1ForExtendedSearch);
        return driver.findElement(h1ForExtendedSearch).getAttribute("innerHTML");


    }


    public String findCarDropdown() {
        return driver.findElement(dropdownCar).getAttribute("data-text");

    }


    public String getTextFromPriceInputFrom() {
        Utils utils = new Utils();
        utils.ScrollWindow(priceInputFrom);
        return driver.findElement(priceInputFrom).getAttribute("value");
    }


    public String getInputFromPriceFieldTo() {
        Utils utils = new Utils();
        utils.ScrollWindow(priceInputTo);
        return driver.findElement(priceInputTo).getAttribute("value");
    }


    public String getTextFromNoResultsMessage() {
        return driver.findElement(noAdsMessage).getText();
    }

    public String getTextFromNewCarsMessge() {
        return driver.findElement(newCarsMessage).getText();
    }

    public WebElement findCarCard() {
        return driver.findElement(carCard);
    }

    public String methodToObtainListOfElements() {
        List<WebElement> Checkbox = driver.findElements(searchresults);
        for (int i = 0; i < Checkbox.size(); i++) {
            WebElement El = Checkbox.get(i);
            String string = El.getAttribute("innerHTML");
            return string;
        }
        return null;
    }

    public String textFromHeader() {
        return driver.findElement(header).getAttribute("innerHTML");
    }
}

