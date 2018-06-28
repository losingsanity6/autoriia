
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import java.util.concurrent.TimeUnit;

public class MainPageObject {
    private WebDriver driver;

    private By usedCarDropdown = By.id("brandTooltipBrandAutocomplete-brand");
    private By usedCarInput = By.id("brandTooltipBrandAutocompleteInput-brand");
    private By newCarDropdown = By.id("marks");
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
    private By loginLocator = By.partialLinkText("Вход в кабинет");

    public MainPageObject(WebDriver driver) {
        this.driver = driver;
    }

    public void ExtendedSearchButtonClick() {
        driver.findElement(extendedSeachButton).click();
    }

    public void carBrandDropdownUsedInput(String carBrand) {
        driver.findElement(usedCarDropdown).click();
        driver.findElement(usedCarInput).sendKeys(carBrand);
    }

    public void clickCarInAutocompletedSearch() {
        Actions action = new Actions(driver);
        action.moveToElement(driver.findElement(autocompletedMarkField)).click().build().perform();
    }

    public void Model(String modelInp) {
        driver.findElement(model).click();
        driver.findElement(modelInput).sendKeys(modelInp);
    }

    public void clickModelInAutocompletedSearch() {
        Actions action = new Actions(driver);
        action.moveToElement(driver.findElement(modelAutocompleted)).click().build().perform();
    }

    public void clickRegion(String regionName) {
        driver.findElement(region).click();
        driver.findElement(regionInput).sendKeys(regionName);
    }

    public void clickAutocompletedRegion() {
        Actions action = new Actions(driver);
        action.moveToElement(driver.findElement(regionAutocomplete)).click().build().perform();
    }
public void selectYearFrom(String yearFromInput, String yearToInput){
        Select yearFrom = new Select(driver.findElement(By.id("yearFrom")));
        Select yearTo = new Select(driver.findElement(By.id("yearTo")));
        yearFrom.selectByVisibleText(yearFromInput);
        yearTo.selectByVisibleText(yearToInput);
}

    public void selectCarNew() {
        driver.findElement(newCarsRadioBtn).click();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        WebElement MarkCarDrpdwn = driver.findElement(By.name("marka_id"));
        Select selectMarkCar = new Select(MarkCarDrpdwn);
        selectMarkCar.selectByIndex(4);

    }

    public void clickSearchButton() {
        driver.findElement(searchButton).click();
    }


    public void priceFieldInput(String priceFrom, String priceTo) {

        driver.findElement(priceFieldFrom).sendKeys(priceFrom);
        driver.findElement(priceFieldTo).sendKeys(priceTo);
    }
    public void clickLogin(){
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        driver.findElement(loginLocator);
    }
}
