package pages;

import utils.Utils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class MainPageObject {
    private WebDriver driver;

    private By usedCarDropdown = By.id("brandTooltipBrandAutocomplete-brand");
    private By usedCarInput = By.id("brandTooltipBrandAutocompleteInput-brand");
    private By newCarDropdown = By.name("marka_id");
    private By searchButton = By.xpath("//*[@id=\"mainSearchForm\"]/div[3]/button");
    private By priceFieldFrom = By.id("priceFrom");
    private By priceFieldTo = By.id("priceTo");
    private By extendedSeachButton = By.xpath("//*[@id=\"mainSearchForm\"]/div[3]/a");
    private By newCarsRadioBtn = By.xpath("//*[@id=\"mainSearchForm\"]/div[1]/label[3]");
    private By autocompletedMarkField = By.xpath("//*[@id=\"brandTooltipBrandAutocomplete-brand\"]/ul/li/a");
    private By model = By.id("brandTooltipBrandAutocomplete-model");
    private By modelInput = By.id("brandTooltipBrandAutocompleteInput-model");
    private By modelAutocompleted = By.xpath("//*[@id=\"brandTooltipBrandAutocomplete-model\"]/ul/li/a");
    private By region = By.id("regionAutocompleteAutocomplete-1");
    private By regionInput = By.id("regionAutocompleteAutocompleteInput-1");
    private By regionAutocomplete = By.xpath("//*[@id=\"regionAutocompleteAutocomplete-1\"]/ul/li/a");
    private By loginLocator = By.linkText("Вход в кабинет");
    private By NewCarsLink = By.linkText("Новые авто");
    private By detailsForCar = By.linkText("Все для авто");
    ExtendedSearchPageObject ExtendedSearchPageObject;


    public MainPageObject(WebDriver driver) {
        this.driver = driver;
    }

    public ExtendedSearchPageObject clickExtendedSearchButton() {
        driver.findElement(extendedSeachButton).click();
        return new ExtendedSearchPageObject(driver);
    }

    public void chooseCarBrand(String carBrand) {
        driver.findElement(usedCarDropdown).click();
        driver.findElement(usedCarInput).sendKeys(carBrand);
        driver.findElement(autocompletedMarkField).click();
    }


    public void clickModel(String modelInp) {
        driver.findElement(model).click();
        driver.findElement(modelInput).sendKeys(modelInp);
        driver.findElement(modelAutocompleted).click();
    }


    public void clickRegion(String regionName) {
        driver.findElement(region).click();
        driver.findElement(regionInput).sendKeys(regionName);
        driver.findElement(regionAutocomplete).click();
    }


    public void selectYearFrom(String yearFromInput, String yearToInput) {
        Select yearFrom = new Select(driver.findElement(By.id("yearFrom")));
        Select yearTo = new Select(driver.findElement(By.id("yearTo")));
        yearFrom.selectByVisibleText(yearFromInput);
        yearTo.selectByVisibleText(yearToInput);
    }

    public void selectCarNew() {
        Utils utils = new Utils(driver);
        driver.findElement(newCarsRadioBtn).click();
        driver.findElement(newCarDropdown).click();
        utils.waitTimeout(newCarDropdown);
        Select selectMarkCar = new Select(driver.findElement(newCarDropdown));
        selectMarkCar.selectByIndex(6);

    }

    public void clickSearchButton() {
        driver.findElement(searchButton).click();
    }


    public void enterPriceToPriceField(String priceFrom, String priceTo) {

        driver.findElement(priceFieldFrom).sendKeys(priceFrom);
        driver.findElement(priceFieldTo).sendKeys(priceTo);
    }

    public void clickLoginButton() {
        driver.findElement(loginLocator).click();
    }

    public void clickNewCars() {
        driver.findElement(NewCarsLink).click();

    }
    public void allForCarsDropDown(){
        driver.findElement(detailsForCar).click();

    }
}
