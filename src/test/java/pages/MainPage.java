package pages;

import configs.DriverProvider;
import org.openqa.selenium.WebElement;
import utils.HelpersForTests;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class MainPage extends DriverProvider {
    private final org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(MainPage.class);
    private final By usedCarDropdown = By.id("brandTooltipBrandAutocomplete-brand");
    private final By usedCarInput = By.id("brandTooltipBrandAutocompleteInput-brand");
    private final By newCarDropdown = By.name("marka_id");
    private final By searchButton = By.xpath("//*[@id='mainSearchForm']/div[@class='footer-form']/button");
    private final By priceFieldFrom = By.id("priceFrom");
    private final By priceFieldTo = By.id("priceTo");
    private final By extendedSeachButton = By.xpath("//*[@id='mainSearchForm']/div[@class='footer-form']/a");
    private final By newCarsRadioButton = By.xpath("//*[@id='mainSearchForm']/div[@class='nav']/label[@for='naRadioType']");
    private final By autocompletedMarkField = By.xpath("//*[@id='brandTooltipBrandAutocomplete-brand']/ul/li/a");
    private final By model = By.id("brandTooltipBrandAutocomplete-model");
    private final By modelInput = By.id("brandTooltipBrandAutocompleteInput-model");
    private final By modelAutocompleted = By.xpath("//*[@id='brandTooltipBrandAutocomplete-model']/ul/li/a");
    private final By region = By.id("regionAutocompleteAutocomplete-1");
    private final By regionInput = By.id("regionAutocompleteAutocompleteInput-1");
    private final By regionAutocomplete = By.xpath("//*[@id='regionAutocompleteAutocomplete-1']/ul/li/a");
    private final By loginLocator = By.linkText("Вход в кабинет");
    private final By regionLocator = By.xpath("//*[@id='regionCenters']");
    private final By allForAutoDropdown = By.id("AllForAuto");
    private final By carTypeLinksLocator = By.cssSelector("a.item-tt");
    private final By usedAndNewCarsLinkContainerLocator = By.xpath("//*[@id='favorite-model-section']/nav[@class='catalog-brands boxed']");
    private final By otherLinkForCarTypeContainersLocator = By.xpath("//*[@id='favorite-model-section']/nav[@class='catalog-referrals open-mobile']");


    public ExtendedSearchPage clickExtendedSearchButton() {
        driver.findElement(extendedSeachButton)
                .click();
        return new ExtendedSearchPage(driver);
    }

    public NewCarsPage clickOnElementByLinkText(String linktext) {
        driver.findElement(By.linkText(linktext))
                .click();
        return new NewCarsPage(driver);
    }

    public MainPage chooseCarBrand(String carBrand) {
        HelpersForTests helpersForTests = new HelpersForTests();
        helpersForTests.waitTimeout(usedCarDropdown);
        driver.findElement(usedCarDropdown).click();
        log.info("Click on car brand dropdown");
        driver.findElement(usedCarInput).sendKeys(carBrand);
        log.info("input data to car brand dropdown");
        driver.findElement(autocompletedMarkField).click();
        log.info("car brand was chosen");
        return this;
    }


    public MainPage clickModel(String modelInp) {
        HelpersForTests helpersForTests = new HelpersForTests();
        driver.findElement(model)
                .click();
        driver.findElement(modelInput)
                .sendKeys(modelInp);
        helpersForTests.waitTimeout(modelAutocompleted);
        driver.findElement(modelAutocompleted)
                .click();
        log.info("Car model was chosen");
        return this;
    }


    public MainPage clickRegion(String regionName) {
        driver.findElement(region).click();
        driver.findElement(regionInput).sendKeys(regionName);
        driver.findElement(regionAutocomplete).click();
        log.info("Region was chosen");
        return this;
    }

    public MainPage selectRegion(String regionName) {
        Select region = new Select(driver.findElement(regionLocator));
        region.selectByVisibleText(regionName);
        return this;
    }


    public MainPage selectYearFrom(String yearFromInput, String yearToInput) {
        Select yearFrom = new Select(driver.findElement(By.id("yearFrom")));
        log.info("Year from dropdown was found");
        Select yearTo = new Select(driver.findElement(By.id("yearTo")));
        log.info("Year to dropdown was found");
        yearFrom.selectByVisibleText(yearFromInput);
        log.info("Year from was selected");
        yearTo.selectByVisibleText(yearToInput);
        log.info("Year to was selected");
        return this;

    }


    public ResultPage clickSearchButton() {
        HelpersForTests helpersForTests = new HelpersForTests();
        helpersForTests.waitTimeout(searchButton);
        driver.findElement(searchButton)
                .click();
        log.info("Click on search button was perfomed");
        return new ResultPage(driver);
    }


    public MainPage enterPriceToPriceField(String priceTo, String priceFrom) {
        driver.findElement(priceFieldFrom).sendKeys(priceTo);
        log.info("Data to price field was inputted");
        driver.findElement(priceFieldTo).sendKeys(priceFrom);
        log.info("Data to price field was inputted");
        return this;
    }


    public LoginPage clickLoginButton() {
        driver.findElement(loginLocator).click();
        log.info("Click on login button was perfomed");
        return new LoginPage();
    }

    public MainPage clickOnradioButton() {
        driver.findElement(newCarsRadioButton).click();
        return this;

    }

    public MainPage clickAllForAutoDropdown() {
        driver.findElement(allForAutoDropdown).click();
        return this;
    }

    //  TODO: move to helpers
    public int getListSize() {
        HelpersForTests helpersForTests = new HelpersForTests();
        helpersForTests.ScrollWindow(carTypeLinksLocator);
        return driver.findElements(carTypeLinksLocator).size();
    }

    public List<WebElement> getListOfElements() {
        return driver.findElements(carTypeLinksLocator);

    }

    public String getTextFromCarTypeLinks() {
        return driver.findElement(carTypeLinksLocator).getText();
    }

    public WebElement getContainerContent() {
        return driver.findElement(usedAndNewCarsLinkContainerLocator);
    }

    public WebElement getOtherContainer() {
        return driver.findElement(otherLinkForCarTypeContainersLocator);
    }

}